package simulaWeb;

import java.util.concurrent.Future;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import dao.DAOGenerico;
import model.Curso;
import model2.EntidadeAcademico;
import model2.EntidadeAulasCursos;
import model2.EntidadeCurso;

public class Main {

	public static void main(String[] args) {
		DAOGenerico daoGenerico = new DAOGenerico();
		/*
		 * for (int i = 0; i < 3; i++) {
		 * 
		 * 
		 * Curso curso = new Curso(); curso.setDescricao("DEscr");
		 * curso.setNome("curso" + i); curso.setRegistroUnico("");
		 * curso.setStatusSincronizacao("HABILITADO");
		 * daoGenerico.inserir(curso); }
		 */

		/*EntidadeAcademico academico = new EntidadeAcademico();
		academico.setEmail("Fernando");
		academico.setNomeAcademico("Fernando");
		academico.setSenha("123");
		academico.setUsuario("Fernando");
		daoGenerico.inserir(academico);

		for (int i = 0; i < 3; i++) {

			EntidadeCurso curso = new EntidadeCurso();
			curso.setDescricaoCurso("DEscr");
			curso.setNomeCurso("curso" + i);
			daoGenerico.inserir(curso);
		}*/
		
		EntidadeAcademico acadRec= (EntidadeAcademico) daoGenerico.recuperaId(EntidadeAcademico.class, 1140L);
		EntidadeCurso cursoRec= (EntidadeCurso) daoGenerico.recuperaId(EntidadeCurso.class, 1146L);
System.out.println(acadRec.getNomeAcademico());
System.out.println(cursoRec.getNomeCurso());
		
		EntidadeAulasCursos relaciona = new EntidadeAulasCursos();
		relaciona.setAcademico(acadRec);
		relaciona.setCurso(cursoRec);
		relaciona.setPontuacao(1222);
		daoGenerico.inserir(relaciona);
		
		
		
		
		

		/*
		 * final WebTarget target = ClientBuilder.newClient()
		 * .target("http://localhost:8080/DB_SERVICE/curso/listaatualizacao");
		 * 
		 * for (int i = 0; i < 10; i++) {
		 * 
		 * System.out.println(target.request().async().get()); }
		 */ }

}
