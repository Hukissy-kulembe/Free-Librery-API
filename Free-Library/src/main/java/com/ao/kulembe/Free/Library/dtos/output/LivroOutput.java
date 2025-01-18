package com.ao.kulembe.Free.Library.dtos.output;

import java.time.LocalDate;
import java.util.Set;

public record LivroOutput(
        Long id,
        String titulo,
        LocalDate anoDePublicacao,
        String isbn,
        int numeroDePagina,
        String idioma,
        String sinopse,
        String editora,
        Set<String> generos,
        Set<String> autores
) {
}
