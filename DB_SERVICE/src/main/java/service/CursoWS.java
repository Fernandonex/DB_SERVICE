package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientAsyncExecutor;
import org.glassfish.jersey.server.ManagedAsync;
import org.glassfish.jersey.server.ManagedAsyncExecutor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOCurso;
import model.Curso;

@Path("/curso")
public class CursoWS {
	DAOCurso dao;
	Gson gson;

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
	public  String listaAtualizacao() {
		try {
			dao = new DAOCurso();
			List<Curso> listaAtualizacoes = dao.listaAtualizacoes();
			System.out.println("Tamanho da lista: " + listaAtualizacoes.size());
			gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String lista = gson.toJson(listaAtualizacoes);
			System.out.println(lista);
			return lista;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
}
	

