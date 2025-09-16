package com.webAppG9.backend.dto.recruiter;

public class RecruiterRequestDTO {

    private String companyName;
    private String companyCountry;
    private String companyAddress;
    private String companyEmail;

    // Getters y Setters
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

}
