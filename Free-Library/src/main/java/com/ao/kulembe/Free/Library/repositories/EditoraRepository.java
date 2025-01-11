package com.ao.kulembe.Free.Library.repositories;

import com.ao.kulembe.Free.Library.models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    @Query("SELECT e FROM Editora e WHERE e.nome = :nome")
    Optional<Editora> findByName(@Param("nome") String nome);

}
