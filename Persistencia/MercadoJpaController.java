/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Mercado;
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
public class MercadoJpaController implements Serializable {

     public MercadoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public void create(Mercado mercado) {
        if (mercado.getOficinamestreCollection() == null) {
            mercado.setOficinamestreCollection(new ArrayList<Oficinamestre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Oficinamestre> attachedOficinamestreCollection = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionOficinamestreToAttach : mercado.getOficinamestreCollection()) {
                oficinamestreCollectionOficinamestreToAttach = em.getReference(oficinamestreCollectionOficinamestreToAttach.getClass(), oficinamestreCollectionOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection.add(oficinamestreCollectionOficinamestreToAttach);
            }
            mercado.setOficinamestreCollection(attachedOficinamestreCollection);
            em.persist(mercado);
            for (Oficinamestre oficinamestreCollectionOficinamestre : mercado.getOficinamestreCollection()) {
                Mercado oldIdmercadoOfOficinamestreCollectionOficinamestre = oficinamestreCollectionOficinamestre.getIdmercado();
                oficinamestreCollectionOficinamestre.setIdmercado(mercado);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
                if (oldIdmercadoOfOficinamestreCollectionOficinamestre != null) {
                    oldIdmercadoOfOficinamestreCollectionOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionOficinamestre);
                    oldIdmercadoOfOficinamestreCollectionOficinamestre = em.merge(oldIdmercadoOfOficinamestreCollectionOficinamestre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mercado mercado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mercado persistentMercado = em.find(Mercado.class, mercado.getIdmercado());
            Collection<Oficinamestre> oficinamestreCollectionOld = persistentMercado.getOficinamestreCollection();
            Collection<Oficinamestre> oficinamestreCollectionNew = mercado.getOficinamestreCollection();
            Collection<Oficinamestre> attachedOficinamestreCollectionNew = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionNewOficinamestreToAttach : oficinamestreCollectionNew) {
                oficinamestreCollectionNewOficinamestreToAttach = em.getReference(oficinamestreCollectionNewOficinamestreToAttach.getClass(), oficinamestreCollectionNewOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollectionNew.add(oficinamestreCollectionNewOficinamestreToAttach);
            }
            oficinamestreCollectionNew = attachedOficinamestreCollectionNew;
            mercado.setOficinamestreCollection(oficinamestreCollectionNew);
            mercado = em.merge(mercado);
            for (Oficinamestre oficinamestreCollectionOldOficinamestre : oficinamestreCollectionOld) {
                if (!oficinamestreCollectionNew.contains(oficinamestreCollectionOldOficinamestre)) {
                    oficinamestreCollectionOldOficinamestre.setIdmercado(null);
                    oficinamestreCollectionOldOficinamestre = em.merge(oficinamestreCollectionOldOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollectionNewOficinamestre : oficinamestreCollectionNew) {
                if (!oficinamestreCollectionOld.contains(oficinamestreCollectionNewOficinamestre)) {
                    Mercado oldIdmercadoOfOficinamestreCollectionNewOficinamestre = oficinamestreCollectionNewOficinamestre.getIdmercado();
                    oficinamestreCollectionNewOficinamestre.setIdmercado(mercado);
                    oficinamestreCollectionNewOficinamestre = em.merge(oficinamestreCollectionNewOficinamestre);
                    if (oldIdmercadoOfOficinamestreCollectionNewOficinamestre != null && !oldIdmercadoOfOficinamestreCollectionNewOficinamestre.equals(mercado)) {
                        oldIdmercadoOfOficinamestreCollectionNewOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionNewOficinamestre);
                        oldIdmercadoOfOficinamestreCollectionNewOficinamestre = em.merge(oldIdmercadoOfOficinamestreCollectionNewOficinamestre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mercado.getIdmercado();
                if (findMercado(id) == null) {
                    throw new NonexistentEntityException("The mercado with id " + id + " no longer exists.");
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
            Mercado mercado;
            try {
                mercado = em.getReference(Mercado.class, id);
                mercado.getIdmercado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mercado with id " + id + " no longer exists.", enfe);
            }
            Collection<Oficinamestre> oficinamestreCollection = mercado.getOficinamestreCollection();
            for (Oficinamestre oficinamestreCollectionOficinamestre : oficinamestreCollection) {
                oficinamestreCollectionOficinamestre.setIdmercado(null);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
            }
            em.remove(mercado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Mercado> findMercadoEntities() {
        return findMercadoEntities(true, -1, -1);
    }

    public List<Mercado> findMercadoEntities(int maxResults, int firstResult) {
        return findMercadoEntities(false, maxResults, firstResult);
    }

    private List<Mercado> findMercadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mercado.class));
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

    public Mercado findMercado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mercado.class, id);
        } finally {
            em.close();
        }
    }

    public int getMercadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mercado> rt = cq.from(Mercado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
