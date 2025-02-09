package com.ao.kulembe.Free.Library.controllers;


import com.ao.kulembe.Free.Library.dtos.input.AutorInput;
import com.ao.kulembe.Free.Library.dtos.output.AutorOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.services.AutorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/freelibrary/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    /**Cadastrar novos autores no banco de dados;
     *
     * @param autorDtoInput
     * @return
     */
    @PostMapping
    public ResponseEntity<AutorOutput> cadastrar(@RequestBody @Valid AutorInput autorDtoInput){
        var autorDtoOutput = autorService.cadastrar(autorDtoInput);
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.CREATED);
    }

    /**Buscar autor pelo seu id;
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<AutorOutput> buscarPorId(@PathVariable @Positive(message = "O id deve ser um número positivo.") Long id){
        var autorDtoOutput = autorService.buscarPorId(id);
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.OK);
    }

    /**Buscar autor pelo seu nome;
     *
     * @param nome
     * @return
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<AutorOutput> buscarPorNome(@PathVariable @NotBlank(message = "O nome não pode estar vazio.") String nome){
        var autorDtoOutput = autorService.buscarPorNome(nome);
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.OK);
    }

    /**Buscar todos os autores;
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<AutorOutput>> buscarTodos(){
        var autorDtoOutput = autorService.listarTodos();
        return new ResponseEntity<>(autorDtoOutput, HttpStatus.OK);
    }

    /**Busccar livros pelo nome do autor;
     *
     * @param autor
     * @return
     */
    @GetMapping("/livros/{autor}")
    public ResponseEntity<Set<LivroOutput>> buscarLivrosDeUmAutor(@PathVariable @NotBlank(message = "O nome não pode estar vazio.") String autor) {
        var livros = autorService.buscarLivrosDeUmAutor(autor);
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    /**Deletar autor pelo seu id;
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable @Positive(message = "O id deve ser um número positivo.") Long id){
        autorService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**Atualizar dados de um autor;
     *
     * @param id
     * @param autorInput
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<AutorOutput> atualizarAutor(@PathVariable @Positive(message = "O id deve ser um número positivo.") Long id,
                                                      @RequestBody AutorInput autorInput) {
        var autor = autorService.atualizar(id, autorInput);
        return new ResponseEntity<>(autor, HttpStatus.OK);
    }

}
