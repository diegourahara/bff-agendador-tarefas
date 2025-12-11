package com.diegofarias.bffagendadortarefas.business;

import com.diegofarias.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.diegofarias.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse tarefasDTOResponse) {
        emailClient.enviarEmail(tarefasDTOResponse);
    }

}