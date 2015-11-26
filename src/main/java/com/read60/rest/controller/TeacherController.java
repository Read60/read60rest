package com.read60.rest.controller;

import java.util.Set;

import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Teacher;
import com.read60.rest.repository.TeacherRepository;

public class TeacherController extends GenericController<Teacher> implements TeacherRepository {

	public TeacherController() {
		super(Teacher.class);
		// TODO Auto-generated constructor stu
	}

	@Override
	public Set<Student> retrieveStudents(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School retrieveSchool(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
