/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relestadocivil;
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
public class RelestadocivilJpaController implements Serializable {

    public RelestadocivilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relestadocivil relestadocivil) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relestadocivil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelestadocivil(relestadocivil.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relestadocivil " + relestadocivil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relestadocivil relestadocivil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relestadocivil = em.merge(relestadocivil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relestadocivil.getLocalidade();
                if (findRelestadocivil(id) == null) {
                    throw new NonexistentEntityException("The relestadocivil with id " + id + " no longer exists.");
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
            Relestadocivil relestadocivil;
            try {
                relestadocivil = em.getReference(Relestadocivil.class, id);
                relestadocivil.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relestadocivil with id " + id + " no longer exists.", enfe);
            }
            em.remove(relestadocivil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relestadocivil> findRelestadocivilEntities() {
        return findRelestadocivilEntities(true, -1, -1);
    }

    public List<Relestadocivil> findRelestadocivilEntities(int maxResults, int firstResult) {
        return findRelestadocivilEntities(false, maxResults, firstResult);
    }

    private List<Relestadocivil> findRelestadocivilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relestadocivil.class));
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

    public Relestadocivil findRelestadocivil(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relestadocivil.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelestadocivilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relestadocivil> rt = cq.from(Relestadocivil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
