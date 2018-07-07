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
import Modelo.Oficinamestre;
import Modelo.Producao;
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
public class ProducaoJpaController implements Serializable {

    public ProducaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public void create(Producao producao) {
        if (producao.getOficinamestreCollection() == null) {
            producao.setOficinamestreCollection(new ArrayList<Oficinamestre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Oficinamestre> attachedOficinamestreCollection = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionOficinamestreToAttach : producao.getOficinamestreCollection()) {
                oficinamestreCollectionOficinamestreToAttach = em.getReference(oficinamestreCollectionOficinamestreToAttach.getClass(), oficinamestreCollectionOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection.add(oficinamestreCollectionOficinamestreToAttach);
            }
            producao.setOficinamestreCollection(attachedOficinamestreCollection);
            em.persist(producao);
            for (Oficinamestre oficinamestreCollectionOficinamestre : producao.getOficinamestreCollection()) {
                Producao oldIdproducaoOfOficinamestreCollectionOficinamestre = oficinamestreCollectionOficinamestre.getIdproducao();
                oficinamestreCollectionOficinamestre.setIdproducao(producao);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
                if (oldIdproducaoOfOficinamestreCollectionOficinamestre != null) {
                    oldIdproducaoOfOficinamestreCollectionOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionOficinamestre);
                    oldIdproducaoOfOficinamestreCollectionOficinamestre = em.merge(oldIdproducaoOfOficinamestreCollectionOficinamestre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producao producao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producao persistentProducao = em.find(Producao.class, producao.getIdproducao());
            Collection<Oficinamestre> oficinamestreCollectionOld = persistentProducao.getOficinamestreCollection();
            Collection<Oficinamestre> oficinamestreCollectionNew = producao.getOficinamestreCollection();
            Collection<Oficinamestre> attachedOficinamestreCollectionNew = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionNewOficinamestreToAttach : oficinamestreCollectionNew) {
                oficinamestreCollectionNewOficinamestreToAttach = em.getReference(oficinamestreCollectionNewOficinamestreToAttach.getClass(), oficinamestreCollectionNewOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollectionNew.add(oficinamestreCollectionNewOficinamestreToAttach);
            }
            oficinamestreCollectionNew = attachedOficinamestreCollectionNew;
            producao.setOficinamestreCollection(oficinamestreCollectionNew);
            producao = em.merge(producao);
            for (Oficinamestre oficinamestreCollectionOldOficinamestre : oficinamestreCollectionOld) {
                if (!oficinamestreCollectionNew.contains(oficinamestreCollectionOldOficinamestre)) {
                    oficinamestreCollectionOldOficinamestre.setIdproducao(null);
                    oficinamestreCollectionOldOficinamestre = em.merge(oficinamestreCollectionOldOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollectionNewOficinamestre : oficinamestreCollectionNew) {
                if (!oficinamestreCollectionOld.contains(oficinamestreCollectionNewOficinamestre)) {
                    Producao oldIdproducaoOfOficinamestreCollectionNewOficinamestre = oficinamestreCollectionNewOficinamestre.getIdproducao();
                    oficinamestreCollectionNewOficinamestre.setIdproducao(producao);
                    oficinamestreCollectionNewOficinamestre = em.merge(oficinamestreCollectionNewOficinamestre);
                    if (oldIdproducaoOfOficinamestreCollectionNewOficinamestre != null && !oldIdproducaoOfOficinamestreCollectionNewOficinamestre.equals(producao)) {
                        oldIdproducaoOfOficinamestreCollectionNewOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionNewOficinamestre);
                        oldIdproducaoOfOficinamestreCollectionNewOficinamestre = em.merge(oldIdproducaoOfOficinamestreCollectionNewOficinamestre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producao.getIdproducao();
                if (findProducao(id) == null) {
                    throw new NonexistentEntityException("The producao with id " + id + " no longer exists.");
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
            Producao producao;
            try {
                producao = em.getReference(Producao.class, id);
                producao.getIdproducao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producao with id " + id + " no longer exists.", enfe);
            }
            Collection<Oficinamestre> oficinamestreCollection = producao.getOficinamestreCollection();
            for (Oficinamestre oficinamestreCollectionOficinamestre : oficinamestreCollection) {
                oficinamestreCollectionOficinamestre.setIdproducao(null);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
            }
            em.remove(producao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Producao> findProducaoEntities() {
        return findProducaoEntities(true, -1, -1);
    }

    public List<Producao> findProducaoEntities(int maxResults, int firstResult) {
        return findProducaoEntities(false, maxResults, firstResult);
    }

    private List<Producao> findProducaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producao.class));
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

    public Producao findProducao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producao.class, id);
        } finally {
            em.close();
        }
    }

    public int getProducaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producao> rt = cq.from(Producao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
