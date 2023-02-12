package com.ramon.repository;

import com.ramon.model.courses.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long>{
    
}
