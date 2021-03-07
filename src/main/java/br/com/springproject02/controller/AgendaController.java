package br.com.springproject02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgendaController {

    @RequestMapping("/home")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("agenda/home");
        return modelAndView;
    }
}
