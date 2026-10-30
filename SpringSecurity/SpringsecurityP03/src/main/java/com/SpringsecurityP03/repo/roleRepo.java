package com.SpringsecurityP03.repo;

import com.SpringsecurityP03.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface roleRepo extends JpaRepository <Role , Long>{

    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
