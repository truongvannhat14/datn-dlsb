package com.example.demo.Controler;

import com.example.demo.IRepository.*;
import com.example.demo.Model.*;
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
public class AdminController {
    Long adminID;
    Booking booking = new Booking();
    Pitch pitch = new Pitch();
    SubPitch subPitch = new SubPitch();
    User user = new User();
    Manager manager = new Manager();

    @Autowired
    IAdminRepository adminRepository;

    @GetMapping("/admin")//mấy cái này chắc ko cần nói
    public ModelAndView loginformanger(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/loginforadmin");
        modelAndView.addObject("admin", new Admin());
        return modelAndView;
    }

    @PostMapping("/dashboard")//như trên
    public ModelAndView login(@ModelAttribute("dashboard") Admin admin, @RequestParam("account_ad_admin") String account_ad_admin, @RequestParam("pass_ad_admin") String pass_ad_admin, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/thongke");
        List<Admin> adminperson = adminRepository.findAll();
        for (Admin person : adminperson) {
            if (person.getAccount_ad_admin().equalsIgnoreCase(account_ad_admin) && person.getPass_ad_admin().equalsIgnoreCase(pass_ad_admin)) {
                adminID = person.getId_ad_admin();
                return modelAndView;
            }
        }
        modelAndView.addObject("message", "Sai email hoặc mât khẩu!");
        return modelAndView;
    }
    @GetMapping("/test")
    public String chaythu(){
        return "admin/sanbong";
    }


}
