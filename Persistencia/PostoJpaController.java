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
import Modelo.Localidade;
import Modelo.Posto;
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
public class PostoJpaController implements Serializable {

    public PostoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
        public void create(Posto posto) {
        if (posto.getLocalidadeCollection() == null) {
            posto.setLocalidadeCollection(new ArrayList<Localidade>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distrito iddistrito = posto.getIddistrito();
            if (iddistrito != null) {
                iddistrito = em.getReference(iddistrito.getClass(), iddistrito.getIddistrito());
                posto.setIddistrito(iddistrito);
            }
            Collection<Localidade> attachedLocalidadeCollection = new ArrayList<Localidade>();
            for (Localidade localidadeCollectionLocalidadeToAttach : posto.getLocalidadeCollection()) {
                localidadeCollectionLocalidadeToAttach = em.getReference(localidadeCollectionLocalidadeToAttach.getClass(), localidadeCollectionLocalidadeToAttach.getIdlocalidade());
                attachedLocalidadeCollection.add(localidadeCollectionLocalidadeToAttach);
            }
            posto.setLocalidadeCollection(attachedLocalidadeCollection);
            em.persist(posto);
            if (iddistrito != null) {
                iddistrito.getPostoCollection().add(posto);
                iddistrito = em.merge(iddistrito);
            }
            for (Localidade localidadeCollectionLocalidade : posto.getLocalidadeCollection()) {
                Posto oldIdpostoOfLocalidadeCollectionLocalidade = localidadeCollectionLocalidade.getIdposto();
                localidadeCollectionLocalidade.setIdposto(posto);
                localidadeCollectionLocalidade = em.merge(localidadeCollectionLocalidade);
                if (oldIdpostoOfLocalidadeCollectionLocalidade != null) {
                    oldIdpostoOfLocalidadeCollectionLocalidade.getLocalidadeCollection().remove(localidadeCollectionLocalidade);
                    oldIdpostoOfLocalidadeCollectionLocalidade = em.merge(oldIdpostoOfLocalidadeCollectionLocalidade);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Posto posto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posto persistentPosto = em.find(Posto.class, posto.getIdposto());
            Distrito iddistritoOld = persistentPosto.getIddistrito();
            Distrito iddistritoNew = posto.getIddistrito();
            Collection<Localidade> localidadeCollectionOld = persistentPosto.getLocalidadeCollection();
            Collection<Localidade> localidadeCollectionNew = posto.getLocalidadeCollection();
            if (iddistritoNew != null) {
                iddistritoNew = em.getReference(iddistritoNew.getClass(), iddistritoNew.getIddistrito());
                posto.setIddistrito(iddistritoNew);
            }
            Collection<Localidade> attachedLocalidadeCollectionNew = new ArrayList<Localidade>();
            for (Localidade localidadeCollectionNewLocalidadeToAttach : localidadeCollectionNew) {
                localidadeCollectionNewLocalidadeToAttach = em.getReference(localidadeCollectionNewLocalidadeToAttach.getClass(), localidadeCollectionNewLocalidadeToAttach.getIdlocalidade());
                attachedLocalidadeCollectionNew.add(localidadeCollectionNewLocalidadeToAttach);
            }
            localidadeCollectionNew = attachedLocalidadeCollectionNew;
            posto.setLocalidadeCollection(localidadeCollectionNew);
            posto = em.merge(posto);
            if (iddistritoOld != null && !iddistritoOld.equals(iddistritoNew)) {
                iddistritoOld.getPostoCollection().remove(posto);
                iddistritoOld = em.merge(iddistritoOld);
            }
            if (iddistritoNew != null && !iddistritoNew.equals(iddistritoOld)) {
                iddistritoNew.getPostoCollection().add(posto);
                iddistritoNew = em.merge(iddistritoNew);
            }
            for (Localidade localidadeCollectionOldLocalidade : localidadeCollectionOld) {
                if (!localidadeCollectionNew.contains(localidadeCollectionOldLocalidade)) {
                    localidadeCollectionOldLocalidade.setIdposto(null);
                    localidadeCollectionOldLocalidade = em.merge(localidadeCollectionOldLocalidade);
                }
            }
            for (Localidade localidadeCollectionNewLocalidade : localidadeCollectionNew) {
                if (!localidadeCollectionOld.contains(localidadeCollectionNewLocalidade)) {
                    Posto oldIdpostoOfLocalidadeCollectionNewLocalidade = localidadeCollectionNewLocalidade.getIdposto();
                    localidadeCollectionNewLocalidade.setIdposto(posto);
                    localidadeCollectionNewLocalidade = em.merge(localidadeCollectionNewLocalidade);
                    if (oldIdpostoOfLocalidadeCollectionNewLocalidade != null && !oldIdpostoOfLocalidadeCollectionNewLocalidade.equals(posto)) {
                        oldIdpostoOfLocalidadeCollectionNewLocalidade.getLocalidadeCollection().remove(localidadeCollectionNewLocalidade);
                        oldIdpostoOfLocalidadeCollectionNewLocalidade = em.merge(oldIdpostoOfLocalidadeCollectionNewLocalidade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = posto.getIdposto();
                if (findPosto(id) == null) {
                    throw new NonexistentEntityException("The posto with id " + id + " no longer exists.");
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
            Posto posto;
            try {
                posto = em.getReference(Posto.class, id);
                posto.getIdposto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posto with id " + id + " no longer exists.", enfe);
            }
            Distrito iddistrito = posto.getIddistrito();
            if (iddistrito != null) {
                iddistrito.getPostoCollection().remove(posto);
                iddistrito = em.merge(iddistrito);
            }
            Collection<Localidade> localidadeCollection = posto.getLocalidadeCollection();
            for (Localidade localidadeCollectionLocalidade : localidadeCollection) {
                localidadeCollectionLocalidade.setIdposto(null);
                localidadeCollectionLocalidade = em.merge(localidadeCollectionLocalidade);
            }
            em.remove(posto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Posto> findPostoEntities() {
        return findPostoEntities(true, -1, -1);
    }

    public List<Posto> findPostoEntities(int maxResults, int firstResult) {
        return findPostoEntities(false, maxResults, firstResult);
    }

    private List<Posto> findPostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Posto.class));
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

    public Posto findPosto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Posto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPostoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Posto> rt = cq.from(Posto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
}
