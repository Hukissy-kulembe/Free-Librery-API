package com.ao.kulembe.Free.Library.services.implementations;

import com.ao.kulembe.Free.Library.EmptyResultException;
import com.ao.kulembe.Free.Library.dtos.input.AutorInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.models.Autor;
import com.ao.kulembe.Free.Library.repositories.AutorRepository;
import com.ao.kulembe.Free.Library.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ao.kulembe.Free.Library.services.uteis.Uteis.autores;
import static com.ao.kulembe.Free.Library.services.uteis.Uteis.generos;

@Service
public class AutorImplemente implements AutorService {

    private AutorRepository autorRepository;

    public AutorImplemente( AutorRepository autorRepository ){
        this.autorRepository = autorRepository;
    }

    @Override
    public AutorOutput cadastrar(AutorInput autorDtoInput) {
        Autor autor = new Autor(
                autorDtoInput.nome(),
                autorDtoInput.nacionalidade(),
                autorDtoInput.dataNascimento(),
                autorDtoInput.biografia()
        );

        var value = autorRepository.save(autor);

        return new AutorOutput(
                value.getId(),
                value.getNome(),
                value.getNacionalidade(),
                value.getDataDeNascimento(),
                value.getBiografia()
        );
    }

    @Override
    public AutorOutput buscarPorId(Long id) {
        var autor = autorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Autor not found for ID: " + id)
        );
        return new AutorOutput(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataDeNascimento(),
                autor.getBiografia()
        );
    }

    @Override
    public void deletarPorId(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public List<AutorOutput> listarTodos() {
        var list = autorRepository.findAll();

        if (list.isEmpty())
            throw new EmptyResultException("Nenhuma informação vinda do banco de dados.");

        return list.stream().map(value -> new AutorOutput(
                value.getId(),
                value.getNome(),
                value.getNacionalidade(),
                value.getDataDeNascimento(),
                value.getBiografia()
        )).collect(Collectors
                .toList());
    }

    @Override
    public AutorOutput buscarPorNome(String nome) {
        var autor = autorRepository.findByName(nome)
                .orElseThrow(() -> new EmptyResultException("Autor não existe para esse nome: " + nome));

        return new AutorOutput(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataDeNascimento(),
                autor.getBiografia()
        );
    }

    @Override
    public AutorOutput atualizar(Long id, AutorInput autorInput) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o ID: " + id));

        autor.setNome(autorInput.nome());
        autor.setNacionalidade(autorInput.nacionalidade());
        autor.setDataDeNascimento(autorInput.dataNascimento());
        autor.setBiografia(autorInput.biografia());

        var value = autorRepository.save(autor);

        return new AutorOutput(
                value.getId(),
                value.getNome(),
                value.getNacionalidade(),
                value.getDataDeNascimento(),
                value.getBiografia()
        );
    }

    @Override
    public Set<LivroOutput> buscarLivrosDeUmAutor(String autor) {

        var a = autorRepository.findByName(autor)
                .orElseThrow(() ->
                        new EntityNotFoundException("Nenhum autor associado ao nome: " + autor));
        return a.getLivro().stream()
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
                        value.getCapa(),
                        value.getPdf()
                )).collect(Collectors
                        .toSet());
    }
}
