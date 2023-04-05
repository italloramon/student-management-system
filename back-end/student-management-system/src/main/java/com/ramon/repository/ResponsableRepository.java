package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.Responsable;

public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    public boolean existsByCpfResponsable(String cpf);

    public Responsable findByCpfResponsable(String cpf);
}
