package com.example.LaboBiochimie.Repository;

import com.example.LaboBiochimie.Entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
