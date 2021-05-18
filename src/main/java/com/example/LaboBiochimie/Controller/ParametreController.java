package com.example.LaboBiochimie.Controller;

import com.example.LaboBiochimie.Entities.Parametre;
import com.example.LaboBiochimie.Service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("parametre")
public class ParametreController {
    @Autowired
    private ParametreService parametreService;
    @PostMapping("/update")
    public Parametre updateParametre(@Validated @RequestBody Parametre parametre) {
        return parametreService.updateParametre(parametre);
    }
    @GetMapping("/getParametre")
    public Parametre getParametre(){
        return parametreService.getParametre();
    }
}
