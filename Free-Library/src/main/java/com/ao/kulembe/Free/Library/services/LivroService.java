package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.LivroInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;

import java.util.List;

public interface LivroService {

    LivroOutput cadastrar(LivroInput livroDtoInput);
    LivroOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    LivroOutput buscarPorIsbn(String isbn);
    LivroOutput buscarPorTitulo(String titulo);
    List<LivroOutput> buscarTodos(String titulo, String autor, String genero, String editora);
    LivroOutput atualizar(Long id, LivroInput livroDtoInput);
    List<AutorOutput> listarAutores(String livro);
    List<LivroOutput> buscarLivrosPorAutor(String nome);
    List<LivroOutput> buscarLivrosPorGenero(String genero);

}
