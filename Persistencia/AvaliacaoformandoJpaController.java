/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Avaliacaoformando;
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
public class AvaliacaoformandoJpaController implements Serializable {

    public AvaliacaoformandoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliacaoformando avaliacaoformando) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(avaliacaoformando);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAvaliacaoformando(avaliacaoformando.getIdavaliacaoformando()) != null) {
                throw new PreexistingEntityException("Avaliacaoformando " + avaliacaoformando + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacaoformando avaliacaoformando) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            avaliacaoformando = em.merge(avaliacaoformando);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacaoformando.getIdavaliacaoformando();
                if (findAvaliacaoformando(id) == null) {
                    throw new NonexistentEntityException("The avaliacaoformando with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacaoformando avaliacaoformando;
            try {
                avaliacaoformando = em.getReference(Avaliacaoformando.class, id);
                avaliacaoformando.getIdavaliacaoformando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacaoformando with id " + id + " no longer exists.", enfe);
            }
            em.remove(avaliacaoformando);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacaoformando> findAvaliacaoformandoEntities() {
        return findAvaliacaoformandoEntities(true, -1, -1);
    }

    public List<Avaliacaoformando> findAvaliacaoformandoEntities(int maxResults, int firstResult) {
        return findAvaliacaoformandoEntities(false, maxResults, firstResult);
    }

    private List<Avaliacaoformando> findAvaliacaoformandoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacaoformando.class));
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

    public Avaliacaoformando findAvaliacaoformando(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacaoformando.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoformandoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacaoformando> rt = cq.from(Avaliacaoformando.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
