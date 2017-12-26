package com.harambase.pioneer.server.core.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class ApiDocController {
    @RequestMapping(value = "/")
    public String apiDoc() {
        return "redirect:swagger-ui.html";
    }
}
