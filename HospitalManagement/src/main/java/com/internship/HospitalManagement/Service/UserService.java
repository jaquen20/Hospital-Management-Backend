package com.internship.HospitalManagement.Service;


import com.internship.HospitalManagement.Model.User;
import com.internship.HospitalManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return  userRepository.findByEmail(email);
    }


}




