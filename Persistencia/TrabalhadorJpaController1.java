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
import Modelo.Trabalhador;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MASSINGUE
 */
public class TrabalhadorJpaController1 implements Serializable {

    public TrabalhadorJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabalhador trabalhador) {
        if (trabalhador.getOficinamestreCollection() == null) {
            trabalhador.setOficinamestreCollection(new ArrayList<Oficinamestre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Oficinamestre> attachedOficinamestreCollection = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionOficinamestreToAttach : trabalhador.getOficinamestreCollection()) {
                oficinamestreCollectionOficinamestreToAttach = em.getReference(oficinamestreCollectionOficinamestreToAttach.getClass(), oficinamestreCollectionOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection.add(oficinamestreCollectionOficinamestreToAttach);
            }
            trabalhador.setOficinamestreCollection(attachedOficinamestreCollection);
            em.persist(trabalhador);
            for (Oficinamestre oficinamestreCollectionOficinamestre : trabalhador.getOficinamestreCollection()) {
                Trabalhador oldNrtrabalhadorOfOficinamestreCollectionOficinamestre = oficinamestreCollectionOficinamestre.getNrtrabalhador();
                oficinamestreCollectionOficinamestre.setNrtrabalhador(trabalhador);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
                if (oldNrtrabalhadorOfOficinamestreCollectionOficinamestre != null) {
                    oldNrtrabalhadorOfOficinamestreCollectionOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionOficinamestre);
                    oldNrtrabalhadorOfOficinamestreCollectionOficinamestre = em.merge(oldNrtrabalhadorOfOficinamestreCollectionOficinamestre);
                }
            }
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
            Trabalhador persistentTrabalhador = em.find(Trabalhador.class, trabalhador.getIdtabalhador());
            Collection<Oficinamestre> oficinamestreCollectionOld = persistentTrabalhador.getOficinamestreCollection();
            Collection<Oficinamestre> oficinamestreCollectionNew = trabalhador.getOficinamestreCollection();
            Collection<Oficinamestre> attachedOficinamestreCollectionNew = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionNewOficinamestreToAttach : oficinamestreCollectionNew) {
                oficinamestreCollectionNewOficinamestreToAttach = em.getReference(oficinamestreCollectionNewOficinamestreToAttach.getClass(), oficinamestreCollectionNewOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollectionNew.add(oficinamestreCollectionNewOficinamestreToAttach);
            }
            oficinamestreCollectionNew = attachedOficinamestreCollectionNew;
            trabalhador.setOficinamestreCollection(oficinamestreCollectionNew);
            trabalhador = em.merge(trabalhador);
            for (Oficinamestre oficinamestreCollectionOldOficinamestre : oficinamestreCollectionOld) {
                if (!oficinamestreCollectionNew.contains(oficinamestreCollectionOldOficinamestre)) {
                    oficinamestreCollectionOldOficinamestre.setNrtrabalhador(null);
                    oficinamestreCollectionOldOficinamestre = em.merge(oficinamestreCollectionOldOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollectionNewOficinamestre : oficinamestreCollectionNew) {
                if (!oficinamestreCollectionOld.contains(oficinamestreCollectionNewOficinamestre)) {
                    Trabalhador oldNrtrabalhadorOfOficinamestreCollectionNewOficinamestre = oficinamestreCollectionNewOficinamestre.getNrtrabalhador();
                    oficinamestreCollectionNewOficinamestre.setNrtrabalhador(trabalhador);
                    oficinamestreCollectionNewOficinamestre = em.merge(oficinamestreCollectionNewOficinamestre);
                    if (oldNrtrabalhadorOfOficinamestreCollectionNewOficinamestre != null && !oldNrtrabalhadorOfOficinamestreCollectionNewOficinamestre.equals(trabalhador)) {
                        oldNrtrabalhadorOfOficinamestreCollectionNewOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionNewOficinamestre);
                        oldNrtrabalhadorOfOficinamestreCollectionNewOficinamestre = em.merge(oldNrtrabalhadorOfOficinamestreCollectionNewOficinamestre);
                    }
                }
            }
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
            Collection<Oficinamestre> oficinamestreCollection = trabalhador.getOficinamestreCollection();
            for (Oficinamestre oficinamestreCollectionOficinamestre : oficinamestreCollection) {
                oficinamestreCollectionOficinamestre.setNrtrabalhador(null);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
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
