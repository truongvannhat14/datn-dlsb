package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "pitch")
public class Pitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pitch")
    private Long id_pitch;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_manager")
    private Manager manager;
    @Column(name = "name_pitch")
    private String name_pitch;
    @Column(name = "desc_pitch")
    private String desc_pitch;
    @Column(name = "address_pitch")
    private String address_pitch;
    @Column(name = "city_pitch")
    private String city_pitch;
    @Column(name = "photo_pitch")
    private String photo_pitch;
    @Column(name = "status_pitch")
    private String status_pitch;

    public Pitch() {
    }

    public Pitch(Manager manager, String name_pitch, String desc_pitch, String address_pitch, String city_pitch, String photo_pitch, String status_pitch) {
        this.manager = manager;
        this.name_pitch = name_pitch;
        this.desc_pitch = desc_pitch;
        this.address_pitch = address_pitch;
        this.city_pitch = city_pitch;
        this.photo_pitch = photo_pitch;
        this.status_pitch = status_pitch;
    }

    public Long getId_pitch() {
        return id_pitch;
    }

    public void setId_pitch(Long id_pitch) {
        this.id_pitch = id_pitch;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getName_pitch() {
        return name_pitch;
    }

    public void setName_pitch(String name_pitch) {
        this.name_pitch = name_pitch;
    }

    public String getDesc_pitch() {
        return desc_pitch;
    }

    public void setDesc_pitch(String desc_pitch) {
        this.desc_pitch = desc_pitch;
    }

    public String getAddress_pitch() {
        return address_pitch;
    }

    public void setAddress_pitch(String address_pitch) {
        this.address_pitch = address_pitch;
    }

    public String getCity_pitch() {
        return city_pitch;
    }

    public void setCity_pitch(String city_pitch) {
        this.city_pitch = city_pitch;
    }

    public String getPhoto_pitch() {
        return photo_pitch;
    }

    public void setPhoto_pitch(String photo_pitch) {
        this.photo_pitch = photo_pitch;
    }

    public String getStatus_pitch() {
        return status_pitch;
    }

    public void setStatus_pitch(String status_pitch) {
        this.status_pitch = status_pitch;
    }
}
