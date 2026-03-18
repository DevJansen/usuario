package com.devjansen.usuario.business.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;

    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;

}
