package org.example.psrauth.repository;

import org.example.psrauth.model.entity.Pigeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigeonRepository extends JpaRepository<Pigeon,Long> {
}
