package com.internship.HospitalManagement.Repository;



import com.internship.HospitalManagement.Model.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepo extends MongoRepository<Hospital,String> {

    Hospital findByHid(String email);

    Hospital findByHospitalName(String hospitalName);
}
