package com.docker.controller;

import com.docker.model.User;
import com.docker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    private UserService userServ;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServ.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return new ResponseEntity<>(userServ.addUser(user), HttpStatus.OK);
    }

    @PostMapping("/update/{emailId}")
    public ResponseEntity<?> updateUser(@PathVariable String emailId, @RequestBody User user) {
        return new ResponseEntity<>(userServ.updateUser(emailId,user),HttpStatus.OK);
    }

    @DeleteMapping("/remove/{emailId}")
    public ResponseEntity<?> removeUser(@PathVariable String emailId) {
        return new ResponseEntity<>(userServ.removeUser(emailId), HttpStatus.OK);
    }

}
