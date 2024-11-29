package com.optilife.mapper;

import com.optilife.model.dto.UsuarioPerfilDTO;
import com.optilife.model.dto.UsuarioRegistroDTO;
import com.optilife.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;

@Mapper
public interface UsuarioMapper {
    private final ModelMapper modelMapper;

    public Usuario toUserEntity(UsuarioRegistroDTO registroDTO){
        return modelMapper.map(registroDTO, Usuario.class);
    }

    public UsuarioPerfilDTO toUsuarioPerfilDTO(Usuario usuario){
        UsuarioPerfilDTO usuarioPerfilDTO = modelMapper.map(usuario, UsuarioPerfilDTO.class);
        if(usuario.get)
    }
}
