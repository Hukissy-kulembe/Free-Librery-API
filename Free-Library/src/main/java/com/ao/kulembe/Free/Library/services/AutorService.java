package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.AutorInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;

import java.util.List;

public interface AutorService {

    AutorOutput cadastrar(AutorInput autorDtoInput);
    AutorOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    List<AutorOutput> listarTodos();
    AutorOutput buscarPorNome(String nome);
    AutorOutput atualizar(Long id, AutorInput autorDtoInput);
    List<LivroOutput> buscarLivrosDeUmAutor(String autor);

}
