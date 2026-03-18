package com.devjansen.usuario.business;

import com.devjansen.usuario.business.converter.UsuarioConverter;
import com.devjansen.usuario.business.dtos.UsuarioDTO;
import com.devjansen.usuario.infrastructure.entity.Usuario;
import com.devjansen.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario =  usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

}
