package com.example.LaboBiochimie.Controller;

import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Entities.RendezVous;
import com.example.LaboBiochimie.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("RDV")
public class RendezVousController {
    @Autowired
    RendezVousService rendezVvousService;
    @PostMapping("/creerRDV")
    public RendezVous addNewRendez_vous(@Validated @RequestBody RendezVous rdv1){
        rendezVvousService.SaveRDV(rdv1);
        return rdv1;
    }
    @PostMapping("/patientPrendRDV")
    public RendezVous patientPrendRDV(@Validated @RequestBody Patient patient){
        return rendezVvousService.PatientPrendRDV(patient);
    }
    @PostMapping("/patientPrendRDVById/{id}")
    public RendezVous patientPrendRDVById(@PathVariable Long id){
        return rendezVvousService.PatientPrendRDVById(id);
    }
    @PutMapping("/RDV/{id}")
    public void modifyRDV(@PathVariable Long id, @Validated @RequestBody RendezVous rdv1){
        rendezVvousService.UpdateRDV(id, rdv1);
    }
    @GetMapping("/all")
    public List<RendezVous> listRDV(){return rendezVvousService.ListRDV();}
    @GetMapping("/RDV/{id}")
    public Optional<RendezVous> getRDVById(@PathVariable Long Id){return rendezVvousService.findRDV(Id);}
    @GetMapping(value = "/findRDVByIdPatient/{id}")
    public List<LocalDateTime> getRDVByIdPatient(@PathVariable (value="id") String id){return rendezVvousService.findRDVByIdPatient(Long.parseLong(id));}
    @DeleteMapping("/RDV/{id}")
    public void deleteRDV(@PathVariable Long id){
        rendezVvousService.RemoveRDV(id);
    }
}
