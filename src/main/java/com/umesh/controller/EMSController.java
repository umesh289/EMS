package com.umesh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umesh.entity.EmployeeRecord;
import com.umesh.exception.RecordNotFoundException;
import com.umesh.services.EmployeeService;


@Controller
@RequestMapping("/")
public class EMSController {

	@Autowired
	EmployeeService service;


	@RequestMapping(path = {"/list"})
	public String getAllEmployeeRecords(Model model) {
		List<EmployeeRecord> list = service.getAllEmployeeRecords();

		model.addAttribute("students", list);
		return "list-students";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editEmployeeRecordById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			EmployeeRecord entity = service.getEmployeeRecordById(id.get());
			model.addAttribute("EmployeeRecord", entity);
		} else {
			model.addAttribute("EmployeeRecord", new EmployeeRecord());
		}
		return "add-edit-employee-record";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeRecordById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteEmployeeRecordById(id);
		return "redirect:/list/";
	}

	@RequestMapping(path = "/createEmployeeRecord", method = RequestMethod.POST)
	public String createOrUpdateEmployeeRecord(EmployeeRecord employeeRecord) {
		service.createOrUpdateEmployeeRecord(employeeRecord);
		return "redirect:/list/";
	}
	
//	@RequestMapping(value = "/403")
//	public ModelAndView accesssDenied(Principal user) {
//
//		ModelAndView model = new ModelAndView();
//
//		if (user != null) {
//			model.addObject("msg", "Hi " + user.getName() 
//			+ ", you do not have permission to access this page!");
//		} else {
//			model.addObject("msg", 
//			"You do not have permission to access this page!");
//		}
//
//		model.setViewName("403");
//		return model;
//	}
}
