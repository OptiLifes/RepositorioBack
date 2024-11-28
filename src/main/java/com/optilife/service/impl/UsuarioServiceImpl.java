package com.optilife.service.impl;

import com.optilife.model.dto.*;
import com.optilife.model.entity.Meta;
import com.optilife.model.entity.Usuario;
import com.optilife.model.enums.ERole;
import com.optilife.repository.MetaRepository;
import com.optilife.repository.RoleRepository;
import com.optilife.repository.UsuarioRepository;
import com.optilife.security.TokenProvider;
import com.optilife.security.UserPrincipal;
import com.optilife.service.UsuarioService;
import com.optilife.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final MetaRepository metaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;


    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final String rutaFotosPerfil = "uploads/fotos_perfil/"; // Ruta de las fotos

    @Override
    public AuthResponseDTO login(UsuarioLoginDTO usuarioLoginDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO
                );
    }   UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Usuario usuario = userPrincipal.getUsuario();

        //Verificar si es un administrador
    boolean isAdmin = usuario.getRole().getName().equals(ERole.ADMIN);
    String token=tokenProvider.createAccessToken(authentication);
    AuthResponseDTO responseDTO = userMapper.toAuthResponseDTO(usuario, token);

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
        Usuario usuario = usuarioRepository.findByEmail(usuarioLoginDTO.getEmail())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

            return passwordEncoder.matches(usuarioLoginDTO.getContraseña(), usuario.getContraseña());
    }

    @Override
    public void actualizarFotoPerfil(String email, MultipartFile foto) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (foto.isEmpty()) {
            throw new Exception("El archivo de imagen está vacío");
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
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        String token = tokenProvider.generarToken(usuario);
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
