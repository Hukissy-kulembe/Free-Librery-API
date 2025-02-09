package com.ao.kulembe.Free.Library.repositories;

import com.ao.kulembe.Free.Library.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Livro> {

    @Query("SELECT l FROM Livro l WHERE l.isbn = :isbn")
    Optional<Livro> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    Optional<Livro> findByTitulo(@Param("titulo") String titulo);

}
