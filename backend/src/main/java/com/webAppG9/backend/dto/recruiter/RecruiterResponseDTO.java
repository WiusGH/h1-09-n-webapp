package com.webAppG9.backend.dto.recruiter;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.Model.Recruiter;

public class RecruiterResponseDTO {

    private Integer userId;
    private String companyName;
    private String companyCountry;
    private String companyAddress;
    private String companyEmail;
    private Boolean approved;

    // Mapear para response DTO
    public static RecruiterResponseDTO toResponseDTO(Recruiter recruiter) {
        User user = recruiter.getUser();
        RecruiterResponseDTO dto = new RecruiterResponseDTO();
        dto.setUserId(user.getId());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setCompanyCountry(recruiter.getCompanyCountry());
        dto.setCompanyAddress(recruiter.getCompanyAddress());
        dto.setCompanyEmail(recruiter.getCompanyEmail());
        dto.setApproved(recruiter.getApproved());
        return dto;
    }

    // Getters y Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) { // corregido
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) { // corregido
        this.companyEmail = companyEmail;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
