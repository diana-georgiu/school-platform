package com.example.school.repository;

import com.example.school.model.Enrolled;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrolledRepository extends CrudRepository<Enrolled, Integer> {

}