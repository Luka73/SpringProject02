package br.com.springproject02.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TBL_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 2928186654213511462L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_USU")
    private Integer  idUsuario;

    @Column(name = "NOME_USU", length = 150, nullable = false)
    private String nome;

    @Column(name = "EMAIL_USU", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "SENHA_USU", length = 50, nullable = false)
    private String senha;

    @Temporal(TemporalType.DATE) //grava somente data no db. O default eh data e hora.
    @Column(name = "DTCRIACAO_USU", nullable = false)
    private Date dataCriacao;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nome, String email, String senha, Date dataCriacao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
