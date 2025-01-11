package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.UsuarioInput;
import com.ao.kulembe.Free.Library.dtos.output.UsuarioOutput;

import java.util.List;

public interface UsuarioService {

    UsuarioOutput cadastraUsuario(UsuarioInput usuarioInput);
    UsuarioOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    List<UsuarioOutput> listarUsuarios();
    UsuarioOutput buscarPorNome(String nome);
    UsuarioOutput atualizar(Long id, UsuarioInput usuarioInput);
    List<UsuarioOutput> buscarLivrosDeUmUsuario(String usuario);

}
