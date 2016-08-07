package simulaWeb;

import dao.DAOGenerico;
import model.Curso;

public class Main {

	public static void main(String[] args) {
		DAOGenerico daoGenerico = new DAOGenerico();
		for (int i = 0; i < 3; i++) {
			
		
		Curso curso = new Curso();
		curso.setDescricao("DEscr");
		curso.setNome("curso" + i);
		curso.setRegistroUnico("");
		curso.setStatusSincronizacao("HABILITADO");
		daoGenerico.inserir(curso);
	}
	}
}
