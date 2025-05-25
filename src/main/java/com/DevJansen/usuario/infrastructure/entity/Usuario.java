package com.DevJansen.usuario.infrastructure.entity;


import com.javanauta.iniciando_com_spring.infrastructure.security.UserDetailsServiceImpl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter //lombok
@Setter //lombok
@AllArgsConstructor //lombok
@NoArgsConstructor //lombok

@Entity//indica que que é uma tabela
@Table(name = "usuario") //nome da tabela

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//criar um id automatico
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    //referencia a outra tabela
    @OneToMany(cascade = CascadeType.ALL) //um usuario para varias tabela
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") //referecia e identificador
    private List<Endereco> enderecos; // apontamento nova tabela

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
