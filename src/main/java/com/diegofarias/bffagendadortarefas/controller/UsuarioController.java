package com.diegofarias.bffagendadortarefas.controller;

import com.diegofarias.bffagendadortarefas.business.UsuarioService;
import com.diegofarias.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.diegofarias.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.diegofarias.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginDTORequest usuarioDTO) {
        //return usuarioService(usuarioDTO); ver se no video essa parte é alterada, pois está com erro
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por e-mail", description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Excluir Usuários por ID", description = "Excluir usuário")
    @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(
            @PathVariable String email,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de  Usuários", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(
            @RequestBody UsuarioDTORequest usuarioDTO,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuários", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(
            @RequestBody EnderecoDTORequest enderecoDTO,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuários", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(
            @RequestBody TelefoneDTORequest telefoneDTO,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuários", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(
            @RequestBody EnderecoDTORequest enderecoDTO,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuários", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(
            @RequestBody TelefoneDTORequest telefoneDTO,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }

}