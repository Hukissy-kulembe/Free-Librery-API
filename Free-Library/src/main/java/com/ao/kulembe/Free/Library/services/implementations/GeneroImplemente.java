package com.ao.kulembe.Free.Library.services.implementations;

import com.ao.kulembe.Free.Library.EmptyResultException;
import com.ao.kulembe.Free.Library.dtos.input.GeneroInput;
import com.ao.kulembe.Free.Library.dtos.output.GeneroOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.models.Genero;
import com.ao.kulembe.Free.Library.repositories.GeneroRepository;
import com.ao.kulembe.Free.Library.services.GeneroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ao.kulembe.Free.Library.services.uteis.Uteis.autores;
import static com.ao.kulembe.Free.Library.services.uteis.Uteis.generos;

@Service
public class GeneroImplemente implements GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroImplemente(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public GeneroOutput cadastrar(GeneroInput generoInput) {
        Genero genero = new Genero(
                generoInput.nome(),
                generoInput.descricao()
        );

        var g = generoRepository.save(genero);

        return new GeneroOutput(
                g.getId(),
                g.getNome(),
                g.getDescricao()
        );
    }

    @Override
    public GeneroOutput buscarPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genero não encontrado para o ID: " + id));
        return new GeneroOutput(
                genero.getId(),
                genero.getNome(),
                genero.getDescricao()
        );
    }

    @Override
    public GeneroOutput buscarPorNome(String nome) {
        Genero genero = generoRepository.findByName(nome)
                .orElseThrow(() -> new EntityNotFoundException("Genero não encontrado para o nome: " + nome));
        return new GeneroOutput(
                genero.getId(),
                genero.getNome(),
                genero.getDescricao()
        );
    }

    @Override
    public Set<GeneroOutput> buscarTodos() {
        if (generoRepository.findAll().isEmpty())
            throw new EmptyResultException("Nenhum registro armazenado no banco de dados.");
        return generoRepository
                .findAll()
                .stream()
                .map(x -> new GeneroOutput(
                        x.getId(),
                        x.getNome(),
                        x.getDescricao()))
                .collect(Collectors.toSet());
    }

    @Override
    public GeneroOutput atualizar(Long id, GeneroInput generoInput) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Impossível atualiza, não existe nenhum genero com o Id(" + id + ") fornecido."));
        genero.setNome(generoInput.nome());
        genero.setDescricao(generoInput.descricao());

        var g = generoRepository.save(genero);
        return new GeneroOutput(
                g.getId(),
                g.getNome(),
                g.getDescricao()
        );
    }

    @Override
    public void deletarPorId(Long id) {
        generoRepository.deleteById(id);
    }

    @Override
    public Set<LivroOutput> buscarLivrosDeUmGenero(String genero) {
        if (generoRepository.findByName(genero).get().getLivro().isEmpty())
            throw new EmptyResultException("Nenhum livro está associado ao gênero: "+genero);
        return generoRepository.findByName(genero)
                .get().getLivro()
                .stream()
                .map(x -> new LivroOutput(
                        x.getId(),
                        x.getTitulo(),
                        x.getAnoDePublicacao(),
                        x.getIsbn(),
                        x.getNumeroDePagina(),
                        x.getIdioma(),
                        x.getSinopse(),
                        x.getEditora().getNome(),
                        generos(x.getGeneros()),
                        autores(x.getAutores()),
                        x.getArquivo().getCapa()
                )).collect(Collectors.toSet());
    }
}
