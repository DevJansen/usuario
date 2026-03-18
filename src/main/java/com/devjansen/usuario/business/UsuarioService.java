package com.devjansen.usuario.business;

import com.devjansen.usuario.business.converter.UsuarioConverter;
import com.devjansen.usuario.business.dtos.UsuarioDTO;
import com.devjansen.usuario.infrastructure.entity.Usuario;
import com.devjansen.usuario.infrastructure.exceptions.ConflictExceptions;
import com.devjansen.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.devjansen.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    //metodo para salva usuario
    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExistente(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario =  usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    //metodo para fazer a verificação se o email existe, retorna um boolean
    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    //metodo se o email existe retorna uma Exceptions
    public void emailExistente(String email) {
        if (verificaEmailExistente(email)) {
            throw new ConflictExceptions("Email ja cadastrado " + email);
        }
    }

    //busca usuario por email
    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado! " + email));
    }

    //deleta usuario por email
    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }


}
