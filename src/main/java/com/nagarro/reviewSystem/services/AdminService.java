package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.model.Admin;
import com.nagarro.reviewSystem.model.AdminAuthResult;
import com.nagarro.reviewSystem.model.Message;

import java.util.List;

public interface AdminService {
    public AdminAuthResult auth(Admin admin);
    public Admin getAdmin(long id);
}
