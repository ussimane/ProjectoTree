/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Areacurso;
import Modelo.Areaespecializacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Formador;
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
public class AreaespecializacaoJpaController implements Serializable {

    public AreaespecializacaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
 public void create(Areaespecializacao areaespecializacao) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Formador formadorOrphanCheck = areaespecializacao.getFormador();
        if (formadorOrphanCheck != null) {
            Areaespecializacao oldAreaespecializacaoOfFormador = formadorOrphanCheck.getAreaespecializacao();
            if (oldAreaespecializacaoOfFormador != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Formador " + formadorOrphanCheck + " already has an item of type Areaespecializacao whose formador column cannot be null. Please make another selection for the formador field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formador formador = areaespecializacao.getFormador();
            if (formador != null) {
                formador = em.getReference(formador.getClass(), formador.getIdformador());
                areaespecializacao.setFormador(formador);
            }
            Areacurso idarea3 = areaespecializacao.getIdarea3();
            if (idarea3 != null) {
                idarea3 = em.getReference(idarea3.getClass(), idarea3.getIdareacurso());
                areaespecializacao.setIdarea3(idarea3);
            }
            Areacurso idarea2 = areaespecializacao.getIdarea2();
            if (idarea2 != null) {
                idarea2 = em.getReference(idarea2.getClass(), idarea2.getIdareacurso());
                areaespecializacao.setIdarea2(idarea2);
            }
            Areacurso idarea1 = areaespecializacao.getIdarea1();
            if (idarea1 != null) {
                idarea1 = em.getReference(idarea1.getClass(), idarea1.getIdareacurso());
                areaespecializacao.setIdarea1(idarea1);
            }
            em.persist(areaespecializacao);
            if (formador != null) {
                formador.setAreaespecializacao(areaespecializacao);
                formador = em.merge(formador);
            }
            if (idarea3 != null) {
                idarea3.getAreaespecializacaoCollection().add(areaespecializacao);
                idarea3 = em.merge(idarea3);
            }
            if (idarea2 != null) {
                idarea2.getAreaespecializacaoCollection().add(areaespecializacao);
                idarea2 = em.merge(idarea2);
            }
            if (idarea1 != null) {
                idarea1.getAreaespecializacaoCollection().add(areaespecializacao);
                idarea1 = em.merge(idarea1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAreaespecializacao(areaespecializacao.getIdformador()) != null) {
                throw new PreexistingEntityException("Areaespecializacao " + areaespecializacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Areaespecializacao areaespecializacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areaespecializacao persistentAreaespecializacao = em.find(Areaespecializacao.class, areaespecializacao.getIdformador());
            Formador formadorOld = persistentAreaespecializacao.getFormador();
            Formador formadorNew = areaespecializacao.getFormador();
            Areacurso idarea3Old = persistentAreaespecializacao.getIdarea3();
            Areacurso idarea3New = areaespecializacao.getIdarea3();
            Areacurso idarea2Old = persistentAreaespecializacao.getIdarea2();
            Areacurso idarea2New = areaespecializacao.getIdarea2();
            Areacurso idarea1Old = persistentAreaespecializacao.getIdarea1();
            Areacurso idarea1New = areaespecializacao.getIdarea1();
            List<String> illegalOrphanMessages = null;
            if (formadorNew != null && !formadorNew.equals(formadorOld)) {
                Areaespecializacao oldAreaespecializacaoOfFormador = formadorNew.getAreaespecializacao();
                if (oldAreaespecializacaoOfFormador != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Formador " + formadorNew + " already has an item of type Areaespecializacao whose formador column cannot be null. Please make another selection for the formador field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formadorNew != null) {
                formadorNew = em.getReference(formadorNew.getClass(), formadorNew.getIdformador());
                areaespecializacao.setFormador(formadorNew);
            }
            if (idarea3New != null) {
                idarea3New = em.getReference(idarea3New.getClass(), idarea3New.getIdareacurso());
                areaespecializacao.setIdarea3(idarea3New);
            }
            if (idarea2New != null) {
                idarea2New = em.getReference(idarea2New.getClass(), idarea2New.getIdareacurso());
                areaespecializacao.setIdarea2(idarea2New);
            }
            if (idarea1New != null) {
                idarea1New = em.getReference(idarea1New.getClass(), idarea1New.getIdareacurso());
                areaespecializacao.setIdarea1(idarea1New);
            }
            areaespecializacao = em.merge(areaespecializacao);
            if (formadorOld != null && !formadorOld.equals(formadorNew)) {
                formadorOld.setAreaespecializacao(null);
                formadorOld = em.merge(formadorOld);
            }
            if (formadorNew != null && !formadorNew.equals(formadorOld)) {
                formadorNew.setAreaespecializacao(areaespecializacao);
                formadorNew = em.merge(formadorNew);
            }
            if (idarea3Old != null && !idarea3Old.equals(idarea3New)) {
                idarea3Old.getAreaespecializacaoCollection().remove(areaespecializacao);
                idarea3Old = em.merge(idarea3Old);
            }
            if (idarea3New != null && !idarea3New.equals(idarea3Old)) {
                idarea3New.getAreaespecializacaoCollection().add(areaespecializacao);
                idarea3New = em.merge(idarea3New);
            }
            if (idarea2Old != null && !idarea2Old.equals(idarea2New)) {
                idarea2Old.getAreaespecializacaoCollection().remove(areaespecializacao);
                idarea2Old = em.merge(idarea2Old);
            }
            if (idarea2New != null && !idarea2New.equals(idarea2Old)) {
                idarea2New.getAreaespecializacaoCollection().add(areaespecializacao);
                idarea2New = em.merge(idarea2New);
            }
            if (idarea1Old != null && !idarea1Old.equals(idarea1New)) {
                idarea1Old.getAreaespecializacaoCollection().remove(areaespecializacao);
                idarea1Old = em.merge(idarea1Old);
            }
            if (idarea1New != null && !idarea1New.equals(idarea1Old)) {
                idarea1New.getAreaespecializacaoCollection().add(areaespecializacao);
                idarea1New = em.merge(idarea1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areaespecializacao.getIdformador();
                if (findAreaespecializacao(id) == null) {
                    throw new NonexistentEntityException("The areaespecializacao with id " + id + " no longer exists.");
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
            Areaespecializacao areaespecializacao;
            try {
                areaespecializacao = em.getReference(Areaespecializacao.class, id);
                areaespecializacao.getIdformador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaespecializacao with id " + id + " no longer exists.", enfe);
            }
            Formador formador = areaespecializacao.getFormador();
            if (formador != null) {
                formador.setAreaespecializacao(null);
                formador = em.merge(formador);
            }
            Areacurso idarea3 = areaespecializacao.getIdarea3();
            if (idarea3 != null) {
                idarea3.getAreaespecializacaoCollection().remove(areaespecializacao);
                idarea3 = em.merge(idarea3);
            }
            Areacurso idarea2 = areaespecializacao.getIdarea2();
            if (idarea2 != null) {
                idarea2.getAreaespecializacaoCollection().remove(areaespecializacao);
                idarea2 = em.merge(idarea2);
            }
            Areacurso idarea1 = areaespecializacao.getIdarea1();
            if (idarea1 != null) {
                idarea1.getAreaespecializacaoCollection().remove(areaespecializacao);
                idarea1 = em.merge(idarea1);
            }
            em.remove(areaespecializacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Areaespecializacao> findAreaespecializacaoEntities() {
        return findAreaespecializacaoEntities(true, -1, -1);
    }

    public List<Areaespecializacao> findAreaespecializacaoEntities(int maxResults, int firstResult) {
        return findAreaespecializacaoEntities(false, maxResults, firstResult);
    }

    private List<Areaespecializacao> findAreaespecializacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areaespecializacao.class));
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

    public Areaespecializacao findAreaespecializacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Areaespecializacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaespecializacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Areaespecializacao> rt = cq.from(Areaespecializacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
}
