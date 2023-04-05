package com.ramon.repository;

import com.ramon.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
    
}
