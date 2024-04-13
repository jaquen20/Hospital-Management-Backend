package com.internship.HospitalManagement.DTO;

import lombok.Data;

@Data
public class DoctorDTO {
    private  String id;
    private String name;
    private String education;
    private String mobileNo;
    private int age;
    private String address;
    private String specialist;
    private int experience;
}
