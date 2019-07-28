package org.yuan.project.recorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public Object index () {
        return "redirect:/web/task/index";
    }
}
