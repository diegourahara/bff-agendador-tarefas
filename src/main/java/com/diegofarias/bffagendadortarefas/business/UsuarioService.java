package com.diegofarias.bffagendadortarefas.business;

import com.diegofarias.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.diegofarias.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.diegofarias.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.diegofarias.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.diegofarias.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return usuarioClient.atualizaDadosUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }

}