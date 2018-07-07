/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Relhabilitacao;
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
public class RelhabilitacaoJpaController implements Serializable {

    public RelhabilitacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relhabilitacao relhabilitacao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relhabilitacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelhabilitacao(relhabilitacao.getLocalidade()) != null) {
                throw new PreexistingEntityException("Relhabilitacao " + relhabilitacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relhabilitacao relhabilitacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relhabilitacao = em.merge(relhabilitacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = relhabilitacao.getLocalidade();
                if (findRelhabilitacao(id) == null) {
                    throw new NonexistentEntityException("The relhabilitacao with id " + id + " no longer exists.");
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
            Relhabilitacao relhabilitacao;
            try {
                relhabilitacao = em.getReference(Relhabilitacao.class, id);
                relhabilitacao.getLocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relhabilitacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(relhabilitacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relhabilitacao> findRelhabilitacaoEntities() {
        return findRelhabilitacaoEntities(true, -1, -1);
    }

    public List<Relhabilitacao> findRelhabilitacaoEntities(int maxResults, int firstResult) {
        return findRelhabilitacaoEntities(false, maxResults, firstResult);
    }

    private List<Relhabilitacao> findRelhabilitacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relhabilitacao.class));
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

    public Relhabilitacao findRelhabilitacao(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relhabilitacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelhabilitacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relhabilitacao> rt = cq.from(Relhabilitacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
