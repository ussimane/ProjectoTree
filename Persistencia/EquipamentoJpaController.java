/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Equipamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Equipamentofalta;
import java.util.ArrayList;
import java.util.List;
import Modelo.Oficinaequipamento;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class EquipamentoJpaController implements Serializable {

    public EquipamentoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
   public void create(Equipamento equipamento) {
        if (equipamento.getEquipamentofaltaCollection() == null) {
            equipamento.setEquipamentofaltaCollection(new ArrayList<Equipamentofalta>());
        }
        if (equipamento.getOficinaequipamentoCollection() == null) {
            equipamento.setOficinaequipamentoCollection(new ArrayList<Oficinaequipamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Equipamentofalta> attachedEquipamentofaltaCollection = new ArrayList<Equipamentofalta>();
            for (Equipamentofalta equipamentofaltaCollectionEquipamentofaltaToAttach : equipamento.getEquipamentofaltaCollection()) {
                equipamentofaltaCollectionEquipamentofaltaToAttach = em.getReference(equipamentofaltaCollectionEquipamentofaltaToAttach.getClass(), equipamentofaltaCollectionEquipamentofaltaToAttach.getEquipamentofaltaPK());
                attachedEquipamentofaltaCollection.add(equipamentofaltaCollectionEquipamentofaltaToAttach);
            }
            equipamento.setEquipamentofaltaCollection(attachedEquipamentofaltaCollection);
            Collection<Oficinaequipamento> attachedOficinaequipamentoCollection = new ArrayList<Oficinaequipamento>();
            for (Oficinaequipamento oficinaequipamentoCollectionOficinaequipamentoToAttach : equipamento.getOficinaequipamentoCollection()) {
                oficinaequipamentoCollectionOficinaequipamentoToAttach = em.getReference(oficinaequipamentoCollectionOficinaequipamentoToAttach.getClass(), oficinaequipamentoCollectionOficinaequipamentoToAttach.getOficinaequipamentoPK());
                attachedOficinaequipamentoCollection.add(oficinaequipamentoCollectionOficinaequipamentoToAttach);
            }
            equipamento.setOficinaequipamentoCollection(attachedOficinaequipamentoCollection);
            em.persist(equipamento);
            for (Equipamentofalta equipamentofaltaCollectionEquipamentofalta : equipamento.getEquipamentofaltaCollection()) {
                Equipamento oldEquipamentoOfEquipamentofaltaCollectionEquipamentofalta = equipamentofaltaCollectionEquipamentofalta.getEquipamento();
                equipamentofaltaCollectionEquipamentofalta.setEquipamento(equipamento);
                equipamentofaltaCollectionEquipamentofalta = em.merge(equipamentofaltaCollectionEquipamentofalta);
                if (oldEquipamentoOfEquipamentofaltaCollectionEquipamentofalta != null) {
                    oldEquipamentoOfEquipamentofaltaCollectionEquipamentofalta.getEquipamentofaltaCollection().remove(equipamentofaltaCollectionEquipamentofalta);
                    oldEquipamentoOfEquipamentofaltaCollectionEquipamentofalta = em.merge(oldEquipamentoOfEquipamentofaltaCollectionEquipamentofalta);
                }
            }
            for (Oficinaequipamento oficinaequipamentoCollectionOficinaequipamento : equipamento.getOficinaequipamentoCollection()) {
                Equipamento oldEquipamentoOfOficinaequipamentoCollectionOficinaequipamento = oficinaequipamentoCollectionOficinaequipamento.getEquipamento();
                oficinaequipamentoCollectionOficinaequipamento.setEquipamento(equipamento);
                oficinaequipamentoCollectionOficinaequipamento = em.merge(oficinaequipamentoCollectionOficinaequipamento);
                if (oldEquipamentoOfOficinaequipamentoCollectionOficinaequipamento != null) {
                    oldEquipamentoOfOficinaequipamentoCollectionOficinaequipamento.getOficinaequipamentoCollection().remove(oficinaequipamentoCollectionOficinaequipamento);
                    oldEquipamentoOfOficinaequipamentoCollectionOficinaequipamento = em.merge(oldEquipamentoOfOficinaequipamentoCollectionOficinaequipamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipamento equipamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipamento persistentEquipamento = em.find(Equipamento.class, equipamento.getIdequipamento());
            Collection<Equipamentofalta> equipamentofaltaCollectionOld = persistentEquipamento.getEquipamentofaltaCollection();
            Collection<Equipamentofalta> equipamentofaltaCollectionNew = equipamento.getEquipamentofaltaCollection();
            Collection<Oficinaequipamento> oficinaequipamentoCollectionOld = persistentEquipamento.getOficinaequipamentoCollection();
            Collection<Oficinaequipamento> oficinaequipamentoCollectionNew = equipamento.getOficinaequipamentoCollection();
            List<String> illegalOrphanMessages = null;
            for (Equipamentofalta equipamentofaltaCollectionOldEquipamentofalta : equipamentofaltaCollectionOld) {
                if (!equipamentofaltaCollectionNew.contains(equipamentofaltaCollectionOldEquipamentofalta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Equipamentofalta " + equipamentofaltaCollectionOldEquipamentofalta + " since its equipamento field is not nullable.");
                }
            }
            for (Oficinaequipamento oficinaequipamentoCollectionOldOficinaequipamento : oficinaequipamentoCollectionOld) {
                if (!oficinaequipamentoCollectionNew.contains(oficinaequipamentoCollectionOldOficinaequipamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Oficinaequipamento " + oficinaequipamentoCollectionOldOficinaequipamento + " since its equipamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Equipamentofalta> attachedEquipamentofaltaCollectionNew = new ArrayList<Equipamentofalta>();
            for (Equipamentofalta equipamentofaltaCollectionNewEquipamentofaltaToAttach : equipamentofaltaCollectionNew) {
                equipamentofaltaCollectionNewEquipamentofaltaToAttach = em.getReference(equipamentofaltaCollectionNewEquipamentofaltaToAttach.getClass(), equipamentofaltaCollectionNewEquipamentofaltaToAttach.getEquipamentofaltaPK());
                attachedEquipamentofaltaCollectionNew.add(equipamentofaltaCollectionNewEquipamentofaltaToAttach);
            }
            equipamentofaltaCollectionNew = attachedEquipamentofaltaCollectionNew;
            equipamento.setEquipamentofaltaCollection(equipamentofaltaCollectionNew);
            Collection<Oficinaequipamento> attachedOficinaequipamentoCollectionNew = new ArrayList<Oficinaequipamento>();
            for (Oficinaequipamento oficinaequipamentoCollectionNewOficinaequipamentoToAttach : oficinaequipamentoCollectionNew) {
                oficinaequipamentoCollectionNewOficinaequipamentoToAttach = em.getReference(oficinaequipamentoCollectionNewOficinaequipamentoToAttach.getClass(), oficinaequipamentoCollectionNewOficinaequipamentoToAttach.getOficinaequipamentoPK());
                attachedOficinaequipamentoCollectionNew.add(oficinaequipamentoCollectionNewOficinaequipamentoToAttach);
            }
            oficinaequipamentoCollectionNew = attachedOficinaequipamentoCollectionNew;
            equipamento.setOficinaequipamentoCollection(oficinaequipamentoCollectionNew);
            equipamento = em.merge(equipamento);
            for (Equipamentofalta equipamentofaltaCollectionNewEquipamentofalta : equipamentofaltaCollectionNew) {
                if (!equipamentofaltaCollectionOld.contains(equipamentofaltaCollectionNewEquipamentofalta)) {
                    Equipamento oldEquipamentoOfEquipamentofaltaCollectionNewEquipamentofalta = equipamentofaltaCollectionNewEquipamentofalta.getEquipamento();
                    equipamentofaltaCollectionNewEquipamentofalta.setEquipamento(equipamento);
                    equipamentofaltaCollectionNewEquipamentofalta = em.merge(equipamentofaltaCollectionNewEquipamentofalta);
                    if (oldEquipamentoOfEquipamentofaltaCollectionNewEquipamentofalta != null && !oldEquipamentoOfEquipamentofaltaCollectionNewEquipamentofalta.equals(equipamento)) {
                        oldEquipamentoOfEquipamentofaltaCollectionNewEquipamentofalta.getEquipamentofaltaCollection().remove(equipamentofaltaCollectionNewEquipamentofalta);
                        oldEquipamentoOfEquipamentofaltaCollectionNewEquipamentofalta = em.merge(oldEquipamentoOfEquipamentofaltaCollectionNewEquipamentofalta);
                    }
                }
            }
            for (Oficinaequipamento oficinaequipamentoCollectionNewOficinaequipamento : oficinaequipamentoCollectionNew) {
                if (!oficinaequipamentoCollectionOld.contains(oficinaequipamentoCollectionNewOficinaequipamento)) {
                    Equipamento oldEquipamentoOfOficinaequipamentoCollectionNewOficinaequipamento = oficinaequipamentoCollectionNewOficinaequipamento.getEquipamento();
                    oficinaequipamentoCollectionNewOficinaequipamento.setEquipamento(equipamento);
                    oficinaequipamentoCollectionNewOficinaequipamento = em.merge(oficinaequipamentoCollectionNewOficinaequipamento);
                    if (oldEquipamentoOfOficinaequipamentoCollectionNewOficinaequipamento != null && !oldEquipamentoOfOficinaequipamentoCollectionNewOficinaequipamento.equals(equipamento)) {
                        oldEquipamentoOfOficinaequipamentoCollectionNewOficinaequipamento.getOficinaequipamentoCollection().remove(oficinaequipamentoCollectionNewOficinaequipamento);
                        oldEquipamentoOfOficinaequipamentoCollectionNewOficinaequipamento = em.merge(oldEquipamentoOfOficinaequipamentoCollectionNewOficinaequipamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipamento.getIdequipamento();
                if (findEquipamento(id) == null) {
                    throw new NonexistentEntityException("The equipamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipamento equipamento;
            try {
                equipamento = em.getReference(Equipamento.class, id);
                equipamento.getIdequipamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Equipamentofalta> equipamentofaltaCollectionOrphanCheck = equipamento.getEquipamentofaltaCollection();
            for (Equipamentofalta equipamentofaltaCollectionOrphanCheckEquipamentofalta : equipamentofaltaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipamento (" + equipamento + ") cannot be destroyed since the Equipamentofalta " + equipamentofaltaCollectionOrphanCheckEquipamentofalta + " in its equipamentofaltaCollection field has a non-nullable equipamento field.");
            }
            Collection<Oficinaequipamento> oficinaequipamentoCollectionOrphanCheck = equipamento.getOficinaequipamentoCollection();
            for (Oficinaequipamento oficinaequipamentoCollectionOrphanCheckOficinaequipamento : oficinaequipamentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipamento (" + equipamento + ") cannot be destroyed since the Oficinaequipamento " + oficinaequipamentoCollectionOrphanCheckOficinaequipamento + " in its oficinaequipamentoCollection field has a non-nullable equipamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(equipamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipamento> findEquipamentoEntities() {
        return findEquipamentoEntities(true, -1, -1);
    }

    public List<Equipamento> findEquipamentoEntities(int maxResults, int firstResult) {
        return findEquipamentoEntities(false, maxResults, firstResult);
    }

    private List<Equipamento> findEquipamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipamento.class));
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

    public Equipamento findEquipamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipamento> rt = cq.from(Equipamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
