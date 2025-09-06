package com.webAppG9.backend.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "can_manage_users", nullable = false)
    private Boolean canManageUsers;

    @Column(name = "can_manage_posts", nullable = false)
    private Boolean canManagePosts;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    // Constructor vacío
    public Admin() {
    }

    // Constructor con parámetros
    public Admin(User user, Boolean canManageUsers, Boolean canManagePosts) {
        this.user = user;
        this.canManageUsers = canManageUsers;
        this.canManagePosts = canManagePosts;
    }

    // Getters y setters
    public Integer getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
