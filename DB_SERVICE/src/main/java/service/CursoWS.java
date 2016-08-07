package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOCurso;
import dao.DAOGenerico;
import model.Curso;
import model.Curso;

@Path("/curso")
public class CursoWS {
	DAOCurso dao = new DAOCurso();
	Curso user = new Curso();
	Gson gson;
	List<Curso> listaAtualizacoes = new ArrayList<Curso>();

	@GET
	@Path("/olamundo")
	@Produces("application/json;charset=utf-8")
	public String olaMundo() {
		gson = new Gson();
		String str = gson.toJson("Olá mundo curso!");
		return str;
	}

	@GET
	@Path("/listaatualizacao")
	@Produces(MediaType.APPLICATION_JSON)
	public String listaAtualizacao() {
		dao = new DAOCurso();
		listaAtualizacoes = dao.listaAtualizacoes(Curso.class);
		System.out.println("Tamanho da lista: " + listaAtualizacoes.size());
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String lista = gson.toJson(listaAtualizacoes);
		System.out.println(lista);
		return lista;
	}	
}
