/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Areacurso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Areaespecializacao;
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
public class AreacursoJpaController implements Serializable {

    public AreacursoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   public void create(Areacurso areacurso) {
        if (areacurso.getAreaespecializacaoCollection() == null) {
            areacurso.setAreaespecializacaoCollection(new ArrayList<Areaespecializacao>());
        }
        if (areacurso.getAreaespecializacaoCollection1() == null) {
            areacurso.setAreaespecializacaoCollection1(new ArrayList<Areaespecializacao>());
        }
        if (areacurso.getAreaespecializacaoCollection2() == null) {
            areacurso.setAreaespecializacaoCollection2(new ArrayList<Areaespecializacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Areaespecializacao> attachedAreaespecializacaoCollection = new ArrayList<Areaespecializacao>();
            for (Areaespecializacao areaespecializacaoCollectionAreaespecializacaoToAttach : areacurso.getAreaespecializacaoCollection()) {
                areaespecializacaoCollectionAreaespecializacaoToAttach = em.getReference(areaespecializacaoCollectionAreaespecializacaoToAttach.getClass(), areaespecializacaoCollectionAreaespecializacaoToAttach.getIdformador());
                attachedAreaespecializacaoCollection.add(areaespecializacaoCollectionAreaespecializacaoToAttach);
            }
            areacurso.setAreaespecializacaoCollection(attachedAreaespecializacaoCollection);
            Collection<Areaespecializacao> attachedAreaespecializacaoCollection1 = new ArrayList<Areaespecializacao>();
            for (Areaespecializacao areaespecializacaoCollection1AreaespecializacaoToAttach : areacurso.getAreaespecializacaoCollection1()) {
                areaespecializacaoCollection1AreaespecializacaoToAttach = em.getReference(areaespecializacaoCollection1AreaespecializacaoToAttach.getClass(), areaespecializacaoCollection1AreaespecializacaoToAttach.getIdformador());
                attachedAreaespecializacaoCollection1.add(areaespecializacaoCollection1AreaespecializacaoToAttach);
            }
            areacurso.setAreaespecializacaoCollection1(attachedAreaespecializacaoCollection1);
            Collection<Areaespecializacao> attachedAreaespecializacaoCollection2 = new ArrayList<Areaespecializacao>();
            for (Areaespecializacao areaespecializacaoCollection2AreaespecializacaoToAttach : areacurso.getAreaespecializacaoCollection2()) {
                areaespecializacaoCollection2AreaespecializacaoToAttach = em.getReference(areaespecializacaoCollection2AreaespecializacaoToAttach.getClass(), areaespecializacaoCollection2AreaespecializacaoToAttach.getIdformador());
                attachedAreaespecializacaoCollection2.add(areaespecializacaoCollection2AreaespecializacaoToAttach);
            }
            areacurso.setAreaespecializacaoCollection2(attachedAreaespecializacaoCollection2);
            em.persist(areacurso);
            for (Areaespecializacao areaespecializacaoCollectionAreaespecializacao : areacurso.getAreaespecializacaoCollection()) {
                Areacurso oldIdarea3OfAreaespecializacaoCollectionAreaespecializacao = areaespecializacaoCollectionAreaespecializacao.getIdarea3();
                areaespecializacaoCollectionAreaespecializacao.setIdarea3(areacurso);
                areaespecializacaoCollectionAreaespecializacao = em.merge(areaespecializacaoCollectionAreaespecializacao);
                if (oldIdarea3OfAreaespecializacaoCollectionAreaespecializacao != null) {
                    oldIdarea3OfAreaespecializacaoCollectionAreaespecializacao.getAreaespecializacaoCollection().remove(areaespecializacaoCollectionAreaespecializacao);
                    oldIdarea3OfAreaespecializacaoCollectionAreaespecializacao = em.merge(oldIdarea3OfAreaespecializacaoCollectionAreaespecializacao);
                }
            }
            for (Areaespecializacao areaespecializacaoCollection1Areaespecializacao : areacurso.getAreaespecializacaoCollection1()) {
                Areacurso oldIdarea2OfAreaespecializacaoCollection1Areaespecializacao = areaespecializacaoCollection1Areaespecializacao.getIdarea2();
                areaespecializacaoCollection1Areaespecializacao.setIdarea2(areacurso);
                areaespecializacaoCollection1Areaespecializacao = em.merge(areaespecializacaoCollection1Areaespecializacao);
                if (oldIdarea2OfAreaespecializacaoCollection1Areaespecializacao != null) {
                    oldIdarea2OfAreaespecializacaoCollection1Areaespecializacao.getAreaespecializacaoCollection1().remove(areaespecializacaoCollection1Areaespecializacao);
                    oldIdarea2OfAreaespecializacaoCollection1Areaespecializacao = em.merge(oldIdarea2OfAreaespecializacaoCollection1Areaespecializacao);
                }
            }
            for (Areaespecializacao areaespecializacaoCollection2Areaespecializacao : areacurso.getAreaespecializacaoCollection2()) {
                Areacurso oldIdarea1OfAreaespecializacaoCollection2Areaespecializacao = areaespecializacaoCollection2Areaespecializacao.getIdarea1();
                areaespecializacaoCollection2Areaespecializacao.setIdarea1(areacurso);
                areaespecializacaoCollection2Areaespecializacao = em.merge(areaespecializacaoCollection2Areaespecializacao);
                if (oldIdarea1OfAreaespecializacaoCollection2Areaespecializacao != null) {
                    oldIdarea1OfAreaespecializacaoCollection2Areaespecializacao.getAreaespecializacaoCollection2().remove(areaespecializacaoCollection2Areaespecializacao);
                    oldIdarea1OfAreaespecializacaoCollection2Areaespecializacao = em.merge(oldIdarea1OfAreaespecializacaoCollection2Areaespecializacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Areacurso areacurso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areacurso persistentAreacurso = em.find(Areacurso.class, areacurso.getIdareacurso());
            Collection<Areaespecializacao> areaespecializacaoCollectionOld = persistentAreacurso.getAreaespecializacaoCollection();
            Collection<Areaespecializacao> areaespecializacaoCollectionNew = areacurso.getAreaespecializacaoCollection();
            Collection<Areaespecializacao> areaespecializacaoCollection1Old = persistentAreacurso.getAreaespecializacaoCollection1();
            Collection<Areaespecializacao> areaespecializacaoCollection1New = areacurso.getAreaespecializacaoCollection1();
            Collection<Areaespecializacao> areaespecializacaoCollection2Old = persistentAreacurso.getAreaespecializacaoCollection2();
            Collection<Areaespecializacao> areaespecializacaoCollection2New = areacurso.getAreaespecializacaoCollection2();
            Collection<Areaespecializacao> attachedAreaespecializacaoCollectionNew = new ArrayList<Areaespecializacao>();
            for (Areaespecializacao areaespecializacaoCollectionNewAreaespecializacaoToAttach : areaespecializacaoCollectionNew) {
                areaespecializacaoCollectionNewAreaespecializacaoToAttach = em.getReference(areaespecializacaoCollectionNewAreaespecializacaoToAttach.getClass(), areaespecializacaoCollectionNewAreaespecializacaoToAttach.getIdformador());
                attachedAreaespecializacaoCollectionNew.add(areaespecializacaoCollectionNewAreaespecializacaoToAttach);
            }
            areaespecializacaoCollectionNew = attachedAreaespecializacaoCollectionNew;
            areacurso.setAreaespecializacaoCollection(areaespecializacaoCollectionNew);
            Collection<Areaespecializacao> attachedAreaespecializacaoCollection1New = new ArrayList<Areaespecializacao>();
            for (Areaespecializacao areaespecializacaoCollection1NewAreaespecializacaoToAttach : areaespecializacaoCollection1New) {
                areaespecializacaoCollection1NewAreaespecializacaoToAttach = em.getReference(areaespecializacaoCollection1NewAreaespecializacaoToAttach.getClass(), areaespecializacaoCollection1NewAreaespecializacaoToAttach.getIdformador());
                attachedAreaespecializacaoCollection1New.add(areaespecializacaoCollection1NewAreaespecializacaoToAttach);
            }
            areaespecializacaoCollection1New = attachedAreaespecializacaoCollection1New;
            areacurso.setAreaespecializacaoCollection1(areaespecializacaoCollection1New);
            Collection<Areaespecializacao> attachedAreaespecializacaoCollection2New = new ArrayList<Areaespecializacao>();
            for (Areaespecializacao areaespecializacaoCollection2NewAreaespecializacaoToAttach : areaespecializacaoCollection2New) {
                areaespecializacaoCollection2NewAreaespecializacaoToAttach = em.getReference(areaespecializacaoCollection2NewAreaespecializacaoToAttach.getClass(), areaespecializacaoCollection2NewAreaespecializacaoToAttach.getIdformador());
                attachedAreaespecializacaoCollection2New.add(areaespecializacaoCollection2NewAreaespecializacaoToAttach);
            }
            areaespecializacaoCollection2New = attachedAreaespecializacaoCollection2New;
            areacurso.setAreaespecializacaoCollection2(areaespecializacaoCollection2New);
            areacurso = em.merge(areacurso);
            for (Areaespecializacao areaespecializacaoCollectionOldAreaespecializacao : areaespecializacaoCollectionOld) {
                if (!areaespecializacaoCollectionNew.contains(areaespecializacaoCollectionOldAreaespecializacao)) {
                    areaespecializacaoCollectionOldAreaespecializacao.setIdarea3(null);
                    areaespecializacaoCollectionOldAreaespecializacao = em.merge(areaespecializacaoCollectionOldAreaespecializacao);
                }
            }
            for (Areaespecializacao areaespecializacaoCollectionNewAreaespecializacao : areaespecializacaoCollectionNew) {
                if (!areaespecializacaoCollectionOld.contains(areaespecializacaoCollectionNewAreaespecializacao)) {
                    Areacurso oldIdarea3OfAreaespecializacaoCollectionNewAreaespecializacao = areaespecializacaoCollectionNewAreaespecializacao.getIdarea3();
                    areaespecializacaoCollectionNewAreaespecializacao.setIdarea3(areacurso);
                    areaespecializacaoCollectionNewAreaespecializacao = em.merge(areaespecializacaoCollectionNewAreaespecializacao);
                    if (oldIdarea3OfAreaespecializacaoCollectionNewAreaespecializacao != null && !oldIdarea3OfAreaespecializacaoCollectionNewAreaespecializacao.equals(areacurso)) {
                        oldIdarea3OfAreaespecializacaoCollectionNewAreaespecializacao.getAreaespecializacaoCollection().remove(areaespecializacaoCollectionNewAreaespecializacao);
                        oldIdarea3OfAreaespecializacaoCollectionNewAreaespecializacao = em.merge(oldIdarea3OfAreaespecializacaoCollectionNewAreaespecializacao);
                    }
                }
            }
            for (Areaespecializacao areaespecializacaoCollection1OldAreaespecializacao : areaespecializacaoCollection1Old) {
                if (!areaespecializacaoCollection1New.contains(areaespecializacaoCollection1OldAreaespecializacao)) {
                    areaespecializacaoCollection1OldAreaespecializacao.setIdarea2(null);
                    areaespecializacaoCollection1OldAreaespecializacao = em.merge(areaespecializacaoCollection1OldAreaespecializacao);
                }
            }
            for (Areaespecializacao areaespecializacaoCollection1NewAreaespecializacao : areaespecializacaoCollection1New) {
                if (!areaespecializacaoCollection1Old.contains(areaespecializacaoCollection1NewAreaespecializacao)) {
                    Areacurso oldIdarea2OfAreaespecializacaoCollection1NewAreaespecializacao = areaespecializacaoCollection1NewAreaespecializacao.getIdarea2();
                    areaespecializacaoCollection1NewAreaespecializacao.setIdarea2(areacurso);
                    areaespecializacaoCollection1NewAreaespecializacao = em.merge(areaespecializacaoCollection1NewAreaespecializacao);
                    if (oldIdarea2OfAreaespecializacaoCollection1NewAreaespecializacao != null && !oldIdarea2OfAreaespecializacaoCollection1NewAreaespecializacao.equals(areacurso)) {
                        oldIdarea2OfAreaespecializacaoCollection1NewAreaespecializacao.getAreaespecializacaoCollection1().remove(areaespecializacaoCollection1NewAreaespecializacao);
                        oldIdarea2OfAreaespecializacaoCollection1NewAreaespecializacao = em.merge(oldIdarea2OfAreaespecializacaoCollection1NewAreaespecializacao);
                    }
                }
            }
            for (Areaespecializacao areaespecializacaoCollection2OldAreaespecializacao : areaespecializacaoCollection2Old) {
                if (!areaespecializacaoCollection2New.contains(areaespecializacaoCollection2OldAreaespecializacao)) {
                    areaespecializacaoCollection2OldAreaespecializacao.setIdarea1(null);
                    areaespecializacaoCollection2OldAreaespecializacao = em.merge(areaespecializacaoCollection2OldAreaespecializacao);
                }
            }
            for (Areaespecializacao areaespecializacaoCollection2NewAreaespecializacao : areaespecializacaoCollection2New) {
                if (!areaespecializacaoCollection2Old.contains(areaespecializacaoCollection2NewAreaespecializacao)) {
                    Areacurso oldIdarea1OfAreaespecializacaoCollection2NewAreaespecializacao = areaespecializacaoCollection2NewAreaespecializacao.getIdarea1();
                    areaespecializacaoCollection2NewAreaespecializacao.setIdarea1(areacurso);
                    areaespecializacaoCollection2NewAreaespecializacao = em.merge(areaespecializacaoCollection2NewAreaespecializacao);
                    if (oldIdarea1OfAreaespecializacaoCollection2NewAreaespecializacao != null && !oldIdarea1OfAreaespecializacaoCollection2NewAreaespecializacao.equals(areacurso)) {
                        oldIdarea1OfAreaespecializacaoCollection2NewAreaespecializacao.getAreaespecializacaoCollection2().remove(areaespecializacaoCollection2NewAreaespecializacao);
                        oldIdarea1OfAreaespecializacaoCollection2NewAreaespecializacao = em.merge(oldIdarea1OfAreaespecializacaoCollection2NewAreaespecializacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areacurso.getIdareacurso();
                if (findAreacurso(id) == null) {
                    throw new NonexistentEntityException("The areacurso with id " + id + " no longer exists.");
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
            Areacurso areacurso;
            try {
                areacurso = em.getReference(Areacurso.class, id);
                areacurso.getIdareacurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areacurso with id " + id + " no longer exists.", enfe);
            }
            Collection<Areaespecializacao> areaespecializacaoCollection = areacurso.getAreaespecializacaoCollection();
            for (Areaespecializacao areaespecializacaoCollectionAreaespecializacao : areaespecializacaoCollection) {
                areaespecializacaoCollectionAreaespecializacao.setIdarea3(null);
                areaespecializacaoCollectionAreaespecializacao = em.merge(areaespecializacaoCollectionAreaespecializacao);
            }
            Collection<Areaespecializacao> areaespecializacaoCollection1 = areacurso.getAreaespecializacaoCollection1();
            for (Areaespecializacao areaespecializacaoCollection1Areaespecializacao : areaespecializacaoCollection1) {
                areaespecializacaoCollection1Areaespecializacao.setIdarea2(null);
                areaespecializacaoCollection1Areaespecializacao = em.merge(areaespecializacaoCollection1Areaespecializacao);
            }
            Collection<Areaespecializacao> areaespecializacaoCollection2 = areacurso.getAreaespecializacaoCollection2();
            for (Areaespecializacao areaespecializacaoCollection2Areaespecializacao : areaespecializacaoCollection2) {
                areaespecializacaoCollection2Areaespecializacao.setIdarea1(null);
                areaespecializacaoCollection2Areaespecializacao = em.merge(areaespecializacaoCollection2Areaespecializacao);
            }
            em.remove(areacurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Areacurso> findAreacursoEntities() {
        return findAreacursoEntities(true, -1, -1);
    }

    public List<Areacurso> findAreacursoEntities(int maxResults, int firstResult) {
        return findAreacursoEntities(false, maxResults, firstResult);
    }

    private List<Areacurso> findAreacursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areacurso.class));
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

    public Areacurso findAreacurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Areacurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreacursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Areacurso> rt = cq.from(Areacurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
