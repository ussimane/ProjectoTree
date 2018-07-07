/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relempregocontaoutrem;
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
public class RelempregocontaoutremJpaController implements Serializable {

    public RelempregocontaoutremJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relempregocontaoutrem relempregocontaoutrem) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relempregocontaoutrem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelempregocontaoutrem(relempregocontaoutrem.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relempregocontaoutrem " + relempregocontaoutrem + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relempregocontaoutrem relempregocontaoutrem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relempregocontaoutrem = em.merge(relempregocontaoutrem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relempregocontaoutrem.getLocalidade();
                if (findRelempregocontaoutrem(id) == null) {
                    throw new NonexistentEntityException("The relempregocontaoutrem with id " + id + " no longer exists.");
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
            Relempregocontaoutrem relempregocontaoutrem;
            try {
                relempregocontaoutrem = em.getReference(Relempregocontaoutrem.class, id);
                relempregocontaoutrem.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relempregocontaoutrem with id " + id + " no longer exists.", enfe);
            }
            em.remove(relempregocontaoutrem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relempregocontaoutrem> findRelempregocontaoutremEntities() {
        return findRelempregocontaoutremEntities(true, -1, -1);
    }

    public List<Relempregocontaoutrem> findRelempregocontaoutremEntities(int maxResults, int firstResult) {
        return findRelempregocontaoutremEntities(false, maxResults, firstResult);
    }

    private List<Relempregocontaoutrem> findRelempregocontaoutremEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relempregocontaoutrem.class));
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

    public Relempregocontaoutrem findRelempregocontaoutrem(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relempregocontaoutrem.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelempregocontaoutremCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relempregocontaoutrem> rt = cq.from(Relempregocontaoutrem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
