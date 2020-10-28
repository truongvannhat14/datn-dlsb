package com.example.demo.Model;

import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id_booking;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_manager")
    private Manager manager;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_slot_subpitch")
    private SlotSubPitch slotSubPitch;
    @Column(name = "bookday")
    private String bookday;
    @Column(name = "message")
    private String message;
    @Column(name = "verifield_id")
    private String verifield_id;
    @Column(name = "total_price")
    private int total_price;
    @Column(name = "status_booking")
    private String status_booking;

    public Booking() {
    }

    public Booking(Long id_booking,User user, Manager manager, SlotSubPitch slotSubPitch, String bookday, String message, String verifield_id, int total_price, String status_booking) {
        this.id_booking = id_booking;
        this.user = user;
        this.manager = manager;
        this.slotSubPitch = slotSubPitch;
        this.bookday = bookday;
        this.message = message;
        this.verifield_id = verifield_id;
        this.total_price = total_price;
        this.status_booking = status_booking;
    }

    public Long getId_booking() {
        return id_booking;
    }

    public void setId_booking(Long id_booking) {
        this.id_booking = id_booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public SlotSubPitch getSlotSubPitch() {
        return slotSubPitch;
    }

    public void setSlotSubPitch(SlotSubPitch slotSubPitch) {
        this.slotSubPitch = slotSubPitch;
    }

    public String getBookday() {
        return bookday;
    }

    public void setBookday(String bookday) {
        this.bookday = bookday;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVerifield_id() {
        return verifield_id;
    }

    public void setVerifield_id(String verifield_id) {
        this.verifield_id = verifield_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getStatus_booking() {
        return status_booking;
    }

    public void setStatus_booking(String status_booking) {
        this.status_booking = status_booking;
    }
}
