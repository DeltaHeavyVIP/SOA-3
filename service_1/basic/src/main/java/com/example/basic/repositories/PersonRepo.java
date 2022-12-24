package com.example.basic.repositories;

import com.example.basic.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, String> {
    Optional<Person> findFirstByPassportID(String passportID);

    @Query(value = "select distinct p.name from person as p", nativeQuery = true)
    ArrayList<String> findAllPersonUniqueName();
}
