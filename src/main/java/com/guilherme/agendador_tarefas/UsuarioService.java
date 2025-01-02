package com.guilherme.agendador_tarefas;

import com.guilherme.agendador_tarefas.business.DTO.EnderecoDTO;
import com.guilherme.agendador_tarefas.business.DTO.TelefoneDTO;
import com.guilherme.agendador_tarefas.business.DTO.UsuarioDTO;
import com.guilherme.agendador_tarefas.business.converter.UsuarioConverter;
import com.guilherme.agendador_tarefas.infrastructure.enitity.Endereco;
import com.guilherme.agendador_tarefas.infrastructure.enitity.Telefone;
import com.guilherme.agendador_tarefas.infrastructure.enitity.Usuario;
import com.guilherme.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.guilherme.agendador_tarefas.infrastructure.repository.EnderecoRepository;
import com.guilherme.agendador_tarefas.infrastructure.repository.TelefoneRepository;
import com.guilherme.agendador_tarefas.infrastructure.repository.UsuarioRepository;
import com.guilherme.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }

    private Usuario emailExiste(String email) {

        public Usuario buscarUsuarioPorEmail(String email) {
            return usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email não encontrado " + email));
        }
    }

    public boolean verificaEmailExistente(String email) {

        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email não encontrado" + email)
                    )
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado " + email);
        }
    }

    public void deletaUsuarioPorEmail(String email) {

        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String String, UsuarioDTO dto) {
                 //Aqui buscamos o email do usuário através do token (tirando obrigatoriedade do email)

        String email= jwtUtil.extrairEmailToken(token.substring());

                //Criptografia de senha
                dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

                //Busca os dados do usuário no banco de dados
                Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                        new ResourceNotFoundException("Email não localizado"));

                // Mesclou os dados que recebemos na requisição DTO com os dados do banco de dados
                Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

                //Salvou os dados do usuário convertido e depois pegou o retorno e converteu para UsuarioDTO
                return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

                //Buscou o email do usuário através do token (tirar a obrigatoriedade do email)
                String email = jwtUtil.extrairEmailToken(token.substring());

    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO){

            Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(()
                    -> new ResourceNotFoundException("Id não encontrado" + idEndereco));

            Endereco endereco = usuarioConverter.updadeEndereco(enderecoDTO, entity);

            return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));

    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO dto){

            Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(()
                    -> new ResourceNotFoundException("Id não encontrado " + idTelefone));

            Telefone telefone = usuarioConverter.updateTelefone(dto,entity);

            return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }


    }

}
