package com.eksad.latihanrest.dao;

import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Users;

public interface UsersDao extends CrudRepository<Users, Integer>{
	public Users findByUsername(String username);
}
