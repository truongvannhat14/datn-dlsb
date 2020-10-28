package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "ad_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ad_admin")
    private Long id_ad_admin;
    @Column(name = "account_ad_admin")
    private String account_ad_admin;
    @Column(name = "pass_ad_admin")
    private String pass_ad_admin;

    public Admin() {
    }

    public Admin(String account_ad_admin, String pass_ad_admin) {
        this.account_ad_admin = account_ad_admin;
        this.pass_ad_admin = pass_ad_admin;
    }

    public Long getId_ad_admin() {
        return id_ad_admin;
    }

    public void setId_ad_admin(Long id_ad_admin) {
        this.id_ad_admin = id_ad_admin;
    }

    public String getAccount_ad_admin() {
        return account_ad_admin;
    }

    public void setAccount_ad_admin(String account_ad_admin) {
        this.account_ad_admin = account_ad_admin;
    }

    public String getPass_ad_admin() {
        return pass_ad_admin;
    }

    public void setPass_ad_admin(String pass_ad_admin) {
        this.pass_ad_admin = pass_ad_admin;
    }
}
