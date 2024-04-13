package com.internship.HospitalManagement.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

 @Data @Document(collection = "Roles")
public class Role {
    @Id
    private String id;

    private String name;
    private List<User> users;
   

}
