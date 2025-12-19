package org.example.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//RestControleer peredaet json ,dannie
@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("appName","Car-Sharing");
        model.addAttribute("great" ,"Hallo dear user");
        return "about";
    }
}
