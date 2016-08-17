package dao;

import connection.ConectaBanco;
import model.Curso;
import model.Usuario;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Synchronization;

/**
 *
 * @author Fernando
 */
public class DAOCurso {

	EntityManager em;
	/*EntityManagerFactory fac = Persistence.createEntityManagerFactory("DB_SERVICE");
	EntityManager em2 = fac.createEntityManager();

	public synchronized List<Curso> listaAtualizacoes3() {
		try {
			em2.getTransaction().begin();
			Query q = em2.createQuery("from " + Curso.class.getSimpleName());
			em2.flush();
			em2.getTransaction().commit();
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em2.clear();
			em2.close();
			fac.close();
		}
	}*/

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
