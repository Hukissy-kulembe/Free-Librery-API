package com.ao.kulembe.Free.Library.services.implementations;

import com.ao.kulembe.Free.Library.dtos.input.EditoraInput;
import com.ao.kulembe.Free.Library.dtos.output.EditoraOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.models.Editora;
import com.ao.kulembe.Free.Library.repositories.EditoraRepository;
import com.ao.kulembe.Free.Library.services.EditoraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ao.kulembe.Free.Library.services.uteis.Uteis.autores;
import static com.ao.kulembe.Free.Library.services.uteis.Uteis.generos;

@Service
public class EditoraImplemente implements EditoraService {

    private EditoraRepository editoraRepository;

    public EditoraImplemente (EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    /**Cadastrar Editora: Adicionar uma nova editora ao sistema com dados como nome, endereço, contato e ano de fundação.
     *
     * @param editoraInput
     * @return
     */
    @Override
    public EditoraOutput cadastrarEditora(EditoraInput editoraInput) {
        var editora = new Editora(
                editoraInput.nome(),
                editoraInput.nif(),
                editoraInput.endereco()
        );

        var a = editoraRepository.save(editora);

        return new EditoraOutput(
                a.getId(),
                a.getNome(),
                a.getNif(),
                a.getEndereco()
        );
    }

    @Override
    public EditoraOutput buscarPorId(Long id) {
        var editora = editoraRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada para o id: " + id));

        EditoraOutput editoraDto = new EditoraOutput(
                editora.getId(),
                editora.getNome(),
                editora.getNif(),
                editora.getEndereco()
        );
        return editoraDto;
    }

    /**Deletar Editora: Remover uma editora do sistema.
     *
     * @param id
     */
    @Override
    public void deletarPorId(Long id) {
        var editora = editoraRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Editora não encontrada para ID: " + id));
        editoraRepository.deleteById(id);
    }

    /**Buscar Editora por Nome: Permitir buscar editoras pelo nome.
     *
     * @param nome
     * @return
     */
    @Override
    public EditoraOutput buscarPorNome(String nome) {
        var editora = editoraRepository.findByName(nome).orElseThrow(() ->
                        new EntityNotFoundException("Editora não encontrada para o titulo: " + nome));

        return new EditoraOutput(
                editora.getId(),
                editora.getNome(),
                editora.getNif(),
                editora.getEndereco()
        );
    }

    /**Atualizar Editora: Editar as informações de uma editora existente.
     *
     * @param id
     * @param editoraInput
     * @return
     */
    @Override
    public EditoraOutput atualizar(Long id, EditoraInput editoraInput) {

        Editora editora = editoraRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Editora não encontrada para o ID: " + id));

        editora.setNome(editoraInput.nome());
        editora.setEndereco(editoraInput.endereco());
        editora.setNif(editoraInput.nif());

        var a = editoraRepository.save(editora);

        return new EditoraOutput(
                a.getId(),
                a.getNome(),
                a.getNif(),
                a.getEndereco()
        );
    }

    /**Listar Editoras: Recuperar uma lista de todas as editoras cadastradas.
     *
     * @return
     */
    public List<EditoraOutput> listarEditoras() {
        var lista = editoraRepository.findAll();

        if (lista.isEmpty())
            throw new EntityNotFoundException("Não há registro na tabela Gênero");

        return lista.stream()
                .map(value -> new EditoraOutput(
                        value.getId(),
                        value.getNome(),
                        value.getNif(),
                        value.getEndereco()
                )).collect(Collectors.toList());
    }

    /**Buscar Livros de uma Editora: Listar todos os livros publicados por uma editora específica.
     *
     * @param editora
     * @return
     */
    @Override
    public List<LivroOutput> buscarLivrosDeUmaEditora(String editora) {
        return editoraRepository.findByName(editora)
                .get().getLivro()
                .stream().map(value -> new LivroOutput(
                        value.getId(),
                        value.getTitulo(),
                        value.getAnoDePublicacao(),
                        value.getIsbn(),
                        value.getNumeroDePagina(),
                        value.getIdioma(),
                        value.getSinopse(),
                        value.getEditora().getNome(),
                        autores(value.getAutores()),
                        generos(value.getGeneros()),
                        value.getArquivo().getCapa()
                )).collect(Collectors
                        .toList());
    }

}
