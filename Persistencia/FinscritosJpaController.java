/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Finscritos;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
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
public class FinscritosJpaController implements Serializable {

    public FinscritosJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Finscritos finscritos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(finscritos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinscritos(finscritos.getIdlocalidade()) != null) {
                throw new PreexistingEntityException("Finscritos " + finscritos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Finscritos finscritos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            finscritos = em.merge(finscritos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = finscritos.getIdlocalidade();
                if (findFinscritos(id) == null) {
                    throw new NonexistentEntityException("The finscritos with id " + id + " no longer exists.");
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
            Finscritos finscritos;
            try {
                finscritos = em.getReference(Finscritos.class, id);
                finscritos.getIdlocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The finscritos with id " + id + " no longer exists.", enfe);
            }
            em.remove(finscritos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Finscritos> findFinscritosEntities() {
        return findFinscritosEntities(true, -1, -1);
    }

    public List<Finscritos> findFinscritosEntities(int maxResults, int firstResult) {
        return findFinscritosEntities(false, maxResults, firstResult);
    }

    private List<Finscritos> findFinscritosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Finscritos.class));
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

    public Finscritos findFinscritos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Finscritos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinscritosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Finscritos> rt = cq.from(Finscritos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
