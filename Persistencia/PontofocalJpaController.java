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
import Modelo.Distrito;
import Modelo.Nivelhabilitacao;
import Modelo.Curso;
import Modelo.Cursoformacao;
import Modelo.Pontofocal;
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
public class PontofocalJpaController implements Serializable {

   public PontofocalJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pontofocal pontofocal) {
        if (pontofocal.getCursoformacaoCollection() == null) {
            pontofocal.setCursoformacaoCollection(new ArrayList<Cursoformacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nivelhabilitacao idnivelhabilitacao = pontofocal.getIdnivelhabilitacao();
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao = em.getReference(idnivelhabilitacao.getClass(), idnivelhabilitacao.getIdnivelhabilitacao());
                pontofocal.setIdnivelhabilitacao(idnivelhabilitacao);
            }
            Distrito iddistrito = pontofocal.getIddistrito();
            if (iddistrito != null) {
                iddistrito = em.getReference(iddistrito.getClass(), iddistrito.getIddistrito());
                pontofocal.setIddistrito(iddistrito);
            }
            Collection<Cursoformacao> attachedCursoformacaoCollection = new ArrayList<Cursoformacao>();
            for (Cursoformacao cursoformacaoCollectionCursoformacaoToAttach : pontofocal.getCursoformacaoCollection()) {
                cursoformacaoCollectionCursoformacaoToAttach = em.getReference(cursoformacaoCollectionCursoformacaoToAttach.getClass(), cursoformacaoCollectionCursoformacaoToAttach.getIdcursoformacao());
                attachedCursoformacaoCollection.add(cursoformacaoCollectionCursoformacaoToAttach);
            }
            pontofocal.setCursoformacaoCollection(attachedCursoformacaoCollection);
            em.persist(pontofocal);
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao.getPontofocalCollection().add(pontofocal);
                idnivelhabilitacao = em.merge(idnivelhabilitacao);
            }
            if (iddistrito != null) {
                iddistrito.getPontofocalCollection().add(pontofocal);
                iddistrito = em.merge(iddistrito);
            }
            for (Cursoformacao cursoformacaoCollectionCursoformacao : pontofocal.getCursoformacaoCollection()) {
                Pontofocal oldIdpontofocalOfCursoformacaoCollectionCursoformacao = cursoformacaoCollectionCursoformacao.getIdpontofocal();
                cursoformacaoCollectionCursoformacao.setIdpontofocal(pontofocal);
                cursoformacaoCollectionCursoformacao = em.merge(cursoformacaoCollectionCursoformacao);
                if (oldIdpontofocalOfCursoformacaoCollectionCursoformacao != null) {
                    oldIdpontofocalOfCursoformacaoCollectionCursoformacao.getCursoformacaoCollection().remove(cursoformacaoCollectionCursoformacao);
                    oldIdpontofocalOfCursoformacaoCollectionCursoformacao = em.merge(oldIdpontofocalOfCursoformacaoCollectionCursoformacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pontofocal pontofocal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pontofocal persistentPontofocal = em.find(Pontofocal.class, pontofocal.getIdpontofocal());
            Nivelhabilitacao idnivelhabilitacaoOld = persistentPontofocal.getIdnivelhabilitacao();
            Nivelhabilitacao idnivelhabilitacaoNew = pontofocal.getIdnivelhabilitacao();
            Distrito iddistritoOld = persistentPontofocal.getIddistrito();
            Distrito iddistritoNew = pontofocal.getIddistrito();
            Collection<Cursoformacao> cursoformacaoCollectionOld = persistentPontofocal.getCursoformacaoCollection();
            Collection<Cursoformacao> cursoformacaoCollectionNew = pontofocal.getCursoformacaoCollection();
            if (idnivelhabilitacaoNew != null) {
                idnivelhabilitacaoNew = em.getReference(idnivelhabilitacaoNew.getClass(), idnivelhabilitacaoNew.getIdnivelhabilitacao());
                pontofocal.setIdnivelhabilitacao(idnivelhabilitacaoNew);
            }
            if (iddistritoNew != null) {
                iddistritoNew = em.getReference(iddistritoNew.getClass(), iddistritoNew.getIddistrito());
                pontofocal.setIddistrito(iddistritoNew);
            }
            Collection<Cursoformacao> attachedCursoformacaoCollectionNew = new ArrayList<Cursoformacao>();
            for (Cursoformacao cursoformacaoCollectionNewCursoformacaoToAttach : cursoformacaoCollectionNew) {
                cursoformacaoCollectionNewCursoformacaoToAttach = em.getReference(cursoformacaoCollectionNewCursoformacaoToAttach.getClass(), cursoformacaoCollectionNewCursoformacaoToAttach.getIdcursoformacao());
                attachedCursoformacaoCollectionNew.add(cursoformacaoCollectionNewCursoformacaoToAttach);
            }
            cursoformacaoCollectionNew = attachedCursoformacaoCollectionNew;
            pontofocal.setCursoformacaoCollection(cursoformacaoCollectionNew);
            pontofocal = em.merge(pontofocal);
            if (idnivelhabilitacaoOld != null && !idnivelhabilitacaoOld.equals(idnivelhabilitacaoNew)) {
                idnivelhabilitacaoOld.getPontofocalCollection().remove(pontofocal);
                idnivelhabilitacaoOld = em.merge(idnivelhabilitacaoOld);
            }
            if (idnivelhabilitacaoNew != null && !idnivelhabilitacaoNew.equals(idnivelhabilitacaoOld)) {
                idnivelhabilitacaoNew.getPontofocalCollection().add(pontofocal);
                idnivelhabilitacaoNew = em.merge(idnivelhabilitacaoNew);
            }
            if (iddistritoOld != null && !iddistritoOld.equals(iddistritoNew)) {
                iddistritoOld.getPontofocalCollection().remove(pontofocal);
                iddistritoOld = em.merge(iddistritoOld);
            }
            if (iddistritoNew != null && !iddistritoNew.equals(iddistritoOld)) {
                iddistritoNew.getPontofocalCollection().add(pontofocal);
                iddistritoNew = em.merge(iddistritoNew);
            }
            for (Cursoformacao cursoformacaoCollectionOldCursoformacao : cursoformacaoCollectionOld) {
                if (!cursoformacaoCollectionNew.contains(cursoformacaoCollectionOldCursoformacao)) {
                    cursoformacaoCollectionOldCursoformacao.setIdpontofocal(null);
                    cursoformacaoCollectionOldCursoformacao = em.merge(cursoformacaoCollectionOldCursoformacao);
                }
            }
            for (Cursoformacao cursoformacaoCollectionNewCursoformacao : cursoformacaoCollectionNew) {
                if (!cursoformacaoCollectionOld.contains(cursoformacaoCollectionNewCursoformacao)) {
                    Pontofocal oldIdpontofocalOfCursoformacaoCollectionNewCursoformacao = cursoformacaoCollectionNewCursoformacao.getIdpontofocal();
                    cursoformacaoCollectionNewCursoformacao.setIdpontofocal(pontofocal);
                    cursoformacaoCollectionNewCursoformacao = em.merge(cursoformacaoCollectionNewCursoformacao);
                    if (oldIdpontofocalOfCursoformacaoCollectionNewCursoformacao != null && !oldIdpontofocalOfCursoformacaoCollectionNewCursoformacao.equals(pontofocal)) {
                        oldIdpontofocalOfCursoformacaoCollectionNewCursoformacao.getCursoformacaoCollection().remove(cursoformacaoCollectionNewCursoformacao);
                        oldIdpontofocalOfCursoformacaoCollectionNewCursoformacao = em.merge(oldIdpontofocalOfCursoformacaoCollectionNewCursoformacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pontofocal.getIdpontofocal();
                if (findPontofocal(id) == null) {
                    throw new NonexistentEntityException("The pontofocal with id " + id + " no longer exists.");
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
            Pontofocal pontofocal;
            try {
                pontofocal = em.getReference(Pontofocal.class, id);
                pontofocal.getIdpontofocal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pontofocal with id " + id + " no longer exists.", enfe);
            }
            Nivelhabilitacao idnivelhabilitacao = pontofocal.getIdnivelhabilitacao();
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao.getPontofocalCollection().remove(pontofocal);
                idnivelhabilitacao = em.merge(idnivelhabilitacao);
            }
            Distrito iddistrito = pontofocal.getIddistrito();
            if (iddistrito != null) {
                iddistrito.getPontofocalCollection().remove(pontofocal);
                iddistrito = em.merge(iddistrito);
            }
            Collection<Cursoformacao> cursoformacaoCollection = pontofocal.getCursoformacaoCollection();
            for (Cursoformacao cursoformacaoCollectionCursoformacao : cursoformacaoCollection) {
                cursoformacaoCollectionCursoformacao.setIdpontofocal(null);
                cursoformacaoCollectionCursoformacao = em.merge(cursoformacaoCollectionCursoformacao);
            }
            em.remove(pontofocal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Pontofocal> findPontofocalEntities() {
        return findPontofocalEntities(true, -1, -1);
    }

    public List<Pontofocal> findPontofocalEntities(int maxResults, int firstResult) {
        return findPontofocalEntities(false, maxResults, firstResult);
    }

    private List<Pontofocal> findPontofocalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pontofocal.class));
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

    public Pontofocal findPontofocal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pontofocal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPontofocalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pontofocal> rt = cq.from(Pontofocal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
