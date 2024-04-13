package com.internship.HospitalManagement.Controller;


import com.internship.HospitalManagement.Model.Candidate;
import com.internship.HospitalManagement.Model.doctors;
import com.internship.HospitalManagement.Model.hospitalPost;
import com.internship.HospitalManagement.Service.CandidateService;
import com.internship.HospitalManagement.Service.DoctorsService;
import com.internship.HospitalManagement.Service.ServiceHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@Controller
@RestController
@RequestMapping("/doc")
public class doctorController {
    @Autowired
   private ServiceHospital hospitalService;
    @Autowired
    private DoctorsService doctorsService;
    @Autowired
    private CandidateService candidateService;

       @GetMapping("/home")
       ResponseEntity<List<hospitalPost>> prof() {
           try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String auth = authentication.getName();
        Optional<doctors> md = doctorsService.getMyProfile(auth);
        if (md.isPresent()) {
            doctors newMd = md.get();
            String str2 = newMd.getSpecialist();
            System.out.println(str2);
            List<hospitalPost> mdq = hospitalService.findBySpecialist(str2);
            return ResponseEntity.ok(mdq);

        } else return ResponseEntity.noContent().build();
    }catch(Exception e){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
       }


    @PostMapping("/createProfile")
    public ResponseEntity<String> createDoctorProfile(@RequestBody doctors medicalProfessional) {
        try {

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = userDetails.getUsername();

            doctorsService.saveDoctorProfile(medicalProfessional, userId);
            return ResponseEntity.ok("Profile created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error creating profile: " + e.getMessage());
        }
    }

    @PostMapping("/apply/{id}")
    ResponseEntity<String> apply(@PathVariable String id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String auth = authentication.getName();

            Optional<hospitalPost> hp = hospitalService.find(id);
            doctors md = doctorsService.findData(auth);
            if (hp.isPresent()) {
                Candidate candidate1 = new Candidate();
                hospitalPost hp1 = hp.get();
                candidate1.setDoc(md);
                candidate1.setHospitalPost(hp1);
                candidateService.saveData(candidate1);
                return ResponseEntity.ok("applied successfully");
            }


            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required");
        }

    }

}
