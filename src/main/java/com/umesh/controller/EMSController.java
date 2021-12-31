package com.umesh.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.umesh.entity.EmployeeRecord;
import com.umesh.entity.Role;
import com.umesh.entity.User;
import com.umesh.exception.RecordNotFoundException;
import com.umesh.services.EmployeeService;
import com.umesh.services.RoleService;
import com.umesh.services.UserService;


@RestController
@RequestMapping("/")
public class EMSController {

	@Autowired
	EmployeeService service;

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<EmployeeRecord>getAllEmployeeRecords(Model model) {
		List<EmployeeRecord> list = service.getAllEmployeeRecords();

		return list;
	}

	
	@RequestMapping(path = {"/employee/{id}" }, method = RequestMethod.GET)
	public EmployeeRecord getEmployeeRecordById(@PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			EmployeeRecord entity = service.getEmployeeRecordById(id.get());
			if (entity != null) {
				return entity;
			}
		} 
		return null;
	}
	
	@RequestMapping(path = {"/employee/search/{firstname}" }, method = RequestMethod.GET)
	public List<EmployeeRecord> getEmployeeRecordById(@PathVariable("firstname") String firstname) throws RecordNotFoundException {
		List<EmployeeRecord> entities = service.getEmployeeRecordsByFirstname(firstname);
		if (entities != null) {
			return entities;
		}
		return null;
	}
	
	@RequestMapping(path = { "/employees/sort" }, method = RequestMethod.GET)
	public List<EmployeeRecord> sortEmployeeRecord(@RequestParam(required=true, value="order") String order)
			throws RecordNotFoundException {
			List<EmployeeRecord> entities = service.sortByFistname(order);

			if (entities != null) {
				return entities;
			}
		
		return null;
	}

	@RequestMapping(path = "/deleteEmployee/{id}")
	public String deleteEmployeeRecordById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteEmployeeRecordById(id);
		return "Deleted Employee with id: " + id;
	}

	@RequestMapping(path = "/editEmployee", method = RequestMethod.POST)
	public EmployeeRecord createOrUpdateEmployeeRecord(@RequestBody EmployeeRecord employeeRecord) {
		EmployeeRecord record = service.createOrUpdateEmployeeRecord(employeeRecord);
		return record;
	}
	
	@RequestMapping(path = "/addRole", method = RequestMethod.POST)
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		Role createdRole = roleService.createOrUpdateRole(role);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		          .path("/{id}")
		          .buildAndExpand(createdRole.getId())
		          .toUri();
		return ResponseEntity.created(uri).body(createdRole);
	}
	
	@RequestMapping(path = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User createdUser = userService.createOrUpdateUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		          .path("/{id}")
		          .buildAndExpand(createdUser.getId())
		          .toUri();
		return ResponseEntity.created(uri).body(createdUser);
	}

}
