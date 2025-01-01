package com.guilherme.agendador_tarefas.infrastructure.repository;


import com.guilherme.agendador_tarefas.infrastructure.enitity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
