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
import Modelo.Nivelhabilitacao;
import Modelo.Pontofocal;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class NivelhabilitacaoJpaController implements Serializable {

      public NivelhabilitacaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

       public void create(Nivelhabilitacao nivelhabilitacao) {
        if (nivelhabilitacao.getMestreCollection() == null) {
            nivelhabilitacao.setMestreCollection(new ArrayList<Mestre>());
        }
        if (nivelhabilitacao.getPontofocalCollection() == null) {
            nivelhabilitacao.setPontofocalCollection(new ArrayList<Pontofocal>());
        }
        if (nivelhabilitacao.getFormandoCollection() == null) {
            nivelhabilitacao.setFormandoCollection(new ArrayList<Formando>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mestre> attachedMestreCollection = new ArrayList<Mestre>();
            for (Mestre mestreCollectionMestreToAttach : nivelhabilitacao.getMestreCollection()) {
                mestreCollectionMestreToAttach = em.getReference(mestreCollectionMestreToAttach.getClass(), mestreCollectionMestreToAttach.getIdmestre());
                attachedMestreCollection.add(mestreCollectionMestreToAttach);
            }
            nivelhabilitacao.setMestreCollection(attachedMestreCollection);
            Collection<Pontofocal> attachedPontofocalCollection = new ArrayList<Pontofocal>();
            for (Pontofocal pontofocalCollectionPontofocalToAttach : nivelhabilitacao.getPontofocalCollection()) {
                pontofocalCollectionPontofocalToAttach = em.getReference(pontofocalCollectionPontofocalToAttach.getClass(), pontofocalCollectionPontofocalToAttach.getIdpontofocal());
                attachedPontofocalCollection.add(pontofocalCollectionPontofocalToAttach);
            }
            nivelhabilitacao.setPontofocalCollection(attachedPontofocalCollection);
            Collection<Formando> attachedFormandoCollection = new ArrayList<Formando>();
            for (Formando formandoCollectionFormandoToAttach : nivelhabilitacao.getFormandoCollection()) {
                formandoCollectionFormandoToAttach = em.getReference(formandoCollectionFormandoToAttach.getClass(), formandoCollectionFormandoToAttach.getIdformando());
                attachedFormandoCollection.add(formandoCollectionFormandoToAttach);
            }
            nivelhabilitacao.setFormandoCollection(attachedFormandoCollection);
            em.persist(nivelhabilitacao);
            for (Mestre mestreCollectionMestre : nivelhabilitacao.getMestreCollection()) {
                Nivelhabilitacao oldIdnivelhabilitacaoOfMestreCollectionMestre = mestreCollectionMestre.getIdnivelhabilitacao();
                mestreCollectionMestre.setIdnivelhabilitacao(nivelhabilitacao);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
                if (oldIdnivelhabilitacaoOfMestreCollectionMestre != null) {
                    oldIdnivelhabilitacaoOfMestreCollectionMestre.getMestreCollection().remove(mestreCollectionMestre);
                    oldIdnivelhabilitacaoOfMestreCollectionMestre = em.merge(oldIdnivelhabilitacaoOfMestreCollectionMestre);
                }
            }
            for (Pontofocal pontofocalCollectionPontofocal : nivelhabilitacao.getPontofocalCollection()) {
                Nivelhabilitacao oldIdnivelhabilitacaoOfPontofocalCollectionPontofocal = pontofocalCollectionPontofocal.getIdnivelhabilitacao();
                pontofocalCollectionPontofocal.setIdnivelhabilitacao(nivelhabilitacao);
                pontofocalCollectionPontofocal = em.merge(pontofocalCollectionPontofocal);
                if (oldIdnivelhabilitacaoOfPontofocalCollectionPontofocal != null) {
                    oldIdnivelhabilitacaoOfPontofocalCollectionPontofocal.getPontofocalCollection().remove(pontofocalCollectionPontofocal);
                    oldIdnivelhabilitacaoOfPontofocalCollectionPontofocal = em.merge(oldIdnivelhabilitacaoOfPontofocalCollectionPontofocal);
                }
            }
            for (Formando formandoCollectionFormando : nivelhabilitacao.getFormandoCollection()) {
                Nivelhabilitacao oldIdnivelhabilitacaoOfFormandoCollectionFormando = formandoCollectionFormando.getIdnivelhabilitacao();
                formandoCollectionFormando.setIdnivelhabilitacao(nivelhabilitacao);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
                if (oldIdnivelhabilitacaoOfFormandoCollectionFormando != null) {
                    oldIdnivelhabilitacaoOfFormandoCollectionFormando.getFormandoCollection().remove(formandoCollectionFormando);
                    oldIdnivelhabilitacaoOfFormandoCollectionFormando = em.merge(oldIdnivelhabilitacaoOfFormandoCollectionFormando);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nivelhabilitacao nivelhabilitacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nivelhabilitacao persistentNivelhabilitacao = em.find(Nivelhabilitacao.class, nivelhabilitacao.getIdnivelhabilitacao());
            Collection<Mestre> mestreCollectionOld = persistentNivelhabilitacao.getMestreCollection();
            Collection<Mestre> mestreCollectionNew = nivelhabilitacao.getMestreCollection();
            Collection<Pontofocal> pontofocalCollectionOld = persistentNivelhabilitacao.getPontofocalCollection();
            Collection<Pontofocal> pontofocalCollectionNew = nivelhabilitacao.getPontofocalCollection();
            Collection<Formando> formandoCollectionOld = persistentNivelhabilitacao.getFormandoCollection();
            Collection<Formando> formandoCollectionNew = nivelhabilitacao.getFormandoCollection();
            Collection<Mestre> attachedMestreCollectionNew = new ArrayList<Mestre>();
            for (Mestre mestreCollectionNewMestreToAttach : mestreCollectionNew) {
                mestreCollectionNewMestreToAttach = em.getReference(mestreCollectionNewMestreToAttach.getClass(), mestreCollectionNewMestreToAttach.getIdmestre());
                attachedMestreCollectionNew.add(mestreCollectionNewMestreToAttach);
            }
            mestreCollectionNew = attachedMestreCollectionNew;
            nivelhabilitacao.setMestreCollection(mestreCollectionNew);
            Collection<Pontofocal> attachedPontofocalCollectionNew = new ArrayList<Pontofocal>();
            for (Pontofocal pontofocalCollectionNewPontofocalToAttach : pontofocalCollectionNew) {
                pontofocalCollectionNewPontofocalToAttach = em.getReference(pontofocalCollectionNewPontofocalToAttach.getClass(), pontofocalCollectionNewPontofocalToAttach.getIdpontofocal());
                attachedPontofocalCollectionNew.add(pontofocalCollectionNewPontofocalToAttach);
            }
            pontofocalCollectionNew = attachedPontofocalCollectionNew;
            nivelhabilitacao.setPontofocalCollection(pontofocalCollectionNew);
            Collection<Formando> attachedFormandoCollectionNew = new ArrayList<Formando>();
            for (Formando formandoCollectionNewFormandoToAttach : formandoCollectionNew) {
                formandoCollectionNewFormandoToAttach = em.getReference(formandoCollectionNewFormandoToAttach.getClass(), formandoCollectionNewFormandoToAttach.getIdformando());
                attachedFormandoCollectionNew.add(formandoCollectionNewFormandoToAttach);
            }
            formandoCollectionNew = attachedFormandoCollectionNew;
            nivelhabilitacao.setFormandoCollection(formandoCollectionNew);
            nivelhabilitacao = em.merge(nivelhabilitacao);
            for (Mestre mestreCollectionOldMestre : mestreCollectionOld) {
                if (!mestreCollectionNew.contains(mestreCollectionOldMestre)) {
                    mestreCollectionOldMestre.setIdnivelhabilitacao(null);
                    mestreCollectionOldMestre = em.merge(mestreCollectionOldMestre);
                }
            }
            for (Mestre mestreCollectionNewMestre : mestreCollectionNew) {
                if (!mestreCollectionOld.contains(mestreCollectionNewMestre)) {
                    Nivelhabilitacao oldIdnivelhabilitacaoOfMestreCollectionNewMestre = mestreCollectionNewMestre.getIdnivelhabilitacao();
                    mestreCollectionNewMestre.setIdnivelhabilitacao(nivelhabilitacao);
                    mestreCollectionNewMestre = em.merge(mestreCollectionNewMestre);
                    if (oldIdnivelhabilitacaoOfMestreCollectionNewMestre != null && !oldIdnivelhabilitacaoOfMestreCollectionNewMestre.equals(nivelhabilitacao)) {
                        oldIdnivelhabilitacaoOfMestreCollectionNewMestre.getMestreCollection().remove(mestreCollectionNewMestre);
                        oldIdnivelhabilitacaoOfMestreCollectionNewMestre = em.merge(oldIdnivelhabilitacaoOfMestreCollectionNewMestre);
                    }
                }
            }
            for (Pontofocal pontofocalCollectionOldPontofocal : pontofocalCollectionOld) {
                if (!pontofocalCollectionNew.contains(pontofocalCollectionOldPontofocal)) {
                    pontofocalCollectionOldPontofocal.setIdnivelhabilitacao(null);
                    pontofocalCollectionOldPontofocal = em.merge(pontofocalCollectionOldPontofocal);
                }
            }
            for (Pontofocal pontofocalCollectionNewPontofocal : pontofocalCollectionNew) {
                if (!pontofocalCollectionOld.contains(pontofocalCollectionNewPontofocal)) {
                    Nivelhabilitacao oldIdnivelhabilitacaoOfPontofocalCollectionNewPontofocal = pontofocalCollectionNewPontofocal.getIdnivelhabilitacao();
                    pontofocalCollectionNewPontofocal.setIdnivelhabilitacao(nivelhabilitacao);
                    pontofocalCollectionNewPontofocal = em.merge(pontofocalCollectionNewPontofocal);
                    if (oldIdnivelhabilitacaoOfPontofocalCollectionNewPontofocal != null && !oldIdnivelhabilitacaoOfPontofocalCollectionNewPontofocal.equals(nivelhabilitacao)) {
                        oldIdnivelhabilitacaoOfPontofocalCollectionNewPontofocal.getPontofocalCollection().remove(pontofocalCollectionNewPontofocal);
                        oldIdnivelhabilitacaoOfPontofocalCollectionNewPontofocal = em.merge(oldIdnivelhabilitacaoOfPontofocalCollectionNewPontofocal);
                    }
                }
            }
            for (Formando formandoCollectionOldFormando : formandoCollectionOld) {
                if (!formandoCollectionNew.contains(formandoCollectionOldFormando)) {
                    formandoCollectionOldFormando.setIdnivelhabilitacao(null);
                    formandoCollectionOldFormando = em.merge(formandoCollectionOldFormando);
                }
            }
            for (Formando formandoCollectionNewFormando : formandoCollectionNew) {
                if (!formandoCollectionOld.contains(formandoCollectionNewFormando)) {
                    Nivelhabilitacao oldIdnivelhabilitacaoOfFormandoCollectionNewFormando = formandoCollectionNewFormando.getIdnivelhabilitacao();
                    formandoCollectionNewFormando.setIdnivelhabilitacao(nivelhabilitacao);
                    formandoCollectionNewFormando = em.merge(formandoCollectionNewFormando);
                    if (oldIdnivelhabilitacaoOfFormandoCollectionNewFormando != null && !oldIdnivelhabilitacaoOfFormandoCollectionNewFormando.equals(nivelhabilitacao)) {
                        oldIdnivelhabilitacaoOfFormandoCollectionNewFormando.getFormandoCollection().remove(formandoCollectionNewFormando);
                        oldIdnivelhabilitacaoOfFormandoCollectionNewFormando = em.merge(oldIdnivelhabilitacaoOfFormandoCollectionNewFormando);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nivelhabilitacao.getIdnivelhabilitacao();
                if (findNivelhabilitacao(id) == null) {
                    throw new NonexistentEntityException("The nivelhabilitacao with id " + id + " no longer exists.");
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
            Nivelhabilitacao nivelhabilitacao;
            try {
                nivelhabilitacao = em.getReference(Nivelhabilitacao.class, id);
                nivelhabilitacao.getIdnivelhabilitacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelhabilitacao with id " + id + " no longer exists.", enfe);
            }
            Collection<Mestre> mestreCollection = nivelhabilitacao.getMestreCollection();
            for (Mestre mestreCollectionMestre : mestreCollection) {
                mestreCollectionMestre.setIdnivelhabilitacao(null);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
            }
            Collection<Pontofocal> pontofocalCollection = nivelhabilitacao.getPontofocalCollection();
            for (Pontofocal pontofocalCollectionPontofocal : pontofocalCollection) {
                pontofocalCollectionPontofocal.setIdnivelhabilitacao(null);
                pontofocalCollectionPontofocal = em.merge(pontofocalCollectionPontofocal);
            }
            Collection<Formando> formandoCollection = nivelhabilitacao.getFormandoCollection();
            for (Formando formandoCollectionFormando : formandoCollection) {
                formandoCollectionFormando.setIdnivelhabilitacao(null);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
            }
            em.remove(nivelhabilitacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nivelhabilitacao> findNivelhabilitacaoEntities() {
        return findNivelhabilitacaoEntities(true, -1, -1);
    }

    public List<Nivelhabilitacao> findNivelhabilitacaoEntities(int maxResults, int firstResult) {
        return findNivelhabilitacaoEntities(false, maxResults, firstResult);
    }

    private List<Nivelhabilitacao> findNivelhabilitacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nivelhabilitacao.class));
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

    public Nivelhabilitacao findNivelhabilitacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nivelhabilitacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelhabilitacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nivelhabilitacao> rt = cq.from(Nivelhabilitacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
