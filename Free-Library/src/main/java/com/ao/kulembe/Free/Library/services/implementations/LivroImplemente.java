package com.ao.kulembe.Free.Library.services.implementations;

import com.ao.kulembe.Free.Library.dtos.input.LivroInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.models.*;
import com.ao.kulembe.Free.Library.repositories.*;
import com.ao.kulembe.Free.Library.services.LivroService;
import com.ao.kulembe.Free.Library.services.specifications.LivroSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ao.kulembe.Free.Library.services.specifications.LivroSpecification.*;
import static com.ao.kulembe.Free.Library.services.uteis.Uteis.autores;
import static com.ao.kulembe.Free.Library.services.uteis.Uteis.generos;

@Service
public class LivroImplemente implements LivroService {

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private GeneroRepository generoRepository;
    private EditoraRepository editoraRepository;
    private ArquivoRepository arquivoRepository;

    public LivroImplemente(LivroRepository livroRepository,
                           AutorRepository autorRepository,
                           GeneroRepository generoRepository,
                           EditoraRepository editoraRepository,
                           ArquivoRepository arquivoRepository
    ) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.editoraRepository = editoraRepository;
        this.arquivoRepository = arquivoRepository;
    }

    /**
     * Cadastrar Livro: Permitir a criação de um novo livro com atributos como título,autor(es), gênero(s), editora, ano de publicação, ISBN e demais atributos.
     *
     * @param livroInput
     * @return
     */
    @Override
    public LivroOutput cadastrar(LivroInput livroInput) {

        Set<Genero> generos = livroInput.generos()
                .stream()
                .map(value -> generoRepository.findById(value).
                        orElseThrow(() ->
                                new EntityNotFoundException("ID não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Set<Autor> autors = livroInput.autores()
                .stream()
                .map(value -> autorRepository.findById(value)
                        .orElseThrow(
                                () -> new EntityNotFoundException("ID: " + livroInput.editora() + " não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Editora editora = editoraRepository
                .findById(livroInput.editora())
                .orElseThrow(
                        () -> new EntityNotFoundException("ID: " + livroInput.editora() + " não encontrado para uma determinada editora")
                );

        Arquivo arquivo = arquivoRepository
                .findById(livroInput.livro())
                .orElseThrow(
                        () -> new EntityNotFoundException("ID: " + livroInput.livro() + " não encontrado para uma determinada arquivo.")
                );

        Livro livro = new Livro(
                livroInput.titulo(),
                livroInput.anoDePublicacao(),
                livroInput.isbn(),
                livroInput.numeroDePagina(),
                livroInput.idioma(),
                livroInput.sinopse(),
                editora,
                autors,
                generos,
                livroInput.capaUrl(),
                arquivo
        );

        var a = livroRepository.save(livro);

        LivroOutput livroDtoOutput = new LivroOutput(
                a.getId(),
                a.getTitulo(),
                a.getAnoDePublicacao(),
                a.getIsbn(),
                a.getNumeroDePagina(),
                a.getIdioma(),
                a.getSinopse(),
                a.getEditora().getNome(),
                generos(a.getGeneros()),
                autores(a.getAutores()),
                a.getCapaUrl()
        );
        return livroDtoOutput;
    }

    /**
     * Buscar Livro por ID: Recuperar os detalhes de um livro com base no seu identificador único.
     *
     * @param id
     */
    @Override
    public LivroOutput buscarPorId(Long id) {

        Livro livro = livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: " + id));

        return new LivroOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getEditora().getNome(),
                generos(livro.getGeneros()),
                autores(livro.getAutores()),
                livro.getCapaUrl()
        );
    }

    /**
     * Deletar Livro: Remover um livro do sistema.
     *
     * @param id
     */
    @Override
    public void deletarPorId(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isEmpty()) {
            throw new EntityNotFoundException("Livro não encontrado para o ID: " + id);
        }
        livroRepository.deleteById(id);
    }

    /**
     * Buscar Livro por ISBN: Recuperar os detalhes de um livro com base no seu isbn.
     *
     * @param isbn
     * @return
     */
    @Override
    public LivroOutput buscarPorIsbn(String isbn) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException(""));
        return new LivroOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getEditora().getNome(),
                generos(livro.getGeneros()),
                autores(livro.getAutores()),
                livro.getCapaUrl()
        );
    }

    /**
     * Buscar Livro por Título: Permitir buscar livros com base no título.
     *
     * @param titulo
     * @return LivroDtoOutput
     */
    @Override
    public LivroOutput buscarPorTitulo(String titulo) {
        Livro livro = livroRepository.findByTitulo(titulo)
                .orElseThrow(() ->
                        new EntityNotFoundException("Livro não encontrado para o titulo: " + titulo));
        return new LivroOutput(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDePublicacao(),
                livro.getIsbn(),
                livro.getNumeroDePagina(),
                livro.getIdioma(),
                livro.getSinopse(),
                livro.getEditora().getNome(),
                generos(livro.getGeneros()),
                autores(livro.getAutores()),
                livro.getCapaUrl()
        );
    }

    /**
     * Listar Livros: Retornar uma lista de todos os livros cadastrados.
     *
     * @return
     */
    @Override
    public Set<LivroOutput> buscarTodos(String titulo, String autor, String genero, String editora) {
        return livroRepository.findAll(LivroSpecification
                        .livroDtoOutputSpecification(titulo)
                        .and(autorLivroEquals(autor)
                                .and(generoLivroEquals(genero)
                                        .and(editoraLivroEquals(editora)))))
                .stream()
                .map(value -> new LivroOutput(
                        value.getId(),
                        value.getTitulo(),
                        value.getAnoDePublicacao(),
                        value.getIsbn(),
                        value.getNumeroDePagina(),
                        value.getIdioma(),
                        value.getSinopse(),
                        value.getEditora().getNome(),
                        generos(value.getGeneros()),
                        autores(value.getAutores()),
                        value.getCapaUrl()
                ))
                .collect(Collectors.toSet());
    }

    /**
     * Atualizar Livro: Editar as informações de um livro existente.
     *
     * @param id
     * @param livroInput
     * @return
     */
    @Override
    public LivroOutput atualizar(Long id, LivroInput livroInput) {
        Livro livro = livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: " + id));

        Set<Genero> generos = livroInput.generos()
                .stream()
                .map(value -> generoRepository.findById(value)
                        .orElseThrow(() ->
                                new EntityNotFoundException("ID não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Set<Autor> autors = livroInput.autores()
                .stream()
                .map(value -> autorRepository.findById(value)
                        .orElseThrow(
                                () -> new EntityNotFoundException("ID não correspondente para um determinado gênero.")
                        )).collect(Collectors.toSet());

        Editora editora = editoraRepository
                .findById(livroInput.editora())
                .orElseThrow(
                        () -> new EntityNotFoundException("ID: " + livroInput.editora() + " não encontrado para uma determinada editora")
                );

        livro.setTitulo(livroInput.titulo());
        livro.setAutores(autors);
        livro.setEditora(editora);
        livro.setGeneros(generos);
        livro.setAnoDePublicacao(livroInput.anoDePublicacao());
        livro.setIsbn(livroInput.isbn());
        livro.setSinopse(livroInput.sinopse());
        livro.setIdioma(livroInput.idioma());
        livro.setNumeroDePagina(livro.getNumeroDePagina());

        var l = livroRepository.save(livro);

        return new LivroOutput(
                l.getId(),
                l.getTitulo(),
                l.getAnoDePublicacao(),
                l.getIsbn(),
                l.getNumeroDePagina(),
                l.getIdioma(),
                l.getSinopse(),
                l.getEditora().getNome(),
                generos(l.getGeneros()),
                autores(l.getAutores()),
                l.getCapaUrl()
        );
    }

    /**
     * Listar todos os autores registrados;
     *
     * @param livro
     * @return
     */
    @Override
    public Set<AutorOutput> listarAutores(String livro) {
        Livro livros = livroRepository.findByTitulo(livro)
                .orElseThrow(
                        () -> new EntityNotFoundException("Livro não encontrado para o titulo: " + livro)
                );

        return livros.getAutores()
                .stream()
                .map(value -> new AutorOutput(
                        value.getId(),
                        value.getNome(),
                        value.getBiografia(),
                        value.getDataDeNascimento(),
                        value.getNacionalidade()))
                .collect(Collectors.toSet());
    }

    /**
     * Buscar Livros por Autor: Listar todos os livros associados a um determinado autor.
     *
     * @param nome
     * @return
     */
    @Override
    public Set<LivroOutput> buscarLivrosPorAutor(String nome) {
        var autor = autorRepository.findByName(nome)
                .orElseThrow(
                        () -> new EntityNotFoundException("Nenhum livro associado ao autor: " + nome));

        return autor.getLivro().stream().map(value ->
                        new LivroOutput(
                                value.getId(),
                                value.getTitulo(),
                                value.getAnoDePublicacao(),
                                value.getIsbn(),
                                value.getNumeroDePagina(),
                                value.getIdioma(),
                                value.getSinopse(),
                                value.getEditora().getNome(),
                                generos(value.getGeneros()),
                                autores(value.getAutores()),
                                value.getCapaUrl()
                        ))
                .collect(Collectors
                        .toSet());
    }

    /**
     * Buscar Livros por Gênero: Listar livros que pertençam a um ou mais gêneros específicos.
     *
     * @param genero
     * @return
     */
    @Override
    public Set<LivroOutput> buscarLivrosPorGenero(String genero) {
        var generos = generoRepository
                .findByName(genero)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum livro associado ao autor: " + genero));

        return generos.getLivro().stream().map(value ->
                        new LivroOutput(
                                value.getId(),
                                value.getTitulo(),
                                value.getAnoDePublicacao(),
                                value.getIsbn(),
                                value.getNumeroDePagina(),
                                value.getIdioma(),
                                value.getSinopse(),
                                value.getEditora().getNome(),
                                generos(value.getGeneros()),
                                autores(value.getAutores()),
                                value.getCapaUrl()
                        ))
                .collect(Collectors
                        .toSet());
    }

}
