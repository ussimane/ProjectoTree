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
import Modelo.Equipamento;
import Modelo.Equipamentofalta;
import Modelo.EquipamentofaltaPK;
import Modelo.Oficinamestre;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MASSINGUE
 */
public class EquipamentofaltaJpaController implements Serializable {

    public EquipamentofaltaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipamentofalta equipamentofalta) throws PreexistingEntityException, Exception {
        if (equipamentofalta.getEquipamentofaltaPK() == null) {
            equipamentofalta.setEquipamentofaltaPK(new EquipamentofaltaPK());
        }
        equipamentofalta.getEquipamentofaltaPK().setIdequipamento(equipamentofalta.getEquipamento().getIdequipamento());
        equipamentofalta.getEquipamentofaltaPK().setIdoficina(equipamentofalta.getOficinamestre().getIdmestre());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipamento equipamento = equipamentofalta.getEquipamento();
            if (equipamento != null) {
                equipamento = em.getReference(equipamento.getClass(), equipamento.getIdequipamento());
                equipamentofalta.setEquipamento(equipamento);
            }
            Oficinamestre oficinamestre = equipamentofalta.getOficinamestre();
            if (oficinamestre != null) {
                oficinamestre = em.getReference(oficinamestre.getClass(), oficinamestre.getIdmestre());
                equipamentofalta.setOficinamestre(oficinamestre);
            }
            em.persist(equipamentofalta);
            if (equipamento != null) {
                equipamento.getEquipamentofaltaCollection().add(equipamentofalta);
                equipamento = em.merge(equipamento);
            }
            if (oficinamestre != null) {
                oficinamestre.getEquipamentofaltaCollection().add(equipamentofalta);
                oficinamestre = em.merge(oficinamestre);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipamentofalta(equipamentofalta.getEquipamentofaltaPK()) != null) {
                throw new PreexistingEntityException("Equipamentofalta " + equipamentofalta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipamentofalta equipamentofalta) throws NonexistentEntityException, Exception {
        equipamentofalta.getEquipamentofaltaPK().setIdequipamento(equipamentofalta.getEquipamento().getIdequipamento());
        equipamentofalta.getEquipamentofaltaPK().setIdoficina(equipamentofalta.getOficinamestre().getIdmestre());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipamentofalta persistentEquipamentofalta = em.find(Equipamentofalta.class, equipamentofalta.getEquipamentofaltaPK());
            Equipamento equipamentoOld = persistentEquipamentofalta.getEquipamento();
            Equipamento equipamentoNew = equipamentofalta.getEquipamento();
            Oficinamestre oficinamestreOld = persistentEquipamentofalta.getOficinamestre();
            Oficinamestre oficinamestreNew = equipamentofalta.getOficinamestre();
            if (equipamentoNew != null) {
                equipamentoNew = em.getReference(equipamentoNew.getClass(), equipamentoNew.getIdequipamento());
                equipamentofalta.setEquipamento(equipamentoNew);
            }
            if (oficinamestreNew != null) {
                oficinamestreNew = em.getReference(oficinamestreNew.getClass(), oficinamestreNew.getIdmestre());
                equipamentofalta.setOficinamestre(oficinamestreNew);
            }
            equipamentofalta = em.merge(equipamentofalta);
            if (equipamentoOld != null && !equipamentoOld.equals(equipamentoNew)) {
                equipamentoOld.getEquipamentofaltaCollection().remove(equipamentofalta);
                equipamentoOld = em.merge(equipamentoOld);
            }
            if (equipamentoNew != null && !equipamentoNew.equals(equipamentoOld)) {
                equipamentoNew.getEquipamentofaltaCollection().add(equipamentofalta);
                equipamentoNew = em.merge(equipamentoNew);
            }
            if (oficinamestreOld != null && !oficinamestreOld.equals(oficinamestreNew)) {
                oficinamestreOld.getEquipamentofaltaCollection().remove(equipamentofalta);
                oficinamestreOld = em.merge(oficinamestreOld);
            }
            if (oficinamestreNew != null && !oficinamestreNew.equals(oficinamestreOld)) {
                oficinamestreNew.getEquipamentofaltaCollection().add(equipamentofalta);
                oficinamestreNew = em.merge(oficinamestreNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EquipamentofaltaPK id = equipamentofalta.getEquipamentofaltaPK();
                if (findEquipamentofalta(id) == null) {
                    throw new NonexistentEntityException("The equipamentofalta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EquipamentofaltaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipamentofalta equipamentofalta;
            try {
                equipamentofalta = em.getReference(Equipamentofalta.class, id);
                equipamentofalta.getEquipamentofaltaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipamentofalta with id " + id + " no longer exists.", enfe);
            }
            Equipamento equipamento = equipamentofalta.getEquipamento();
            if (equipamento != null) {
                equipamento.getEquipamentofaltaCollection().remove(equipamentofalta);
                equipamento = em.merge(equipamento);
            }
            Oficinamestre oficinamestre = equipamentofalta.getOficinamestre();
            if (oficinamestre != null) {
                oficinamestre.getEquipamentofaltaCollection().remove(equipamentofalta);
                oficinamestre = em.merge(oficinamestre);
            }
            em.remove(equipamentofalta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Equipamentofalta> findEquipamentofaltaEntities() {
        return findEquipamentofaltaEntities(true, -1, -1);
    }

    public List<Equipamentofalta> findEquipamentofaltaEntities(int maxResults, int firstResult) {
        return findEquipamentofaltaEntities(false, maxResults, firstResult);
    }

    private List<Equipamentofalta> findEquipamentofaltaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipamentofalta.class));
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

    public Equipamentofalta findEquipamentofalta(EquipamentofaltaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipamentofalta.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipamentofaltaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipamentofalta> rt = cq.from(Equipamentofalta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
