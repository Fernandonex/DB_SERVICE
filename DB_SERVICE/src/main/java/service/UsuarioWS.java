package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOCurso;
import dao.DAOUsuario;
import model.Curso;
import model.Usuario;

@Path("/usuario")
public class UsuarioWS {
	DAOUsuario daoUsuario = new DAOUsuario();
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
	@Path("/listausuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public String listaAtualizacao() {
		listaAtualizacoes = daoUsuario.listaUsuarios(Usuario.class);
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
	public Response inserirRelacionamento(String json) {
		System.out.println("INSERIR: " + json);
		daoUsuario = new DAOUsuario();
		DAOCurso daoCurso = new DAOCurso();
		gson = new Gson();
		try {
			// Converte json
			Usuario user = gson.fromJson(json, Usuario.class);
			// Busca curso pelo RU
			List<Curso> listaRegistroCurso = new ArrayList<Curso>();
			listaRegistroCurso = daoCurso.procuraObjeto(Curso.class, user.getCurso().getRegistroUnico().toString());
			// Percorre a lista com o Curso referente ao RU recuperado
			for (Curso curso : listaRegistroCurso) {
				// Busca a ID do curso no banco e atribui ao objetoCurso
				Curso objetoCurso = (Curso) daoCurso.recuperaId(Curso.class, curso.getId());
				// Inseri o usuario com o curso recuperado
				user.setCurso(objetoCurso);
				daoUsuario.inserir(user);
			}
			return Response.status(200).build();
		} catch (Exception e) {
			System.out.println("Não inseriu");
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterar(String json) {
		System.out.println("ALTERAR WS");
		daoUsuario = new DAOUsuario();
		gson = new Gson();
		try {
			Usuario user = gson.fromJson(json, Usuario.class);
			List<Usuario> listaRegistro = new ArrayList<Usuario>();
			// Procura o objeto pelo registro unico
			listaRegistro = daoUsuario.procuraObjeto(Usuario.class, user.getRegistroUnico());
			System.out.println("Total de registros: " + listaRegistro.size());
			for (Usuario users : listaRegistro) {
				Usuario alt = (Usuario) daoUsuario.recuperaId(Usuario.class, users.getId());
				alt.setNome(user.getNome());
				alt.setSenha(user.getSenha());
				alt.setUsuario(user.getUsuario());
				alt.setStatusSincronizacao("SINCRONIZADO");
				daoUsuario.alterar(users);
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
		daoUsuario = new DAOUsuario();
		gson = new Gson();
		try {
			Usuario user = gson.fromJson(json, Usuario.class);
			List<Usuario> listaRegistro = new ArrayList<Usuario>();
			// Procura o objeto pelo registro unico
			listaRegistro = daoUsuario.procuraObjeto(Usuario.class, user.getRegistroUnico().toString());
			System.out.println("Total de registros: " + listaRegistro.size() + user.getRegistroUnico().toString());
			for (Usuario users : listaRegistro) {
				Usuario del = (Usuario) daoUsuario.recuperaId(Usuario.class, users.getId());
				daoUsuario.deletar(del);
			}
			return Response.status(200).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
