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
public class DAOUsuario {

	EntityManager em;

	public List listaUsuarios(Class T) {
		em = ConectaBanco.getInstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + T.getSimpleName());
		em.getTransaction().commit();
		em.clear();
		return q.getResultList();
	}

	public List procuraObjeto(Class T, String registro) {
		em = ConectaBanco.getInstancia().getEm();
		em.getTransaction().begin();
		Query q = em.createQuery("from " + T.getSimpleName() + " where (registroUnico is '" + registro + "')");
		em.getTransaction().commit();
		em.clear();
		return q.getResultList();
	}

	public void inserir(Object objeto) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			em.persist(objeto);
			em.getTransaction().commit();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletar(Object objeto) throws Exception {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
			objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
			em.remove(objeto);
			em.getTransaction().commit();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void alterar(Object objeto) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object recuperaId(Class classe, Long id) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			Object retornando = null;
			em.getTransaction().begin();
			retornando = em.find(classe, id);
			em.getTransaction().commit();
			em.clear();
			return retornando;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
