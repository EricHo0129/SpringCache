package com.eric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eric.dao.EmployeDAO;
import com.eric.model.EmployeModel;

@RestController
public class EmployeController {

	@Autowired
	private EmployeDAO employeDAO;
	
	@GetMapping("/getAllEmploye")
	public ResponseEntity<List<EmployeModel>> getAll() throws Exception {
		return new ResponseEntity<List<EmployeModel>>(employeDAO.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/employe/{id}")
	public ResponseEntity<EmployeModel> getOne(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<EmployeModel>(employeDAO.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping(value="/create")
	public ResponseEntity<Boolean> create(@RequestBody EmployeModel employe) throws Exception {
		employeDAO.create(employe);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PostMapping(value="/update")
	public ResponseEntity<EmployeModel> update(@RequestBody EmployeModel employe) throws Exception {
		return new ResponseEntity<EmployeModel>(employeDAO.update(employe), HttpStatus.OK);
	}
	
	@DeleteMapping("/employe/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<Boolean>(employeDAO.delete(id), HttpStatus.OK);
	}
}
