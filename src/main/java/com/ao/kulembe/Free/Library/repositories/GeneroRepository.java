package com.ao.kulembe.Free.Library.repositories;

import com.ao.kulembe.Free.Library.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    @Query("SELECT g FROM Genero g WHERE g.nome = :nome")
    Optional<Genero> findByName(@Param("nome") String nome);

}
