package com.example.userdetails.repository;

import com.example.userdetails.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    // You can add custom query methods here if needed
	
}