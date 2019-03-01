package com.josemy.grantapp.repositories;

import com.josemy.grantapp.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
