package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOGenerico;
import model.Usuario;

@Path("/usuarioBK23")
public class WSPreModBK {
	DAOGenerico dao = new DAOGenerico();
	Usuario user = new Usuario();
	Gson gson;
	List<Usuario> listaAtualizacoes = new ArrayList<Usuario>();

	@GET
	@Path("/olamundo")
	@Produces("application/json;charset=utf-8")
	public String olaMundo() {
		return "Olá mundo usuario!";
	}

	@GET
	@Path("/listaatualizacao")
	@Produces(MediaType.APPLICATION_JSON)
	public String listaAtualizacao() {
		listaAtualizacoes = dao.listaAtualizacoes(Usuario.class);
		System.out.println("Tamanho da lista: " + listaAtualizacoes.size());
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String lista = gson.toJson(listaAtualizacoes);
		System.out.println(lista);
		return lista;
	}

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(String json) {
		System.out.println("INSERIR: " + json);
		dao = new DAOGenerico();
		gson = new Gson();
		try {
			Usuario user = gson.fromJson(json, Usuario.class);
			user.setStatusSincronizacao("SINCRONIZADO");
			dao.inserir(user);
			return Response.status(200).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
/*	@POST
	@Path("/inserirrelacionamento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirRelacionamento(String json) {
		System.out.println("INSERIR: " + json);
		dao = new DAOGenerico();
		gson = new Gson();
		try {
			Usuario user = gson.fromJson(json, Usuario.class);
			List<Usuario> listaRegistro = new ArrayList<Usuario>();
			listaRegistroCurso = dao.procuraObjeto(Curso.class, user.getCurso.getRegistroUnico().toString());
			//System.out.println("Total de registros: " + listaRegistro.size() + user.getRegistroUnico().toString());
			for (Curso curso : listaRegistroCurso) {
				Curso objCurso = (Curso) dao.recuperaId(Curso.class, curso.getId());
				user.setCurso(objCurso);
				user.setStatusSincronizacao("SINCRONIZADO");
				//Vai dar problema porque vai tentar setar o ru do curso no usuario. Uma opção é criar o atributo ruCurso dentro de usuario
				dao.inserir(user);
			}	
			return Response.status(200).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}*/

	@POST
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterar(String json) {
		System.out.println("ALTERAR WS");
		dao = new DAOGenerico();
		gson = new Gson();
		try {
			Usuario user = gson.fromJson(json, Usuario.class);
			List<Usuario> listaRegistro = new ArrayList<Usuario>();
			// Procura o objeto pelo registro unico
			listaRegistro = dao.procuraObjeto(Usuario.class, user.getRegistroUnico());
			System.out.println("Total de registros: " + listaRegistro.size());
			for (Usuario users : listaRegistro) {
				Usuario alt = (Usuario) dao.recuperaId(Usuario.class, users.getId());
				alt.setNome(user.getNome());
				alt.setSenha(user.getSenha());
				alt.setUsuario(user.getUsuario());
				alt.setStatusSincronizacao("SINCRONIZADO");
				dao.alterar(users);
			}
			return Response.status(200).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(String json) {
		System.out.println("DELETAR WS");
		System.out.println("Registro: " + json);
		dao = new DAOGenerico();
		gson = new Gson();
		try {
			Usuario user = gson.fromJson(json, Usuario.class);
			List<Usuario> listaRegistro = new ArrayList<Usuario>();
			// Procura o objeto pelo registro unico
			listaRegistro = dao.procuraObjeto(Usuario.class, user.getRegistroUnico().toString());
			System.out.println("Total de registros: " + listaRegistro.size() + user.getRegistroUnico().toString());
			for (Usuario users : listaRegistro) {
				Usuario del = (Usuario) dao.recuperaId(Usuario.class, users.getId());
				dao.deletar(del);
			}
			return Response.status(200).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
