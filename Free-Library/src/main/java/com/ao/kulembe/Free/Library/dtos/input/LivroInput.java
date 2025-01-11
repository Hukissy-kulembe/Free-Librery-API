package com.ao.kulembe.Free.Library.dtos.input;

import java.time.LocalDate;
import java.util.Set;

public record LivroInput(
        String titulo,
        LocalDate anoDePublicacao,
        String isbn,
        int numeroDePagina,
        String idioma,
        String sinopse,
        Long editora,
        Set<Long> generos,
        Set<Long> autores,
        byte[] capa,
        byte[] pdf
) {
}
