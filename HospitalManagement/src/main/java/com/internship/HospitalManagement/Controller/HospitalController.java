package com.internship.HospitalManagement.Controller;


import com.internship.HospitalManagement.Model.Hospital;
import com.internship.HospitalManagement.Service.HospitalRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Controller
@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalRegService hospitalService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Hospital newHospital) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(newHospital.getHid(), newHospital.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("login successful!");
        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID or password");
        } catch (Exception e) {
            // Handle other unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }

    }


    @PostMapping("/register")
    public ResponseEntity<?> registerHospital(@RequestBody Hospital newHospital) {

        try {
            String uniqueId = generateUniqueHospitalId("HOS_");

            Hospital hospital = new Hospital();
            hospital.setHid(uniqueId);
            hospital.setHospitalName(newHospital.getHospitalName());
            hospital.setMobileNo(newHospital.getMobileNo());
            hospital.setAddress(newHospital.getAddress());
            hospital.setPassword(passwordEncoder.encode(newHospital.getPassword()));

            hospitalService.save(hospital);
            return ResponseEntity.ok("Hospital registered successfully/n with id "+ uniqueId);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering hospital");
        }
    }

    private  String generateUniqueHospitalId(String prefix) {
        String generatedId;

        do {
            generatedId = generateRandomAlphanumericString1();
        } while (hospitalService.findByHID(generatedId)!=null);

        return prefix+generatedId;
    }

    private static String generateRandomAlphanumericString1() {
        StringBuilder builder = new StringBuilder();
        String characterSet = "0123456789";
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characterSet.length());
            builder.append(characterSet.charAt(index));
        }

        return builder.toString();
    }

}
