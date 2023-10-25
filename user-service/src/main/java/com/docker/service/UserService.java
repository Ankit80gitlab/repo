package com.docker.service;

import com.docker.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User addUser(User user);
    public User updateUser(String emailId, User user);
    public boolean removeUser(String emailId);
}
