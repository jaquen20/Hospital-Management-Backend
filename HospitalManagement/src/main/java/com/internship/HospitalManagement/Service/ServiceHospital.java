package com.internship.HospitalManagement.Service;

import com.internship.HospitalManagement.Model.Hospital;
import com.internship.HospitalManagement.Model.doctors;
import com.internship.HospitalManagement.Model.hospitalPost;
import com.internship.HospitalManagement.Repository.DoctorRepo;
import com.internship.HospitalManagement.Repository.HospitalPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceHospital {
    @Autowired
    HospitalPostRepo hospitalPostRepo;
    @Autowired
    HospitalRegService hospitalRegService;
    @Autowired
    DoctorRepo doctorRepo;

    public List<doctors> getData() {
        return doctorRepo.findAll() ;
    }

    public void postJob(hospitalPost hospitalPost, String auth) {
        Hospital hs=hospitalRegService.findByHID(auth);
        hospitalPost.setHospital(hs);

        hospitalPostRepo.save(hospitalPost);
    }



    public List<hospitalPost> findBySpecialist(String str) {
        return hospitalPostRepo.findBySpecialityReq(str);
    }

    public Optional<hospitalPost> find(String id) {
        return hospitalPostRepo.findById(id);
    }



    public List<hospitalPost> findByHospital(Hospital hos) {
        return hospitalPostRepo.findByHospital(hos);
    }

    public Optional<hospitalPost> findByPostId(String id) {
        return hospitalPostRepo.findById(id);
    }


}
