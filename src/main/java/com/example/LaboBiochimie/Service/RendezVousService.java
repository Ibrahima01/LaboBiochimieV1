package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.Admin;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Entities.Personnel;
import com.example.LaboBiochimie.Entities.RendezVous;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RendezVousService {
    void SaveRDV(RendezVous rendez_vous);
    RendezVous PatientPrendRDV(Patient patient);
    RendezVous PatientPrendRDVById(Long id);
    void PersonnelPrendRDV(Personnel personnel);
    public RendezVous AdminPrendRDVById(Long Id);
    void UpdateRDV (Long Id, RendezVous rendez_vous);
    List<RendezVous> ListRDV();
    void RemoveRDV (Long id);
    public Optional<RendezVous> findRDV(Long Id);
    List<RendezVous>findByDate(LocalDateTime date);
    List<LocalDateTime> findRDVByIdPatient(Long Id);
    List<RendezVous> historiqueRDVPatient(Long Id);
    RendezVous findRDVByPatient(Long id);

}
