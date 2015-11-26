package com.read60.rest.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.read60.rest.hibernate.HibernateUtil;
import com.read60.rest.repository.GenericRepository;

public abstract class GenericController<T> implements GenericRepository<T> {
	protected static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	protected static ObjectMapper mapper = new ObjectMapper();
	protected Session session;
	Class<T> type;

	@JsonCreator
	GenericController(Class<T> type) {
		this.type = type;
	}

	@Override
	public T create(T entity) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();		
		session.close();
		return entity;
	}

	@Override
	public T retrieve(Long id) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		T entity = session.get(type, id);
		session.close();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> retrieveAll() {
		session = sessionFactory.openSession();
		List<T> entities = session.createCriteria(type).list();
		session.close();
		return entities;
	}

	@Override
	public void udpate(T entity) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.flush();
		session.clear();
		session.close();

	}

	@Override
	public void delete(T entity) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.flush();
		session.clear();
		session.close();

	}

}
