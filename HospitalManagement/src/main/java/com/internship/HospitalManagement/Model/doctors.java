package com.internship.HospitalManagement.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @Document(collection = "doctors")
public class doctors {
    @Id
   private String id;
   private String name;
   private String education;
   private String mobileNo;
   private int age;
   private String address;
   private String specialist;
   private int experience;

   private User users;
}
