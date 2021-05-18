package com.example.LaboBiochimie.Repository;

import com.example.LaboBiochimie.Entities.Admin;
import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Optional<Admin> findByUser(AppUser user);

}
