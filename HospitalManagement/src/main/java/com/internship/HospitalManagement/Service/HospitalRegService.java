package com.internship.HospitalManagement.Service;


import com.internship.HospitalManagement.Model.Hospital;
import com.internship.HospitalManagement.Repository.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HospitalRegService {
    @Autowired
    private HospitalRepo hospitalRepo;

    public Hospital findByHID(String hid) {
        return hospitalRepo.findByHid(hid);
    }

    public void save(Hospital newHospital) {
        hospitalRepo.save(newHospital);
    }

    public Hospital findByName(String hospitalName) {
        return hospitalRepo.findByHospitalName(hospitalName);
    }
}
