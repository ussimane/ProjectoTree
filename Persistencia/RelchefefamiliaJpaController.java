/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relchefefamilia;
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
public class RelchefefamiliaJpaController implements Serializable {

    public RelchefefamiliaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relchefefamilia relchefefamilia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relchefefamilia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelchefefamilia(relchefefamilia.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relchefefamilia " + relchefefamilia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relchefefamilia relchefefamilia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relchefefamilia = em.merge(relchefefamilia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relchefefamilia.getLocalidade();
                if (findRelchefefamilia(id) == null) {
                    throw new NonexistentEntityException("The relchefefamilia with id " + id + " no longer exists.");
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
            Relchefefamilia relchefefamilia;
            try {
                relchefefamilia = em.getReference(Relchefefamilia.class, id);
                relchefefamilia.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relchefefamilia with id " + id + " no longer exists.", enfe);
            }
            em.remove(relchefefamilia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relchefefamilia> findRelchefefamiliaEntities() {
        return findRelchefefamiliaEntities(true, -1, -1);
    }

    public List<Relchefefamilia> findRelchefefamiliaEntities(int maxResults, int firstResult) {
        return findRelchefefamiliaEntities(false, maxResults, firstResult);
    }

    private List<Relchefefamilia> findRelchefefamiliaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relchefefamilia.class));
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

    public Relchefefamilia findRelchefefamilia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relchefefamilia.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelchefefamiliaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relchefefamilia> rt = cq.from(Relchefefamilia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
