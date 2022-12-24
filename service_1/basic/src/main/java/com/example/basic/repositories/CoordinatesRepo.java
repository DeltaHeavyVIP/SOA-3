package com.example.basic.repositories;

import com.example.basic.model.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinatesRepo extends JpaRepository<Coordinates, Integer> {
    Optional<Coordinates> findFirstByXAndY(Float x, Float y);
}
