package com.jansen.usuario.infrastructure.repository;


import com.jansen.usuario.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
