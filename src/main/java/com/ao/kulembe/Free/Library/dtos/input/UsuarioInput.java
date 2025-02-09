package com.ao.kulembe.Free.Library.dtos.input;

import java.time.LocalDate;

public record UsuarioInput (
        String senha,
        String email,
        String nome,
        LocalDate dataNascimento
){
}
