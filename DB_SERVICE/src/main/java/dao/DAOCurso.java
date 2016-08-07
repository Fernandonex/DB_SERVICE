package dao;

import connection.ConectaBanco;
import model.Usuario;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Fernando
 */
public class DAOCurso {

	EntityManager em;

	public List listaAtualizacoes(Class T) {
		em = ConectaBanco.getInstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + T.getSimpleName());
		em.getTransaction().commit();
		return q.getResultList();
	}
	
	
	public List procuraObjeto(Class T, String registro) {
		em = ConectaBanco.getInstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + T.getSimpleName() + " where (registroUnico is '" + registro + "')");
		em.getTransaction().commit();
		return q.getResultList();
	}
	
	public Object recuperaId(Class classe, Long id) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			Object retornando = null;
			em.getTransaction().begin();
			retornando = em.find(classe, id);
			em.getTransaction().commit();
			return retornando;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
