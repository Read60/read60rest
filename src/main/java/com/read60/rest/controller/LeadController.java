package com.read60.rest.controller;

import com.read60.rest.entity.Lead;
import com.read60.rest.repository.LeadRepository;

public class LeadController extends GenericController<Lead> implements LeadRepository {

	public LeadController() {
		super(Lead.class);
	}
	
}
