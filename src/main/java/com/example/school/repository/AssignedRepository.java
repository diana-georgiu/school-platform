package com.example.school.repository;

import com.example.school.model.Assigned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignedRepository extends CrudRepository<Assigned, Integer> {

}
