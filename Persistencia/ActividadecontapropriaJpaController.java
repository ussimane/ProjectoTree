/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Actividadecontapropria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Formando;
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
public class ActividadecontapropriaJpaController implements Serializable {

   public ActividadecontapropriaJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   public void create(Actividadecontapropria actividadecontapropria) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Formando formandoOrphanCheck = actividadecontapropria.getFormando();
        if (formandoOrphanCheck != null) {
            Actividadecontapropria oldActividadecontapropriaOfFormando = formandoOrphanCheck.getActividadecontapropria();
            if (oldActividadecontapropriaOfFormando != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Formando " + formandoOrphanCheck + " already has an item of type Actividadecontapropria whose formando column cannot be null. Please make another selection for the formando field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formando formando = actividadecontapropria.getFormando();
            if (formando != null) {
                formando = em.getReference(formando.getClass(), formando.getIdformando());
                actividadecontapropria.setFormando(formando);
            }
            em.persist(actividadecontapropria);
            if (formando != null) {
                formando.setActividadecontapropria(actividadecontapropria);
                formando = em.merge(formando);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActividadecontapropria(actividadecontapropria.getIdformando()) != null) {
                throw new PreexistingEntityException("Actividadecontapropria " + actividadecontapropria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadecontapropria actividadecontapropria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadecontapropria persistentActividadecontapropria = em.find(Actividadecontapropria.class, actividadecontapropria.getIdformando());
            Formando formandoOld = persistentActividadecontapropria.getFormando();
            Formando formandoNew = actividadecontapropria.getFormando();
            List<String> illegalOrphanMessages = null;
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                Actividadecontapropria oldActividadecontapropriaOfFormando = formandoNew.getActividadecontapropria();
                if (oldActividadecontapropriaOfFormando != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Formando " + formandoNew + " already has an item of type Actividadecontapropria whose formando column cannot be null. Please make another selection for the formando field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formandoNew != null) {
                formandoNew = em.getReference(formandoNew.getClass(), formandoNew.getIdformando());
                actividadecontapropria.setFormando(formandoNew);
            }
            actividadecontapropria = em.merge(actividadecontapropria);
            if (formandoOld != null && !formandoOld.equals(formandoNew)) {
                formandoOld.setActividadecontapropria(null);
                formandoOld = em.merge(formandoOld);
            }
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                formandoNew.setActividadecontapropria(actividadecontapropria);
                formandoNew = em.merge(formandoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadecontapropria.getIdformando();
                if (findActividadecontapropria(id) == null) {
                    throw new NonexistentEntityException("The actividadecontapropria with id " + id + " no longer exists.");
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
            Actividadecontapropria actividadecontapropria;
            try {
                actividadecontapropria = em.getReference(Actividadecontapropria.class, id);
                actividadecontapropria.getIdformando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadecontapropria with id " + id + " no longer exists.", enfe);
            }
            Formando formando = actividadecontapropria.getFormando();
            if (formando != null) {
                formando.setActividadecontapropria(null);
                formando = em.merge(formando);
            }
            em.remove(actividadecontapropria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividadecontapropria> findActividadecontapropriaEntities() {
        return findActividadecontapropriaEntities(true, -1, -1);
    }

    public List<Actividadecontapropria> findActividadecontapropriaEntities(int maxResults, int firstResult) {
        return findActividadecontapropriaEntities(false, maxResults, firstResult);
    }

    private List<Actividadecontapropria> findActividadecontapropriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividadecontapropria.class));
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

    public Actividadecontapropria findActividadecontapropria(Integer id) {
        EntityManager em = getEntityManager();
        try {
//            Actividadecontapropria ac=em.find(Actividadecontapropria.class, id);
//            em.refresh(ac);
              return em.find(Actividadecontapropria.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadecontapropriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividadecontapropria> rt = cq.from(Actividadecontapropria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
