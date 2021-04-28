package com.example.LaboBiochimie.Controller;

import com.example.LaboBiochimie.Entities.Personnel;
import com.example.LaboBiochimie.Service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("personnel")
public class PersonnelController {
    @Autowired
    PersonnelService personnelService;
    @PostMapping("/newPersonnel")
    public void addNewPersonnel(@Validated @RequestBody Personnel p1){
        personnelService.SavePersonnel(p1);
    }
    @PutMapping("/personnel/{id}")
    public void modifyPersonnel(@PathVariable Long id, @Validated @RequestBody Personnel p1){
        personnelService.UpdatePersonnel(id, p1);
    }
    @GetMapping("/all")
    public List<Personnel> listPersonnel(){return personnelService.ListPersonnel();}
    @GetMapping("/personnel/{id}")
    public Optional<Personnel> getPersonnelById(@PathVariable Long Id){return personnelService.findPersonnel(Id);}
    @DeleteMapping("/patient/{id}")
    public void deletePersonnel(@PathVariable Long id){
        personnelService.RemovePersonnel(id);
    }
}
