package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.CONSTANTS;
import com.nagarro.reviewSystem.model.AuthResult;
import com.nagarro.reviewSystem.model.Message;
import com.nagarro.reviewSystem.model.User;
import com.nagarro.reviewSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CONSTANTS constants;

    @Override
    public AuthResult auth(User user) {
        AuthResult res = new AuthResult();
        Message message = new Message();
        List<User> authenticatedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(authenticatedUser.size() == 1) {
            message.setMessage("Authentication Successful");
            message.setStatus(constants.SUCCESS);
            res.setUser(authenticatedUser.get(0));
            res.setMessage(message);
        } else {
            message.setMessage("Authentication Failed");
            message.setStatus(constants.FAILURE);
            res.setUser(null);
            res.setMessage(message);
        }
        return res;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public long totalUsers() {
        return userRepository.count();
    }
}
