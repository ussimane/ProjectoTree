/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Distrito;
import Modelo.Formador;
import Modelo.Pontofocal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Posto;
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
public class DistritoJpaController implements Serializable {

    public DistritoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   public void create(Distrito distrito) {
        if (distrito.getPostoCollection() == null) {
            distrito.setPostoCollection(new ArrayList<Posto>());
        }
        if (distrito.getPontofocalCollection() == null) {
            distrito.setPontofocalCollection(new ArrayList<Pontofocal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Posto> attachedPostoCollection = new ArrayList<Posto>();
            for (Posto postoCollectionPostoToAttach : distrito.getPostoCollection()) {
                postoCollectionPostoToAttach = em.getReference(postoCollectionPostoToAttach.getClass(), postoCollectionPostoToAttach.getIdposto());
                attachedPostoCollection.add(postoCollectionPostoToAttach);
            }
            distrito.setPostoCollection(attachedPostoCollection);
            Collection<Pontofocal> attachedPontofocalCollection = new ArrayList<Pontofocal>();
            for (Pontofocal pontofocalCollectionPontofocalToAttach : distrito.getPontofocalCollection()) {
                pontofocalCollectionPontofocalToAttach = em.getReference(pontofocalCollectionPontofocalToAttach.getClass(), pontofocalCollectionPontofocalToAttach.getIdpontofocal());
                attachedPontofocalCollection.add(pontofocalCollectionPontofocalToAttach);
            }
            distrito.setPontofocalCollection(attachedPontofocalCollection);
            em.persist(distrito);
            for (Posto postoCollectionPosto : distrito.getPostoCollection()) {
                Distrito oldIddistritoOfPostoCollectionPosto = postoCollectionPosto.getIddistrito();
                postoCollectionPosto.setIddistrito(distrito);
                postoCollectionPosto = em.merge(postoCollectionPosto);
                if (oldIddistritoOfPostoCollectionPosto != null) {
                    oldIddistritoOfPostoCollectionPosto.getPostoCollection().remove(postoCollectionPosto);
                    oldIddistritoOfPostoCollectionPosto = em.merge(oldIddistritoOfPostoCollectionPosto);
                }
            }
            for (Pontofocal pontofocalCollectionPontofocal : distrito.getPontofocalCollection()) {
                Distrito oldIddistritoOfPontofocalCollectionPontofocal = pontofocalCollectionPontofocal.getIddistrito();
                pontofocalCollectionPontofocal.setIddistrito(distrito);
                pontofocalCollectionPontofocal = em.merge(pontofocalCollectionPontofocal);
                if (oldIddistritoOfPontofocalCollectionPontofocal != null) {
                    oldIddistritoOfPontofocalCollectionPontofocal.getPontofocalCollection().remove(pontofocalCollectionPontofocal);
                    oldIddistritoOfPontofocalCollectionPontofocal = em.merge(oldIddistritoOfPontofocalCollectionPontofocal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Distrito distrito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito persistentDistrito = em.find(Distrito.class, distrito.getIddistrito());
            Collection<Posto> postoCollectionOld = persistentDistrito.getPostoCollection();
            Collection<Posto> postoCollectionNew = distrito.getPostoCollection();
            Collection<Pontofocal> pontofocalCollectionOld = persistentDistrito.getPontofocalCollection();
            Collection<Pontofocal> pontofocalCollectionNew = distrito.getPontofocalCollection();
            Collection<Posto> attachedPostoCollectionNew = new ArrayList<Posto>();
            for (Posto postoCollectionNewPostoToAttach : postoCollectionNew) {
                postoCollectionNewPostoToAttach = em.getReference(postoCollectionNewPostoToAttach.getClass(), postoCollectionNewPostoToAttach.getIdposto());
                attachedPostoCollectionNew.add(postoCollectionNewPostoToAttach);
            }
            postoCollectionNew = attachedPostoCollectionNew;
            distrito.setPostoCollection(postoCollectionNew);
            Collection<Pontofocal> attachedPontofocalCollectionNew = new ArrayList<Pontofocal>();
            for (Pontofocal pontofocalCollectionNewPontofocalToAttach : pontofocalCollectionNew) {
                pontofocalCollectionNewPontofocalToAttach = em.getReference(pontofocalCollectionNewPontofocalToAttach.getClass(), pontofocalCollectionNewPontofocalToAttach.getIdpontofocal());
                attachedPontofocalCollectionNew.add(pontofocalCollectionNewPontofocalToAttach);
            }
            pontofocalCollectionNew = attachedPontofocalCollectionNew;
            distrito.setPontofocalCollection(pontofocalCollectionNew);
            distrito = em.merge(distrito);
            for (Posto postoCollectionOldPosto : postoCollectionOld) {
                if (!postoCollectionNew.contains(postoCollectionOldPosto)) {
                    postoCollectionOldPosto.setIddistrito(null);
                    postoCollectionOldPosto = em.merge(postoCollectionOldPosto);
                }
            }
            for (Posto postoCollectionNewPosto : postoCollectionNew) {
                if (!postoCollectionOld.contains(postoCollectionNewPosto)) {
                    Distrito oldIddistritoOfPostoCollectionNewPosto = postoCollectionNewPosto.getIddistrito();
                    postoCollectionNewPosto.setIddistrito(distrito);
                    postoCollectionNewPosto = em.merge(postoCollectionNewPosto);
                    if (oldIddistritoOfPostoCollectionNewPosto != null && !oldIddistritoOfPostoCollectionNewPosto.equals(distrito)) {
                        oldIddistritoOfPostoCollectionNewPosto.getPostoCollection().remove(postoCollectionNewPosto);
                        oldIddistritoOfPostoCollectionNewPosto = em.merge(oldIddistritoOfPostoCollectionNewPosto);
                    }
                }
            }
            for (Pontofocal pontofocalCollectionOldPontofocal : pontofocalCollectionOld) {
                if (!pontofocalCollectionNew.contains(pontofocalCollectionOldPontofocal)) {
                    pontofocalCollectionOldPontofocal.setIddistrito(null);
                    pontofocalCollectionOldPontofocal = em.merge(pontofocalCollectionOldPontofocal);
                }
            }
            for (Pontofocal pontofocalCollectionNewPontofocal : pontofocalCollectionNew) {
                if (!pontofocalCollectionOld.contains(pontofocalCollectionNewPontofocal)) {
                    Distrito oldIddistritoOfPontofocalCollectionNewPontofocal = pontofocalCollectionNewPontofocal.getIddistrito();
                    pontofocalCollectionNewPontofocal.setIddistrito(distrito);
                    pontofocalCollectionNewPontofocal = em.merge(pontofocalCollectionNewPontofocal);
                    if (oldIddistritoOfPontofocalCollectionNewPontofocal != null && !oldIddistritoOfPontofocalCollectionNewPontofocal.equals(distrito)) {
                        oldIddistritoOfPontofocalCollectionNewPontofocal.getPontofocalCollection().remove(pontofocalCollectionNewPontofocal);
                        oldIddistritoOfPontofocalCollectionNewPontofocal = em.merge(oldIddistritoOfPontofocalCollectionNewPontofocal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = distrito.getIddistrito();
                if (findDistrito(id) == null) {
                    throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.");
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
            Distrito distrito;
            try {
                distrito = em.getReference(Distrito.class, id);
                distrito.getIddistrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distrito with id " + id + " no longer exists.", enfe);
            }
            Collection<Posto> postoCollection = distrito.getPostoCollection();
            for (Posto postoCollectionPosto : postoCollection) {
                postoCollectionPosto.setIddistrito(null);
                postoCollectionPosto = em.merge(postoCollectionPosto);
            }
            Collection<Pontofocal> pontofocalCollection = distrito.getPontofocalCollection();
            for (Pontofocal pontofocalCollectionPontofocal : pontofocalCollection) {
                pontofocalCollectionPontofocal.setIddistrito(null);
                pontofocalCollectionPontofocal = em.merge(pontofocalCollectionPontofocal);
            }
            em.remove(distrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Distrito> findDistritoEntities() {
        return findDistritoEntities(true, -1, -1);
    }

    public List<Distrito> findDistritoEntities(int maxResults, int firstResult) {
        return findDistritoEntities(false, maxResults, firstResult);
    }

    private List<Distrito> findDistritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Distrito.class));
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

    public Distrito findDistrito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Distrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Distrito> rt = cq.from(Distrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public Distrito getDistritoByLoc(int idd) {
        EntityManager em = getEntityManager();
        Query q = em.createNativeQuery("select d.* from distrito d, posto p, localidade l where d.iddistrito = p.iddistrito and "
                + "p.idposto = l.idposto and l.idlocalidade = ?", Distrito.class);
          
        q.setParameter(1, idd);

        return (Distrito) q.getSingleResult();
    }
    
}
