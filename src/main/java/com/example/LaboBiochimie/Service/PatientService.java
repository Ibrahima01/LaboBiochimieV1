package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.exception.UserException;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    void SavePatient(Patient patient) throws UserException;
    void UpdatePatient(Long Id, Patient patient);
    List<Patient> ListPatients();
    void RemovePatient(Long Id);
    public Optional<Patient> findPatient(Long Id);
    public Optional<Patient> findPatientByUser(AppUser user);
}
