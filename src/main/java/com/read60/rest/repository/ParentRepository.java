package com.read60.rest.repository;

import java.util.Set;

import com.read60.rest.entity.Parent;
import com.read60.rest.entity.Student;

public interface ParentRepository extends GenericRepository<Parent> {

	Set<Student> retrieveChildren(Long id);
	
}
