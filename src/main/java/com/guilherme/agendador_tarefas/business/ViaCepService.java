package com.guilherme.agendador_tarefas.business;

import com.guilherme.agendador_tarefas.infrastructure.clients.ViaCepClient;
import com.guilherme.agendador_tarefas.infrastructure.clients.ViaCepDTO;
import com.guilherme.agendador_tarefas.infrastructure.exceptions.IlegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){

    }

    private String processarCep(String cep){

        String cepFormatado = cep.replace(" ", "").
                replace("-", "");


        if (!cepFormatado.matches("[0-9]") || cepFormatado.length() < 8){
            throw new IlegalArgumentException("O cep contém caracteres inválidos, favor verificar");
        }

        return cepFormatado;
    }
}
