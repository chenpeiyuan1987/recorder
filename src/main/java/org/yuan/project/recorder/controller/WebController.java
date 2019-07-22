package org.yuan.project.recorder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(WebController.ROOT)
public class WebController {

    public static final String ROOT = "/web";

    @GetMapping({
        "/task/index",
        "/task/save",
        "/task/info",
    })
    public String index(HttpServletRequest req) {
        String path = req.getRequestURI().replace(ROOT, "");
        log.debug("ftl path - {}", path);
        return path;
    }

}
