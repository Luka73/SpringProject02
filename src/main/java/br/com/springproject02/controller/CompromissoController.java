package br.com.springproject02.controller;

import br.com.springproject02.service.CompromissoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CompromissoController {
    @Autowired // injeção de dependência
    private CompromissoService compromissoService;

    @RequestMapping("/cadastro-compromisso")
    public ModelAndView cadastro(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("agenda/cadastro-compromisso");

        return modelAndView;
    }

    @RequestMapping("/consulta-compromisso")
    public ModelAndView consulta(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("agenda/consulta-compromisso");

        return modelAndView;
    }

    @RequestMapping("/edicao-compromisso")
    public ModelAndView edicao(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("agenda/edicao-compromisso");

        return modelAndView;
    }
}
