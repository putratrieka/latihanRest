package com.eksad.latihanrest.controller;

import java.lang.management.MemoryType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/product")
@Api(tags = "Product")
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	 
	@Autowired
	BrandDao brandDao;
	
	@ApiOperation(
			value = "API to retrive all product",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
	@GetMapping("getAll")
	public List<Product> getAll(){
		List<Product> result = new ArrayList<>();
		
		productDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@ApiOperation(
			value = "API to retrive product by id",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
	@GetMapping("getByBrandID/{brandId}")
	public List<Product> getByBrandId(@PathVariable Long brandId){
		List<Product> result = new ArrayList<Product>();
		productDao.findByBrandId(brandId).forEach(result::add);
		
		return result;		
	}
	
	@ApiOperation(
			value = "Add new product",
			notes = "Add new product to database",
			tags = "Data Manipulation API"
			)
	@PostMapping(value = "save")
	public Product save(@RequestBody Product product) {	
		Brand brand = brandDao.findById(product.getBrandId()).orElse(null);
		
		if(brand != null) {
			product.setBrand(brand);
			return productDao.save(product);
		}
			return null;
	}
	
//	=================================================================================
//	======================================Tugas======================================
//	=================================================================================
	
//	Update
	@ApiOperation(
			value = "Update product's data",
			notes = "Update product's data based on provide ID and attached data",
			tags = "Data Manipulation API"
			)
	@PutMapping(value = "update/{id}")
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
	@ApiOperation(
			value = "Delete product's data",
			notes = "Delete product's data based on provide ID and attached data",
			tags = "Data Manipulation API"
			)
	@DeleteMapping(value = "delete/{id}")
	public HashMap<String, Object> delete(@PathVariable	Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "berhasil dihapus");
		
		return result;
	}
//	Search By Name
	@ApiOperation(
			value = "API to retrive product'data by name",
			notes = "Return data with JSON format",
			tags = "Get Data API"
			)
	@GetMapping(value = "search/{name}")
	public List<Product> getByName(Product product, @PathVariable String name) {
		List<Product> result = new ArrayList<Product>();
		productDao.searchByName(name).forEach(result::add);
		System.out.println(result);
		return result;	
		
	}
}
