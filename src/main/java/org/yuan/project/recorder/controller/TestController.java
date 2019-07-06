package org.yuan.project.recorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    @RequestMapping("/index")
    public Object index() {
        return "hello, I'm recorder";
    }
}
