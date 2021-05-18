package com.example.LaboBiochimie.ServiceImplementation;

import com.example.LaboBiochimie.Entities.Admin;
import com.example.LaboBiochimie.Entities.AppUser;
import com.example.LaboBiochimie.Repository.AdminRepository;
import com.example.LaboBiochimie.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public void SaveAdmin (Admin admin){
        adminRepository.save(admin);
    }
    @Override
    public void UpdateAdmin( Long Id, Admin admin){
        Optional<Admin> admin1=adminRepository.findById(Id);
        if (admin1.isEmpty())
        {
            System.out.println("Id inexistant");
        }
        else {
            Admin newAdmin = admin1.get();
            newAdmin.setLogin(admin.getLogin());
            newAdmin.setPassword(admin.getPassword());
            newAdmin.setNom(admin.getNom());
            newAdmin.setPrenom(admin.getPrenom());
            //newAdmin.setPhotoAdmin(admin.getPhotoAdmin());
            adminRepository.save(newAdmin);
        }
    }
    @Override
    public List<Admin> ListAdmin (){
        return adminRepository.findAll();
    }
    @Override
    public void RemoveAdmin (Long Id){
        adminRepository.deleteById(Id);
    }
    @Override
    public Optional<Admin> findAdmin(Long Id){
        return  adminRepository.findById(Id);
    }

    @Override
    public Optional<Admin> findAdminByUser(AppUser user) {
        return adminRepository.findByUser(user);
    }
}
