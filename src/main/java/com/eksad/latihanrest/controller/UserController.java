package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.dao.ProductDao;
import com.eksad.latihanrest.dao.UsersDao;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;
import com.eksad.latihanrest.model.Users;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("api/user")
@Api(tags = "User")
public class UserController {
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UsersDao usersDao;
	
	
	@RequestMapping("")
	public HashMap<String, Object> user(){
		
		HashMap<String, Object> map  = new HashMap<String, Object>();	
		map.put("Message", "Masuk Sebagai User");	
		return map;
	}
	
	@PostMapping("users/save")
	public Users save(@RequestBody Users user) {
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));// encode password
		return usersDao.save(user);
	}
	
	@GetMapping("brand/getAll")
	public List<Brand> getAllBrand(){
		List<Brand> result = new ArrayList<>();
		
		brandDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@GetMapping("brand/getOne/{id}")
	public Brand getOne(@PathVariable Long id) {
		
		return brandDao.findById(id).orElse(null);
	}
	
	@GetMapping("product/search/{name}")
	public List<Product> getByName(Product product, @PathVariable String name) {
		List<Product> result = new ArrayList<Product>();
		productDao.searchByName(name).forEach(result::add);
		System.out.println(result);
		return result;		
	}
	
	@GetMapping("product/getAll")
	public List<Product> getAllProduct(){
		List<Product> result = new ArrayList<>();
		
		productDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@GetMapping("product/getByBrandID/{brandId}")
	public List<Product> getByBrandId(@PathVariable Long brandId){
		List<Product> result = new ArrayList<Product>();
		productDao.findByBrandId(brandId).forEach(result::add);
		
		return result;		
	}

}
