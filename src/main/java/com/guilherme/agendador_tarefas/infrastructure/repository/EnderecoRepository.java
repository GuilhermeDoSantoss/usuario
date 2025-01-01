package com.guilherme.agendador_tarefas.infrastructure.repository;


import com.guilherme.agendador_tarefas.infrastructure.enitity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
