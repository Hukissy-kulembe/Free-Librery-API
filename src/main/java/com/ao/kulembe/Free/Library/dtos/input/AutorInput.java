package com.ao.kulembe.Free.Library.dtos.input;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AutorInput(

        @NotBlank(message = "O nome não pode estar vazio.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @NotBlank(message = "A nacionalidade não pode estar vazia.")
        String nacionalidade,

        @NotNull(message = "A data de nascimento não pode ser nula.")
        @Past(message = "A data de nascimento deve estar no passado.")
        @Min(value = 1920)
        LocalDate dataNascimento,

        @NotBlank(message = "A biografia não pode estar vazia.")
        @Size(max = 5000, message = "A biografia deve ter no máximo 5000 caracteres.")
        String biografia
) {
}
