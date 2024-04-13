package com.internship.HospitalManagement.Repository;


import com.internship.HospitalManagement.Model.Candidate;
import com.internship.HospitalManagement.Model.hospitalPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepo extends MongoRepository<Candidate,Long> {


    List<Candidate> findByHospitalPost(hospitalPost hp);


//    List<Candidate> findByHospitalPost(hospitalPost hs);
}
