package br.com.springproject02.interfaces;

import br.com.springproject02.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    // JPQL - Java Persistence Query Language
    @Query("select u from Usuario u where u.email = :param1")
    List<Usuario> get(@Param("param1") String email);

    @Query("select u from Usuario u where u.email = :param1 and u.senha = :param2")
    List<Usuario> get(@Param("param1") String email, @Param("param2") String senha);

}
