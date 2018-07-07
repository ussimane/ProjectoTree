/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Trabalhador;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MASSINGUE
 */
public class TrabalhadorJpaController implements Serializable {

     public TrabalhadorJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabalhador trabalhador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(trabalhador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabalhador trabalhador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            trabalhador = em.merge(trabalhador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = trabalhador.getIdtabalhador();
                if (findTrabalhador(id) == null) {
                    throw new NonexistentEntityException("The trabalhador with id " + id + " no longer exists.");
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
            Trabalhador trabalhador;
            try {
                trabalhador = em.getReference(Trabalhador.class, id);
                trabalhador.getIdtabalhador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabalhador with id " + id + " no longer exists.", enfe);
            }
            em.remove(trabalhador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabalhador> findTrabalhadorEntities() {
        return findTrabalhadorEntities(true, -1, -1);
    }

    public List<Trabalhador> findTrabalhadorEntities(int maxResults, int firstResult) {
        return findTrabalhadorEntities(false, maxResults, firstResult);
    }

    private List<Trabalhador> findTrabalhadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabalhador.class));
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

    public Trabalhador findTrabalhador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabalhador.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabalhadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabalhador> rt = cq.from(Trabalhador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
