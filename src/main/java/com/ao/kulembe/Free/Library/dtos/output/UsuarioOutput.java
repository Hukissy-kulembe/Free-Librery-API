package com.ao.kulembe.Free.Library.dtos.output;

import java.time.LocalDate;

public record UsuarioOutput(
        Long id,
        String senha,
        String email,
        String nome,
        LocalDate dataNascimento
) {
}
