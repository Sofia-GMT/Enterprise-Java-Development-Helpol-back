package com.ironhack.primersservice.repository;

import com.ironhack.primersservice.models.Primers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimersRepository extends JpaRepository<Primers, Integer> {
}
