package com.example.LaboBiochimie.ServiceImplementation;

import com.example.LaboBiochimie.Entities.Personnel;
import com.example.LaboBiochimie.Repository.PersonnelRepository;
import com.example.LaboBiochimie.Service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    PersonnelRepository personnelRepository;
    public void SavePersonnel (Personnel personnel){
        personnelRepository.save(personnel);
    }
    @Override
    public void UpdatePersonnel (Long Id, Personnel personnel){
        Optional<Personnel> personnel1=personnelRepository.findById(Id);
        if (personnel1.isEmpty())
        {
            System.out.println("Id inexistant");
        }
        else {
            Personnel newPersonnel = personnel1.get();
            newPersonnel.setNom_personnel(personnel.getNom_personnel());
            newPersonnel.setPrenom_personnel(personnel.getPrenom_personnel());
            newPersonnel.setGouvernorat(personnel.getGouvernorat());
            //newPersonnel.setPhotoPersonnel(personnel.getPhotoPersonnel());
            personnelRepository.save(newPersonnel);
        }
    }
    @Override
    public List<Personnel> ListPersonnel(){
        return personnelRepository.findAll();
    }
    @Override
    public void RemovePersonnel(Long Id){
        personnelRepository.deleteById(Id);
    }
    @Override
    public Optional<Personnel> findPersonnel(Long Id){
        return personnelRepository.findById(Id);
    }
}
