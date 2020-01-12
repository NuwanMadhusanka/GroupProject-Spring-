package com.gp.learners.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gp.learners.entities.Complain;
import com.gp.learners.repositories.ComplainRepository;
import com.gp.learners.service.ComplainService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ComplainController {

	@Autowired
	ComplainService complainService;

	@Autowired
	ComplainRepository complainRepository;

	@GetMapping("/complains")
	public List<Complain> getComplainList() {
		System.out.println("In Complain Controller");
		return complainService.getComplainList();

	}

	// get Specific Video data
	@GetMapping("complain/{complainId}")
	public ResponseEntity<Complain> getComplain(@PathVariable("complainId") Integer complainId) {
		System.out.println("In Complain Controller VmoreDetails");
		Complain complain = complainService.getComplainDetails(complainId);
		if (complain.getComplainId() != null) {
			return ResponseEntity.ok(complain);
		}
		return ResponseEntity.noContent().build();
	}

	// save video
	@PostMapping("/complain/add")

	public Complain saveComplain(@RequestBody Complain complain) {
		System.out.println("In complain controller Adding method");
		return complainRepository.save(complain);
	}

	// delete Video
	@DeleteMapping("complain/{complainId}")
	public ResponseEntity<Void> deleteComplain(@PathVariable("complainId") Integer complainId) {
		System.out.println("In complain controller delete method");

		String reply = complainService.deleteComplain(complainId);
		if (reply.equals("success")) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	// update video Details
	@PutMapping("complain/update")
	public ResponseEntity<Complain> updateComplain(@Valid @RequestBody Complain object) {
		Complain complain = complainService.updateComplain(object);
		if (complain.getComplainId() != null) {
			return ResponseEntity.ok(complain);
		}
		return ResponseEntity.notFound().build();
	}

}