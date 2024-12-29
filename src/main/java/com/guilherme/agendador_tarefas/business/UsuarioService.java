package com.guilherme.agendador_tarefas.business;

import com.guilherme.agendador_tarefas.business.DTO.UsuarioDTO;
import com.guilherme.agendador_tarefas.business.converter.UsuarioConverter;
import com.guilherme.agendador_tarefas.infrastructure.enitity.Usuario;
import com.guilherme.agendador_tarefas.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }
}
