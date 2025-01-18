package com.ao.kulembe.Free.Library.services.uteis;

import com.ao.kulembe.Free.Library.dtos.input.ArquivoInput;
import com.ao.kulembe.Free.Library.dtos.output.ArquivoOutput;

public interface ArquivoService {

    ArquivoOutput salvarArquivo(ArquivoInput arquivoInput);
    ArquivoOutput busalvarArquivoId(Long id);
    void deletarPorId(Long id);

}
