package com.ao.kulembe.Free.Library.dtos.input;

import java.time.LocalDate;

public record LivroInput(
        String titulo,
        LocalDate anoDePublicacao,
        String isbn,
        int numeroDePagina,
        String idioma,
        String sinopse,
        Long editora,
        Long[] generos,
        Long[] autores,
        Long[] usuarios,
        byte[] capa,
        byte[] pdf
) {
}
