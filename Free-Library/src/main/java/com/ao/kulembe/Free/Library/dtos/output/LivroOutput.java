package com.ao.kulembe.Free.Library.dtos.output;

import java.time.LocalDate;

public record LivroOutput(
        Long id,
        String titulo,
        LocalDate anoDePublicacao,
        String isbn,
        int numeroDePagina,
        String idioma,
        String sinopse,
        String editora,
        String[] generos,
        String[] autores,
        String[] usuarios,
        byte[] capa,
        byte[] pdf
) {
}
