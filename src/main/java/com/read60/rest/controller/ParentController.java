package com.read60.rest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.read60.rest.entity.Parent;
import com.read60.rest.entity.Student;
import com.read60.rest.repository.ParentRepository;

public class ParentController extends GenericController<Parent> implements ParentRepository {

	public ParentController() {
		super(Parent.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Student> retrieveChildren(Long id) {
		Set<Student> students = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Student student WHERE student.parent.id=:parent_id ORDER BY student.firstName");
		q.setParameter("parent_id", id);
		List<Student> temp = q.list();
		students = new HashSet<Student>(temp);
		session.close();
		return students;
	}

}
