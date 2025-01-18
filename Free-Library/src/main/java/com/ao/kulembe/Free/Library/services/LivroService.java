package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.LivroInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;

import java.io.IOException;
import java.util.Set;

public interface LivroService {

    LivroOutput cadastrar(LivroInput livroDtoInput) throws IOException;
    LivroOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    LivroOutput buscarPorIsbn(String isbn);
    LivroOutput buscarPorTitulo(String titulo);
    Set<LivroOutput> buscarTodos(String titulo, String autor, String genero, String editora);
    LivroOutput atualizar(Long id, LivroInput livroDtoInput) throws IOException;
    Set<AutorOutput> listarAutores(String livro);
    Set<LivroOutput> buscarLivrosPorAutor(String nome);
    Set<LivroOutput> buscarLivrosPorGenero(String genero);

}
