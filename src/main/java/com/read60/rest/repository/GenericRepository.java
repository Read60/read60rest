package com.read60.rest.repository;

import java.util.List;

public interface GenericRepository<T> {
	
	T create(T entity);
	T retrieve(Long id);
	List<T> retrieveAll();
	void udpate(T entity);
	void delete(T entity);

}
