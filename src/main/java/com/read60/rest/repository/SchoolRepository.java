package com.read60.rest.repository;

import java.util.Set;

import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Teacher;

public interface SchoolRepository extends GenericRepository<School> {

	Set<Student> retrieveStudents(Long id);
	Set<Teacher> retrieveTeachers(Long id);
	
}
