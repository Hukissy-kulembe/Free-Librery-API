package com.ao.kulembe.Free.Library.dtos.output;

import java.time.LocalDate;

public record AutorOutput(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento,
        String biografia
) {
}
