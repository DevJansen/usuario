package com.devjansen.usuario.business;

import com.devjansen.usuario.business.converter.UsuarioConverter;
import com.devjansen.usuario.business.dtos.EnderecoDTO;
import com.devjansen.usuario.business.dtos.TelefoneDTO;
import com.devjansen.usuario.business.dtos.UsuarioDTO;
import com.devjansen.usuario.infrastructure.entity.Endereco;
import com.devjansen.usuario.infrastructure.entity.Telefone;
import com.devjansen.usuario.infrastructure.entity.Usuario;
import com.devjansen.usuario.infrastructure.exceptions.ConflictExceptions;
import com.devjansen.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.devjansen.usuario.infrastructure.repository.EnderecoRepository;
import com.devjansen.usuario.infrastructure.repository.TelefoneRepository;
import com.devjansen.usuario.infrastructure.repository.UsuarioRepository;
import com.devjansen.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    //metodo para salva usuario
    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExistente(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
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
    public UsuarioDTO buscaUsuarioPorEmail(String email) {
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado! " + email)));
    }

    //deleta usuario por email
    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    //atualiza dados de usuario
    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO usuarioDTO) {

        //extrair o email do token
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        //criptografia de senha
        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        //busca o usuario atravez do email
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado! " + email));

        //Mescla os dados que recebemos na requisição DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(usuarioDTO, usuarioEntity);

        //salva o usuario e retorna um usuario dto
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }

    //metodo para atuatualiza endereco
    public EnderecoDTO atualizaDadosEndereco(Long idEndereco, EnderecoDTO enderecoDTO) {

        Endereco enderecoEntity = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotFoundException("ID não encontrado! " + idEndereco));
        Endereco endereco = usuarioConverter.atualizaEndereco(enderecoDTO, enderecoEntity);
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

















    public TelefoneDTO atualizaDadostelefone(Long idTelefone, TelefoneDTO telefoneDTO) {

        Telefone telefoneEntity = telefoneRepository.findById(idTelefone).orElseThrow( () ->
                new ResourceNotFoundException("ID não encontrado! " + idTelefone));

        Telefone telefone = usuarioConverter.atualizaTelefone(telefoneDTO, telefoneEntity);
        return  usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

}


//teste de java