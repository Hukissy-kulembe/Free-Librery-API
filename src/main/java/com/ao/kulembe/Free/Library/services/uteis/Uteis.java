package com.ao.kulembe.Free.Library.services.uteis;

import com.ao.kulembe.Free.Library.models.Autor;
import com.ao.kulembe.Free.Library.models.Genero;
import com.ao.kulembe.Free.Library.models.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public class Uteis {

    public static Set<String> autores(Set<Autor> autores) {
        return autores.stream()
                .map(x -> x.getNome()).collect(Collectors.toSet());
    }

    public static Set<String> generos(Set<Genero> generos) {
        return generos.stream()
                .map(x -> x.getNome()).collect(Collectors.toSet());
    }

    public static Set<String> usuarios(Set<Usuario> usuarios) {
        return usuarios.stream()
                .map(x -> x.getNome()).collect(Collectors.toSet());
    }
}
