/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relnrformando;
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
public class RelnrformandoJpaController implements Serializable {

    public RelnrformandoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relnrformando relnrformando) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relnrformando);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelnrformando(relnrformando.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relnrformando " + relnrformando + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relnrformando relnrformando) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relnrformando = em.merge(relnrformando);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relnrformando.getLocalidade();
                if (findRelnrformando(id) == null) {
                    throw new NonexistentEntityException("The relnrformando with id " + id + " no longer exists.");
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
            Relnrformando relnrformando;
            try {
                relnrformando = em.getReference(Relnrformando.class, id);
                relnrformando.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relnrformando with id " + id + " no longer exists.", enfe);
            }
            em.remove(relnrformando);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relnrformando> findRelnrformandoEntities() {
        return findRelnrformandoEntities(true, -1, -1);
    }

    public List<Relnrformando> findRelnrformandoEntities(int maxResults, int firstResult) {
        return findRelnrformandoEntities(false, maxResults, firstResult);
    }

    private List<Relnrformando> findRelnrformandoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relnrformando.class));
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

    public Relnrformando findRelnrformando(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relnrformando.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelnrformandoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relnrformando> rt = cq.from(Relnrformando.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
