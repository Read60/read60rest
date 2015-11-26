package com.read60.rest.controller;

import java.util.Set;

import com.read60.rest.entity.Parent;
import com.read60.rest.entity.Student;
import com.read60.rest.repository.ParentRepository;

public class ParentController extends GenericController<Parent> implements ParentRepository {

	public ParentController() {
		super(Parent.class);
	}

	@Override
	public Set<Student> retrieveChildren(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
