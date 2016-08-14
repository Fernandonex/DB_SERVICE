package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

import dao.DAOGenerico;
import model.Usuario;

@Path("/usuariobk")
public class UsuarioWS_BK {
	DAOGenerico dao = new DAOGenerico();
	Usuario user = new Usuario();
	Gson gson = new Gson();
	List<Usuario> listaAtualizacoes = new ArrayList<Usuario>();

	public void UsuarioWS() {
	}

	@GET
	@Path("/olamundo")
	public String olaMundo() {
		return "Olá mundo!";
	}

	@GET
	@Path("/listaatualizacao")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listaAtualizacao() {
		listaAtualizacoes = dao.listaAtualizacoes(Usuario.class);
		return listaAtualizacoes;
	}

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void inserir(@FormParam("nome") String nome, @FormParam("senha") String senha,
			@FormParam("email") String email, @FormParam("usuario") String usuario,
			@FormParam("registroUnico") String registroUnico) {
		System.out.println("INSERIR");
		dao = new DAOGenerico();
		user = new Usuario();
		user.setEmail(email);
		user.setNome(nome);
		user.setSenha(senha);
		user.setUsuario(usuario);
		user.setStatusSincronizacao("SINCRONIZADO");
		user.setRegistroUnico(registroUnico);
		dao.inserir(user);
	}
	@POST
	@Path("/inserirgson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void inserirGson(String json) {
		System.out.println("INSERIR: "+json);
		dao = new DAOGenerico();
		Usuario user = gson.fromJson(json, Usuario.class);
		user.setStatusSincronizacao("SINCRONIZADO");
		System.out.println("Nome: "+user.getNome());
		System.out.println("Email: "+user.getEmail());
		System.out.println("Senha: "+user.getSenha());
		System.out.println("Registro: "+user.getRegistroUnico());
		System.out.println("Usuario: "+user.getUsuario());
		dao.inserir(user);
	}

	@POST
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void alterar(@FormParam("nome") String nome, @FormParam("senha") String senha,
			@FormParam("email") String email, @FormParam("usuario") String usuario,
			@FormParam("registroUnico") String registroUnico) {
		System.out.println("ALTERAR WS");
		System.out.println("Nome: " + nome);
		System.out.println("Usuario: " + usuario);
		System.out.println("Senha: " + senha);
		System.out.println("Email: " + email);
		System.out.println("Registro: " + registroUnico);
		dao = new DAOGenerico();
		List<Usuario> listaRegistro = new ArrayList<Usuario>();
		listaRegistro = dao.procuraObjeto(Usuario.class, registroUnico);
		System.out.println("Total de registros: " + listaRegistro.size());
		for (Usuario user : listaRegistro) {
			Usuario alt = (Usuario) dao.recuperaId(Usuario.class, user.getId());
			alt.setEmail(email);
			alt.setNome(nome);
			alt.setSenha(senha);
			alt.setUsuario(usuario);
			alt.setStatusSincronizacao("SINCRONIZADO");
			dao.alterar(user);
		}
	}

	@POST
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void deletar(@FormParam("registroUnico") String registroUnico) {
		System.out.println("DELETAR WS");
		System.out.println("Registro: " + registroUnico);
		dao = new DAOGenerico();
		List<Usuario> listaRegistro = new ArrayList<Usuario>();
		listaRegistro = dao.procuraObjeto(Usuario.class, registroUnico);
		System.out.println("Total de registros: " + listaRegistro.size());
		for (Usuario user : listaRegistro) {
			Usuario del = (Usuario) dao.recuperaId(Usuario.class, user.getId());
			try {
				dao.deletar(del);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
