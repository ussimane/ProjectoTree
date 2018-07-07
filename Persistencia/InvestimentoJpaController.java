/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Investimento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Oficinamestre;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class InvestimentoJpaController implements Serializable {

    public InvestimentoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Investimento investimento) {
        if (investimento.getOficinamestreCollection() == null) {
            investimento.setOficinamestreCollection(new ArrayList<Oficinamestre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Oficinamestre> attachedOficinamestreCollection = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionOficinamestreToAttach : investimento.getOficinamestreCollection()) {
                oficinamestreCollectionOficinamestreToAttach = em.getReference(oficinamestreCollectionOficinamestreToAttach.getClass(), oficinamestreCollectionOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection.add(oficinamestreCollectionOficinamestreToAttach);
            }
            investimento.setOficinamestreCollection(attachedOficinamestreCollection);
            em.persist(investimento);
            for (Oficinamestre oficinamestreCollectionOficinamestre : investimento.getOficinamestreCollection()) {
                Investimento oldIdanoinvestimentosequipamentosOfOficinamestreCollectionOficinamestre = oficinamestreCollectionOficinamestre.getIdanoinvestimentosequipamentos();
                oficinamestreCollectionOficinamestre.setIdanoinvestimentosequipamentos(investimento);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
                if (oldIdanoinvestimentosequipamentosOfOficinamestreCollectionOficinamestre != null) {
                    oldIdanoinvestimentosequipamentosOfOficinamestreCollectionOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionOficinamestre);
                    oldIdanoinvestimentosequipamentosOfOficinamestreCollectionOficinamestre = em.merge(oldIdanoinvestimentosequipamentosOfOficinamestreCollectionOficinamestre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Investimento investimento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Investimento persistentInvestimento = em.find(Investimento.class, investimento.getIdinvestimento());
            Collection<Oficinamestre> oficinamestreCollectionOld = persistentInvestimento.getOficinamestreCollection();
            Collection<Oficinamestre> oficinamestreCollectionNew = investimento.getOficinamestreCollection();
            Collection<Oficinamestre> attachedOficinamestreCollectionNew = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionNewOficinamestreToAttach : oficinamestreCollectionNew) {
                oficinamestreCollectionNewOficinamestreToAttach = em.getReference(oficinamestreCollectionNewOficinamestreToAttach.getClass(), oficinamestreCollectionNewOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollectionNew.add(oficinamestreCollectionNewOficinamestreToAttach);
            }
            oficinamestreCollectionNew = attachedOficinamestreCollectionNew;
            investimento.setOficinamestreCollection(oficinamestreCollectionNew);
            investimento = em.merge(investimento);
            for (Oficinamestre oficinamestreCollectionOldOficinamestre : oficinamestreCollectionOld) {
                if (!oficinamestreCollectionNew.contains(oficinamestreCollectionOldOficinamestre)) {
                    oficinamestreCollectionOldOficinamestre.setIdanoinvestimentosequipamentos(null);
                    oficinamestreCollectionOldOficinamestre = em.merge(oficinamestreCollectionOldOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollectionNewOficinamestre : oficinamestreCollectionNew) {
                if (!oficinamestreCollectionOld.contains(oficinamestreCollectionNewOficinamestre)) {
                    Investimento oldIdanoinvestimentosequipamentosOfOficinamestreCollectionNewOficinamestre = oficinamestreCollectionNewOficinamestre.getIdanoinvestimentosequipamentos();
                    oficinamestreCollectionNewOficinamestre.setIdanoinvestimentosequipamentos(investimento);
                    oficinamestreCollectionNewOficinamestre = em.merge(oficinamestreCollectionNewOficinamestre);
                    if (oldIdanoinvestimentosequipamentosOfOficinamestreCollectionNewOficinamestre != null && !oldIdanoinvestimentosequipamentosOfOficinamestreCollectionNewOficinamestre.equals(investimento)) {
                        oldIdanoinvestimentosequipamentosOfOficinamestreCollectionNewOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionNewOficinamestre);
                        oldIdanoinvestimentosequipamentosOfOficinamestreCollectionNewOficinamestre = em.merge(oldIdanoinvestimentosequipamentosOfOficinamestreCollectionNewOficinamestre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = investimento.getIdinvestimento();
                if (findInvestimento(id) == null) {
                    throw new NonexistentEntityException("The investimento with id " + id + " no longer exists.");
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
            Investimento investimento;
            try {
                investimento = em.getReference(Investimento.class, id);
                investimento.getIdinvestimento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The investimento with id " + id + " no longer exists.", enfe);
            }
            Collection<Oficinamestre> oficinamestreCollection = investimento.getOficinamestreCollection();
            for (Oficinamestre oficinamestreCollectionOficinamestre : oficinamestreCollection) {
                oficinamestreCollectionOficinamestre.setIdanoinvestimentosequipamentos(null);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
            }
            em.remove(investimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Investimento> findInvestimentoEntities() {
        return findInvestimentoEntities(true, -1, -1);
    }

    public List<Investimento> findInvestimentoEntities(int maxResults, int firstResult) {
        return findInvestimentoEntities(false, maxResults, firstResult);
    }

    private List<Investimento> findInvestimentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Investimento.class));
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

    public Investimento findInvestimento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Investimento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvestimentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Investimento> rt = cq.from(Investimento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
