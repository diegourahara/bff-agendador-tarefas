package com.diegofarias.bffagendadortarefas.infrastructure.client.config;

import com.diegofarias.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.diegofarias.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.diegofarias.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.diegofarias.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.diegofarias.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);

        switch (response.status()) {
            case 400:
                return new IllegalArgumentException("Erro " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro " + mensagemErro);
            case 404://403
                return new ResourceNotFoundException("Erro " + mensagemErro);
            case 409:
                return new ConflictException("Erro " + mensagemErro);
            default:
                return new BusinessException("Erro " + mensagemErro);
        }
    }

    private String mensagemErro(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }

            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}