package com.nagarro.reviewSystem.repository;

import com.nagarro.reviewSystem.model.Admin;
import com.nagarro.reviewSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public List<Admin> findByEmailAndPassword(String email, String password);

}
