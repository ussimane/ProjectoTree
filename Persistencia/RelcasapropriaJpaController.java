/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relcasapropria;
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
public class RelcasapropriaJpaController implements Serializable {

    public RelcasapropriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relcasapropria relcasapropria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relcasapropria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelcasapropria(relcasapropria.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relcasapropria " + relcasapropria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relcasapropria relcasapropria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relcasapropria = em.merge(relcasapropria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relcasapropria.getLocalidade();
                if (findRelcasapropria(id) == null) {
                    throw new NonexistentEntityException("The relcasapropria with id " + id + " no longer exists.");
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
            Relcasapropria relcasapropria;
            try {
                relcasapropria = em.getReference(Relcasapropria.class, id);
                relcasapropria.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relcasapropria with id " + id + " no longer exists.", enfe);
            }
            em.remove(relcasapropria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relcasapropria> findRelcasapropriaEntities() {
        return findRelcasapropriaEntities(true, -1, -1);
    }

    public List<Relcasapropria> findRelcasapropriaEntities(int maxResults, int firstResult) {
        return findRelcasapropriaEntities(false, maxResults, firstResult);
    }

    private List<Relcasapropria> findRelcasapropriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relcasapropria.class));
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

    public Relcasapropria findRelcasapropria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relcasapropria.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelcasapropriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relcasapropria> rt = cq.from(Relcasapropria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
