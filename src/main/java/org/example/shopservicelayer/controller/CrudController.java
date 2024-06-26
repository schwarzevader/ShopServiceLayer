package org.example.shopservicelayer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/crud")
public class CrudController {

    @RequestMapping("/")
    public ModelAndView thymeleafView(Map<String, Object> model) {
        model.put("number", 1234);
        model.put("myMessage", "Hello from Spring MVC");
        return new ModelAndView("products/h");
    }

}
