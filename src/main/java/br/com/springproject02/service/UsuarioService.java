package br.com.springproject02.service;

import br.com.springproject02.entity.Usuario;
import br.com.springproject02.interfaces.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service //define como classe de serviço do Spring
@Transactional //permite o uso de transações de banco de dados
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public void createOrUpdate(Usuario usuario) throws Exception {

        if(usuario.getIdUsuario() == null && usuarioRepository.get(usuario.getEmail()).size() == 1)
            throw new Exception("O email informado já encontra-se cadastrado.");

        usuarioRepository.save(usuario);
    }

    public void delete(Usuario usuario) throws Exception {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> getAll() throws Exception {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario get(Integer id) throws Exception{
        return usuarioRepository.findById(id).get();
    }

    public Usuario get(String email, String senha) throws Exception{
        List<Usuario> result = usuarioRepository.get(email, senha);

        if(result.size() == 1)
            return result.get(0); //retornando o usuario obtido

        return null;
    }
}