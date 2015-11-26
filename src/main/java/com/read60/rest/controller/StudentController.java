package com.read60.rest.controller;

import java.util.List;

import org.hibernate.Query;

import com.read60.rest.entity.Library;
import com.read60.rest.entity.ReadLog;
import com.read60.rest.entity.Student;
import com.read60.rest.repository.StudentRepository;

public class StudentController extends GenericController<Student> implements StudentRepository {

	public StudentController() {
		super(Student.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReadLog> retrieveLog(Long id) {
		List<ReadLog> logs = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from ReadLog log WHERE log.student.id=:student_id ORDER BY log.entryDate");
		q.setParameter("student_id", id);
		logs = q.list();
		session.close();
		return logs;
	}

	@Override
	public Library retrieveLibrary(Long id) {
		Library library = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Library lib WHERE lib.student.id=:student_id");
		q.setParameter("student_id", id);
		if(!q.list().isEmpty())
			library = (Library) q.list().get(0);
		return library;
	}

}
