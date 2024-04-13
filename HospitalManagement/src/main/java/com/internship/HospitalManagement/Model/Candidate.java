package com.internship.HospitalManagement.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @Document(collection = "Candidate")
public class Candidate {
    @Id
    private String id;

   private hospitalPost hospitalPost;

    private doctors doc;
}
