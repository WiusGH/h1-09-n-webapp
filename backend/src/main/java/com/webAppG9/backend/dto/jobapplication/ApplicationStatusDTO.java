package com.webAppG9.backend.dto.jobapplication;

import com.webAppG9.backend.Model.JobApplication;

public class ApplicationStatusDTO {
    private String status;
    private String statusMessage;

    public ApplicationStatusDTO(String status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public ApplicationStatusDTO() {

    }

    public ApplicationStatusDTO(JobApplication app) {
        this.status = app.getStatus().name();
        this.statusMessage = app.getStatusMessage();
    }

    // Getters y setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSratusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
