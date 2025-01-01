package com.guilherme.agendador_tarefas.infrastructure.repository;


import com.guilherme.agendador_tarefas.infrastructure.enitity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

   Optional<Usuario> findByEmail(String email);

   @Transactional
    void deleteByEmail(String email);
}
