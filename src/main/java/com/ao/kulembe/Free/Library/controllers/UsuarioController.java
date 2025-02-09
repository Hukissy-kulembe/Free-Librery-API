package com.ao.kulembe.Free.Library.controllers;

import com.ao.kulembe.Free.Library.dtos.input.UsuarioInput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.dtos.output.UsuarioOutput;
import com.ao.kulembe.Free.Library.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    private ResponseEntity<UsuarioOutput> cadastrar(@RequestBody UsuarioInput usuarioInput){
        var usuarioOutput = usuarioService.cadastraUsuario(usuarioInput);
        return new ResponseEntity<>(usuarioOutput, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UsuarioOutput> buscarPorId(@PathVariable Long id){
        var usuarioOutput = usuarioService.buscarPorId(id);
        return new ResponseEntity<>(usuarioOutput, HttpStatus.FOUND);
    }

    @GetMapping("/nome/{nome}")
    private ResponseEntity<UsuarioOutput> buscarPorNome(@PathVariable String nome) {
        var usuarioOutput = usuarioService.buscarPorNome(nome);
        return new ResponseEntity<>(usuarioOutput, HttpStatus.FOUND);
    }

    @GetMapping
    private ResponseEntity<Set<UsuarioOutput>> listarUsuarios() {
        var listaDeUsuarios = usuarioService.listarUsuarios();
        if (listaDeUsuarios.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return new ResponseEntity<>(listaDeUsuarios, HttpStatus.OK);
    }

    @GetMapping("/livros/{nome}")
    private ResponseEntity<Set<LivroOutput>> buscarLivrosDeUmUsuario(@PathVariable String nome) {
        var result = usuarioService.buscarLivrosDeUmUsuario(nome);
        if (result == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<UsuarioOutput> atualizar(@PathVariable Long id, @RequestBody UsuarioInput usuarioInput) {
        var usuarioOutput = usuarioService.atualizar(id, usuarioInput);
        return new ResponseEntity<>(usuarioOutput, HttpStatus.OK);
    }
}
