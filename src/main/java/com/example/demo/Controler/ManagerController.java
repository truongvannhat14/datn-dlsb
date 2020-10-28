package com.example.demo.Controler;

import com.example.demo.IRepository.*;
import com.example.demo.Model.Booking;
import com.example.demo.Model.Manager;

import com.example.demo.Model.Pitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
public class ManagerController {
    Long managerID;
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
            for(int i = 0; i < bookingList.size(); i++){
                if((bookingList.get(i).getSlotSubPitch().getSubPitch().getPitch().getManager().getId_manager()+"").equalsIgnoreCase(managerID+"")){
                    bookingForManager.add(bookingList.get(i));
                }
            }
        }
        model.addAttribute("booking", bookingForManager);
        modelAndView.addObject("message", "Sai email hoặc mât khẩu!");
        return modelAndView;
    }
}
