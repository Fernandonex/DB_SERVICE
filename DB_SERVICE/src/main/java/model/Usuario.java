package model;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	private Long id;
	@Expose
	private String email;
	@Expose
	private String nome;
	@Expose
	private String registroUnico;
	@Expose
	private String senha;
	@Expose
	private String statusSincronizacao;
	@Expose
	private String usuario;

	@Expose
	@JoinColumn(name="fk_curso", referencedColumnName="idCurso")
	@ManyToOne(optional = false)
	private Curso curso;
	
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
	public Usuario() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	
	public String getRegistroUnico() {
		return registroUnico;
	}


	public void setRegistroUnico(String registroUnico) {
		this.registroUnico = registroUnico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
  
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 

	public String getStatusSincronizacao() {
		return statusSincronizacao;
	}

	public void setStatusSincronizacao(String statusSincronizacao) {
		this.statusSincronizacao = statusSincronizacao;
	}

	private static final long serialVersionUID = 1L;
}
