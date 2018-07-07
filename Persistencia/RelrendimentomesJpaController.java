/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relrendimentomes;
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
public class RelrendimentomesJpaController implements Serializable {

    public RelrendimentomesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relrendimentomes relrendimentomes) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relrendimentomes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelrendimentomes(relrendimentomes.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relrendimentomes " + relrendimentomes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relrendimentomes relrendimentomes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relrendimentomes = em.merge(relrendimentomes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relrendimentomes.getLocalidade();
                if (findRelrendimentomes(id) == null) {
                    throw new NonexistentEntityException("The relrendimentomes with id " + id + " no longer exists.");
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
            Relrendimentomes relrendimentomes;
            try {
                relrendimentomes = em.getReference(Relrendimentomes.class, id);
                relrendimentomes.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relrendimentomes with id " + id + " no longer exists.", enfe);
            }
            em.remove(relrendimentomes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relrendimentomes> findRelrendimentomesEntities() {
        return findRelrendimentomesEntities(true, -1, -1);
    }

    public List<Relrendimentomes> findRelrendimentomesEntities(int maxResults, int firstResult) {
        return findRelrendimentomesEntities(false, maxResults, firstResult);
    }

    private List<Relrendimentomes> findRelrendimentomesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relrendimentomes.class));
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

    public Relrendimentomes findRelrendimentomes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relrendimentomes.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelrendimentomesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relrendimentomes> rt = cq.from(Relrendimentomes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
