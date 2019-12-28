package com.gp.learners.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gp.learners.entities.Pdf;
import com.gp.learners.entities.User;
import com.gp.learners.repositories.PdfRepository;

@Service
public class PdfService {

	@Autowired
	PdfRepository pdfRepository;
	
	@Autowired
	S3Service s3Service;
	
	@Value("${aws.s3.bucket.profile_image}")
	private String bucketName; // bucket name should be changed

	// getPdfList
	public List<Pdf> getPdfList() {
		System.out.println("IN pdfrepo");
		List<Pdf> pdfList = pdfRepository.findAll();
		// Pdf pdf1=pdfList.get(0);
		return pdfList;
	}

	public Pdf getPdfByID(Integer pdfId) {
		if (pdfId != null) {
			if (pdfRepository.existsById(pdfId)) {
				return pdfRepository.getPdfById(pdfId);
			}
		}
		return new Pdf();
	}

	// Add PDF
	public Pdf addPdf(Pdf pdf) {

		Pdf result = pdfRepository.save(pdf);
		if (result != null)
			return result;
		
		else
			return null;
	}

	// delete Pdf
	public String deletePdf(Integer pdfId) {
		System.out.println("Delete Pdf Serv");
		if (pdfId != null) {
			if (pdfRepository.existsById(pdfId)) {
				pdfRepository.deleteById(pdfId);
				return "success";
			}
		}
		return "error";
	}

	// update Student Details
	public Pdf updatePdf(Pdf pdf) {
		if (pdfRepository.existsById(pdf.getPdfId())) {
			// Pdf pdf1=pdfRepository.getPdfById(pdf.getPdfId());
			return pdfRepository.save(pdf);
		}

		return new Pdf();
	}
	public String uploadPdf(MultipartFile file, Integer pdfId) {
		if (pdfRepository.existsById(pdfId)) {
			String keyName = pdfId + ".pdf";
			if (keyName != null) {
				s3Service.uploadFile(keyName, file, bucketName);
				Pdf pdf = pdfRepository.getPdfById(pdfId);
				//user.setProfileImage(1);
				//userRepository.save(user);
				return "success";
			}
		}
		return null;
	}

}