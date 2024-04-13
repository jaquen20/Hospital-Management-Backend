package com.internship.HospitalManagement.Repository;

import com.internship.HospitalManagement.Model.Hospital;
import com.internship.HospitalManagement.Model.hospitalPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalPostRepo extends MongoRepository<hospitalPost,String> {
    List<hospitalPost>findByJobDescription(String jd);

    List<hospitalPost> findBySpecialityReq(String str);

    List<hospitalPost> findByHospital(Hospital hos);
}
