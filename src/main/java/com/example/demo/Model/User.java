package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id_user;
    @Column(name = "email_user")
    private String email_user;
    @Column(name = "pass_user")
    private String pass_user;
    @Column(name = "name_user")
    private String name_user;
    @Column(name = "photo_user")
    private String photo_user;
    @Column(name = "phone_user")
    private String phone_user;
    @Column(name = "address_user")
    private String address_user;
    @Column(name = "status_user")
    private String status_user;

    public User() {
    }

    public User(Long id_user, String email_user, String pass_user, String name_user,String photo_user, String phone_user, String address_user, String status_user) {
        this.id_user= id_user;
        this.email_user = email_user;
        this.pass_user = pass_user;
        this.name_user = name_user;
        this.phone_user = phone_user;
        this.photo_user = photo_user;
        this.address_user = address_user;
        this.status_user = status_user;
    }

    public Long getid_user() {
        return id_user;
    }

    public void setid_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPass_user() {
        return pass_user;
    }

    public void setPass_user(String pass_user) {
        this.pass_user = pass_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPhoto_user() {
        return photo_user;
    }

    public void setPhoto_user(String photo_user) {
        this.photo_user = photo_user;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }

    public String getAddress_user() {
        return address_user;
    }

    public void setAddress_user(String address_user) {
        this.address_user = address_user;
    }

    public String getStatus_user() {
        return status_user;
    }

    public void setStatus_user(String status_user) {
        this.status_user = status_user;
    }
}
