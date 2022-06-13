package com.simplerest.buildblocks.repository;

import com.simplerest.buildblocks.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person,Long> {
    Person findByUsername(String username);
}
