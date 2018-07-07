/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relactividadecontapropria;
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
public class RelactividadecontapropriaJpaController implements Serializable {

    public RelactividadecontapropriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relactividadecontapropria relactividadecontapropria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relactividadecontapropria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelactividadecontapropria(relactividadecontapropria.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relactividadecontapropria " + relactividadecontapropria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relactividadecontapropria relactividadecontapropria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relactividadecontapropria = em.merge(relactividadecontapropria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relactividadecontapropria.getLocalidade();
                if (findRelactividadecontapropria(id) == null) {
                    throw new NonexistentEntityException("The relactividadecontapropria with id " + id + " no longer exists.");
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
            Relactividadecontapropria relactividadecontapropria;
            try {
                relactividadecontapropria = em.getReference(Relactividadecontapropria.class, id);
                relactividadecontapropria.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relactividadecontapropria with id " + id + " no longer exists.", enfe);
            }
            em.remove(relactividadecontapropria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relactividadecontapropria> findRelactividadecontapropriaEntities() {
        return findRelactividadecontapropriaEntities(true, -1, -1);
    }

    public List<Relactividadecontapropria> findRelactividadecontapropriaEntities(int maxResults, int firstResult) {
        return findRelactividadecontapropriaEntities(false, maxResults, firstResult);
    }

    private List<Relactividadecontapropria> findRelactividadecontapropriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relactividadecontapropria.class));
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

    public Relactividadecontapropria findRelactividadecontapropria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relactividadecontapropria.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelactividadecontapropriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relactividadecontapropria> rt = cq.from(Relactividadecontapropria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
