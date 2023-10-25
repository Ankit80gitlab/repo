package com.docker.service;

import com.docker.model.User;
import com.docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    //RegisterProxy regPro;

    @Override
    public List<User> getAllUser() {
        System.out.println("fetching all users");
        return userRepo.findAll();
    }

    @Override
    public boolean checkUser(String email, String password) {
        if(userRepo.findById(email).isPresent()){
            User ch = userRepo.findById(email).get();

            if(ch.getPassword().equals(password)){
                ch.setPassword("");
                System.out.println("user is logged in");
                return true;
            }else{
                System.out.println("wrong password");
                return false;
            }
        }
        else{
            System.out.println("invalid credentials");
            return false;
        }
    }

    @Override
    public User addUser(User user) {
        if(userRepo.findById(user.getEmail()).isPresent()){
            System.out.println("User is already registered yes");
            return null;
        }
        else{
            System.out.println("Feign client called");
//            RegisterDTO regDto = new RegisterDTO();
//            regDto.setEmail(user.getEmail());
//            regDto.setName(user.getName());
//            regDto.setPassword(user.getPassword());
//            regDto.setRole(user.getRole());
//            ResponseEntity<?> result= regPro.registerThroughUser(regDto);
            System.out.println("Communication established");
            return userRepo.save(user);
        }
    }

    @Override
    public User updateUser(String emailId, User user) {
        if(userRepo.findById(emailId).isPresent()){
            System.out.println("user found with email "+emailId);
            User updateUser = userRepo.findById(emailId).get();
            updateUser.setName(user.getName());
            updateUser.setPassword(user.getPassword());
            updateUser.setRole(user.getRole());
            userRepo.save(updateUser);
            return updateUser;
        }
        else{
            System.out.println("user is invalid");
            return null;
        }
    }

    @Override
    public boolean removeUser(String emailId) {
        if(userRepo.findById(emailId).isPresent()){
            userRepo.deleteById(emailId);
            return true;
        }
        else{
            System.out.println("invalid user");
            return false;
        }
    }
}
