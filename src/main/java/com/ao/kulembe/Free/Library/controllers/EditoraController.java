package com.ao.kulembe.Free.Library.controllers;

import com.ao.kulembe.Free.Library.dtos.input.EditoraInput;
import com.ao.kulembe.Free.Library.dtos.output.EditoraOutput;
import com.ao.kulembe.Free.Library.dtos.output.LivroOutput;
import com.ao.kulembe.Free.Library.services.EditoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelibrary/editora")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    /**Cadastrar novas editoras no nosso banco de dados;
     *
     * @param editoraInput
     * @return
     */
    @PostMapping
    public ResponseEntity<EditoraOutput> cadastrar(@RequestBody EditoraInput editoraInput) {
        var editoraDtoOutput = editoraService.cadastrarEditora(editoraInput);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.CREATED);
    }

    /**Buscar editora pelo seu nome;
     *
     * @param nome
     * @return
     */
    @GetMapping("/titulo/{nome}")
    public ResponseEntity<EditoraOutput> buscarPorTitulo(@PathVariable String nome) {
        var editoraDtoOutput = editoraService.buscarPorNome(nome);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.OK);
    }

    /**Buscar editora pelo seu id;
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<EditoraOutput> buscarPorId(@PathVariable Long id) {
        var editoraDtoOutput = editoraService.buscarPorId(id);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.OK);
    }

    /**Atualizar editora;
     *
     * @param id
     * @param editoraInput
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<EditoraOutput> atualizarEditora(@PathVariable Long id, @RequestBody EditoraInput editoraInput) {
        var editoraDtoOutput = editoraService.atualizar(id, editoraInput);
        return new ResponseEntity<>(editoraDtoOutput, HttpStatus.OK);
    }

    /**Deletar editora pelo seu campo id;
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEditora(@PathVariable Long id) {
        editoraService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**Listar todas editoras registradas no banco de dados;
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<EditoraOutput>> listarEditoras() {
        var lista = editoraService.listarEditoras();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**Buscar livros de uma editora;
     *
     * @param editora
     * @return
     */
    @GetMapping("/editora/{editora}")
    public ResponseEntity<List<LivroOutput>> buscarLivrosDeUmaEditora(@PathVariable String editora) {
        var listaLivros = editoraService.buscarLivrosDeUmaEditora(editora);
        return new ResponseEntity<>(listaLivros, HttpStatus.OK);
    }

}
