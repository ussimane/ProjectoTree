/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Cursoformacao;
import Modelo.Supervisao;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MASSINGUE
 */
public class SupervisaoJpaController implements Serializable {

    public SupervisaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Supervisao supervisao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursoformacao idcursoformacao = supervisao.getIdcursoformacao();
            if (idcursoformacao != null) {
                idcursoformacao = em.getReference(idcursoformacao.getClass(), idcursoformacao.getIdcursoformacao());
                supervisao.setIdcursoformacao(idcursoformacao);
            }
            em.persist(supervisao);
            if (idcursoformacao != null) {
                idcursoformacao.getSupervisaoCollection().add(supervisao);
                idcursoformacao = em.merge(idcursoformacao);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSupervisao(supervisao.getIdsupervisao()) != null) {
                throw new PreexistingEntityException("Supervisao " + supervisao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Supervisao supervisao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Supervisao persistentSupervisao = em.find(Supervisao.class, supervisao.getIdsupervisao());
            Cursoformacao idcursoformacaoOld = persistentSupervisao.getIdcursoformacao();
            Cursoformacao idcursoformacaoNew = supervisao.getIdcursoformacao();
            if (idcursoformacaoNew != null) {
                idcursoformacaoNew = em.getReference(idcursoformacaoNew.getClass(), idcursoformacaoNew.getIdcursoformacao());
                supervisao.setIdcursoformacao(idcursoformacaoNew);
            }
            supervisao = em.merge(supervisao);
            if (idcursoformacaoOld != null && !idcursoformacaoOld.equals(idcursoformacaoNew)) {
                idcursoformacaoOld.getSupervisaoCollection().remove(supervisao);
                idcursoformacaoOld = em.merge(idcursoformacaoOld);
            }
            if (idcursoformacaoNew != null && !idcursoformacaoNew.equals(idcursoformacaoOld)) {
                idcursoformacaoNew.getSupervisaoCollection().add(supervisao);
                idcursoformacaoNew = em.merge(idcursoformacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = supervisao.getIdsupervisao();
                if (findSupervisao(id) == null) {
                    throw new NonexistentEntityException("The supervisao with id " + id + " no longer exists.");
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
            Supervisao supervisao;
            try {
                supervisao = em.getReference(Supervisao.class, id);
                supervisao.getIdsupervisao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The supervisao with id " + id + " no longer exists.", enfe);
            }
            Cursoformacao idcursoformacao = supervisao.getIdcursoformacao();
            if (idcursoformacao != null) {
                idcursoformacao.getSupervisaoCollection().remove(supervisao);
                idcursoformacao = em.merge(idcursoformacao);
            }
            em.remove(supervisao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Supervisao> findSupervisaoEntities() {
        return findSupervisaoEntities(true, -1, -1);
    }

    public List<Supervisao> findSupervisaoEntities(int maxResults, int firstResult) {
        return findSupervisaoEntities(false, maxResults, firstResult);
    }

    private List<Supervisao> findSupervisaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Supervisao.class));
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

    public Supervisao findSupervisao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Supervisao.class, id);
        } finally {
            em.close();
        }
    }

    public int getSupervisaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Supervisao> rt = cq.from(Supervisao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
