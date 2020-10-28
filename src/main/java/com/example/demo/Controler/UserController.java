package com.example.demo.Controler;

import com.example.demo.IRepository.*;
import com.example.demo.Model.Booking;
import com.example.demo.Model.SlotSubPitch;
import com.example.demo.Model.SubPitch;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    Long userID;
    Long IDsubPitch;
    Long slotSubpitchID;
    SubPitch sub = new SubPitch();
    Booking bookingForUser = new Booking();
    @Autowired
    IAdminRepository adminRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ISubPitchRepository subPitchRepository;
    @Autowired
    ISlotSubPitchRepository slotSubPitchRepository;
    @Autowired
    IBookingRepository bookingRepository;

    @GetMapping("/login")//mấy cái này chắc ko cần nói
    public ModelAndView loginformanger(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/login")//như trên
    public ModelAndView login(@ModelAttribute("user") User user, @RequestParam("email_user") String email_user, @RequestParam("pass_user") String pass_user, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = userRepository.findAll();
        List<SubPitch> subPitch = subPitchRepository.findAll();
        for (User person : list) {
            if (email_user.equalsIgnoreCase(person.getEmail_user()) && pass_user.equalsIgnoreCase(person.getPass_user())) {
                userID = person.getid_user();
                modelAndView = new ModelAndView("index");
            }
        }
        model.addAttribute("subpitchAll", subPitch);
        modelAndView.addObject("message", "Sai email hoặc mât khẩu!");
        return modelAndView;
    }


    @GetMapping("/chitiet/{subPitchID}")//chi tiết sân bóng khi click vào link
    public ModelAndView detailSubpitch(@PathVariable Long subPitchID) {
        //Lấy ngày giờ để kiểm tra và đẩy dữ liệu
        String daySlot = java.time.LocalDate.now() + "";
        String hours = java.time.LocalTime.now() + "";
        String checkCa = hours.substring(0, 2);
        //Kiểm tra tìm kiếm dữ liệu
        Optional<SubPitch> detail = subPitchRepository.findById(subPitchID);
        IDsubPitch = detail.get().getId_subpitch();
        SubPitch subPitch = new SubPitch(detail.get().getId_subpitch(),
                detail.get().getPitch(),
                detail.get().getName_subpitch(),
                detail.get().getStart_time(),
                detail.get().getEnd_time(),
                detail.get().getPrice_per_hour(),
                detail.get().getPhoto_subpitch(),
                detail.get().getStatus_pitch());
        List<SlotSubPitch> slotSubPitchList = slotSubPitchRepository.findAll();
        List<SlotSubPitch> slotDay = new ArrayList<>();
        //Kiểm tra
        if (slotSubPitchList.size() > 0) {
            for (SlotSubPitch slot : slotSubPitchList) {
                //kiểm tra nếu chưa có ngày đó trong dữ liệu
                if (!slot.getSlot_day().equalsIgnoreCase(daySlot)) {
                    int gioCasang = 6;
                    for (int j = 0; j < 4; j++) {
                        SlotSubPitch slotSang = new SlotSubPitch(null, subPitch, daySlot + "", "Ca " + (1 + j), "0" + (gioCasang + j) + ":00 - " + (gioCasang + j + 1) + ":00", "Còn sân");
                        slotSubPitchRepository.save(slotSang);
                    }
                    int gioCaChieu = 13;
                    for (int y = 0; y < 8; y++) {
                        SlotSubPitch slotChieu = new SlotSubPitch(null, subPitch, daySlot + "", "Ca " + (5 + y), (gioCaChieu + y) + ":00 - " + (gioCaChieu + y + 1) + ":00", "Còn sân");
                        slotSubPitchRepository.save(slotChieu);
                    }
                    break;
                } else {
                    for (int i = 0; i < slotSubPitchList.size(); i++) {
                        if (slotSubPitchList.get(i).getSlot_day().equalsIgnoreCase(daySlot)) {
                            String timeSlot = slotSubPitchList.get(i).getTime_start_end();
                            String checkSlot = timeSlot.substring(0, 2);
                            //kiểm tra để vô hiệu hóa các ca quá giờ
                            if (Integer.parseInt(checkSlot) <= Integer.parseInt(checkCa) && slotSubPitchList.get(i).getSlot_day().equalsIgnoreCase(daySlot)) {
                                slotSubPitchList.get(i).setStatus_slot_subPitch("Hết hạn");
                                slotSubPitchRepository.save(slotSubPitchList.get(i));
                            } else if (slotSubPitchList.get(i).getSlot_day().equalsIgnoreCase(daySlot) && slotSubPitchList.get(i).getStatus_slot_subPitch().equalsIgnoreCase("Còn sân")) {
                                slotDay.add(slotSubPitchList.get(i));
                            }
                        }
                    }
                    break;
                }
            }
        } else {
            int gioCasang = 6;
            for (int j = 0; j < 4; j++) {
                SlotSubPitch slotSang = new SlotSubPitch(null, subPitch, daySlot + "", "Ca " + (1 + j), "0" + (gioCasang + j) + ":00 - " + (gioCasang + j + 1) + ":00", "Còn sân");
                slotSubPitchRepository.save(slotSang);
            }
            int gioCaChieu = 13;
            for (int y = 0; y < 8; y++) {
                SlotSubPitch slotChieu = new SlotSubPitch(null, subPitch, daySlot + "", "Ca " + (5 + y), (gioCaChieu + y) + ":00 - " + (gioCaChieu + y + 1) + ":00", "Còn sân");
                slotSubPitchRepository.save(slotChieu);
            }
            List<SlotSubPitch> slotNew = slotSubPitchRepository.findAll();
            for (int i = 0; i < slotNew.size(); i++) {
                if (slotNew.get(i).getSlot_day().equalsIgnoreCase(daySlot)) {
                    String timeSlot = slotNew.get(i).getTime_start_end();
                    String checkSlot = timeSlot.substring(0, 2);
                    //kiểm tra để vô hiệu hóa các ca quá giờ
                    if (Integer.parseInt(checkSlot) <= Integer.parseInt(checkCa) && slotNew.get(i).getSlot_day().equalsIgnoreCase(daySlot)) {
                        slotNew.get(i).setStatus_slot_subPitch("Hết hạn");
                        slotSubPitchRepository.save(slotNew.get(i));
                    } else if (slotNew.get(i).getSlot_day().equalsIgnoreCase(daySlot) && slotNew.get(i).getStatus_slot_subPitch().equalsIgnoreCase("Còn sân")) {
                        slotDay.add(slotNew.get(i));
                    }
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("detailPitch");
        modelAndView.addObject("subpitch", slotDay);
        return modelAndView;
    }

    //Hiển thị form để người dùng đặt lịch
    @GetMapping("booking/{id_slot_subPitch}")
    public ModelAndView bookingSlot(@PathVariable Long id_slot_subPitch) {
        Optional<SlotSubPitch> slotSubPitch = slotSubPitchRepository.findById(id_slot_subPitch);
        slotSubpitchID = slotSubPitch.get().getId_slot_subPitch();
        Optional<User> user = userRepository.findById(userID);
        Optional<SubPitch> subPitch = subPitchRepository.findById(IDsubPitch);
        //Thêm dữ liệu người dùng để đẩy vào booking
        User us = new User(user.get().getid_user(),
                user.get().getEmail_user(),
                user.get().getPass_user(),
                user.get().getName_user(),
                user.get().getPhoto_user(),
                user.get().getPhone_user(),
                user.get().getAddress_user(),
                user.get().getStatus_user());
        //thêm dữ liệu vào object mới để đẩy sang slotsubpitch
        SubPitch sub = new SubPitch(subPitch.get().getId_subpitch(),
                subPitch.get().getPitch(),
                subPitch.get().getName_subpitch(),
                subPitch.get().getStart_time(),
                subPitch.get().getEnd_time(),
                subPitch.get().getPrice_per_hour(),
                subPitch.get().getPhoto_subpitch(),
                subPitch.get().getStatus_pitch());
        //thêm dữ liệu vào object mới để đẩy sang slotsubpitch
        SlotSubPitch slot = new SlotSubPitch(
                slotSubPitch.get().getId_slot_subPitch(),
                sub,
                slotSubPitch.get().getSlot_day(),
                slotSubPitch.get().getName_slot_subPitch(),
                slotSubPitch.get().getTime_start_end(),
                slotSubPitch.get().getStatus_slot_subPitch()
        );
        bookingForUser = new Booking(null, us, null, slot, "", "", "", slot.getSubPitch().getPrice_per_hour(), "Chờ xác nhận");
        System.out.println(bookingForUser.getTotal_price());
        ModelAndView modelAndView = new ModelAndView("booking");
        modelAndView.addObject("booking", bookingForUser);
        return modelAndView;
    }
    @PostMapping("booking")
    public ModelAndView doBooking(@ModelAttribute("booking")Booking booking){
        booking = bookingForUser;
        String bookDay = java.time.LocalDate.now()+"";
        int verityCode = (int) (Math.floor(Math.random()*10000)+ 10000);
        booking.setBookday(bookDay);
        booking.setVerifield_id(verityCode+"");
        bookingRepository.save(booking);
        Optional<SlotSubPitch> changeStatus = slotSubPitchRepository.findById(booking.getSlotSubPitch().getId_slot_subPitch());
        changeStatus.get().setStatus_slot_subPitch("Đã thuê");
        SlotSubPitch change = new SlotSubPitch(
                changeStatus.get().getId_slot_subPitch(),
                changeStatus.get().getSubPitch(),
                changeStatus.get().getSlot_day(),
                changeStatus.get().getName_slot_subPitch(),
                changeStatus.get().getTime_start_end(),
                "Đã thuê"
        );
        slotSubPitchRepository.save(change);
        ModelAndView modelAndView = new ModelAndView("booking");
        return modelAndView;
    }
}


