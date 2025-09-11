package com.webAppG9.backend.dto.admin;

import com.webAppG9.backend.Model.Admin;

public class AdminDTO {

    // atributos de la clase
    private Integer userId;

    // Constructor vacío para JPA
    public AdminDTO() {
    }

    // Constructor con parámetros
    public AdminDTO(Integer userId) {
        this.userId = userId;
    }

    // Creacion de la entidad
    public static AdminDTO fromEntity(Admin admin) {
        return new AdminDTO(
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
