package com.example.LaboBiochimie.Service;

import com.example.LaboBiochimie.Entities.Parametre;
import com.example.LaboBiochimie.Repository.ParametreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametreService {
    @Autowired
    ParametreRepository parametreRepository;
    public void SaveParametre(Parametre parametre){
        parametreRepository.save(parametre);
    }
    public Parametre getParametre(){
        List<Parametre> parametres = parametreRepository.findAll();
        return parametres != null && !parametres.isEmpty() ? parametres.get(0): null;
    }
    public Parametre updateParametre(Parametre parametre){
        Parametre p = getParametre();
        Long id = p.getId();
        p = parametre;
        p.setId(id);
        return parametreRepository.save(p);
    }
}
