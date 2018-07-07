/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Accaoformacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Formando;
import Modelo.Curso;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class AccaoformacaoJpaController implements Serializable {

    public AccaoformacaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public void create(Accaoformacao accaoformacao) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Formando formandoOrphanCheck = accaoformacao.getFormando();
        if (formandoOrphanCheck != null) {
            Accaoformacao oldAccaoformacaoOfFormando = formandoOrphanCheck.getAccaoformacao();
            if (oldAccaoformacaoOfFormando != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Formando " + formandoOrphanCheck + " already has an item of type Accaoformacao whose formando column cannot be null. Please make another selection for the formando field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formando formando = accaoformacao.getFormando();
            if (formando != null) {
                formando = em.getReference(formando.getClass(), formando.getIdformando());
                accaoformacao.setFormando(formando);
            }
            Curso idcurso = accaoformacao.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdcurso());
                accaoformacao.setIdcurso(idcurso);
            }
            em.persist(accaoformacao);
            if (formando != null) {
                formando.setAccaoformacao(accaoformacao);
                formando = em.merge(formando);
            }
            if (idcurso != null) {
                idcurso.getAccaoformacaoCollection().add(accaoformacao);
                idcurso = em.merge(idcurso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccaoformacao(accaoformacao.getIdformando()) != null) {
                throw new PreexistingEntityException("Accaoformacao " + accaoformacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accaoformacao accaoformacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accaoformacao persistentAccaoformacao = em.find(Accaoformacao.class, accaoformacao.getIdformando());
            Formando formandoOld = persistentAccaoformacao.getFormando();
            Formando formandoNew = accaoformacao.getFormando();
            Curso idcursoOld = persistentAccaoformacao.getIdcurso();
            Curso idcursoNew = accaoformacao.getIdcurso();
            List<String> illegalOrphanMessages = null;
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                Accaoformacao oldAccaoformacaoOfFormando = formandoNew.getAccaoformacao();
                if (oldAccaoformacaoOfFormando != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Formando " + formandoNew + " already has an item of type Accaoformacao whose formando column cannot be null. Please make another selection for the formando field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formandoNew != null) {
                formandoNew = em.getReference(formandoNew.getClass(), formandoNew.getIdformando());
                accaoformacao.setFormando(formandoNew);
            }
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdcurso());
                accaoformacao.setIdcurso(idcursoNew);
            }
            accaoformacao = em.merge(accaoformacao);
            if (formandoOld != null && !formandoOld.equals(formandoNew)) {
                formandoOld.setAccaoformacao(null);
                formandoOld = em.merge(formandoOld);
            }
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                formandoNew.setAccaoformacao(accaoformacao);
                formandoNew = em.merge(formandoNew);
            }
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getAccaoformacaoCollection().remove(accaoformacao);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getAccaoformacaoCollection().add(accaoformacao);
                idcursoNew = em.merge(idcursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accaoformacao.getIdformando();
                if (findAccaoformacao(id) == null) {
                    throw new NonexistentEntityException("The accaoformacao with id " + id + " no longer exists.");
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
            Accaoformacao accaoformacao;
            try {
                accaoformacao = em.getReference(Accaoformacao.class, id);
                accaoformacao.getIdformando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accaoformacao with id " + id + " no longer exists.", enfe);
            }
            Formando formando = accaoformacao.getFormando();
            if (formando != null) {
                formando.setAccaoformacao(null);
                formando = em.merge(formando);
            }
            Curso idcurso = accaoformacao.getIdcurso();
            if (idcurso != null) {
                idcurso.getAccaoformacaoCollection().remove(accaoformacao);
                idcurso = em.merge(idcurso);
            }
            em.remove(accaoformacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Accaoformacao> findAccaoformacaoEntities() {
        return findAccaoformacaoEntities(true, -1, -1);
    }

    public List<Accaoformacao> findAccaoformacaoEntities(int maxResults, int firstResult) {
        return findAccaoformacaoEntities(false, maxResults, firstResult);
    }

    private List<Accaoformacao> findAccaoformacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accaoformacao.class));
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

    public Accaoformacao findAccaoformacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accaoformacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccaoformacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accaoformacao> rt = cq.from(Accaoformacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
