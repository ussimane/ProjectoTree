/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Reldeficiente;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MASSINGUE
 */
public class ReldeficienteJpaController implements Serializable {

    public ReldeficienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reldeficiente reldeficiente) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reldeficiente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReldeficiente(reldeficiente.getLocalidade()) != null) {
                throw new PreexistingEntityException("Reldeficiente " + reldeficiente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reldeficiente reldeficiente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reldeficiente = em.merge(reldeficiente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reldeficiente.getLocalidade();
                if (findReldeficiente(id) == null) {
                    throw new NonexistentEntityException("The reldeficiente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reldeficiente reldeficiente;
            try {
                reldeficiente = em.getReference(Reldeficiente.class, id);
                reldeficiente.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reldeficiente with id " + id + " no longer exists.", enfe);
            }
            em.remove(reldeficiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reldeficiente> findReldeficienteEntities() {
        return findReldeficienteEntities(true, -1, -1);
    }

    public List<Reldeficiente> findReldeficienteEntities(int maxResults, int firstResult) {
        return findReldeficienteEntities(false, maxResults, firstResult);
    }

    private List<Reldeficiente> findReldeficienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reldeficiente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reldeficiente findReldeficiente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reldeficiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getReldeficienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reldeficiente> rt = cq.from(Reldeficiente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
