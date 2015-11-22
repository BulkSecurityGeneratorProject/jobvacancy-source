package com.jobvacancy.web.rest;

import java.net.URISyntaxException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.util.FieldValidator;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.service.MailService;
import com.jobvacancy.web.rest.dto.JobApplicationDTO;
import com.jobvacancy.web.rest.exceptions.CreateJobApplicationException;
import com.jobvacancy.web.rest.util.HeaderUtil;

@RestController
@RequestMapping("/api")
public class JobApplicationResource {

    private final Logger log = LoggerFactory.getLogger(JobOfferResource.class);

    @Inject
    private JobOfferRepository jobOfferRepository;

    @Inject
    private MailService mailService;

    /**
     * POST  /Application -> Create a new jobOffer.
     * @throws ParseException 
     */
    @RequestMapping(value = "/Application",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> createJobApplication(@Valid @RequestBody JobApplicationDTO jobApplication) throws URISyntaxException, ParseException {
        log.debug("REST request to save JobApplication : {}", jobApplication);
        try {
			validateFields(jobApplication);
		} catch (CreateJobApplicationException e) {
			return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
        JobOffer jobOffer = jobOfferRepository.findOne(jobApplication.getOfferId());
        this.mailService.sendApplication(jobApplication, jobOffer);
        
        updateApplicantCounter(jobOffer);
        
        return ResponseEntity.accepted()
            .headers(HeaderUtil.createAlert("Application created and sent offer's owner", "")).body(null);
    }

	private void validateFields(JobApplicationDTO jobApplication) throws CreateJobApplicationException {
		if(validateEmail(jobApplication)){
			throw new CreateJobApplicationException("Invalid e-mail");
        }
        if(validateUrl(jobApplication)){
        	throw new CreateJobApplicationException("CV Link is required");
        }
	}

	private void updateApplicantCounter(JobOffer jobOffer) {
		jobOffer.addApplicants();
        jobOfferRepository.save(jobOffer);
	}

	private boolean validateUrl(JobApplicationDTO jobApplication) {
		return !FieldValidator.validateUrl(jobApplication.getCVLink());
	}

	private boolean validateEmail(JobApplicationDTO jobApplication) {
		return !FieldValidator.validateEmail(jobApplication.getEmail());
	}
}
