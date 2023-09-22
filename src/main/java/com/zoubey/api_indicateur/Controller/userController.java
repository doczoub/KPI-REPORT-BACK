package com.zoubey.api_indicateur.Controller;

import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Model.User;
import com.zoubey.api_indicateur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class userController {


    @Autowired
    public UserRepository userRepository;

    //list
    @GetMapping
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //creation
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<User> getUserId(@PathVariable Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(user);
    }

    //update
    @PutMapping("{id}")
    public  ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetail){
        User updateUser = userRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updateUser.setPrenom(userDetail.getPrenom());
        updateUser.setNom(userDetail.getNom());
        updateUser.setEmail(userDetail.getEmail());
        updateUser.setNomuser(userDetail.getNomuser());
        updateUser.setMotpass(userDetail.getMotpass());

        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("l'utilisateur n'existe pas"));

        userRepository.delete(user);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
