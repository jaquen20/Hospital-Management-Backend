package com.internship.HospitalManagement.Controller;



import com.internship.HospitalManagement.DTO.DoctorDTO;
import com.internship.HospitalManagement.DTO.HospitalPostDTO;
import com.internship.HospitalManagement.Model.Candidate;
import com.internship.HospitalManagement.Model.Hospital;
import com.internship.HospitalManagement.Model.hospitalPost;
import com.internship.HospitalManagement.Service.CandidateService;
import com.internship.HospitalManagement.Service.HospitalRegService;
import com.internship.HospitalManagement.Service.ServiceHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@Controller
@RequestMapping("/hos")
public class hospitalPostController {
    @Autowired
    CandidateService candidateService;
    @Autowired
    HospitalRegService RegService;


    @Autowired
    ServiceHospital hospitalService;

    @GetMapping("/getPostList/candidate/{id}")
    public ResponseEntity<List<DoctorDTO>> candidateList2(@PathVariable String id) {
        try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String auth = authentication.getName();
        Optional<hospitalPost> hospitalPostOptional = hospitalService.findByPostId(id);
        if (hospitalPostOptional.isPresent()) {
            hospitalPost hospitalPost = hospitalPostOptional.get();
            List<Candidate> candidateList = candidateService.findCandidateByPost(hospitalPost);

            List<DoctorDTO> doctorDTOList = candidateList.stream()
                    .map(candidate -> {
                        DoctorDTO dto = new DoctorDTO();
                        dto.setId(candidate.getDoc().getId());
                        dto.setName(candidate.getDoc().getName());
                        dto.setEducation(candidate.getDoc().getEducation());
                        dto.setMobileNo(candidate.getDoc().getMobileNo());
                        dto.setAge(candidate.getDoc().getAge());
                        dto.setAddress(candidate.getDoc().getAddress());
                        dto.setSpecialist(candidate.getDoc().getSpecialist());
                        dto.setExperience(candidate.getDoc().getExperience());
                        return dto;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(doctorDTOList);
        }
        return ResponseEntity.notFound().build();
    } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getPostList")
    ResponseEntity<List<HospitalPostDTO>> getList2() {
        try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String auth = authentication.getName();
        Hospital hos = RegService.findByHID(auth);
        List<hospitalPost> hospitalPostList = hospitalService.findByHospital(hos);

        List<HospitalPostDTO> hospitalPostDTOList = hospitalPostList.stream()
                .map(hospitalPost -> {
                    HospitalPostDTO dto = new HospitalPostDTO();
                    dto.setId(hospitalPost.getId());
                    dto.setJobTitle(hospitalPost.getJobTitle());
                    dto.setJobDescription(hospitalPost.getJobDescription());
                    dto.setLocation(hospitalPost.getLocation());
                    dto.setQualificationReq(hospitalPost.getQualificationReq());
                    dto.setSpecialityReq(hospitalPost.getSpecialityReq());
                    dto.setSalary(hospitalPost.getSalary());
                    dto.setExperienceReq(hospitalPost.getExperienceReq());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(hospitalPostDTOList);
    }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/post1Job")
    public ResponseEntity<String> jobPost(@RequestBody HospitalPostDTO hospitalPostDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String auth = authentication.getName();

            hospitalPost hospitalPost1 = new hospitalPost();
            hospitalPost1.setJobTitle(hospitalPostDTO.getJobTitle());
            hospitalPost1.setJobDescription(hospitalPostDTO.getJobDescription());
            hospitalPost1.setLocation(hospitalPostDTO.getLocation());
            hospitalPost1.setQualificationReq(hospitalPostDTO.getQualificationReq());
            hospitalPost1.setSpecialityReq(hospitalPostDTO.getSpecialityReq());
            hospitalPost1.setSalary(hospitalPostDTO.getSalary());
            hospitalPost1.setExperienceReq(hospitalPostDTO.getExperienceReq());

            hospitalService.postJob(hospitalPost1, auth);

            return ResponseEntity.ok("SUCCESSFULLY ADDED");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add hospital post. Please try again later.");
        }
    }



}
