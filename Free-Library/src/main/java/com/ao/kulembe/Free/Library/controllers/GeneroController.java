package com.ao.kulembe.Free.Library.controllers;

import com.ao.kulembe.Free.Library.dtos.input.GeneroInput;
import com.ao.kulembe.Free.Library.dtos.output.GeneroOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.services.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/freelibrary/genero")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PostMapping
    private ResponseEntity<GeneroOutput> cadastrar(@RequestBody GeneroInput generoInput) {
        var generoOutput = generoService.cadastrar(generoInput);
        return new ResponseEntity<>(generoOutput, HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<Set<GeneroOutput>> buscarTodos() {
        var generoOutput = generoService.buscarTodos();
        return new ResponseEntity<>(generoOutput, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    private ResponseEntity<GeneroOutput> buscarPorId(@PathVariable Long id) {
        var generoOutput = generoService.buscarPorId(id);
        return new ResponseEntity<>(generoOutput, HttpStatus.FOUND);
    }

    @GetMapping("/nome/{nome}")
    private ResponseEntity<GeneroOutput> buscarPorNome(@PathVariable String nome) {
        var generoOutput = generoService.buscarPorNome(nome);
        return new ResponseEntity<>(generoOutput, HttpStatus.FOUND);
    }

    @GetMapping("/livros/{nome}")
    private ResponseEntity<Set<LivroOutput>> buscarLivrosDeUmGenero(@PathVariable String nome) {
        var livros = generoService.buscarLivrosDeUmGenero(nome);
        return new ResponseEntity<>(livros, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletarPorId(@PathVariable long id) {
        generoService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<GeneroOutput> atualizarGenero(@PathVariable Long id, @RequestBody GeneroInput generoInput){
        var generoOutput = generoService.atualizar(id, generoInput);
        return new ResponseEntity<>(generoOutput, HttpStatus.OK);
    }
}
