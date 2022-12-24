package com.example.basic.repositories;

import com.example.basic.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    Optional<Location> findFirstByXAndYAndZ(Integer x, Double y, Double z);
}
