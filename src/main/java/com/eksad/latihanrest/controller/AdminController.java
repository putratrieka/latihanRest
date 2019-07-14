package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.BrandDao;
import com.eksad.latihanrest.dao.ProductDao;
import com.eksad.latihanrest.dao.UsersDao;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;
import com.eksad.latihanrest.model.Users;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/admin")
@Api(tags = "Admin")
public class AdminController {
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@GetMapping("users/getAll")
	public Iterable<Users> getAll(){
		return usersDao.findAll();
	}
	
	@PostMapping("save")
	public Users save(@RequestBody Users user) {
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));// encode password
		return usersDao.save(user);
	}
	
	@RequestMapping("")
	public HashMap<String, Object> admin(){
		
		HashMap<String, Object> map  = new HashMap<String, Object>();	
		map.put("Message", "Masuk Sebagai Admin");	
		return map;
	}
	
	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		usersDao.deleteById(id);
		return "berhasil dihapus";
	}

	
	@GetMapping("brand/getAll")
	public List<Brand> getAllBrand(){
		List<Brand> result = new ArrayList<>();
		
		brandDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@PostMapping(value = "brand/save")// default method = RequestMethod.GET)
	public Brand save(@RequestBody Brand brand) {
		try {
			return brandDao.save(brand);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value = "brand/update/{id}", method = RequestMethod.PUT)
	public Brand update(@RequestBody Brand brand, @PathVariable Long id) {
		Brand brandSelected = brandDao.findById(id).orElse(null);
		System.out.println("brandSelected");
		System.out.println(brandSelected);
		if (brandSelected != null) {
			brandSelected.setName(brand.getName()); 
			brandSelected.setProductType(brand.getProductType());
			
			return brandDao.save(brandSelected);
			 
		}else {
			return null;
		}
	}
	
	@DeleteMapping(value = "brand/delete/{id}")
	public HashMap<String, Object> delete(@PathVariable	Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		
		return result;
	}
	
	@GetMapping("product/getAll")
	public List<Product> getAllProduct(){
		List<Product> result = new ArrayList<>();
		
		productDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@PostMapping("product/save")
	public Product save(@RequestBody Product product) {	
		Brand brand = brandDao.findById(product.getBrandId()).orElse(null);
		
		if(brand != null) {
			product.setBrand(brand);
			return productDao.save(product);
		}
			return null;
	}
	
	@PutMapping("product/update/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		Product productSelected = productDao.findById(id).orElse(null);
		if (productSelected != null) {
			productSelected.setName(product.getName()); 
			productSelected.setPrice(product.getPrice());
			productSelected.setBrand(product.getBrand());	
			return productDao.save(productSelected);
			 
		}else {
			return null;
		}
	}
//	Delete
	@DeleteMapping("product/delete/{id}")
	public HashMap<String, Object> deleteProduct(@PathVariable	Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		
		return result;
	}

}
