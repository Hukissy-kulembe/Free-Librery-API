package com.ao.kulembe.Free.Library.services.specifications;

import com.ao.kulembe.Free.Library.models.Livro;
import com.ao.kulembe.Free.Library.repositories.LivroRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class LivroSpecification {

    private static final LivroRepository livroRepository = null;


    public static Specification<Livro> livroDtoOutputSpecification (String titulo) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(titulo))
                return null;

            return builder.like(root.get("titulo"), "%" + titulo + "%");
        };
    }

    public static Specification<Livro> autorLivroEquals(String autor) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(autor))
                return null;

            return builder
                    .like(root.join("autores")
                            .get("autorNome"), "%"+ autor+"%");
        };

    }

    public static Specification<Livro> generoLivroEquals(String genero) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(genero)) return null;
            return builder
                    .like(root.join("generos")
                            .get("generoNome"), "%"+genero+"%");
        };
    }

    public static Specification<Livro> editoraLivroEquals(String editora) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(editora))
                return null;
            return builder.like(root.join("editora")
                    .get("editoraNome"), "%"+editora+"%");
        };
    }

}

