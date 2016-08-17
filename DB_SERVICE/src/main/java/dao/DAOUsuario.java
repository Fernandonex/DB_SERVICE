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

	public List<Usuario> listaUsuarios() {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			Query q = em.createQuery("from " + Usuario.class.getSimpleName());
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

	public List<Usuario> procuraPorEmail(String registro) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			Query q = em.createQuery("from " + Usuario.class.getSimpleName() + " where (email is '" + registro + "')");
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

	public boolean inserir(Object objeto) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			em.persist(objeto);
			em.flush();
			em.getTransaction().commit();
			em.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public void deletar(Object objeto) throws Exception {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
			objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
			em.remove(objeto);
			em.flush();
			em.getTransaction().commit();
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	public boolean alterar(Object objeto) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			em.getTransaction().begin();
			em.merge(objeto);
			em.flush();
			em.getTransaction().commit();
			em.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public Object recuperaId(Long id) {
		try {
			em = ConectaBanco.getInstancia().getEm();
			Object retornando = null;
			em.getTransaction().begin();
			retornando = em.find(Usuario.class, id);
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
