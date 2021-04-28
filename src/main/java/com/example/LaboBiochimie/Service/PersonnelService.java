package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.Personnel;

import java.util.List;
import java.util.Optional;

public interface PersonnelService {
    void SavePersonnel (Personnel personnel);
    void UpdatePersonnel (Long Id, Personnel personnel);
    List<Personnel> ListPersonnel ();
    void RemovePersonnel (Long Id);
    public Optional<Personnel> findPersonnel (Long Id);
}
