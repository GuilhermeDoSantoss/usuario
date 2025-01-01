package com.guilherme.agendador_tarefas.business;

import com.guilherme.agendador_tarefas.business.DTO.UsuarioDTO;
import com.guilherme.agendador_tarefas.business.converter.UsuarioConverter;
import com.guilherme.agendador_tarefas.infrastructure.enitity.Usuario;
import com.guilherme.agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.guilherme.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.guilherme.agendador_tarefas.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService<UsuarioDTO> {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario((com.guilherme.agendador_tarefas.business.DTO.UsuarioDTO) usuarioDTO);
        return (UsuarioDTO) usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch(ConflictException e){
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente (String email){

        return usuarioRepository.existsByEmail(email);
    }


    public Usuario buscarUsuarioPorEmail (String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }
    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);}
}
