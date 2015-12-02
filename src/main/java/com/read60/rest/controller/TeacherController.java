package com.read60.rest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Teacher;
import com.read60.rest.repository.TeacherRepository;

public class TeacherController extends GenericController<Teacher> implements TeacherRepository {

	public TeacherController() {
		super(Teacher.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> retrieveStudents(Long id) {
		Set<Student> students = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Student student WHERE student.teacher.id=:teacher_id ORDER BY student.lastName");
		q.setParameter("teacher_id", id);
		List<Student> temp = q.list();
		students = new HashSet<Student>(temp);
		session.close();
		return students;
	}

	@Override
	public School retrieveSchool(Long id) {
		School school = null;
		Teacher teacher = retrieve(id);
		school = teacher.getSchool();
		return school;
	}
}
