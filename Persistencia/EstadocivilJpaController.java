/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Estadocivil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Formando;
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
public class EstadocivilJpaController implements Serializable {

    public EstadocivilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadocivil estadocivil) {
        if (estadocivil.getFormandoCollection() == null) {
            estadocivil.setFormandoCollection(new ArrayList<Formando>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Formando> attachedFormandoCollection = new ArrayList<Formando>();
            for (Formando formandoCollectionFormandoToAttach : estadocivil.getFormandoCollection()) {
                formandoCollectionFormandoToAttach = em.getReference(formandoCollectionFormandoToAttach.getClass(), formandoCollectionFormandoToAttach.getIdformando());
                attachedFormandoCollection.add(formandoCollectionFormandoToAttach);
            }
            estadocivil.setFormandoCollection(attachedFormandoCollection);
            em.persist(estadocivil);
            for (Formando formandoCollectionFormando : estadocivil.getFormandoCollection()) {
                Estadocivil oldIdestadocivilOfFormandoCollectionFormando = formandoCollectionFormando.getIdestadocivil();
                formandoCollectionFormando.setIdestadocivil(estadocivil);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
                if (oldIdestadocivilOfFormandoCollectionFormando != null) {
                    oldIdestadocivilOfFormandoCollectionFormando.getFormandoCollection().remove(formandoCollectionFormando);
                    oldIdestadocivilOfFormandoCollectionFormando = em.merge(oldIdestadocivilOfFormandoCollectionFormando);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadocivil estadocivil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadocivil persistentEstadocivil = em.find(Estadocivil.class, estadocivil.getIdestadocivil());
            Collection<Formando> formandoCollectionOld = persistentEstadocivil.getFormandoCollection();
            Collection<Formando> formandoCollectionNew = estadocivil.getFormandoCollection();
            Collection<Formando> attachedFormandoCollectionNew = new ArrayList<Formando>();
            for (Formando formandoCollectionNewFormandoToAttach : formandoCollectionNew) {
                formandoCollectionNewFormandoToAttach = em.getReference(formandoCollectionNewFormandoToAttach.getClass(), formandoCollectionNewFormandoToAttach.getIdformando());
                attachedFormandoCollectionNew.add(formandoCollectionNewFormandoToAttach);
            }
            formandoCollectionNew = attachedFormandoCollectionNew;
            estadocivil.setFormandoCollection(formandoCollectionNew);
            estadocivil = em.merge(estadocivil);
            for (Formando formandoCollectionOldFormando : formandoCollectionOld) {
                if (!formandoCollectionNew.contains(formandoCollectionOldFormando)) {
                    formandoCollectionOldFormando.setIdestadocivil(null);
                    formandoCollectionOldFormando = em.merge(formandoCollectionOldFormando);
                }
            }
            for (Formando formandoCollectionNewFormando : formandoCollectionNew) {
                if (!formandoCollectionOld.contains(formandoCollectionNewFormando)) {
                    Estadocivil oldIdestadocivilOfFormandoCollectionNewFormando = formandoCollectionNewFormando.getIdestadocivil();
                    formandoCollectionNewFormando.setIdestadocivil(estadocivil);
                    formandoCollectionNewFormando = em.merge(formandoCollectionNewFormando);
                    if (oldIdestadocivilOfFormandoCollectionNewFormando != null && !oldIdestadocivilOfFormandoCollectionNewFormando.equals(estadocivil)) {
                        oldIdestadocivilOfFormandoCollectionNewFormando.getFormandoCollection().remove(formandoCollectionNewFormando);
                        oldIdestadocivilOfFormandoCollectionNewFormando = em.merge(oldIdestadocivilOfFormandoCollectionNewFormando);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadocivil.getIdestadocivil();
                if (findEstadocivil(id) == null) {
                    throw new NonexistentEntityException("The estadocivil with id " + id + " no longer exists.");
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
            Estadocivil estadocivil;
            try {
                estadocivil = em.getReference(Estadocivil.class, id);
                estadocivil.getIdestadocivil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadocivil with id " + id + " no longer exists.", enfe);
            }
            Collection<Formando> formandoCollection = estadocivil.getFormandoCollection();
            for (Formando formandoCollectionFormando : formandoCollection) {
                formandoCollectionFormando.setIdestadocivil(null);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
            }
            em.remove(estadocivil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadocivil> findEstadocivilEntities() {
        return findEstadocivilEntities(true, -1, -1);
    }

    public List<Estadocivil> findEstadocivilEntities(int maxResults, int firstResult) {
        return findEstadocivilEntities(false, maxResults, firstResult);
    }

    private List<Estadocivil> findEstadocivilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadocivil.class));
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

    public Estadocivil findEstadocivil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadocivil.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadocivilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadocivil> rt = cq.from(Estadocivil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
