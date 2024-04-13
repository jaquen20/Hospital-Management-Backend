package com.internship.HospitalManagement.Repository;


import com.internship.HospitalManagement.Model.User;
import com.internship.HospitalManagement.Model.doctors;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepo extends MongoRepository<doctors,String> {

    doctors findByUsers(User usr);
}
