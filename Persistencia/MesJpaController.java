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
import Modelo.Artigofinal;
import java.util.ArrayList;
import java.util.List;
import Modelo.Artigo;
import Modelo.Mes;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class MesJpaController implements Serializable {

   public MesJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mes mes) {
        if (mes.getArtigofinalCollection() == null) {
            mes.setArtigofinalCollection(new ArrayList<Artigofinal>());
        }
        if (mes.getArtigoCollection() == null) {
            mes.setArtigoCollection(new ArrayList<Artigo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Artigofinal> attachedArtigofinalCollection = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionArtigofinalToAttach : mes.getArtigofinalCollection()) {
                artigofinalCollectionArtigofinalToAttach = em.getReference(artigofinalCollectionArtigofinalToAttach.getClass(), artigofinalCollectionArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollection.add(artigofinalCollectionArtigofinalToAttach);
            }
            mes.setArtigofinalCollection(attachedArtigofinalCollection);
            Collection<Artigo> attachedArtigoCollection = new ArrayList<Artigo>();
            for (Artigo artigoCollectionArtigoToAttach : mes.getArtigoCollection()) {
                artigoCollectionArtigoToAttach = em.getReference(artigoCollectionArtigoToAttach.getClass(), artigoCollectionArtigoToAttach.getArtigoPK());
                attachedArtigoCollection.add(artigoCollectionArtigoToAttach);
            }
            mes.setArtigoCollection(attachedArtigoCollection);
            em.persist(mes);
            for (Artigofinal artigofinalCollectionArtigofinal : mes.getArtigofinalCollection()) {
                Mes oldIdmesOfArtigofinalCollectionArtigofinal = artigofinalCollectionArtigofinal.getIdmes();
                artigofinalCollectionArtigofinal.setIdmes(mes);
                artigofinalCollectionArtigofinal = em.merge(artigofinalCollectionArtigofinal);
                if (oldIdmesOfArtigofinalCollectionArtigofinal != null) {
                    oldIdmesOfArtigofinalCollectionArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionArtigofinal);
                    oldIdmesOfArtigofinalCollectionArtigofinal = em.merge(oldIdmesOfArtigofinalCollectionArtigofinal);
                }
            }
            for (Artigo artigoCollectionArtigo : mes.getArtigoCollection()) {
                Mes oldIdmesOfArtigoCollectionArtigo = artigoCollectionArtigo.getIdmes();
                artigoCollectionArtigo.setIdmes(mes);
                artigoCollectionArtigo = em.merge(artigoCollectionArtigo);
                if (oldIdmesOfArtigoCollectionArtigo != null) {
                    oldIdmesOfArtigoCollectionArtigo.getArtigoCollection().remove(artigoCollectionArtigo);
                    oldIdmesOfArtigoCollectionArtigo = em.merge(oldIdmesOfArtigoCollectionArtigo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mes mes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mes persistentMes = em.find(Mes.class, mes.getIdmes());
            Collection<Artigofinal> artigofinalCollectionOld = persistentMes.getArtigofinalCollection();
            Collection<Artigofinal> artigofinalCollectionNew = mes.getArtigofinalCollection();
            Collection<Artigo> artigoCollectionOld = persistentMes.getArtigoCollection();
            Collection<Artigo> artigoCollectionNew = mes.getArtigoCollection();
            Collection<Artigofinal> attachedArtigofinalCollectionNew = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionNewArtigofinalToAttach : artigofinalCollectionNew) {
                artigofinalCollectionNewArtigofinalToAttach = em.getReference(artigofinalCollectionNewArtigofinalToAttach.getClass(), artigofinalCollectionNewArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollectionNew.add(artigofinalCollectionNewArtigofinalToAttach);
            }
            artigofinalCollectionNew = attachedArtigofinalCollectionNew;
            mes.setArtigofinalCollection(artigofinalCollectionNew);
            Collection<Artigo> attachedArtigoCollectionNew = new ArrayList<Artigo>();
            for (Artigo artigoCollectionNewArtigoToAttach : artigoCollectionNew) {
                artigoCollectionNewArtigoToAttach = em.getReference(artigoCollectionNewArtigoToAttach.getClass(), artigoCollectionNewArtigoToAttach.getArtigoPK());
                attachedArtigoCollectionNew.add(artigoCollectionNewArtigoToAttach);
            }
            artigoCollectionNew = attachedArtigoCollectionNew;
            mes.setArtigoCollection(artigoCollectionNew);
            mes = em.merge(mes);
            for (Artigofinal artigofinalCollectionOldArtigofinal : artigofinalCollectionOld) {
                if (!artigofinalCollectionNew.contains(artigofinalCollectionOldArtigofinal)) {
                    artigofinalCollectionOldArtigofinal.setIdmes(null);
                    artigofinalCollectionOldArtigofinal = em.merge(artigofinalCollectionOldArtigofinal);
                }
            }
            for (Artigofinal artigofinalCollectionNewArtigofinal : artigofinalCollectionNew) {
                if (!artigofinalCollectionOld.contains(artigofinalCollectionNewArtigofinal)) {
                    Mes oldIdmesOfArtigofinalCollectionNewArtigofinal = artigofinalCollectionNewArtigofinal.getIdmes();
                    artigofinalCollectionNewArtigofinal.setIdmes(mes);
                    artigofinalCollectionNewArtigofinal = em.merge(artigofinalCollectionNewArtigofinal);
                    if (oldIdmesOfArtigofinalCollectionNewArtigofinal != null && !oldIdmesOfArtigofinalCollectionNewArtigofinal.equals(mes)) {
                        oldIdmesOfArtigofinalCollectionNewArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionNewArtigofinal);
                        oldIdmesOfArtigofinalCollectionNewArtigofinal = em.merge(oldIdmesOfArtigofinalCollectionNewArtigofinal);
                    }
                }
            }
            for (Artigo artigoCollectionOldArtigo : artigoCollectionOld) {
                if (!artigoCollectionNew.contains(artigoCollectionOldArtigo)) {
                    artigoCollectionOldArtigo.setIdmes(null);
                    artigoCollectionOldArtigo = em.merge(artigoCollectionOldArtigo);
                }
            }
            for (Artigo artigoCollectionNewArtigo : artigoCollectionNew) {
                if (!artigoCollectionOld.contains(artigoCollectionNewArtigo)) {
                    Mes oldIdmesOfArtigoCollectionNewArtigo = artigoCollectionNewArtigo.getIdmes();
                    artigoCollectionNewArtigo.setIdmes(mes);
                    artigoCollectionNewArtigo = em.merge(artigoCollectionNewArtigo);
                    if (oldIdmesOfArtigoCollectionNewArtigo != null && !oldIdmesOfArtigoCollectionNewArtigo.equals(mes)) {
                        oldIdmesOfArtigoCollectionNewArtigo.getArtigoCollection().remove(artigoCollectionNewArtigo);
                        oldIdmesOfArtigoCollectionNewArtigo = em.merge(oldIdmesOfArtigoCollectionNewArtigo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mes.getIdmes();
                if (findMes(id) == null) {
                    throw new NonexistentEntityException("The mes with id " + id + " no longer exists.");
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
            Mes mes;
            try {
                mes = em.getReference(Mes.class, id);
                mes.getIdmes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mes with id " + id + " no longer exists.", enfe);
            }
            Collection<Artigofinal> artigofinalCollection = mes.getArtigofinalCollection();
            for (Artigofinal artigofinalCollectionArtigofinal : artigofinalCollection) {
                artigofinalCollectionArtigofinal.setIdmes(null);
                artigofinalCollectionArtigofinal = em.merge(artigofinalCollectionArtigofinal);
            }
            Collection<Artigo> artigoCollection = mes.getArtigoCollection();
            for (Artigo artigoCollectionArtigo : artigoCollection) {
                artigoCollectionArtigo.setIdmes(null);
                artigoCollectionArtigo = em.merge(artigoCollectionArtigo);
            }
            em.remove(mes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mes> findMesEntities() {
        return findMesEntities(true, -1, -1);
    }

    public List<Mes> findMesEntities(int maxResults, int firstResult) {
        return findMesEntities(false, maxResults, firstResult);
    }

    private List<Mes> findMesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mes.class));
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

    public Mes findMes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mes.class, id);
        } finally {
            em.close();
        }
    }

    public int getMesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mes> rt = cq.from(Mes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
