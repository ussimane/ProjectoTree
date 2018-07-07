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
import Modelo.Formando;
import java.util.ArrayList;
import java.util.List;
import Modelo.Mestre;
import Modelo.Nivelprofissional;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MASSINGUE
 */
public class NivelprofissionalJpaController implements Serializable {

    public NivelprofissionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Nivelprofissional nivelprofissional) {
        if (nivelprofissional.getMestreCollection() == null) {
            nivelprofissional.setMestreCollection(new ArrayList<Mestre>());
        }
        if (nivelprofissional.getFormandoCollection() == null) {
            nivelprofissional.setFormandoCollection(new ArrayList<Formando>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mestre> attachedMestreCollection = new ArrayList<Mestre>();
            for (Mestre mestreCollectionMestreToAttach : nivelprofissional.getMestreCollection()) {
                mestreCollectionMestreToAttach = em.getReference(mestreCollectionMestreToAttach.getClass(), mestreCollectionMestreToAttach.getIdmestre());
                attachedMestreCollection.add(mestreCollectionMestreToAttach);
            }
            nivelprofissional.setMestreCollection(attachedMestreCollection);
            Collection<Formando> attachedFormandoCollection = new ArrayList<Formando>();
            for (Formando formandoCollectionFormandoToAttach : nivelprofissional.getFormandoCollection()) {
                formandoCollectionFormandoToAttach = em.getReference(formandoCollectionFormandoToAttach.getClass(), formandoCollectionFormandoToAttach.getIdformando());
                attachedFormandoCollection.add(formandoCollectionFormandoToAttach);
            }
            nivelprofissional.setFormandoCollection(attachedFormandoCollection);
            em.persist(nivelprofissional);
            for (Mestre mestreCollectionMestre : nivelprofissional.getMestreCollection()) {
                Nivelprofissional oldIdnivelprofOfMestreCollectionMestre = mestreCollectionMestre.getIdnivelprof();
                mestreCollectionMestre.setIdnivelprof(nivelprofissional);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
                if (oldIdnivelprofOfMestreCollectionMestre != null) {
                    oldIdnivelprofOfMestreCollectionMestre.getMestreCollection().remove(mestreCollectionMestre);
                    oldIdnivelprofOfMestreCollectionMestre = em.merge(oldIdnivelprofOfMestreCollectionMestre);
                }
            }
            for (Formando formandoCollectionFormando : nivelprofissional.getFormandoCollection()) {
                Nivelprofissional oldIdnivelprofOfFormandoCollectionFormando = formandoCollectionFormando.getIdnivelprof();
                formandoCollectionFormando.setIdnivelprof(nivelprofissional);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
                if (oldIdnivelprofOfFormandoCollectionFormando != null) {
                    oldIdnivelprofOfFormandoCollectionFormando.getFormandoCollection().remove(formandoCollectionFormando);
                    oldIdnivelprofOfFormandoCollectionFormando = em.merge(oldIdnivelprofOfFormandoCollectionFormando);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nivelprofissional nivelprofissional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nivelprofissional persistentNivelprofissional = em.find(Nivelprofissional.class, nivelprofissional.getIdnivelprofissional());
            Collection<Mestre> mestreCollectionOld = persistentNivelprofissional.getMestreCollection();
            Collection<Mestre> mestreCollectionNew = nivelprofissional.getMestreCollection();
            Collection<Formando> formandoCollectionOld = persistentNivelprofissional.getFormandoCollection();
            Collection<Formando> formandoCollectionNew = nivelprofissional.getFormandoCollection();
            Collection<Mestre> attachedMestreCollectionNew = new ArrayList<Mestre>();
            for (Mestre mestreCollectionNewMestreToAttach : mestreCollectionNew) {
                mestreCollectionNewMestreToAttach = em.getReference(mestreCollectionNewMestreToAttach.getClass(), mestreCollectionNewMestreToAttach.getIdmestre());
                attachedMestreCollectionNew.add(mestreCollectionNewMestreToAttach);
            }
            mestreCollectionNew = attachedMestreCollectionNew;
            nivelprofissional.setMestreCollection(mestreCollectionNew);
            Collection<Formando> attachedFormandoCollectionNew = new ArrayList<Formando>();
            for (Formando formandoCollectionNewFormandoToAttach : formandoCollectionNew) {
                formandoCollectionNewFormandoToAttach = em.getReference(formandoCollectionNewFormandoToAttach.getClass(), formandoCollectionNewFormandoToAttach.getIdformando());
                attachedFormandoCollectionNew.add(formandoCollectionNewFormandoToAttach);
            }
            formandoCollectionNew = attachedFormandoCollectionNew;
            nivelprofissional.setFormandoCollection(formandoCollectionNew);
            nivelprofissional = em.merge(nivelprofissional);
            for (Mestre mestreCollectionOldMestre : mestreCollectionOld) {
                if (!mestreCollectionNew.contains(mestreCollectionOldMestre)) {
                    mestreCollectionOldMestre.setIdnivelprof(null);
                    mestreCollectionOldMestre = em.merge(mestreCollectionOldMestre);
                }
            }
            for (Mestre mestreCollectionNewMestre : mestreCollectionNew) {
                if (!mestreCollectionOld.contains(mestreCollectionNewMestre)) {
                    Nivelprofissional oldIdnivelprofOfMestreCollectionNewMestre = mestreCollectionNewMestre.getIdnivelprof();
                    mestreCollectionNewMestre.setIdnivelprof(nivelprofissional);
                    mestreCollectionNewMestre = em.merge(mestreCollectionNewMestre);
                    if (oldIdnivelprofOfMestreCollectionNewMestre != null && !oldIdnivelprofOfMestreCollectionNewMestre.equals(nivelprofissional)) {
                        oldIdnivelprofOfMestreCollectionNewMestre.getMestreCollection().remove(mestreCollectionNewMestre);
                        oldIdnivelprofOfMestreCollectionNewMestre = em.merge(oldIdnivelprofOfMestreCollectionNewMestre);
                    }
                }
            }
            for (Formando formandoCollectionOldFormando : formandoCollectionOld) {
                if (!formandoCollectionNew.contains(formandoCollectionOldFormando)) {
                    formandoCollectionOldFormando.setIdnivelprof(null);
                    formandoCollectionOldFormando = em.merge(formandoCollectionOldFormando);
                }
            }
            for (Formando formandoCollectionNewFormando : formandoCollectionNew) {
                if (!formandoCollectionOld.contains(formandoCollectionNewFormando)) {
                    Nivelprofissional oldIdnivelprofOfFormandoCollectionNewFormando = formandoCollectionNewFormando.getIdnivelprof();
                    formandoCollectionNewFormando.setIdnivelprof(nivelprofissional);
                    formandoCollectionNewFormando = em.merge(formandoCollectionNewFormando);
                    if (oldIdnivelprofOfFormandoCollectionNewFormando != null && !oldIdnivelprofOfFormandoCollectionNewFormando.equals(nivelprofissional)) {
                        oldIdnivelprofOfFormandoCollectionNewFormando.getFormandoCollection().remove(formandoCollectionNewFormando);
                        oldIdnivelprofOfFormandoCollectionNewFormando = em.merge(oldIdnivelprofOfFormandoCollectionNewFormando);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nivelprofissional.getIdnivelprofissional();
                if (findNivelprofissional(id) == null) {
                    throw new NonexistentEntityException("The nivelprofissional with id " + id + " no longer exists.");
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
            Nivelprofissional nivelprofissional;
            try {
                nivelprofissional = em.getReference(Nivelprofissional.class, id);
                nivelprofissional.getIdnivelprofissional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelprofissional with id " + id + " no longer exists.", enfe);
            }
            Collection<Mestre> mestreCollection = nivelprofissional.getMestreCollection();
            for (Mestre mestreCollectionMestre : mestreCollection) {
                mestreCollectionMestre.setIdnivelprof(null);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
            }
            Collection<Formando> formandoCollection = nivelprofissional.getFormandoCollection();
            for (Formando formandoCollectionFormando : formandoCollection) {
                formandoCollectionFormando.setIdnivelprof(null);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
            }
            em.remove(nivelprofissional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nivelprofissional> findNivelprofissionalEntities() {
        return findNivelprofissionalEntities(true, -1, -1);
    }

    public List<Nivelprofissional> findNivelprofissionalEntities(int maxResults, int firstResult) {
        return findNivelprofissionalEntities(false, maxResults, firstResult);
    }

    private List<Nivelprofissional> findNivelprofissionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nivelprofissional.class));
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

    public Nivelprofissional findNivelprofissional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nivelprofissional.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelprofissionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nivelprofissional> rt = cq.from(Nivelprofissional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
