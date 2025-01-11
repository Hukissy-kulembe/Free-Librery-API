package com.ao.kulembe.Free.Library.repositories;

import com.ao.kulembe.Free.Library.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT g FROM Autor g WHERE g.nome = :nome")
    Optional<Autor> findByName(@Param(value = "nome") String nome);

}
