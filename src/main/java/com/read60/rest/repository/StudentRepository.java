package com.read60.rest.repository;

import java.util.List;

import com.read60.rest.entity.Library;
import com.read60.rest.entity.ReadLog;
import com.read60.rest.entity.Student;

public interface StudentRepository extends GenericRepository<Student> {

	List<ReadLog> retrieveLog(Long id);
	Library retrieveLibrary(Long id);
}
