package com.webAppG9.backend.dto.admin;

import com.webAppG9.backend.Model.Admin;

public class AdminRequestDTO {

    // atributos de la clase
    private Integer userId;

    // Constructor vacío para JPA
    public AdminRequestDTO() {
    }

    // Constructor con parámetros
    public AdminRequestDTO(Integer userId) {
        this.userId = userId;
    }

    // Creacion de la entidad
    public static AdminRequestDTO fromEntity(Admin admin) {
        return new AdminRequestDTO(
                admin.getUser().getId());
    }

    // Getters y Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
