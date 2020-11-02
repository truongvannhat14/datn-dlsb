package com.example.demo.Controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotFoundController {
    @GetMapping("**")
    public String errorNotFound(){
        return "404notfound";
    }
}
