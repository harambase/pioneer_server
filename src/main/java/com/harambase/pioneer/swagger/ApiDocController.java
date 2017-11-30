package com.harambase.pioneer.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Home redirection to swagger api documentation 
 */
@Controller

public class ApiDocController {
	@RequestMapping(value = "/swagger")
	public String apiDoc() {
		return "redirect:swagger-ui.html";
	}
}