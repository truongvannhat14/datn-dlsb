package com.example.demo.Controler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotFoundController {
    @GetMapping("**")
    public String errorNotFound(){
        return "notfound";
    }
}
