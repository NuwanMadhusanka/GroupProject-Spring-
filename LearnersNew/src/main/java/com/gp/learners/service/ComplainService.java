package com.gp.learners.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.learners.entities.Complain;

import com.gp.learners.repositories.ComplainRepository;


@Service
public class ComplainService {

	@Autowired
	ComplainRepository complainRepository;

	@Autowired
	ComplainService complainService;

	// getVideoList
	public List<Complain> getComplainList() {
		System.out.println("In Complain Repo");
		List<Complain> complainList = complainRepository.findAll();
//		Video video = videoList.get(0);
/*		System.out.println(video.getDescription());*/
		return complainList;
	}

	// get Video Details
	public Complain getComplainDetails(Integer complainId) {
		if (complainId != null) {
			if (complainRepository.existsById(complainId)) {
				return complainRepository.getComplainById(complainId);
			}
		}
		return new Complain();
	}

	// Add Video
	public String addComplain(Complain complain) {

		Complain result = complainRepository.save(complain);
		if (result != null)
			return "success";
		else
			return "notsuccess";
	}

	// delete video
	public String deleteComplain(Integer complainId) {
		if (complainId != null) {
			if (complainRepository.existsById(complainId)) {
				complainRepository.deleteById(complainId);
				return "success";
			}
		}
		return "error";
	}

	// update Video Details
	public Complain updateComplain(Complain complain) {
		if (complainRepository.existsById(complain.getComplainId())) {
			return complainRepository.save(complain);
		}

		return new Complain();
	}

}