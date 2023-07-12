package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.model.AuthResult;
import com.nagarro.reviewSystem.model.User;

public interface UserService {
    public AuthResult auth(User user);

    public User getUserById(long id);

    public User addUser(User user);

    public long totalUsers();


}
