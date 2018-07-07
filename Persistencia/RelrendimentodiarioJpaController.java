/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relrendimentodiario;
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
public class RelrendimentodiarioJpaController implements Serializable {

    public RelrendimentodiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relrendimentodiario relrendimentodiario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relrendimentodiario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelrendimentodiario(relrendimentodiario.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relrendimentodiario " + relrendimentodiario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relrendimentodiario relrendimentodiario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relrendimentodiario = em.merge(relrendimentodiario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relrendimentodiario.getLocalidade();
                if (findRelrendimentodiario(id) == null) {
                    throw new NonexistentEntityException("The relrendimentodiario with id " + id + " no longer exists.");
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
            Relrendimentodiario relrendimentodiario;
            try {
                relrendimentodiario = em.getReference(Relrendimentodiario.class, id);
                relrendimentodiario.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relrendimentodiario with id " + id + " no longer exists.", enfe);
            }
            em.remove(relrendimentodiario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relrendimentodiario> findRelrendimentodiarioEntities() {
        return findRelrendimentodiarioEntities(true, -1, -1);
    }

    public List<Relrendimentodiario> findRelrendimentodiarioEntities(int maxResults, int firstResult) {
        return findRelrendimentodiarioEntities(false, maxResults, firstResult);
    }

    private List<Relrendimentodiario> findRelrendimentodiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relrendimentodiario.class));
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

    public Relrendimentodiario findRelrendimentodiario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relrendimentodiario.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelrendimentodiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relrendimentodiario> rt = cq.from(Relrendimentodiario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
