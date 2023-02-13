package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.ResponsableModel;

public interface ResponsableRepository extends JpaRepository<ResponsableModel, Long> {
    public boolean existsByCpfResponsable(String cpf);

    public ResponsableModel findByCpfResponsable(String cpf);
}
