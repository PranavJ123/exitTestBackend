package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.CONSTANTS;
import com.nagarro.reviewSystem.model.Admin;
import com.nagarro.reviewSystem.model.AdminAuthResult;
import com.nagarro.reviewSystem.model.Message;
import com.nagarro.reviewSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CONSTANTS constants;
    @Override
    public AdminAuthResult auth(Admin admin) {
        AdminAuthResult res = new AdminAuthResult();
        System.out.println(admin);
        try {
            Admin result = adminRepository.findByEmailAndPassword(admin.getEmail(), admin.getPassword()).get(0);
            res.setAdmin(result);
            res.setStatus(constants.SUCCESS);
        } catch (Exception e){
            res.setStatus(constants.FAILURE);
        }
        return res;
    }

    @Override
    public Admin getAdmin(long id) {
        return adminRepository.getReferenceById(id);
    }
}
