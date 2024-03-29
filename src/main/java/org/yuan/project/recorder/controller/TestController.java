package org.yuan.project.recorder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuan.project.recorder.entity.Demo;
import org.yuan.project.recorder.service.IDemoService;
import org.yuan.project.recorder.utils.Fault;
import org.yuan.project.recorder.utils.Result;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IDemoService demoService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("result", "success");
        return "test/index";
    }

    @ResponseBody
    @GetMapping("/demo/list")
    public Object demoList() {
        List<Demo> list = demoService.list();
        return Result.success(list);
    }

    @ResponseBody
    @GetMapping("/demo/save")
    public Object demoSave(String info) {
        Demo demo = new Demo();
        demo.initCreate(1);
        demo.setInfo(info);

        if (!demoService.save(demo)) {
            return Result.failure();
        }
        return Result.success(demo);
    }

    @ResponseBody
    @GetMapping("/fault")
    public Object fault() {
        throw new Fault("This is a fault.");
    }
}
