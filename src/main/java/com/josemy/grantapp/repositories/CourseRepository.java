package com.josemy.grantapp.repositories;

import com.josemy.grantapp.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
