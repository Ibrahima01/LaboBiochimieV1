package com.example.LaboBiochimie.Controller;

import com.example.LaboBiochimie.Entities.Commentaire;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("commentaire")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;
    @PostMapping("/createCommenteaire")
    public Commentaire creerCommentaire(@Validated @RequestBody Commentaire commentaire){
        commentaireService.SaveCommentaire(commentaire);
        return commentaire;
    }
    @PutMapping("/repondre/{id}")
    public String repondreCommentaire(@PathVariable Long id,@Validated @RequestBody String commentaire){
        commentaireService.RepondreCommentaire(id,commentaire);
        return commentaire;
    }
    @GetMapping("/getCommentaires")
    public List<Commentaire> getCommentaires(){return commentaireService.getCommentaire();}
    @GetMapping("/getReponseById/{id}")
    public List<String> getReponseById(@PathVariable Long id){
        return commentaireService.getReponseById(id);
    }
    @GetMapping("/getInfoPatient/{id}")
    public Patient getInfoPatient(@PathVariable Long id){ return commentaireService.getInfoPatient(id); }
}
