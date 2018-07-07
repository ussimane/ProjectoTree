/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Reldiplocursoprof;
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
public class ReldiplocursoprofJpaController implements Serializable {

    public ReldiplocursoprofJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reldiplocursoprof reldiplocursoprof) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reldiplocursoprof);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReldiplocursoprof(reldiplocursoprof.getLocalidade()) != null) {
                throw new PreexistingEntityException("Reldiplocursoprof " + reldiplocursoprof + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reldiplocursoprof reldiplocursoprof) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reldiplocursoprof = em.merge(reldiplocursoprof);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reldiplocursoprof.getLocalidade();
                if (findReldiplocursoprof(id) == null) {
                    throw new NonexistentEntityException("The reldiplocursoprof with id " + id + " no longer exists.");
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
            Reldiplocursoprof reldiplocursoprof;
            try {
                reldiplocursoprof = em.getReference(Reldiplocursoprof.class, id);
                reldiplocursoprof.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reldiplocursoprof with id " + id + " no longer exists.", enfe);
            }
            em.remove(reldiplocursoprof);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reldiplocursoprof> findReldiplocursoprofEntities() {
        return findReldiplocursoprofEntities(true, -1, -1);
    }

    public List<Reldiplocursoprof> findReldiplocursoprofEntities(int maxResults, int firstResult) {
        return findReldiplocursoprofEntities(false, maxResults, firstResult);
    }

    private List<Reldiplocursoprof> findReldiplocursoprofEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reldiplocursoprof.class));
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

    public Reldiplocursoprof findReldiplocursoprof(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reldiplocursoprof.class, id);
        } finally {
            em.close();
        }
    }

    public int getReldiplocursoprofCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reldiplocursoprof> rt = cq.from(Reldiplocursoprof.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
