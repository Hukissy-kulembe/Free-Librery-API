package com.ao.kulembe.Free.Library.services;

import com.ao.kulembe.Free.Library.dtos.input.EditoraInput;
import com.ao.kulembe.Free.Library.dtos.output.EditoraOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;

import java.util.List;

public interface EditoraService {

    EditoraOutput cadastrarEditora(EditoraInput editoraDtoInput);
    EditoraOutput buscarPorId(Long id);
    void deletarPorId(Long id);
    EditoraOutput buscarPorTitulo(String titulo);
    EditoraOutput atualizar(Long id, EditoraInput editoraDtoInput);
    List<EditoraOutput> listarEditoras();
    List<LivroOutput> buscarLivrosDeUmaEditora(String editora);

}
