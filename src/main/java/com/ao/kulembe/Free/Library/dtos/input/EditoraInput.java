package com.ao.kulembe.Free.Library.dtos.input;

import jakarta.validation.constraints.NotBlank;

public record EditoraInput (

        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,
        @NotBlank(message = "Nif não pode estar vazio.")
        String nif,
        @NotBlank(message = "Endereço não pode estar vazio.")
        String endereco
){
}
