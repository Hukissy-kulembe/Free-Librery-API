package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.GeneroInput;
import com.ao.kulembe.Free.Library.dtos.output.GeneroOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;

import java.util.List;

public interface GeneroService {

    GeneroOutput cadastrar(GeneroInput generoDtoInput);
    GeneroOutput buscarPorId(Long id);
    GeneroOutput buscarPorNome(String nome);
    List<GeneroOutput> buscarTodos();
    GeneroOutput atualizar(Long id, GeneroInput generoDtoInput);
    void deletarPorId(Long id);
    List<LivroOutput> buscarLivrosDeUmGenero(String genero);

}
