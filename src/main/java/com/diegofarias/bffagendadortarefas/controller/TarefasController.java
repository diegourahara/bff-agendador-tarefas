package com.diegofarias.bffagendadortarefas.controller;

import com.diegofarias.bffagendadortarefas.business.TarefasService;
import com.diegofarias.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.diegofarias.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.diegofarias.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas Usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(
            @RequestBody TarefasDTORequest tarefasDTO,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(
                tarefasService.gravarTarefa(token, tarefasDTO)
        );
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas por Período", description = "Busca Tarefas cadastradas por Período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de  Tarefas por E-mail de Usuário", description = "Busca Tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);

        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta Tarefas por Id", description = "Deleta Tarefas cadastradas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status de Tarefas", description = "Altera Status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da Tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de Tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }

}