package com.guilherme.agendador_tarefas.controller;

import com.guilherme.agendador_tarefas.UsuarioService;
import com.guilherme.agendador_tarefas.business.DTO.EnderecoDTO;
import com.guilherme.agendador_tarefas.business.DTO.TelefoneDTO;
import com.guilherme.agendador_tarefas.business.DTO.UsuarioDTO;
import com.guilherme.agendador_tarefas.business.ViaCepService;
import com.guilherme.agendador_tarefas.infrastructure.clients.ViaCepDTO;
import com.guilherme.agendador_tarefas.infrastructure.security.JwtUtil;
import com.guilherme.agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ViaCepService viaCepService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok((UsuarioDTO) usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                usuarioDTO.getSenha())
        );
        return "Bearer" + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone (@RequestBody TelefoneDTO dto,
                                                         @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader("Authorization")String token){
    return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastraTelefone (@RequestBody TelefoneDTO dto,
                                                         @RequestParam("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")

    public ResponseEntity<ViaCepDTO> buscarDadosCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(viaCepService.buscarDadosEndereco(cep));
    }
 }
