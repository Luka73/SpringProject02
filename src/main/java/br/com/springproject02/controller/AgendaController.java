package br.com.springproject02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AgendaController {

    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        if(isLoggedIn(request)) {
            modelAndView.setViewName("agenda/home");
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("usuario_autenticado") != null;
    }
}
