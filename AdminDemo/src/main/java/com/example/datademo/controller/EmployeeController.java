package com.example.datademo.controller;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.datademo.model.Holiday;
import com.example.datademo.model.Leave;
import com.example.datademo.model.User;
import com.example.datademo.repo.LeaveRepository;
import com.example.datademo.repo.UserRepository;

@Controller
public class EmployeeController {
	private UserRepository mRepo;
	private LeaveRepository lRepo;
	@Autowired
	public void setlRepo(LeaveRepository lRepo) {
		this.lRepo = lRepo;
	}

	@Autowired
	public void setmRepo(UserRepository mRepo) {
		this.mRepo = mRepo;
	}

	@RequestMapping(path="/staff")
	public String Index()
	{
		return "homeEmployee";
	}
	@RequestMapping(path="/manager")
	public String In()
	{
		return "homeManager";
	}
	@RequestMapping(path = "/leaves/addform", method = RequestMethod.GET)
    public String createLeave(Model model) {
        model.addAttribute("leave", new Leave());
        return "leaveform";
    }
	  @RequestMapping(path = "/leaves/viewform", method = RequestMethod.POST)
	    public String saveLeave(@Valid Leave l, BindingResult bindingResult, Model model) {
	    	if (bindingResult.hasErrors()) {
	            return "viewleaveform";
	        }
	  
	        lRepo.save(l);
	    	ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAll();
		 	model.addAttribute("leavelist", plist);
	       
	
	        return "leaveform";
	    }
	  @RequestMapping(path = "/leaves/viewform", method = RequestMethod.GET)
	    public String getAllLeave(Model model) {
	    	 ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAll();
	 		model.addAttribute("leavelist", plist);
	     
	        return "viewleaveform";
	    } 
	    @RequestMapping(path = "/leaves/editform/{id}", method = RequestMethod.GET)
	    public String editLeave( @PathVariable(value = "id") String id,@Valid Leave l,Model model) {   	
	    	l = lRepo.findById(id).orElse(null);
	    	System.out.println(l);
	    	  lRepo.save(l);
	        model.addAttribute("leaves", l);
	        return "leaveform";
	    }
	    @RequestMapping(path = "/leaves/editform/{id}", method = RequestMethod.POST)
	    public String updateLeave( @PathVariable(value = "id") String id,@Valid Leave l,Model model) {   	
	    	
	    	  lRepo.save(l);
	    	  ArrayList<Leave> plist = (ArrayList<Leave>) lRepo.findAll();
			 	model.addAttribute("leavelist", plist);
		       
			     return "redirect:/leaves/viewform";
	    }
	    @RequestMapping(path = "/leaves/deleteform/{id}", method = RequestMethod.GET)
	    public String deleteLeave(@PathVariable(name = "id") String id,Model m,Leave l) {
	    	lRepo.delete(lRepo.findById(id).orElse(null));
	    	m.addAttribute("leaves", l);
	        return "redirect:/leaves/viewform";
	    }
	    
	  

}
