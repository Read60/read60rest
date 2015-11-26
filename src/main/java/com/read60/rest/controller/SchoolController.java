package com.read60.rest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Teacher;
import com.read60.rest.repository.SchoolRepository;

public class SchoolController extends GenericController<School> implements SchoolRepository{

	public SchoolController() {
		super(School.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> retrieveStudents(Long id) {
		Set<Student> students = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Student student WHERE student.school.id=:school_id ORDER BY student.lastName");
		q.setParameter("school_id", id);
		List<Student> temp = q.list();
		students = new HashSet<Student>(temp);
		session.close();
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Teacher> retrieveTeachers(Long id) {
		Set<Teacher> teachers = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Teacher teacher WHERE teacher.school.id=:school_id ORDER BY teacher.lastName");
		q.setParameter("school_id", id);
		List<Teacher> temp = q.list();
		teachers = new HashSet<Teacher>(temp);
		session.close();
		return teachers;
	}

}
