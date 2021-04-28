package com.example.LaboBiochimie.Controller;

import com.example.LaboBiochimie.Entities.Admin;
import com.example.LaboBiochimie.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/newAdmin")
    public void addNewAdmin(@Validated @RequestBody Admin a1){
        adminService.SaveAdmin(a1);
    }
    @PutMapping("/admin/{id}")
    public void modifyAdmin(@PathVariable Long id, @Validated @RequestBody Admin a1){
        adminService.UpdateAdmin(id, a1);
    }
    @GetMapping("/all")
    public List<Admin> listAdmin(){return adminService.ListAdmin();}
    @GetMapping("/admin/{id}")
    public Optional<Admin> getAdminById(@PathVariable Long Id){return adminService.findAdmin(Id);}
    @DeleteMapping("/admin/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.RemoveAdmin(id);
    }
}
