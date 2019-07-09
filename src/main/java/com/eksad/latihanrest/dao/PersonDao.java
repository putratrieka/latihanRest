package com.eksad.latihanrest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.support.PagingAndSortingTemplateVariables;

import com.eksad.latihanrest.model.Person;

@RepositoryRestResource(path = "persons")
public interface PersonDao extends PagingAndSortingRepository<Person, Long>{
	
}
