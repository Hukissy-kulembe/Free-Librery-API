package com.ao.kulembe.Free.Library.dtos.input;

import org.springframework.web.multipart.MultipartFile;

public record ArquivoInput(
        MultipartFile arquivo
) {
}
