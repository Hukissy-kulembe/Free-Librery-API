package com.ao.kulembe.Free.Library.controllers;

import com.ao.kulembe.Free.Library.dtos.input.ArquivoInput;
import com.ao.kulembe.Free.Library.dtos.output.ArquivoOutput;
import com.ao.kulembe.Free.Library.models.Livro;
import com.ao.kulembe.Free.Library.repositories.LivroRepository;
import com.ao.kulembe.Free.Library.services.ArquivoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/freelibrary/arquivo")
public class ArquivoController {

    private final ArquivoService arquivoService;
    private final LivroRepository livroRepository;

    public ArquivoController(ArquivoService arquivoService, LivroRepository livroRepository) {
        this.arquivoService = arquivoService;
        this.livroRepository = livroRepository;
    }

    /**Upload de Arquivos para o repositorio
     *
     * @param capa
     * @param arquivo
     * @return
     */
    @PostMapping
    public ResponseEntity<ArquivoOutput> uploadArquivo(@RequestParam MultipartFile capa,
                                                       @RequestParam MultipartFile arquivo) {
        ArquivoInput arquivoInput = new ArquivoInput(
                capa,
                arquivo
        );

        ArquivoOutput arquivoOutput = arquivoService.salvarArquivo(arquivoInput);
        return new ResponseEntity<>(arquivoOutput, HttpStatus.CREATED);
    }

    /**Busca os arquivos pelo seu id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArquivoOutput> buscarPorId(@PathVariable Long id) {
        ArquivoOutput arquivoOutput = arquivoService.busalvarArquivoId(id);
        return new ResponseEntity<>(arquivoOutput, HttpStatus.OK);
    }

    /**Download do arquivo associado ao livro pelo seu id
     *
     * @param id
     * @return
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ID: "+id));
        ArquivoOutput arquivo = arquivoService.busalvarArquivoId(livro.getArquivo().getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("arquivo_" + id + ".pdf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(arquivo.arquivo());
    }

    /**Deleta o arquivo associado ao livro pelo seu id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        arquivoService.deletarPorId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }
}
