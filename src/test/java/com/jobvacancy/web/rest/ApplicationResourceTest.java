package com.jobvacancy.web.rest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.jobvacancy.Application;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.User;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.repository.UserRepository;
import com.jobvacancy.service.MailService;
import com.jobvacancy.web.rest.dto.JobApplicationDTO;

/**
 * Created by nicopaez on 10/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationResourceTest {

    private static final String APPLICANT_BAD_EMAIL = "test1()@pepe.com.";
	private static final String APPLICANT_FULLNAME = "THE APPLICANT";
    private static final String APPLICANT_EMAIL = "APPLICANT@TEST.COM";
    private static final String APPLICANT_CV_LINK = "http://www.linktoCV.com";
    private static final String APPLICANT_BAD_CV_LINK = "linktoCV.com";
    private MockMvc restMockMvc;

    private static final long OFFER_ID = 1;
    private static final String OFFER_TITLE = "SAMPLE_TEXT";

    @Mock
    private MailService mailService;

    @Mock
    private JobOfferRepository jobOfferRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private JobOffer offer;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Optional<User> user = userRepository.findOneByLogin("user");
        offer = new JobOffer();
        offer.setTitle(OFFER_TITLE);
        offer.setId(OFFER_ID);
        offer.setOwner(user.get());
        when(jobOfferRepository.findOne(OFFER_ID)).thenReturn(offer);
        JobApplicationResource jobApplicationResource = new JobApplicationResource();
        ReflectionTestUtils.setField(jobApplicationResource, "jobOfferRepository", jobOfferRepository);
        ReflectionTestUtils.setField(jobApplicationResource, "mailService", mailService);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(jobApplicationResource)
            .setMessageConverters(jacksonMessageConverter).build();
    }
    
    @Test
    @Transactional
    public void createJobApplicationWithoutEmail() throws Exception {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(null);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setCVLink(APPLICANT_CV_LINK);
        dto.setOfferId(OFFER_ID);

        doNothing().when(mailService).sendApplication(dto, offer);

        restMockMvc.perform(post("/api/Application")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void createJobApplicationWithBadEmail() throws Exception {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_BAD_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setCVLink(APPLICANT_CV_LINK);
        dto.setOfferId(OFFER_ID);

        doNothing().when(mailService).sendApplication(dto, offer);

        restMockMvc.perform(post("/api/Application")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @Transactional
    public void createJobApplicationWithoutCVLink() throws Exception {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setCVLink(null);
        dto.setOfferId(OFFER_ID);

        doNothing().when(mailService).sendApplication(dto, offer);

        restMockMvc.perform(post("/api/Application")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @Transactional
    public void createJobApplicationWithBadCVLinkl() throws Exception {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_BAD_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setCVLink(APPLICANT_BAD_CV_LINK);
        dto.setOfferId(OFFER_ID);

        doNothing().when(mailService).sendApplication(dto, offer);

        restMockMvc.perform(post("/api/Application")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void createJobApplication() throws Exception {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setEmail(APPLICANT_EMAIL);
        dto.setFullname(APPLICANT_FULLNAME);
        dto.setCVLink(APPLICANT_CV_LINK);
        dto.setOfferId(OFFER_ID);

        doNothing().when(mailService).sendApplication(dto, offer);

        restMockMvc.perform(post("/api/Application")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isAccepted());

    }

}
