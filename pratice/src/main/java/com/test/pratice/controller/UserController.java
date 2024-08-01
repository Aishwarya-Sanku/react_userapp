package com.test.pratice.controller;

import com.test.pratice.exception.UserNotFoundException;
import com.test.pratice.model.User;
import com.test.pratice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
       return userRepository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserByID(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id) );


    }

//    @PutMapping("/user/")
//    User updateUser(@RequestBody User newUser){
//        return userRepository.findById(newUser.getId())
//                .map(user ->{
//                        user.setUsername(newUser.getUsername());
//                        user.setName(newUser.getName());
//                        user.setEmail(newUser.getEmail());
//                        return userRepository.save(user);
//                }).orElseThrow(()-> new UserNotFoundException(newUser.getId()));
//    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
       return userRepository.findById(id)
               .map(user ->{
                        user.setUsername(newUser.getUsername());
                        user.setName(newUser.getName());
                        user.setEmail(newUser.getEmail());
                        return userRepository.save(user);
               }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user deleted successfully !!!!";

    }

}
