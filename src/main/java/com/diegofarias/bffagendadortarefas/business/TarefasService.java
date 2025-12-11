package com.diegofarias.bffagendadortarefas.business;

import com.diegofarias.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.diegofarias.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.diegofarias.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest tarefasDTO) {
        return tarefasClient.gravarTarefas(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            String token
    ) {
        return tarefasClient.buscarListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(
            StatusNotificacaoEnum status,
            String id,
            String token
    ) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(
            TarefasDTORequest dto,
            String id,
            String token
    ) {
        return tarefasClient.updateTarefas(dto, id, token);
    }

}