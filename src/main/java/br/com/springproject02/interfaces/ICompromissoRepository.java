package br.com.springproject02.interfaces;

import br.com.springproject02.entity.Compromisso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ICompromissoRepository extends CrudRepository<Compromisso, Integer> {
    // JPQL - Java Persistence Query Language
    //Transcreve os comando para query SQL em tempo de execução

    @Query("select c from Compromisso c where c.data between :param1 and :param2 and c.usuario.idUsuario = :param3 order by c.data")
    public List<Compromisso> find(
            @Param("param1") Date dataMin,
            @Param("param2") Date dataMax,
            @Param("param3") Integer idUsuario);
}
