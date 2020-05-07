package com.cg.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Person;
import com.cg.exceptions.PersonException;
import com.cg.Services.PersonService;


@CrossOrigin
@RestController
public class HealthController {
//	@Autowired
//	DiagnosticCenterRepository dao;
	
	@Autowired
	PersonService personService;

	/*
	 * @PostMapping(value = "/add") public String add(@RequestBody DiagnosticCenter
	 * d) { dao.save(d).getListOfTests(); return "added"; }
	 */
	@PostMapping(value = "/newUser")
	public String add(@RequestBody Person person)
	{
	personService.save(person);
		return "added";
	}
	@PutMapping(value="/changePassword/{userEmail}")
	public String changePassword(@PathVariable String userEmail,@RequestBody Person p)
	{
		Person person=personService.fetchByUserEmail(userEmail);
		person.setUserPassword(p.getUserPassword());
		personService.save(p);
		return "updated";
		
	}
	@GetMapping(value = "/getUsers")
	public List<Person> fetchAll()
	{
	
		return personService.fetchAll();
	}
	@GetMapping(value = "/getUsers/{userEmail}")
	public Person fetchAll(@PathVariable String userEmail)
	{
	Person person=personService.fetchByUserEmail(userEmail);
	if(person!=null)
	{
		return person;
		
	}
	else
	{
		throw new  PersonException("EMAIL ID is not registered with us");
	}
		/*
		 * if(person.isPresent()) { return person.get(); } else { throw new
		 * RuntimeException("No ID exists"); }
		 */
	}

}
