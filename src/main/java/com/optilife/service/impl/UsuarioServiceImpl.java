package com.optilife.service.impl;

import com.optilife.model.dto.*;
import com.optilife.model.entity.Meta;
import com.optilife.model.entity.Usuario;
import com.optilife.model.enums.Role;
import com.optilife.repository.MetaRepository;
import com.optilife.repository.UsuarioRepository;
import com.optilife.security.TokenProvider;
import com.optilife.service.UsuarioService;
import com.optilife.service.EmailService;
import com.optilife.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    private final TokenProvider tokenProvider;
    private final String rutaFotosPerfil = "uploads/fotos_perfil/"; // Ruta de las fotos

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public UsuarioPerfilDTO obtenerPerfilPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return new UsuarioPerfilDTO(usuario.getNombre(), usuario.getEmail());
    }

    @Override
    public void actualizarPerfilUsuario(String email, UsuarioActualizacionDTO usuarioActualizacionDTO) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (usuarioActualizacionDTO.getNombre() != null) {
            usuario.setNombre(usuarioActualizacionDTO.getNombre());
        }
        if (usuarioActualizacionDTO.getEmail() != null) {
            usuario.setEmail(usuarioActualizacionDTO.getEmail());
        }

        if (usuarioActualizacionDTO.getMetasSalud() != null) {
            Meta metaSalud = new Meta();
            metaSalud.setPerfil(usuario.getPerfil());
            metaSalud.setTipoMeta("Salud");
            metaSalud.setDescripcionMeta(usuarioActualizacionDTO.getMetasSalud());
            metaRepository.save(metaSalud);
        }

        usuarioRepository.save(usuario);
    }

    @Override
    public void registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
        if (usuarioRepository.existsByEmail(usuarioRegistroDTO.getEmail())) {
            throw new Exception("El correo ya está registrado.");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioRegistroDTO.getNombre());
        nuevoUsuario.setEmail(usuarioRegistroDTO.getEmail());
        nuevoUsuario.setContraseña(passwordEncoder.encode(usuarioRegistroDTO.getContraseña()));
        nuevoUsuario.setTipoUsuario(usuarioRegistroDTO.getTipoUsuario());

        usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public boolean validarCredenciales(UsuarioLoginDTO usuarioLoginDTO) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(usuarioLoginDTO.getEmail());

        if (usuario != null) {
            return passwordEncoder.matches(usuarioLoginDTO.getContraseña(), usuario.getContraseña());
        }

        return false;
    }

    @Override
    public void actualizarFotoPerfil(String email, MultipartFile foto) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (foto.isEmpty()) {
            throw new Exception("El archivo de imagen está vacío");
        }

        String tipoArchivo = foto.getContentType();
        if (!tipoArchivo.equals("image/jpeg") && !tipoArchivo.equals("image/png")) {
            throw new Exception("Formato de imagen no soportado. Solo se permiten JPG y PNG.");
        }

        String nombreArchivo = email + "_" + System.currentTimeMillis() + "_" + foto.getOriginalFilename();
        Path rutaArchivo = Paths.get(rutaFotosPerfil + nombreArchivo);

        File directorio = new File(rutaFotosPerfil);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try {
            Files.write(rutaArchivo, foto.getBytes());
        } catch (IOException e) {
            throw new Exception("Error al guardar la imagen en el servidor.");
        }

        usuario.getPerfil().setFotoPerfil(nombreArchivo);
        usuarioRepository.save(usuario);
    }

    @Override
    public void generarTokenRecuperacion(String email) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        String token = tokenService.generarToken(usuario);
        emailService.enviarEmailRecuperacion(usuario.getEmail(), token);
    }

    @Override
    public void restablecerContraseña(String token, String nuevaContraseña) throws Exception {
        Optional<Usuario> usuarioOptional = tokenService.validarToken(token);

        if (!usuarioOptional.isPresent()) {
            throw new Exception("Token inválido o expirado");
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setContraseña(passwordEncoder.encode(nuevaContraseña));
        usuarioRepository.save(usuario);
    }

    @Override
    public AuthResponseDTO iniciarSesion(UsuarioLoginDTO usuarioLoginDTO) throws Exception {
        if (!validarCredenciales(usuarioLoginDTO)) {
            throw new Exception("Credenciales inválidas");
        }

        Usuario usuario = usuarioRepository.findByEmail(usuarioLoginDTO.getEmail());
        String token = tokenProvider.generarToken(usuario);

        // Guardar el token en la base de datos
        tokenService.saveTokenForUser(usuario, token);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setIdUsuario(usuario.getIdUsuario().longValue());
        response.setEmail(usuario.getEmail());
        response.setRole(Role.valueOf(usuario.getTipoUsuario()));

        return response;
    }
}
