/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relfaixaetaria;
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
public class RelfaixaetariaJpaController implements Serializable {

    public RelfaixaetariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relfaixaetaria relfaixaetaria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relfaixaetaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelfaixaetaria(relfaixaetaria.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relfaixaetaria " + relfaixaetaria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relfaixaetaria relfaixaetaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relfaixaetaria = em.merge(relfaixaetaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relfaixaetaria.getLocalidade();
                if (findRelfaixaetaria(id) == null) {
                    throw new NonexistentEntityException("The relfaixaetaria with id " + id + " no longer exists.");
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
            Relfaixaetaria relfaixaetaria;
            try {
                relfaixaetaria = em.getReference(Relfaixaetaria.class, id);
                relfaixaetaria.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relfaixaetaria with id " + id + " no longer exists.", enfe);
            }
            em.remove(relfaixaetaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relfaixaetaria> findRelfaixaetariaEntities() {
        return findRelfaixaetariaEntities(true, -1, -1);
    }

    public List<Relfaixaetaria> findRelfaixaetariaEntities(int maxResults, int firstResult) {
        return findRelfaixaetariaEntities(false, maxResults, firstResult);
    }

    private List<Relfaixaetaria> findRelfaixaetariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relfaixaetaria.class));
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

    public Relfaixaetaria findRelfaixaetaria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relfaixaetaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelfaixaetariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relfaixaetaria> rt = cq.from(Relfaixaetaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
