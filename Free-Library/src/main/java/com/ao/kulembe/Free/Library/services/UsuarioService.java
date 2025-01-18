package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.UsuarioInput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.dtos.output.UsuarioOutput;

import java.util.Set;

public interface UsuarioService {

    UsuarioOutput cadastraUsuario(UsuarioInput usuarioInput);
    UsuarioOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    Set<UsuarioOutput> listarUsuarios();
    UsuarioOutput buscarPorNome(String nome);
    UsuarioOutput atualizar(Long id, UsuarioInput usuarioInput);
    Set<LivroOutput> buscarLivrosDeUmUsuario(String usuario);

}
