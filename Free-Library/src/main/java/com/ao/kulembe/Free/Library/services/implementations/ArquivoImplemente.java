package com.ao.kulembe.Free.Library.services.implementations;

import com.ao.kulembe.Free.Library.dtos.input.ArquivoInput;
import com.ao.kulembe.Free.Library.dtos.output.ArquivoOutput;
import com.ao.kulembe.Free.Library.models.Arquivo;
import com.ao.kulembe.Free.Library.repositories.ArquivoRepository;
import com.ao.kulembe.Free.Library.services.ArquivoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArquivoImplemente implements ArquivoService {

    private final ArquivoRepository arquivoRepository;

    public ArquivoImplemente(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    @Override
    public ArquivoOutput salvarArquivo(ArquivoInput arquivoInput) {
        try {
            MultipartFile capa = arquivoInput.capa();
            MultipartFile arquivo = arquivoInput.arquivo();

            Arquivo novoArquivo = new Arquivo(
                    capa.getBytes(),
                    arquivo.getBytes()
            );

            Arquivo arquivoSalvo = arquivoRepository.save(novoArquivo);

            return new ArquivoOutput(
                    arquivoSalvo.getId(),
                    arquivoSalvo.getCapa(),
                    arquivoSalvo.getArquivo()
            );

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo", e);
        }
    }

    @Override
    public ArquivoOutput busalvarArquivoId(Long id) {
        Arquivo arquivo = arquivoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo não encontrado para o ID: " + id));
        return new ArquivoOutput(
                arquivo.getId(),
                arquivo.getCapa(),
                arquivo.getArquivo()
        );
    }

    @Override
    public void deletarPorId(Long id) {
        arquivoRepository.deleteById(id);
    }

}
