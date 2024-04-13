package com.internship.HospitalManagement.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @Document(collection = "Hospital")
public class Hospital {
   @Id
    private String id;

    private String hid;
    private String password;
    private String hospitalName;
    private String mobileNo;
    private String address;


}
