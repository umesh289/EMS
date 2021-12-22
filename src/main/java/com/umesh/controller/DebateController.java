package com.umesh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umesh.entity.DebateRecord;
import com.umesh.exception.RecordNotFoundException;
import com.umesh.services.DebateService;


@Controller
@RequestMapping("/")
public class DebateController {

	@Autowired
	DebateService service;


	@RequestMapping(path = {"/list"})
	public String getAllDebateRecords(Model model) {
		List<DebateRecord> list = service.getAllDebateRecords();

		model.addAttribute("students", list);
		return "list-students";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editDebateRecordById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			DebateRecord entity = service.getDebateRecordById(id.get());
			model.addAttribute("debateRecord", entity);
		} else {
			model.addAttribute("debateRecord", new DebateRecord());
		}
		return "add-edit-debate-record";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteDebateRecordById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteDebateRecordById(id);
		return "redirect:/list/";
	}

	@RequestMapping(path = "/createDebateRecord", method = RequestMethod.POST)
	public String createOrUpdateDebateRecord(DebateRecord debateRecord) {
		service.createOrUpdateDebateRecord(debateRecord);
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
