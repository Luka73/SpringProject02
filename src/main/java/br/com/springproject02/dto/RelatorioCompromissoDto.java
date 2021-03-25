package br.com.springproject02.dto;

import br.com.springproject02.entity.Compromisso;
import br.com.springproject02.entity.Usuario;

import java.util.Date;
import java.util.List;

public class RelatorioCompromissoDto {
    private Date dataInicio;
    private Date dataFim;

    private Usuario usuario;
    private List<Compromisso> compromissos;

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(List<Compromisso> compromissos) {
        this.compromissos = compromissos;
    }
}
