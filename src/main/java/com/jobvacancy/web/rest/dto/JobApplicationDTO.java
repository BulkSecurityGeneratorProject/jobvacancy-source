package com.jobvacancy.web.rest.dto;

/**
 * Created by nicopaez on 10/11/15.
 */
public class JobApplicationDTO {
    private Long offerId;
    private String fullname;
    private String email;
    private String cvLink;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long id) {
        this.offerId = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCVLink() {
        return cvLink;
    }

    public void setCVLink(String cvLink) {
        this.cvLink = cvLink;
    }

}
