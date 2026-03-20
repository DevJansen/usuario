package com.devjansen.usuario.business.converter;

import com.devjansen.usuario.business.dtos.EnderecoDTO;
import com.devjansen.usuario.business.dtos.TelefoneDTO;
import com.devjansen.usuario.business.dtos.UsuarioDTO;
import com.devjansen.usuario.infrastructure.entity.Endereco;
import com.devjansen.usuario.infrastructure.entity.Telefone;
import com.devjansen.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class UsuarioConverter {

    // Metodos para converter UsuarioDto para Usuario

    //metodo para recebe os dados do UsuarioDTO e retorna um Usuario
    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {

        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha())
                .email(usuarioDTO.getEmail())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();

    }

    //recebe a lista de EnderecoDTO e retorna uma lista de Endereco
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTO) {

        return enderecoDTO.stream().map(this::paraEndereco).toList();

    }

    //recebe um EnderecoDTO e retorna um Endereco
    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    //recebe a lista de TelefoneDTO e retorna uma lista de Telefone
    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTO) {
            return telefoneDTO.stream().map(this::paraTelefone).toList();

    }

    //recebe um TelefoneDTO e retorna um Telefone
    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();

    }


    // Metodos para converter Usuario para UsuarioDTO

    //metodo para recebe os dados do Usuario e retorna um UsuarioDTO
    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {

        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .senha(usuario.getSenha())
                .email(usuario.getEmail())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    //recebe a lista de Endereco e retorna uma lista de EnderecoDTO
    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> endereco) {
        return endereco.stream().map(this::paraEnderecoDTO).toList();
    }

    //recebe um Endereco e retorna um EnderecoDTO
    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    //recebe a lista de Telefone e retorna uma lista de TelefoneDTO
    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefone) {
        return telefone.stream().map(this::paraTelefoneDTO).toList();

    }

    //recebe um Telefone e retorna um TelefoneDTO
    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();

    }

    //metodo para atualiza o usuario
    public Usuario updateUsuario(UsuarioDTO usuarioDTO,  Usuario usuario) {
        return Usuario.builder()
                .id(usuario.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuario.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuario.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuario.getSenha())
                .telefones(usuario.getTelefones())
                .enderecos(usuario.getEnderecos())
                .build();
    }

    //metodo para atualiza o endereço
    public Endereco atualizaEndereco(EnderecoDTO enderecoDTO, Endereco endereco){
        return Endereco.builder()
                .id(endereco.getId())
                .logradouro(enderecoDTO.getLogradouro() != null ? enderecoDTO.getLogradouro() : endereco.getLogradouro())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : endereco.getNumero())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : endereco.getComplemento())
                .bairro(enderecoDTO.getBairro() != null ? enderecoDTO.getBairro() : endereco.getBairro())
                .cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : endereco.getCidade())
                .cep(enderecoDTO.getCep() != null ? enderecoDTO.getCep() : endereco.getCep())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : endereco.getEstado())
                .build();
    }

    //metodo para atualiza o telefone
    public Telefone atualizaTelefone(TelefoneDTO telefoneDTO, Telefone telefone) {
        return Telefone.builder()
                .id(telefone.getId())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefone.getDdd())
                .numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefone.getNumero())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO enderecoDTO, Long idUsuario) {
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .usuarioId(idUsuario)
                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO telefoneDTO, Long idUsuario) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .usuarioId(idUsuario)
                .build();
    }


}
