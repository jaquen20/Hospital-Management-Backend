package com.internship.HospitalManagement.Service;


import com.internship.HospitalManagement.Model.User;
import com.internship.HospitalManagement.Model.doctors;
import com.internship.HospitalManagement.Repository.DoctorRepo;
import com.internship.HospitalManagement.Repository.UserRepository;
import com.internship.HospitalManagement.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorsService {
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    UserRepository userRepo;

    public Optional<doctors> getMyProfile(String email) {
        User usr=userRepo.findByEmail(email);
        if (usr!=null){
            return Optional.ofNullable(doctorRepo.findByUsers(usr));
        }
        else{
            return Optional.empty();
        }

    }

    public void saveDoctorProfile(doctors medicalProfessional, String auth) {
        User usr=userRepo.findByEmail(auth);
        if (usr!=null){
            doctors mp=new doctors();
            mp.setName(medicalProfessional.getName());
            mp.setAge(medicalProfessional.getAge());
            mp.setAddress(medicalProfessional.getAddress());
            mp.setExperience(medicalProfessional.getExperience());
            mp.setEducation(medicalProfessional.getEducation());
            mp.setSpecialist(medicalProfessional.getSpecialist());
            mp.setMobileNo(medicalProfessional.getMobileNo());
            mp.setUsers(usr);
            doctorRepo.save(mp);
        }else {
            throw new UserNotFoundException("User with email " + auth + " not found");
        }


    }

    public void updateData(String auth, doctors mdp) {
        User usr=userRepo.findByEmail(auth);
        if (usr!=null){
            doctors doc=new doctors();

        }

    }
    public doctors findData(String email) {
        User usr=userRepo.findByEmail(email);
            return doctorRepo.findByUsers(usr);

    }


}
