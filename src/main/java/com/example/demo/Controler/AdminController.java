package com.example.demo.Controler;

import com.example.demo.IRepository.*;
import com.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IManagerRepository managerRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IPitchRepository pitchRepository;

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

    //hiển thị thông tin tất cả tài khoản
    @GetMapping("/allAccount")
    public ModelAndView allAccount(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/quanlytaikhoan");
        List<User> userList = userRepository.findAll();
        List<User> usefilter = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getStatus_user().equalsIgnoreCase("active")) {
                usefilter.add(userList.get(i));
            }
        }
        modelAndView.addObject("accounts", usefilter);
        return modelAndView;

    }

    //Chi tiết người dùng
    @GetMapping("chitiet/{id_user}")
    public ModelAndView detailUser(@PathVariable Long id_user) {
        Optional<User> person = userRepository.findById(id_user);
        user.setid_user(person.get().getid_user());
        user.setEmail_user(person.get().getEmail_user());
        user.setPass_user(person.get().getPass_user());
        user.setName_user(person.get().getName_user());
        user.setPhoto_user(person.get().getPhoto_user());
        user.setPhone_user(person.get().getPhone_user());
        user.setAddress_user(person.get().getAddress_user());
        user.setStatus_user(person.get().getStatus_user());
        ModelAndView modelAndView = new ModelAndView("admin/chitietnguoidung");
        return modelAndView;
    }

    //tiến hành block người dùng
    @PostMapping("blockuser")
    public ModelAndView doBlockUser() {
        user.setStatus_user("blocked");
        userRepository.save(user);
        ModelAndView modelAndView = new ModelAndView("admin/chitietnguoidung");
        return modelAndView;
    }

    //thông tin chi tiết nhân viên
    @GetMapping("allManage")
    public ModelAndView allManager() {
        List<Manager> managerList = managerRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/quanlynhanvien");
        modelAndView.addObject("allmanager", managerList);
        return modelAndView;
    }

    //Chi tiết nhân viên
    @GetMapping("nhanvien/{id_manager}")
    public ModelAndView detailManager(@PathVariable Long id_manager) {
        Optional<Manager> person = managerRepository.findById(id_manager);
        manager.setId_manager(person.get().getId_manager());
        manager.setRole(person.get().getRole());
        manager.setEmail_manager(person.get().getEmail_manager());
        manager.setPass_manager(person.get().getPass_manager());
        manager.setName_manager(person.get().getName_manager());
        manager.setPhoto_manager(person.get().getPhoto_manager());
        manager.setPhone_manager(person.get().getPhone_manager());
        manager.setAddress_manager(person.get().getAddress_manager());
        manager.setStatus_manager(person.get().getStatus_manager());
        ModelAndView modelAndView = new ModelAndView("admin/chitietquanly");
        modelAndView.addObject("detail", manager);
        return modelAndView;
    }

    //sửa thông tin nhân viên
    @PostMapping("update")
    public ModelAndView updateManager(@ModelAttribute("manager")Manager managerUpdate){
        managerUpdate = manager;
        managerRepository.save(managerUpdate);
        ModelAndView modelAndView = new ModelAndView("admin/quanlynhanvien");
        return modelAndView;
    }

    //thêm nhân viên
    @GetMapping("themnhanvien")
    public ModelAndView addManager(@ModelAttribute("manager")Manager addManager){
        List<Role> roleList = roleRepository.findAll();
        Role roleToAddManager = new Role();
        for(int i = 0; i < roleList.size(); i++){
            if(roleList.get(i).getName_role().equalsIgnoreCase("Manager")){
                roleToAddManager.setId_role(roleList.get(i).getId_role());
                roleToAddManager.setName_role(roleList.get(i).getName_role());
                roleToAddManager.setStatus_role(roleList.get(i).getStatus_role());
            }
        }
        addManager.setRole(roleToAddManager);
        addManager.setPass_manager("123");
        addManager.setStatus_manager("active");
        managerRepository.save(addManager);
        ModelAndView modelAndView = new ModelAndView("admin/quanlynhanvien");
        return modelAndView;
    }

    //sửa thông tin nhân viên
    @PostMapping("deactive")
    public ModelAndView deleteManager(@ModelAttribute("manager")Manager managerDelete){
        managerDelete = manager;
        manager.setStatus_manager("Deactive");
        managerRepository.save(managerDelete);
        ModelAndView modelAndView = new ModelAndView("admin/quanlynhanvien");
        return modelAndView;
    }

    //quản lý sân
    //hiển thị sân lớn
    @GetMapping("allpitch")
    public ModelAndView showPitch(){
        List<Pitch> pitchList = pitchRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/quanlysan");
        modelAndView.addObject("allpitch", pitchList);
        return modelAndView;
    }

    //hiển thị chi tiết
    @GetMapping("san/{id_pitch}")
    public ModelAndView detailPitch(@PathVariable long id_pitch){
        Optional<Pitch> detail = pitchRepository.findById(id_pitch);
        ModelAndView modelAndView = new ModelAndView("admin/chitietsan");
        modelAndView.addObject("pitch", detail);
        return modelAndView;
    }

}



