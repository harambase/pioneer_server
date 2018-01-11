package com.harambase.pioneer.server.core.swagger;

import com.harambase.pioneer.server.core.dao.repository.PersonRepository;
import com.harambase.pioneer.server.core.pojo.base.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class ApiDocController {

    private final PersonRepository personRepository;

    @Autowired
    public ApiDocController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/")
    public String apiDoc(@RequestParam String userId) {
        Person person = personRepository.findOne(userId);
        if (person.getRoleId().contains("1"))
            return "redirect:swagger-ui.html";
        else
            return "error";
    }
}
