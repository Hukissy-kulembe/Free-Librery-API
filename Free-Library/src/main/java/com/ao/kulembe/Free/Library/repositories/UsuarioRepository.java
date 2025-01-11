package com.ao.kulembe.Free.Library.repositories;

import com.ao.kulembe.Free.Library.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
