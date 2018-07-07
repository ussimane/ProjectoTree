/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Actividadeprofissao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Oficinamestre;
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
public class ActividadeprofissaoJpaController implements Serializable {

   public ActividadeprofissaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
       public void create(Actividadeprofissao actividadeprofissao) {
        if (actividadeprofissao.getOficinamestreCollection() == null) {
            actividadeprofissao.setOficinamestreCollection(new ArrayList<Oficinamestre>());
        }
        if (actividadeprofissao.getOficinamestreCollection1() == null) {
            actividadeprofissao.setOficinamestreCollection1(new ArrayList<Oficinamestre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Oficinamestre> attachedOficinamestreCollection = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionOficinamestreToAttach : actividadeprofissao.getOficinamestreCollection()) {
                oficinamestreCollectionOficinamestreToAttach = em.getReference(oficinamestreCollectionOficinamestreToAttach.getClass(), oficinamestreCollectionOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection.add(oficinamestreCollectionOficinamestreToAttach);
            }
            actividadeprofissao.setOficinamestreCollection(attachedOficinamestreCollection);
            Collection<Oficinamestre> attachedOficinamestreCollection1 = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollection1OficinamestreToAttach : actividadeprofissao.getOficinamestreCollection1()) {
                oficinamestreCollection1OficinamestreToAttach = em.getReference(oficinamestreCollection1OficinamestreToAttach.getClass(), oficinamestreCollection1OficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection1.add(oficinamestreCollection1OficinamestreToAttach);
            }
            actividadeprofissao.setOficinamestreCollection1(attachedOficinamestreCollection1);
            em.persist(actividadeprofissao);
            for (Oficinamestre oficinamestreCollectionOficinamestre : actividadeprofissao.getOficinamestreCollection()) {
                Actividadeprofissao oldIdanosoficinaOfOficinamestreCollectionOficinamestre = oficinamestreCollectionOficinamestre.getIdanosoficina();
                oficinamestreCollectionOficinamestre.setIdanosoficina(actividadeprofissao);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
                if (oldIdanosoficinaOfOficinamestreCollectionOficinamestre != null) {
                    oldIdanosoficinaOfOficinamestreCollectionOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionOficinamestre);
                    oldIdanosoficinaOfOficinamestreCollectionOficinamestre = em.merge(oldIdanosoficinaOfOficinamestreCollectionOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollection1Oficinamestre : actividadeprofissao.getOficinamestreCollection1()) {
                Actividadeprofissao oldIdanosexperienciaOfOficinamestreCollection1Oficinamestre = oficinamestreCollection1Oficinamestre.getIdanosexperiencia();
                oficinamestreCollection1Oficinamestre.setIdanosexperiencia(actividadeprofissao);
                oficinamestreCollection1Oficinamestre = em.merge(oficinamestreCollection1Oficinamestre);
                if (oldIdanosexperienciaOfOficinamestreCollection1Oficinamestre != null) {
                    oldIdanosexperienciaOfOficinamestreCollection1Oficinamestre.getOficinamestreCollection1().remove(oficinamestreCollection1Oficinamestre);
                    oldIdanosexperienciaOfOficinamestreCollection1Oficinamestre = em.merge(oldIdanosexperienciaOfOficinamestreCollection1Oficinamestre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadeprofissao actividadeprofissao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadeprofissao persistentActividadeprofissao = em.find(Actividadeprofissao.class, actividadeprofissao.getIdactividadeprofissao());
            Collection<Oficinamestre> oficinamestreCollectionOld = persistentActividadeprofissao.getOficinamestreCollection();
            Collection<Oficinamestre> oficinamestreCollectionNew = actividadeprofissao.getOficinamestreCollection();
            Collection<Oficinamestre> oficinamestreCollection1Old = persistentActividadeprofissao.getOficinamestreCollection1();
            Collection<Oficinamestre> oficinamestreCollection1New = actividadeprofissao.getOficinamestreCollection1();
            Collection<Oficinamestre> attachedOficinamestreCollectionNew = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollectionNewOficinamestreToAttach : oficinamestreCollectionNew) {
                oficinamestreCollectionNewOficinamestreToAttach = em.getReference(oficinamestreCollectionNewOficinamestreToAttach.getClass(), oficinamestreCollectionNewOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollectionNew.add(oficinamestreCollectionNewOficinamestreToAttach);
            }
            oficinamestreCollectionNew = attachedOficinamestreCollectionNew;
            actividadeprofissao.setOficinamestreCollection(oficinamestreCollectionNew);
            Collection<Oficinamestre> attachedOficinamestreCollection1New = new ArrayList<Oficinamestre>();
            for (Oficinamestre oficinamestreCollection1NewOficinamestreToAttach : oficinamestreCollection1New) {
                oficinamestreCollection1NewOficinamestreToAttach = em.getReference(oficinamestreCollection1NewOficinamestreToAttach.getClass(), oficinamestreCollection1NewOficinamestreToAttach.getIdmestre());
                attachedOficinamestreCollection1New.add(oficinamestreCollection1NewOficinamestreToAttach);
            }
            oficinamestreCollection1New = attachedOficinamestreCollection1New;
            actividadeprofissao.setOficinamestreCollection1(oficinamestreCollection1New);
            actividadeprofissao = em.merge(actividadeprofissao);
            for (Oficinamestre oficinamestreCollectionOldOficinamestre : oficinamestreCollectionOld) {
                if (!oficinamestreCollectionNew.contains(oficinamestreCollectionOldOficinamestre)) {
                    oficinamestreCollectionOldOficinamestre.setIdanosoficina(null);
                    oficinamestreCollectionOldOficinamestre = em.merge(oficinamestreCollectionOldOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollectionNewOficinamestre : oficinamestreCollectionNew) {
                if (!oficinamestreCollectionOld.contains(oficinamestreCollectionNewOficinamestre)) {
                    Actividadeprofissao oldIdanosoficinaOfOficinamestreCollectionNewOficinamestre = oficinamestreCollectionNewOficinamestre.getIdanosoficina();
                    oficinamestreCollectionNewOficinamestre.setIdanosoficina(actividadeprofissao);
                    oficinamestreCollectionNewOficinamestre = em.merge(oficinamestreCollectionNewOficinamestre);
                    if (oldIdanosoficinaOfOficinamestreCollectionNewOficinamestre != null && !oldIdanosoficinaOfOficinamestreCollectionNewOficinamestre.equals(actividadeprofissao)) {
                        oldIdanosoficinaOfOficinamestreCollectionNewOficinamestre.getOficinamestreCollection().remove(oficinamestreCollectionNewOficinamestre);
                        oldIdanosoficinaOfOficinamestreCollectionNewOficinamestre = em.merge(oldIdanosoficinaOfOficinamestreCollectionNewOficinamestre);
                    }
                }
            }
            for (Oficinamestre oficinamestreCollection1OldOficinamestre : oficinamestreCollection1Old) {
                if (!oficinamestreCollection1New.contains(oficinamestreCollection1OldOficinamestre)) {
                    oficinamestreCollection1OldOficinamestre.setIdanosexperiencia(null);
                    oficinamestreCollection1OldOficinamestre = em.merge(oficinamestreCollection1OldOficinamestre);
                }
            }
            for (Oficinamestre oficinamestreCollection1NewOficinamestre : oficinamestreCollection1New) {
                if (!oficinamestreCollection1Old.contains(oficinamestreCollection1NewOficinamestre)) {
                    Actividadeprofissao oldIdanosexperienciaOfOficinamestreCollection1NewOficinamestre = oficinamestreCollection1NewOficinamestre.getIdanosexperiencia();
                    oficinamestreCollection1NewOficinamestre.setIdanosexperiencia(actividadeprofissao);
                    oficinamestreCollection1NewOficinamestre = em.merge(oficinamestreCollection1NewOficinamestre);
                    if (oldIdanosexperienciaOfOficinamestreCollection1NewOficinamestre != null && !oldIdanosexperienciaOfOficinamestreCollection1NewOficinamestre.equals(actividadeprofissao)) {
                        oldIdanosexperienciaOfOficinamestreCollection1NewOficinamestre.getOficinamestreCollection1().remove(oficinamestreCollection1NewOficinamestre);
                        oldIdanosexperienciaOfOficinamestreCollection1NewOficinamestre = em.merge(oldIdanosexperienciaOfOficinamestreCollection1NewOficinamestre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadeprofissao.getIdactividadeprofissao();
                if (findActividadeprofissao(id) == null) {
                    throw new NonexistentEntityException("The actividadeprofissao with id " + id + " no longer exists.");
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
            Actividadeprofissao actividadeprofissao;
            try {
                actividadeprofissao = em.getReference(Actividadeprofissao.class, id);
                actividadeprofissao.getIdactividadeprofissao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadeprofissao with id " + id + " no longer exists.", enfe);
            }
            Collection<Oficinamestre> oficinamestreCollection = actividadeprofissao.getOficinamestreCollection();
            for (Oficinamestre oficinamestreCollectionOficinamestre : oficinamestreCollection) {
                oficinamestreCollectionOficinamestre.setIdanosoficina(null);
                oficinamestreCollectionOficinamestre = em.merge(oficinamestreCollectionOficinamestre);
            }
            Collection<Oficinamestre> oficinamestreCollection1 = actividadeprofissao.getOficinamestreCollection1();
            for (Oficinamestre oficinamestreCollection1Oficinamestre : oficinamestreCollection1) {
                oficinamestreCollection1Oficinamestre.setIdanosexperiencia(null);
                oficinamestreCollection1Oficinamestre = em.merge(oficinamestreCollection1Oficinamestre);
            }
            em.remove(actividadeprofissao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Actividadeprofissao> findActividadeprofissaoEntities() {
        return findActividadeprofissaoEntities(true, -1, -1);
    }

    public List<Actividadeprofissao> findActividadeprofissaoEntities(int maxResults, int firstResult) {
        return findActividadeprofissaoEntities(false, maxResults, firstResult);
    }

    private List<Actividadeprofissao> findActividadeprofissaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividadeprofissao.class));
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

    public Actividadeprofissao findActividadeprofissao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividadeprofissao.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadeprofissaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividadeprofissao> rt = cq.from(Actividadeprofissao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
