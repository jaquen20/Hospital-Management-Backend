package com.internship.HospitalManagement.Model;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @Document(collection = "Hospital_post")
public class hospitalPost {

   @Id
   private String id;

   private String jobTitle;
   private String jobDescription;
   private String location;
   private String qualificationReq;
   private String specialityReq;
   private String salary;
   private int experienceReq;

   private Hospital hospital;
}
