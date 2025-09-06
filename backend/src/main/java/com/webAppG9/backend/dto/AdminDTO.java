package com.webAppG9.backend.dto;

import com.webAppG9.backend.Model.Admin;

public class AdminDTO {

    // atributos de la clase
    private Integer userId;
    private Boolean canManageUsers;
    private Boolean canManagePosts;

    // Constructor vacío para JPA
    public AdminDTO() {
    }

    // Constructor con parámetros
    public AdminDTO(Integer userId, Boolean canManageUsers, Boolean canManagePosts) {
        this.userId = userId;
        this.canManageUsers = canManageUsers;
        this.canManagePosts = canManagePosts;
    }

    // Creacion de la entidad
    public static AdminDTO fromEntity(Admin admin) {
        return new AdminDTO(
                admin.getUser().getId(),
                admin.getCanManageUsers(),
                admin.getCanManagePosts());
    }

    // Getters y Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getCanManageUsers() {
        return canManageUsers;
    }

    public void setCanManageUsers(Boolean canManageUsers) {
        this.canManageUsers = canManageUsers;
    }

    public Boolean getCanManagePosts() {
        return canManagePosts;
    }

    public void setCanManagePosts(Boolean canManagePosts) {
        this.canManagePosts = canManagePosts;
    }

}
