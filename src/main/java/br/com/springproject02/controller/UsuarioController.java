package br.com.springproject02.controller;

import br.com.springproject02.entity.Usuario;
import br.com.springproject02.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("register");

        try {
            usuario.setDataCriacao(new Date());
            usuarioService.createOrUpdate(usuario);
            modelAndView.addObject("mensagem_sucesso", "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            modelAndView.addObject("mensagem_erro", e.getMessage());
        }
        modelAndView.addObject("usuario", new Usuario());

        return modelAndView;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView loginUser(Usuario usuario, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("login");

        try {
            Usuario registro = usuarioService.get(usuario.getEmail(), usuario.getSenha());
            if(registro != null) {
                request.getSession().setAttribute("usuario_autenticado", registro);
                modelAndView.setViewName("redirect:/home");
            } else {
                throw new Exception("Acesso negado. Usuario invalido.");
            }
        }
        catch(Exception e) {
            modelAndView.addObject("mensagem_erro", e.getMessage());
        }
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){

        request.getSession().removeAttribute("usuario_autenticado"); //destroi só esse item da sessao
        //request.getSession().invalidate(); //destroi tudo q estiver na sessao

        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }
}
