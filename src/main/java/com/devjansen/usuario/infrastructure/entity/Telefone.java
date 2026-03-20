package com.devjansen.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ddd", length = 3, nullable = false)
    private String ddd;

    @Column(name = "numero", length = 15, nullable = false)
    private String numero;

    @Column(name = "usuario_id")
    private Long usuarioId;

}
