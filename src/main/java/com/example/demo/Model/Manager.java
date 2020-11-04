package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manager")
    private Long id_manager;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private Role role;
    @Column(name = "email_manager")
    private String email_manager;
    @Column(name = "pass_manager")
    private String pass_manager;
    @Column(name = "name_manager")
    private String name_manager;
    @Column(name = "photo_manager")
    private String photo_manager;
    @Column(name = "phone_manager")
    private String phone_manager;
    @Column(name = "address_manager")
    private String address_manager;
    @Column(name = "status_manager")
    private String status_manager;

    public Manager() {

    }

    public Manager(Role role, String email_manager, String pass_manager, String name_manager, String phone_manager, String address_manager, String status_manager) {
        this.role = role;
        this.email_manager = email_manager;
        this.pass_manager = pass_manager;
        this.name_manager = name_manager;
        this.phone_manager = phone_manager;
        this.address_manager = address_manager;
        this.status_manager = status_manager;
    }

    public Long getId_manager() {
        return id_manager;
    }

    public void setId_manager(Long id_manager) {
        this.id_manager = id_manager;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail_manager() {
        return email_manager;
    }

    public void setEmail_manager(String email_manager) {
        this.email_manager = email_manager;
    }

    public String getPass_manager() {
        return pass_manager;
    }

    public void setPass_manager(String pass_manager) {
        this.pass_manager = pass_manager;
    }

    public String getName_manager() {
        return name_manager;
    }

    public void setName_manager(String name_manager) {
        this.name_manager = name_manager;
    }

    public String getPhoto_manager() {
        return photo_manager;
    }

    public void setPhoto_manager(String photo_manager) {
        this.photo_manager = photo_manager;
    }

    public String getPhone_manager() {
        return phone_manager;
    }

    public void setPhone_manager(String phone_manager) {
        this.phone_manager = phone_manager;
    }

    public String getAddress_manager() {
        return address_manager;
    }

    public void setAddress_manager(String address_manager) {
        this.address_manager = address_manager;
    }

    public String getStatus_manager() {
        return status_manager;
    }

    public void setStatus_manager(String status_manager) {
        this.status_manager = status_manager;
    }
}
