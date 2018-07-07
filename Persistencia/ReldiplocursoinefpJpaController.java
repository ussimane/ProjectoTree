/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Reldiplocursoinefp;
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
public class ReldiplocursoinefpJpaController implements Serializable {

    public ReldiplocursoinefpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reldiplocursoinefp reldiplocursoinefp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reldiplocursoinefp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReldiplocursoinefp(reldiplocursoinefp.getLocalidade()) != null) {
                throw new PreexistingEntityException("Reldiplocursoinefp " + reldiplocursoinefp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reldiplocursoinefp reldiplocursoinefp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reldiplocursoinefp = em.merge(reldiplocursoinefp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = reldiplocursoinefp.getLocalidade();
                if (findReldiplocursoinefp(id) == null) {
                    throw new NonexistentEntityException("The reldiplocursoinefp with id " + id + " no longer exists.");
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
            Reldiplocursoinefp reldiplocursoinefp;
            try {
                reldiplocursoinefp = em.getReference(Reldiplocursoinefp.class, id);
                reldiplocursoinefp.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reldiplocursoinefp with id " + id + " no longer exists.", enfe);
            }
            em.remove(reldiplocursoinefp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reldiplocursoinefp> findReldiplocursoinefpEntities() {
        return findReldiplocursoinefpEntities(true, -1, -1);
    }

    public List<Reldiplocursoinefp> findReldiplocursoinefpEntities(int maxResults, int firstResult) {
        return findReldiplocursoinefpEntities(false, maxResults, firstResult);
    }

    private List<Reldiplocursoinefp> findReldiplocursoinefpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reldiplocursoinefp.class));
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

    public Reldiplocursoinefp findReldiplocursoinefp(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reldiplocursoinefp.class, id);
        } finally {
            em.close();
        }
    }

    public int getReldiplocursoinefpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reldiplocursoinefp> rt = cq.from(Reldiplocursoinefp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
