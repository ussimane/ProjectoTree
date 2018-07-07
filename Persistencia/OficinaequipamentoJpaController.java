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
import Modelo.Oficinamestre;
import Modelo.Equipamento;
import Modelo.Oficinaequipamento;
import Modelo.OficinaequipamentoPK;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MASSINGUE
 */
public class OficinaequipamentoJpaController implements Serializable {

    public OficinaequipamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Oficinaequipamento oficinaequipamento) throws PreexistingEntityException, Exception {
        if (oficinaequipamento.getOficinaequipamentoPK() == null) {
            oficinaequipamento.setOficinaequipamentoPK(new OficinaequipamentoPK());
        }
        oficinaequipamento.getOficinaequipamentoPK().setIdoficina(oficinaequipamento.getOficinamestre().getIdmestre());
        oficinaequipamento.getOficinaequipamentoPK().setIdequipamento(oficinaequipamento.getEquipamento().getIdequipamento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oficinamestre oficinamestre = oficinaequipamento.getOficinamestre();
            if (oficinamestre != null) {
                oficinamestre = em.getReference(oficinamestre.getClass(), oficinamestre.getIdmestre());
                oficinaequipamento.setOficinamestre(oficinamestre);
            }
            Equipamento equipamento = oficinaequipamento.getEquipamento();
            if (equipamento != null) {
                equipamento = em.getReference(equipamento.getClass(), equipamento.getIdequipamento());
                oficinaequipamento.setEquipamento(equipamento);
            }
            em.persist(oficinaequipamento);
            if (oficinamestre != null) {
                oficinamestre.getOficinaequipamentoCollection().add(oficinaequipamento);
                oficinamestre = em.merge(oficinamestre);
            }
            if (equipamento != null) {
                equipamento.getOficinaequipamentoCollection().add(oficinaequipamento);
                equipamento = em.merge(equipamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOficinaequipamento(oficinaequipamento.getOficinaequipamentoPK()) != null) {
                throw new PreexistingEntityException("Oficinaequipamento " + oficinaequipamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Oficinaequipamento oficinaequipamento) throws NonexistentEntityException, Exception {
        oficinaequipamento.getOficinaequipamentoPK().setIdoficina(oficinaequipamento.getOficinamestre().getIdmestre());
        oficinaequipamento.getOficinaequipamentoPK().setIdequipamento(oficinaequipamento.getEquipamento().getIdequipamento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oficinaequipamento persistentOficinaequipamento = em.find(Oficinaequipamento.class, oficinaequipamento.getOficinaequipamentoPK());
            Oficinamestre oficinamestreOld = persistentOficinaequipamento.getOficinamestre();
            Oficinamestre oficinamestreNew = oficinaequipamento.getOficinamestre();
            Equipamento equipamentoOld = persistentOficinaequipamento.getEquipamento();
            Equipamento equipamentoNew = oficinaequipamento.getEquipamento();
            if (oficinamestreNew != null) {
                oficinamestreNew = em.getReference(oficinamestreNew.getClass(), oficinamestreNew.getIdmestre());
                oficinaequipamento.setOficinamestre(oficinamestreNew);
            }
            if (equipamentoNew != null) {
                equipamentoNew = em.getReference(equipamentoNew.getClass(), equipamentoNew.getIdequipamento());
                oficinaequipamento.setEquipamento(equipamentoNew);
            }
            oficinaequipamento = em.merge(oficinaequipamento);
            if (oficinamestreOld != null && !oficinamestreOld.equals(oficinamestreNew)) {
                oficinamestreOld.getOficinaequipamentoCollection().remove(oficinaequipamento);
                oficinamestreOld = em.merge(oficinamestreOld);
            }
            if (oficinamestreNew != null && !oficinamestreNew.equals(oficinamestreOld)) {
                oficinamestreNew.getOficinaequipamentoCollection().add(oficinaequipamento);
                oficinamestreNew = em.merge(oficinamestreNew);
            }
            if (equipamentoOld != null && !equipamentoOld.equals(equipamentoNew)) {
                equipamentoOld.getOficinaequipamentoCollection().remove(oficinaequipamento);
                equipamentoOld = em.merge(equipamentoOld);
            }
            if (equipamentoNew != null && !equipamentoNew.equals(equipamentoOld)) {
                equipamentoNew.getOficinaequipamentoCollection().add(oficinaequipamento);
                equipamentoNew = em.merge(equipamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OficinaequipamentoPK id = oficinaequipamento.getOficinaequipamentoPK();
                if (findOficinaequipamento(id) == null) {
                    throw new NonexistentEntityException("The oficinaequipamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OficinaequipamentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oficinaequipamento oficinaequipamento;
            try {
                oficinaequipamento = em.getReference(Oficinaequipamento.class, id);
                oficinaequipamento.getOficinaequipamentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The oficinaequipamento with id " + id + " no longer exists.", enfe);
            }
            Oficinamestre oficinamestre = oficinaequipamento.getOficinamestre();
            if (oficinamestre != null) {
                oficinamestre.getOficinaequipamentoCollection().remove(oficinaequipamento);
                oficinamestre = em.merge(oficinamestre);
            }
            Equipamento equipamento = oficinaequipamento.getEquipamento();
            if (equipamento != null) {
                equipamento.getOficinaequipamentoCollection().remove(oficinaequipamento);
                equipamento = em.merge(equipamento);
            }
            em.remove(oficinaequipamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Oficinaequipamento> findOficinaequipamentoEntities() {
        return findOficinaequipamentoEntities(true, -1, -1);
    }

    public List<Oficinaequipamento> findOficinaequipamentoEntities(int maxResults, int firstResult) {
        return findOficinaequipamentoEntities(false, maxResults, firstResult);
    }

    private List<Oficinaequipamento> findOficinaequipamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Oficinaequipamento.class));
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

    public Oficinaequipamento findOficinaequipamento(OficinaequipamentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Oficinaequipamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getOficinaequipamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Oficinaequipamento> rt = cq.from(Oficinaequipamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
