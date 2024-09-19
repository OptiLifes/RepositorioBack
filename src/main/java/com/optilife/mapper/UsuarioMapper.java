package com.optilife.mapper;

import com.optilife.model.dto.UsuarioPerfilDTO;
import com.optilife.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // Método para convertir de entidad Usuario a DTO UsuarioPerfilDTO
    UsuarioPerfilDTO toUsuarioPerfilDTO(Usuario usuario);

    // Método para convertir de DTO UsuarioPerfilDTO a entidad Usuario (en caso de necesidad)
    Usuario toUsuario(UsuarioPerfilDTO usuarioPerfilDTO);
}
