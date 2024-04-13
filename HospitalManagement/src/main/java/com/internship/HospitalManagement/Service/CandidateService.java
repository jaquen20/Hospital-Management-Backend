package com.internship.HospitalManagement.Service;


import com.internship.HospitalManagement.Model.Candidate;
import com.internship.HospitalManagement.Model.hospitalPost;
import com.internship.HospitalManagement.Repository.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;
    public void saveData(Candidate candidate1) {
        candidateRepo.save(candidate1);

    }


    public List<Candidate> findCandidateByPost(hospitalPost HP) {
        return candidateRepo.findByHospitalPost(HP);
    }


}
