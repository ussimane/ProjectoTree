/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Areadeformacao;
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
public class AreadeformacaoJpaController implements Serializable {

   public AreadeformacaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Areadeformacao areadeformacao) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Formando formandoOrphanCheck = areadeformacao.getFormando();
        if (formandoOrphanCheck != null) {
            Areadeformacao oldAreadeformacaoOfFormando = formandoOrphanCheck.getAreadeformacao();
            if (oldAreadeformacaoOfFormando != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Formando " + formandoOrphanCheck + " already has an item of type Areadeformacao whose formando column cannot be null. Please make another selection for the formando field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formando formando = areadeformacao.getFormando();
            if (formando != null) {
                formando = em.getReference(formando.getClass(), formando.getIdformando());
                areadeformacao.setFormando(formando);
            }
            Curso idcurso3 = areadeformacao.getIdcurso3();
            if (idcurso3 != null) {
                idcurso3 = em.getReference(idcurso3.getClass(), idcurso3.getIdcurso());
                areadeformacao.setIdcurso3(idcurso3);
            }
            Curso idcurso2 = areadeformacao.getIdcurso2();
            if (idcurso2 != null) {
                idcurso2 = em.getReference(idcurso2.getClass(), idcurso2.getIdcurso());
                areadeformacao.setIdcurso2(idcurso2);
            }
            Curso idcurso1 = areadeformacao.getIdcurso1();
            if (idcurso1 != null) {
                idcurso1 = em.getReference(idcurso1.getClass(), idcurso1.getIdcurso());
                areadeformacao.setIdcurso1(idcurso1);
            }
            em.persist(areadeformacao);
            if (formando != null) {
                formando.setAreadeformacao(areadeformacao);
                formando = em.merge(formando);
            }
            if (idcurso3 != null) {
                idcurso3.getAreadeformacaoCollection().add(areadeformacao);
                idcurso3 = em.merge(idcurso3);
            }
            if (idcurso2 != null) {
                idcurso2.getAreadeformacaoCollection().add(areadeformacao);
                idcurso2 = em.merge(idcurso2);
            }
            if (idcurso1 != null) {
                idcurso1.getAreadeformacaoCollection().add(areadeformacao);
                idcurso1 = em.merge(idcurso1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAreadeformacao(areadeformacao.getIdformando()) != null) {
                throw new PreexistingEntityException("Areadeformacao " + areadeformacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Areadeformacao areadeformacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areadeformacao persistentAreadeformacao = em.find(Areadeformacao.class, areadeformacao.getIdformando());
            Formando formandoOld = persistentAreadeformacao.getFormando();
            Formando formandoNew = areadeformacao.getFormando();
            Curso idcurso3Old = persistentAreadeformacao.getIdcurso3();
            Curso idcurso3New = areadeformacao.getIdcurso3();
            Curso idcurso2Old = persistentAreadeformacao.getIdcurso2();
            Curso idcurso2New = areadeformacao.getIdcurso2();
            Curso idcurso1Old = persistentAreadeformacao.getIdcurso1();
            Curso idcurso1New = areadeformacao.getIdcurso1();
            List<String> illegalOrphanMessages = null;
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                Areadeformacao oldAreadeformacaoOfFormando = formandoNew.getAreadeformacao();
                if (oldAreadeformacaoOfFormando != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Formando " + formandoNew + " already has an item of type Areadeformacao whose formando column cannot be null. Please make another selection for the formando field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formandoNew != null) {
                formandoNew = em.getReference(formandoNew.getClass(), formandoNew.getIdformando());
                areadeformacao.setFormando(formandoNew);
            }
            if (idcurso3New != null) {
                idcurso3New = em.getReference(idcurso3New.getClass(), idcurso3New.getIdcurso());
                areadeformacao.setIdcurso3(idcurso3New);
            }
            if (idcurso2New != null) {
                idcurso2New = em.getReference(idcurso2New.getClass(), idcurso2New.getIdcurso());
                areadeformacao.setIdcurso2(idcurso2New);
            }
            if (idcurso1New != null) {
                idcurso1New = em.getReference(idcurso1New.getClass(), idcurso1New.getIdcurso());
                areadeformacao.setIdcurso1(idcurso1New);
            }
            areadeformacao = em.merge(areadeformacao);
            if (formandoOld != null && !formandoOld.equals(formandoNew)) {
                formandoOld.setAreadeformacao(null);
                formandoOld = em.merge(formandoOld);
            }
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                formandoNew.setAreadeformacao(areadeformacao);
                formandoNew = em.merge(formandoNew);
            }
            if (idcurso3Old != null && !idcurso3Old.equals(idcurso3New)) {
                idcurso3Old.getAreadeformacaoCollection().remove(areadeformacao);
                idcurso3Old = em.merge(idcurso3Old);
            }
            if (idcurso3New != null && !idcurso3New.equals(idcurso3Old)) {
                idcurso3New.getAreadeformacaoCollection().add(areadeformacao);
                idcurso3New = em.merge(idcurso3New);
            }
            if (idcurso2Old != null && !idcurso2Old.equals(idcurso2New)) {
                idcurso2Old.getAreadeformacaoCollection().remove(areadeformacao);
                idcurso2Old = em.merge(idcurso2Old);
            }
            if (idcurso2New != null && !idcurso2New.equals(idcurso2Old)) {
                idcurso2New.getAreadeformacaoCollection().add(areadeformacao);
                idcurso2New = em.merge(idcurso2New);
            }
            if (idcurso1Old != null && !idcurso1Old.equals(idcurso1New)) {
                idcurso1Old.getAreadeformacaoCollection().remove(areadeformacao);
                idcurso1Old = em.merge(idcurso1Old);
            }
            if (idcurso1New != null && !idcurso1New.equals(idcurso1Old)) {
                idcurso1New.getAreadeformacaoCollection().add(areadeformacao);
                idcurso1New = em.merge(idcurso1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areadeformacao.getIdformando();
                if (findAreadeformacao(id) == null) {
                    throw new NonexistentEntityException("The areadeformacao with id " + id + " no longer exists.");
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
            Areadeformacao areadeformacao;
            try {
                areadeformacao = em.getReference(Areadeformacao.class, id);
                areadeformacao.getIdformando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areadeformacao with id " + id + " no longer exists.", enfe);
            }
            Formando formando = areadeformacao.getFormando();
            if (formando != null) {
                formando.setAreadeformacao(null);
                formando = em.merge(formando);
            }
            Curso idcurso3 = areadeformacao.getIdcurso3();
            if (idcurso3 != null) {
                idcurso3.getAreadeformacaoCollection().remove(areadeformacao);
                idcurso3 = em.merge(idcurso3);
            }
            Curso idcurso2 = areadeformacao.getIdcurso2();
            if (idcurso2 != null) {
                idcurso2.getAreadeformacaoCollection().remove(areadeformacao);
                idcurso2 = em.merge(idcurso2);
            }
            Curso idcurso1 = areadeformacao.getIdcurso1();
            if (idcurso1 != null) {
                idcurso1.getAreadeformacaoCollection().remove(areadeformacao);
                idcurso1 = em.merge(idcurso1);
            }
            em.remove(areadeformacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Areadeformacao> findAreadeformacaoEntities() {
        return findAreadeformacaoEntities(true, -1, -1);
    }

    public List<Areadeformacao> findAreadeformacaoEntities(int maxResults, int firstResult) {
        return findAreadeformacaoEntities(false, maxResults, firstResult);
    }

    private List<Areadeformacao> findAreadeformacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areadeformacao.class));
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

    public Areadeformacao findAreadeformacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Areadeformacao a = em.find(Areadeformacao.class, id);
              em.refresh(a);
            return a;
        } finally {
            em.close();
        }
    }

    public int getAreadeformacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Areadeformacao> rt = cq.from(Areadeformacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
