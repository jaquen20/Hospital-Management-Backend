package com.internship.HospitalManagement.Services;


import com.internship.HospitalManagement.Model.Hospital;
import com.internship.HospitalManagement.Model.Role;
import com.internship.HospitalManagement.Model.User;
import com.internship.HospitalManagement.Repository.HospitalRepo;
import com.internship.HospitalManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service

public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HospitalRepo hospitalRepo;

    public CustomUserService(UserRepository userRepository, HospitalRepo hospitalInfo) {
        this.userRepository = userRepository;
        this.hospitalRepo = hospitalInfo;
    }

    @Override
    public UserDetails loadUserByUsername(String UniqueId) throws UsernameNotFoundException {

        if (UniqueId.contains("HOS")) {
            Hospital hospital = hospitalRepo.findByHid(UniqueId);
            if (hospital != null) {
                return new org.springframework.security.core.userdetails.User(hospital.getHid(),
                        hospital.getPassword(),
                        Collections.emptyList());
            } else {
                throw new UsernameNotFoundException("Invalid hospital id or password.");
            }

        } else {
            User user = userRepository.findByEmail(UniqueId);

            if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        mapRolesToAuthorities(user.getRoles()));
            } else {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
        }
    }






    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
