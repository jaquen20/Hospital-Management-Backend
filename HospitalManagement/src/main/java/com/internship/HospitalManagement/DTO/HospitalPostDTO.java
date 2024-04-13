package com.internship.HospitalManagement.DTO;

import lombok.Data;

@Data
public class HospitalPostDTO {

    private String id;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private String qualificationReq;
    private String specialityReq;
    private String salary;
    private int experienceReq;
}
