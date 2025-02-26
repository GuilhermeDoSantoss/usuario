package com.guilherme.agendador_tarefas.business;

import com.guilherme.agendador_tarefas.infrastructure.clients.ViaCepClient;
import com.guilherme.agendador_tarefas.infrastructure.clients.ViaCepDTO;
import com.guilherme.agendador_tarefas.infrastructure.exceptions.IlegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep) {
         try {
            return client.buscaDadosEndereco(processarCep(cep));
        }  catch (IlegalArgumentException e) {
        throw new IlegalArgumentException("Erro ", e);
        }

    }

    private String processarCep(String cep){

        String cepFormatado = cep.replace(" ", "").
                replace("-", "");


        if (!cepFormatado.matches("[0-9]")
                || !Objects.equals(cepFormatado.length(), 8)){
            throw new IlegalArgumentException("O cep contém caracteres inválidos, favor verificar");
        }

        return cepFormatado;
    }
}
