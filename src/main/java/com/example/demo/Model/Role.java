package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "roler")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private long id_role;
    @Column(name = "name_role")
    private String name_role;
    @Column(name = "status_role")
    private String status_role;

    public Role() {
    }

    public Role(String name_role, String status_role) {
        this.name_role = name_role;
        this.status_role = status_role;
    }

    public long getId_role() {
        return id_role;
    }

    public void setId_role(long id_role) {
        this.id_role = id_role;
    }

    public String getName_role() {
        return name_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }

    public String getStatus_role() {
        return status_role;
    }

    public void setStatus_role(String status_role) {
        this.status_role = status_role;
    }
}
