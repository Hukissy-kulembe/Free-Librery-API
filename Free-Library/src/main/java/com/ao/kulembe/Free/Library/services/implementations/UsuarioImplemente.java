package com.ao.kulembe.Free.Library.services.implementations;

import com.ao.kulembe.Free.Library.dtos.input.UsuarioInput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.dtos.output.UsuarioOutput;
import com.ao.kulembe.Free.Library.models.Usuario;
import com.ao.kulembe.Free.Library.repositories.UsuarioRepository;
import com.ao.kulembe.Free.Library.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ao.kulembe.Free.Library.services.uteis.Uteis.autores;
import static com.ao.kulembe.Free.Library.services.uteis.Uteis.generos;

@Service
public class UsuarioImplemente implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioImplemente(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioOutput cadastraUsuario(UsuarioInput usuarioInput) {
        Usuario usuario = new Usuario(
                usuarioInput.nome(),
                usuarioInput.email(),
                usuarioInput.senha(),
                usuarioInput.dataNascimento()
        );

        Usuario usuarioOutput = usuarioRepository.save(usuario);

        return new UsuarioOutput(
                usuarioOutput.getId(),
                usuarioOutput.getSenha(),
                usuarioOutput.getEmail(),
                usuarioOutput.getNome(),
                usuarioOutput.getDataNascimento()
        );
    }

    @Override
    public UsuarioOutput buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID: " + id));
        return new UsuarioOutput(
                usuario.getId(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getDataNascimento()
        );
    }

    @Override
    public void deletarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Set<UsuarioOutput> listarUsuarios() {
        return usuarioRepository
                .findAll().stream()
                .map(usuario -> new UsuarioOutput(
                        usuario.getId(),
                        usuario.getSenha(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getDataNascimento()))
                .collect(Collectors.toSet());
    }

    @Override
    public UsuarioOutput buscarPorNome(String nome) {
        Usuario usuario = usuarioRepository.findByName(nome)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o nome: " + nome));
        return new UsuarioOutput(
                usuario.getId(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataNascimento()
        );
    }

    @Override
    public UsuarioOutput atualizar(Long id, UsuarioInput usuarioInput) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID: " + id));
        usuario.setDataNascimento(usuarioInput.dataNascimento());
        usuario.setEmail(usuarioInput.email());
        usuario.setNome(usuarioInput.nome());

        Usuario usuarioOutput = usuarioRepository.save(usuario);

        return new UsuarioOutput(
                usuarioOutput.getId(),
                usuarioOutput.getSenha(),
                usuarioOutput.getEmail(),
                usuarioOutput.getNome(),
                usuarioOutput.getDataNascimento()
        );
    }

    @Override
    public Set<LivroOutput> buscarLivrosDeUmUsuario(String usuario) {
        return usuarioRepository.findByName(usuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o nome: " + usuario))
                .getLivros().stream()
                .map(livro -> new LivroOutput(
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
                        livro.getArquivo().getCapa()
                )).collect(Collectors.toSet());
    }
}
