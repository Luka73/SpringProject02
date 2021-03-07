package br.com.springproject02.service;

import br.com.springproject02.entity.Compromisso;
import br.com.springproject02.interfaces.ICompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service //Me habilita a injetar essa classe com o Autowired, pois é um Serviço
@Transactional
public class CompromissoService {

    @Autowired
    private ICompromissoRepository compromissoRepository;

    public void createOrUpdate(Compromisso compromisso) throws Exception {
        compromissoRepository.save(compromisso);
    }

    public void delete(Compromisso compromisso) throws Exception {
        compromissoRepository.delete(compromisso);
    }

    public List<Compromisso> getAll() throws Exception {
        return (List<Compromisso>) compromissoRepository.findAll();
    }

    public Compromisso get(Integer id) throws Exception {
        return compromissoRepository.findById(id).get();
    }

    public List<Compromisso> get(Date dataMin, Date dataMax, Integer idUsuario) throws Exception {
        return compromissoRepository.find(dataMin, dataMax, idUsuario);
    }
}
