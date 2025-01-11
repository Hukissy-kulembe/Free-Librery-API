package com.ao.kulembe.Free.Library.dtos.input;

import java.time.LocalDate;

public record AutorInput(
        String nome,
        String nacionalidade,
        LocalDate dataNascimento,
        String biografia
) {
}
