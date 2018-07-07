/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relfreqcursoprof;
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
public class RelfreqcursoprofJpaController implements Serializable {

    public RelfreqcursoprofJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relfreqcursoprof relfreqcursoprof) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relfreqcursoprof);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelfreqcursoprof(relfreqcursoprof.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relfreqcursoprof " + relfreqcursoprof + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relfreqcursoprof relfreqcursoprof) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relfreqcursoprof = em.merge(relfreqcursoprof);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relfreqcursoprof.getLocalidade();
                if (findRelfreqcursoprof(id) == null) {
                    throw new NonexistentEntityException("The relfreqcursoprof with id " + id + " no longer exists.");
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
            Relfreqcursoprof relfreqcursoprof;
            try {
                relfreqcursoprof = em.getReference(Relfreqcursoprof.class, id);
                relfreqcursoprof.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relfreqcursoprof with id " + id + " no longer exists.", enfe);
            }
            em.remove(relfreqcursoprof);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relfreqcursoprof> findRelfreqcursoprofEntities() {
        return findRelfreqcursoprofEntities(true, -1, -1);
    }

    public List<Relfreqcursoprof> findRelfreqcursoprofEntities(int maxResults, int firstResult) {
        return findRelfreqcursoprofEntities(false, maxResults, firstResult);
    }

    private List<Relfreqcursoprof> findRelfreqcursoprofEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relfreqcursoprof.class));
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

    public Relfreqcursoprof findRelfreqcursoprof(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relfreqcursoprof.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelfreqcursoprofCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relfreqcursoprof> rt = cq.from(Relfreqcursoprof.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
