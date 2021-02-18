package com.example.school.repository;

import com.example.school.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

}

