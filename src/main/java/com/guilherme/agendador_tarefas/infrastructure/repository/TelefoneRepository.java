package com.guilherme.agendador_tarefas.infrastructure.repository;

import com.santos.firsy_spring_app.infrastructure.enitity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
