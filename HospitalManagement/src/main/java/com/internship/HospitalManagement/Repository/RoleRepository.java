package com.internship.HospitalManagement.Repository;


import com.internship.HospitalManagement.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
