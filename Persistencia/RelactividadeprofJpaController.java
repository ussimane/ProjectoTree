/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relactividadeprof;
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
public class RelactividadeprofJpaController implements Serializable {

    public RelactividadeprofJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relactividadeprof relactividadeprof) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relactividadeprof);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelactividadeprof(relactividadeprof.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relactividadeprof " + relactividadeprof + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relactividadeprof relactividadeprof) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relactividadeprof = em.merge(relactividadeprof);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relactividadeprof.getLocalidade();
                if (findRelactividadeprof(id) == null) {
                    throw new NonexistentEntityException("The relactividadeprof with id " + id + " no longer exists.");
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
            Relactividadeprof relactividadeprof;
            try {
                relactividadeprof = em.getReference(Relactividadeprof.class, id);
                relactividadeprof.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relactividadeprof with id " + id + " no longer exists.", enfe);
            }
            em.remove(relactividadeprof);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relactividadeprof> findRelactividadeprofEntities() {
        return findRelactividadeprofEntities(true, -1, -1);
    }

    public List<Relactividadeprof> findRelactividadeprofEntities(int maxResults, int firstResult) {
        return findRelactividadeprofEntities(false, maxResults, firstResult);
    }

    private List<Relactividadeprof> findRelactividadeprofEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relactividadeprof.class));
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

    public Relactividadeprof findRelactividadeprof(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relactividadeprof.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelactividadeprofCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relactividadeprof> rt = cq.from(Relactividadeprof.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
