package dao;

import connection.ConectaBanco;
import model.Curso;
import model.Usuario;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Synchronization;

/**
 *
 * @author Fernando
 */
public class DAOCurso {

	EntityManager em;

	public synchronized List<Curso> listaAtualizacoes() {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			Query q = em.createQuery("from " + Curso.class.getSimpleName());
			em.flush();
			em.getTransaction().commit();
			if (em.getTransaction() != null) {
				em.clear();
			}
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;

		}
	}

	// Nao funcional
	@Deprecated
	public List<Curso> listaAtualizacoes2() {
		try {
			em = ConectaBanco.getInstancia().getEm();
			Query q = em.createQuery("from " + Curso.class.getSimpleName());
			return q.getResultList();
		} finally {
			em.clear();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Curso> procuraObjeto(String registro) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			Query q = em.createQuery(
					"from " + Curso.class.getSimpleName() + " where (registroUnico is '" + registro + "')");
			em.flush();
			em.getTransaction().commit();
			em.clear();
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		}
	}

	public Object recuperaId(Long id) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			Object retornando = null;
			em.getTransaction().begin();
			retornando = em.find(Curso.class, id);
			em.flush();
			em.getTransaction().commit();
			em.clear();
			return retornando;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		}
	}
}
