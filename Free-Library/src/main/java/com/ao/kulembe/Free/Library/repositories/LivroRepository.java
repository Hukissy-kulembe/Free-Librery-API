package com.ao.kulembe.Free.Library.repositories;

import com.ao.kulembe.Free.Library.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
