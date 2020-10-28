package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "subpitchs")
public class SubPitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subpitch")
    private Long id_subpitch;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pitch")
    private Pitch pitch;
    @Column(name = "name_subpitch")
    private String name_subpitch;
    @Column(name = "start_time")
    private String start_time;
    @Column(name = "end_time")
    private String end_time;
    @Column(name = "price_per_hour")
    private int price_per_hour;
    @Column(name = "photo_subpitch")
    private String photo_subpitch;
    @Column(name = "status_pitch")
    private String status_pitch;

    public SubPitch() {
    }

    public SubPitch(Long id_subpitch, Pitch pitch, String name_subpitch, String start_time, String end_time, int price_per_hour, String photo_subpitch, String status_pitch) {
        this.id_subpitch = id_subpitch;
        this.pitch = pitch;
        this.name_subpitch = name_subpitch;
        this.start_time = start_time;
        this.end_time = end_time;
        this.price_per_hour = price_per_hour;
        this.photo_subpitch = photo_subpitch;
        this.status_pitch = status_pitch;
    }

    public Long getId_subpitch() {
        return id_subpitch;
    }

    public void setId_subpitch(Long id_subpitch) {
        this.id_subpitch = id_subpitch;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public String getName_subpitch() {
        return name_subpitch;
    }

    public void setName_subpitch(String name_subpitch) {
        this.name_subpitch = name_subpitch;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(int price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public String getPhoto_subpitch() {
        return photo_subpitch;
    }

    public void setPhoto_subpitch(String photo_subpitch) {
        this.photo_subpitch = photo_subpitch;
    }

    public String getStatus_pitch() {
        return status_pitch;
    }

    public void setStatus_pitch(String status_pitch) {
        this.status_pitch = status_pitch;
    }
}
