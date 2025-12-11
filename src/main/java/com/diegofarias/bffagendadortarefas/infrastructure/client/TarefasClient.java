package com.diegofarias.bffagendadortarefas.infrastructure.client;

import com.diegofarias.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.diegofarias.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefas(
            @RequestBody TarefasDTORequest tarefasDTO,
            @RequestHeader("Authorization") String token
    );

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token
    );

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );

    @PutMapping
    TarefasDTOResponse updateTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );


}