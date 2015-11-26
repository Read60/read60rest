package com.read60.rest.repository;

import com.read60.rest.entity.Teacher;

import java.util.Set;

import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;

public interface TeacherRepository extends GenericRepository<Teacher>{

	Set<Student> retrieveStudents(Long id);
	School retrieveSchool(Long id);
	
}
