package com.webAppG9.backend.dto.recruiter;

public class UpdateStatusRequestDTO {
    private String status;
    private String statusMessage;

    // Getters y setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}