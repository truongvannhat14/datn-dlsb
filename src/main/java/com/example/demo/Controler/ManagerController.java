package com.example.demo.Controler;

import com.example.demo.IRepository.*;
import com.example.demo.Model.Booking;
import com.example.demo.Model.Manager;

import com.example.demo.Model.Pitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ManagerController {
    Long managerID;
    Booking bookingToConfirm = new Booking();
    @Autowired
    IAdminRepository adminRepository;
    @Autowired
    IPitchRepository pitchRepository;
    @Autowired
    IManagerRepository managerRepository;
    @Autowired
    ISubPitchRepository subPitchRepository;
    @Autowired
    ISlotSubPitchRepository slotSubPitchRepository;
    @Autowired
    IBookingRepository bookingRepository;

    @GetMapping("/manager")//mấy cái này chắc ko cần nói
    public ModelAndView loginformanger(Model model) {
        ModelAndView modelAndView = new ModelAndView("manager/loginformanager");
        modelAndView.addObject("manager", new Manager());
        return modelAndView;
    }

    @PostMapping("/loginformanager")//như trên
    public ModelAndView login(@ModelAttribute("manager") Manager manager, @RequestParam("email_manager") String email_manager, @RequestParam("pass_manager") String pass_manager, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Manager> list = managerRepository.findAll();
        List<Booking> bookingList = bookingRepository.findAll();
        List<Pitch> pitchList = pitchRepository.findAll();
        List<Booking> bookingForManager = new ArrayList<>();
        for (Manager person : list) {
            if (email_manager.equalsIgnoreCase(person.getEmail_manager()) && pass_manager.equalsIgnoreCase(person.getPass_manager())) {
                managerID = person.getId_manager();
                modelAndView = new ModelAndView("manager/bookingmanager");
            }
            for (int i = 0; i < bookingList.size(); i++) {
                if ((bookingList.get(i).getSlotSubPitch().getSubPitch().getPitch().getManager().getId_manager() + "").equalsIgnoreCase(managerID + "")) {
                    bookingForManager.add(bookingList.get(i));
                }
            }
        }
        model.addAttribute("booking", bookingForManager);
        modelAndView.addObject("message", "Sai email hoặc mât khẩu!");
        return modelAndView;
    }

    //Chuyển form để bắt đầu confirm lịch đặt
    @GetMapping("confirm/{id_booking}")
    public ModelAndView confirmBooking(@PathVariable Long id_booking) {
        Optional<Booking> confirm = bookingRepository.findById(id_booking);
        if (managerID == null) {
            ModelAndView modelAndView = new ModelAndView("manager/loginformanager");
            modelAndView.addObject("manager", new Manager());
            return modelAndView;
        }
        bookingToConfirm.setId_booking(confirm.get().getId_booking());
        bookingToConfirm.setUser(confirm.get().getUser());
        bookingToConfirm.setManager(confirm.get().getManager());
        bookingToConfirm.setSlotSubPitch(confirm.get().getSlotSubPitch());
        bookingToConfirm.setBookday(confirm.get().getBookday());
        bookingToConfirm.setMessage(confirm.get().getMessage());
        bookingToConfirm.setVerifield_id(confirm.get().getVerifield_id());
        bookingToConfirm.setTotal_price(confirm.get().getTotal_price());
        bookingToConfirm.setStatus_booking(confirm.get().getStatus_booking());
        ModelAndView modelAndView = new ModelAndView("manager/detailbooking");
        modelAndView.addObject("booking", confirm);
        return modelAndView;
    }

    //Action Confirm
    @PostMapping("confirmbooking")
    public ModelAndView doConfirm(@ModelAttribute("booking") Booking booking) {
        booking = bookingToConfirm;
        Optional<Manager> manager = managerRepository.findById(managerID);
        booking.setManager(manager.get());
        booking.setStatus_booking("Đã xác nhận");
        bookingRepository.save(booking);
        ModelAndView modelAndView = new ModelAndView("manager/detailbooking");
        return modelAndView;
    }

}
