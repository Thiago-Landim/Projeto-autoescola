package com.sci.com.repositories;

import com.sci.com.Entities.InstrutoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<InstrutoresEntity, Long> {
}
