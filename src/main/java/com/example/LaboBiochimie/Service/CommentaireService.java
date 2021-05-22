package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.Commentaire;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    PatientService patientService;
    public void SaveCommentaire(Commentaire commentaire){
        commentaireRepository.save(commentaire);
    }
    public List<Commentaire> getCommentaire(){
        return commentaireRepository.findAll();
    }
    public List<String> getReponseById(Long id){
        return commentaireRepository.findById(id).get().getReponse();
    }
    public void RepondreCommentaire(Long id, String reponse){
        Commentaire commentaire1=commentaireRepository.findById(id).get();
        commentaire1.getReponse().add(reponse);
        //commentaire1.setReponse(commentaire1.getReponse());
        commentaire1.setId(id);
        commentaireRepository.save(commentaire1);
    }
    public Patient getInfoPatient(Long idCommentaire){
        Commentaire commentaire=commentaireRepository.findById(idCommentaire).get();
        Patient patient=commentaire.getPatient_commentaire();
        return patient;
    }
}
