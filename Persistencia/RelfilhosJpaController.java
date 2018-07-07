/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relfilhos;
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
public class RelfilhosJpaController implements Serializable {

    public RelfilhosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relfilhos relfilhos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relfilhos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelfilhos(relfilhos.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relfilhos " + relfilhos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relfilhos relfilhos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relfilhos = em.merge(relfilhos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relfilhos.getLocalidade();
                if (findRelfilhos(id) == null) {
                    throw new NonexistentEntityException("The relfilhos with id " + id + " no longer exists.");
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
            Relfilhos relfilhos;
            try {
                relfilhos = em.getReference(Relfilhos.class, id);
                relfilhos.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relfilhos with id " + id + " no longer exists.", enfe);
            }
            em.remove(relfilhos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relfilhos> findRelfilhosEntities() {
        return findRelfilhosEntities(true, -1, -1);
    }

    public List<Relfilhos> findRelfilhosEntities(int maxResults, int firstResult) {
        return findRelfilhosEntities(false, maxResults, firstResult);
    }

    private List<Relfilhos> findRelfilhosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relfilhos.class));
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

    public Relfilhos findRelfilhos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relfilhos.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelfilhosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relfilhos> rt = cq.from(Relfilhos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
