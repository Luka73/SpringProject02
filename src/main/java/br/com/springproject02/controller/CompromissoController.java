package br.com.springproject02.controller;

import br.com.springproject02.dto.CadastroCompromissoDto;
import br.com.springproject02.dto.ConsultaCompromissoDto;
import br.com.springproject02.dto.RelatorioCompromissoDto;
import br.com.springproject02.entity.Compromisso;
import br.com.springproject02.entity.Usuario;
import br.com.springproject02.enums.ModoDeExibicao;
import br.com.springproject02.enums.PrioridadeCompromisso;
import br.com.springproject02.enums.TipoCompromisso;
import br.com.springproject02.reports.CompromissoReport;
import br.com.springproject02.service.CompromissoService;
import br.com.springproject02.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Controller
public class CompromissoController {
    @Autowired // injeção de dependência
    private CompromissoService compromissoService;

    @RequestMapping("/cadastro-compromisso")
    public ModelAndView cadastro(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("agenda/cadastro-compromisso");
        modelAndView.addObject("dto", new CadastroCompromissoDto());
        modelAndView.addObject("tipos", TipoCompromisso.values());
        modelAndView.addObject("prioridades", PrioridadeCompromisso.values());

        return modelAndView;
    }

    @RequestMapping(value = "cadastrarCompromisso", method = RequestMethod.POST) //recebe o submit do formulário
    public ModelAndView cadastro(CadastroCompromissoDto dto, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("agenda/cadastro-compromisso");

        try {

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

            Compromisso compromisso = new Compromisso();
            compromisso.setNome(dto.getNome());
            compromisso.setData(DateUtil.toDate(dto.getData()));
            compromisso.setHora(dto.getHora());
            compromisso.setTipo(dto.getTipo());
            compromisso.setPrioridade(dto.getPrioridade());
            compromisso.setDescricao(dto.getDescricao());
            compromisso.setUsuario(usuario);

            compromissoService.createOrUpdate(compromisso);
            modelAndView.addObject("mensagem_sucesso", "Compromisso cadastrado com sucesso!");

        }
        catch(Exception e) {

        }

        modelAndView.addObject("dto", new CadastroCompromissoDto());
        modelAndView.addObject("tipos", TipoCompromisso.values());
        modelAndView.addObject("prioridades", PrioridadeCompromisso.values());

        return modelAndView;
    }

    @RequestMapping("/consulta-compromisso") //abre a view
    public ModelAndView consulta() {
        ModelAndView modelAndView = new ModelAndView("agenda/consulta-compromisso");

        modelAndView.addObject("dto", new ConsultaCompromissoDto());
        modelAndView.addObject("modosDeExibicao", ModoDeExibicao.values());

        return modelAndView;
    }

    @RequestMapping(value = "consultarCompromissos", method = RequestMethod.POST) //abre a view
    public ModelAndView consulta(ConsultaCompromissoDto dto, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("agenda/consulta-compromisso");

        try{
            Date dataInicio = DateUtil.toDate(dto.getDataInicio());
            Date dataFim = DateUtil.toDate(dto.getDataFim());

            //Obter o usuário da Sessão
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");

            List<Compromisso> compromissos = compromissoService.get(dataInicio, dataFim, usuario.getIdUsuario());

            switch (dto.getModoDeExibicao()) {
                case HTML:
                    modelAndView.addObject("compromissos", compromissos);
                    break;
                case PDF:
                    RelatorioCompromissoDto relatorioDto = new RelatorioCompromissoDto();
                    relatorioDto.setDataInicio(dataInicio);
                    relatorioDto.setDataFim(dataFim);
                    relatorioDto.setUsuario(usuario);
                    relatorioDto.setCompromissos(compromissos);

                    CompromissoReport report = new CompromissoReport();
                    InputStream stream = report.getPdf(relatorioDto);
                    byte[] pdf = stream.readAllBytes();

                    //Montando o header e nome do arquivo para download
                    response.setContentType("application/pdf");
                    response.addHeader("Content-Disposition", "attachment; filename=compromissos.pdf");

                    //download
                    OutputStream out = response.getOutputStream();
                    out.write(pdf, 0, pdf.length);
                    out.flush();
                    out.close();

                    response.getOutputStream().flush(); //flush no response tbm
                    return null; //o break, continua no método. Aqui fez o download e acabou, sai do método
            }

        } catch (Exception e) {
            modelAndView.addObject("mensagem_erro", e.getMessage());
        }

        modelAndView.addObject("dto", dto); //Não dá new pq senão ele vai limpar os campos, dps q da pesquisa
        modelAndView.addObject("modosDeExibicao", ModoDeExibicao.values());

        return modelAndView;
    }

    @RequestMapping("/edicao-compromisso")
    public ModelAndView edicao(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("agenda/edicao-compromisso");

        return modelAndView;
    }
}
