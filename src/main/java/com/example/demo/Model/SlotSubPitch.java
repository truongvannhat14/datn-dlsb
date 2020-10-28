package com.example.demo.Model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "slot_subpitch")
public class SlotSubPitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_slot_subpitch")
    private Long id_slot_subPitch;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subpitch")
    private SubPitch subPitch;
    @Column(name = "slot_day")
    private String slot_day;
    @Column(name = "name_slot_subpitch")
    private String name_slot_subPitch;
    @Column(name = "time_start_end")
    private String time_start_end;
    @Column(name = "status_slot_subpitch")
    private String status_slot_subPitch;

    public SlotSubPitch() {
    }

    public SlotSubPitch(Long id_slot_subPitch, SubPitch subPitch, String slot_day, String name_slot_subPitch, String time_start_end, String status_slot_subPitch) {
        this.id_slot_subPitch = id_slot_subPitch;
        this.subPitch = subPitch;
        this.slot_day = slot_day;
        this.name_slot_subPitch = name_slot_subPitch;
        this.time_start_end = time_start_end;
        this.status_slot_subPitch = status_slot_subPitch;
    }

    public Long getId_slot_subPitch() {
        return id_slot_subPitch;
    }

    public void setId_slot_subPitch(Long id_slot_subPitch) {
        this.id_slot_subPitch = id_slot_subPitch;
    }

    public SubPitch getSubPitch() {
        return subPitch;
    }

    public void setSubPitch(SubPitch subPitch) {
        this.subPitch = subPitch;
    }

    public String getSlot_day() {
        return slot_day;
    }

    public void setSlot_day(String slot_day) {
        this.slot_day = slot_day;
    }

    public String getName_slot_subPitch() {
        return name_slot_subPitch;
    }

    public void setName_slot_subPitch(String name_slot_subPitch) {
        this.name_slot_subPitch = name_slot_subPitch;
    }

    public String getTime_start_end() {
        return time_start_end;
    }

    public void setTime_start_end(String time_start_end) {
        this.time_start_end = time_start_end;
    }

    public String getStatus_slot_subPitch() {
        return status_slot_subPitch;
    }

    public void setStatus_slot_subPitch(String status_slot_subPitch) {
        this.status_slot_subPitch = status_slot_subPitch;
    }
}
