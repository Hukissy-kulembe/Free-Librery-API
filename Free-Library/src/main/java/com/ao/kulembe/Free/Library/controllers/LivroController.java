package com.ao.kulembe.Free.Library.controllers;

import com.ao.kulembe.Free.Library.dtos.input.LivroInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.services.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/freelibrary/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    private ResponseEntity<LivroOutput> cadastrar( @RequestBody LivroInput livroInput) throws IOException {
        var livroOutput = livroService.cadastrar(livroInput);
        return new ResponseEntity<>(livroOutput, HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<Set<LivroOutput>> buscarTodos(@RequestParam(required = false) String titulo,
                                                         @RequestParam(required = false) String autor,
                                                         @RequestParam(required = false) String genero,
                                                         @RequestParam(required = false) String editora){
        var livros = livroService.buscarTodos(titulo, autor, genero, editora);
        return new ResponseEntity<>(livros, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    private ResponseEntity<LivroOutput> buscarPorId(@PathVariable Long id) {
        var livroOutput = livroService.buscarPorId(id);
        return new ResponseEntity<>(livroOutput, HttpStatus.FOUND);
    }

    @GetMapping("/titulo/{titulo}")
    private ResponseEntity<LivroOutput> buscarPorTitulo(@PathVariable String titulo) {
        var livroOutput = livroService.buscarPorTitulo(titulo);
        return new ResponseEntity<>(livroOutput, HttpStatus.FOUND);
    }

    @GetMapping("/isbn/{isbn}")
    private ResponseEntity<LivroOutput> buscarPorIsbn(@PathVariable String isnb) {
        var livroOutput = livroService.buscarPorIsbn(isnb);
        return new ResponseEntity<>(livroOutput, HttpStatus.FOUND);
    }

    @GetMapping("/autores/{livro}")
    private ResponseEntity<Set<AutorOutput>> listarAutores(@PathVariable String livro) {
        var autorOutputs = livroService.listarAutores(livro);
        return new ResponseEntity<>(autorOutputs, HttpStatus.FOUND);
    }

    @GetMapping("/generos/{genero}")
    private ResponseEntity<Set<LivroOutput>> buscarLivrosPorGenero(@PathVariable String genero) {
        var generoOutput = livroService.buscarLivrosPorGenero(genero);
        return new ResponseEntity<>(generoOutput, HttpStatus.FOUND);
    }

    @GetMapping("/autor/{nome}")
    private ResponseEntity<Set<LivroOutput>> buscarLivrosPorAutor(@PathVariable String nome) {
        var livroOutputs = livroService.buscarLivrosPorAutor(nome);
        return new ResponseEntity<>(livroOutputs, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        livroService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<LivroOutput> atualizar(@PathVariable Long id, @RequestBody LivroInput livroInput) throws IOException {
        var livroOutput = livroService.atualizar(id, livroInput);
        return new ResponseEntity<>(livroOutput, HttpStatus.OK);
    }

}
