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
import Modelo.Formador;
import Modelo.Artigofinal;
import Modelo.Formadorturma;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
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
public class FormadorturmaJpaController implements Serializable {

    public FormadorturmaJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formadorturma formadorturma) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (formadorturma.getArtigofinalCollection() == null) {
            formadorturma.setArtigofinalCollection(new ArrayList<Artigofinal>());
        }
        List<String> illegalOrphanMessages = null;
        Formador formadorOrphanCheck = formadorturma.getFormador();
        if (formadorOrphanCheck != null) {
            Formadorturma oldFormadorturmaOfFormador = formadorOrphanCheck.getFormadorturma();
            if (oldFormadorturmaOfFormador != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Formador " + formadorOrphanCheck + " already has an item of type Formadorturma whose formador column cannot be null. Please make another selection for the formador field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formador formador = formadorturma.getFormador();
            if (formador != null) {
                formador = em.getReference(formador.getClass(), formador.getIdformador());
                formadorturma.setFormador(formador);
            }
            Collection<Artigofinal> attachedArtigofinalCollection = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionArtigofinalToAttach : formadorturma.getArtigofinalCollection()) {
                artigofinalCollectionArtigofinalToAttach = em.getReference(artigofinalCollectionArtigofinalToAttach.getClass(), artigofinalCollectionArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollection.add(artigofinalCollectionArtigofinalToAttach);
            }
            formadorturma.setArtigofinalCollection(attachedArtigofinalCollection);
            em.persist(formadorturma);
            if (formador != null) {
                formador.setFormadorturma(formadorturma);
                formador = em.merge(formador);
            }
            for (Artigofinal artigofinalCollectionArtigofinal : formadorturma.getArtigofinalCollection()) {
                Formadorturma oldFormadorturmaOfArtigofinalCollectionArtigofinal = artigofinalCollectionArtigofinal.getFormadorturma();
                artigofinalCollectionArtigofinal.setFormadorturma(formadorturma);
                artigofinalCollectionArtigofinal = em.merge(artigofinalCollectionArtigofinal);
                if (oldFormadorturmaOfArtigofinalCollectionArtigofinal != null) {
                    oldFormadorturmaOfArtigofinalCollectionArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionArtigofinal);
                    oldFormadorturmaOfArtigofinalCollectionArtigofinal = em.merge(oldFormadorturmaOfArtigofinalCollectionArtigofinal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormadorturma(formadorturma.getIdformador()) != null) {
                throw new PreexistingEntityException("Formadorturma " + formadorturma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formadorturma formadorturma) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadorturma persistentFormadorturma = em.find(Formadorturma.class, formadorturma.getIdformador());
            Formador formadorOld = persistentFormadorturma.getFormador();
            Formador formadorNew = formadorturma.getFormador();
            Collection<Artigofinal> artigofinalCollectionOld = persistentFormadorturma.getArtigofinalCollection();
            Collection<Artigofinal> artigofinalCollectionNew = formadorturma.getArtigofinalCollection();
            List<String> illegalOrphanMessages = null;
            if (formadorNew != null && !formadorNew.equals(formadorOld)) {
                Formadorturma oldFormadorturmaOfFormador = formadorNew.getFormadorturma();
                if (oldFormadorturmaOfFormador != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Formador " + formadorNew + " already has an item of type Formadorturma whose formador column cannot be null. Please make another selection for the formador field.");
                }
            }
            for (Artigofinal artigofinalCollectionOldArtigofinal : artigofinalCollectionOld) {
                if (!artigofinalCollectionNew.contains(artigofinalCollectionOldArtigofinal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artigofinal " + artigofinalCollectionOldArtigofinal + " since its formadorturma field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formadorNew != null) {
                formadorNew = em.getReference(formadorNew.getClass(), formadorNew.getIdformador());
                formadorturma.setFormador(formadorNew);
            }
            Collection<Artigofinal> attachedArtigofinalCollectionNew = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionNewArtigofinalToAttach : artigofinalCollectionNew) {
                artigofinalCollectionNewArtigofinalToAttach = em.getReference(artigofinalCollectionNewArtigofinalToAttach.getClass(), artigofinalCollectionNewArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollectionNew.add(artigofinalCollectionNewArtigofinalToAttach);
            }
            artigofinalCollectionNew = attachedArtigofinalCollectionNew;
            formadorturma.setArtigofinalCollection(artigofinalCollectionNew);
            formadorturma = em.merge(formadorturma);
            if (formadorOld != null && !formadorOld.equals(formadorNew)) {
                formadorOld.setFormadorturma(null);
                formadorOld = em.merge(formadorOld);
            }
            if (formadorNew != null && !formadorNew.equals(formadorOld)) {
                formadorNew.setFormadorturma(formadorturma);
                formadorNew = em.merge(formadorNew);
            }
            for (Artigofinal artigofinalCollectionNewArtigofinal : artigofinalCollectionNew) {
                if (!artigofinalCollectionOld.contains(artigofinalCollectionNewArtigofinal)) {
                    Formadorturma oldFormadorturmaOfArtigofinalCollectionNewArtigofinal = artigofinalCollectionNewArtigofinal.getFormadorturma();
                    artigofinalCollectionNewArtigofinal.setFormadorturma(formadorturma);
                    artigofinalCollectionNewArtigofinal = em.merge(artigofinalCollectionNewArtigofinal);
                    if (oldFormadorturmaOfArtigofinalCollectionNewArtigofinal != null && !oldFormadorturmaOfArtigofinalCollectionNewArtigofinal.equals(formadorturma)) {
                        oldFormadorturmaOfArtigofinalCollectionNewArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionNewArtigofinal);
                        oldFormadorturmaOfArtigofinalCollectionNewArtigofinal = em.merge(oldFormadorturmaOfArtigofinalCollectionNewArtigofinal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formadorturma.getIdformador();
                if (findFormadorturma(id) == null) {
                    throw new NonexistentEntityException("The formadorturma with id " + id + " no longer exists.");
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
            Formadorturma formadorturma;
            try {
                formadorturma = em.getReference(Formadorturma.class, id);
                formadorturma.getIdformador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formadorturma with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Artigofinal> artigofinalCollectionOrphanCheck = formadorturma.getArtigofinalCollection();
            for (Artigofinal artigofinalCollectionOrphanCheckArtigofinal : artigofinalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formadorturma (" + formadorturma + ") cannot be destroyed since the Artigofinal " + artigofinalCollectionOrphanCheckArtigofinal + " in its artigofinalCollection field has a non-nullable formadorturma field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Formador formador = formadorturma.getFormador();
            if (formador != null) {
                formador.setFormadorturma(null);
                formador = em.merge(formador);
            }
            em.remove(formadorturma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Formadorturma> findFormadorturmaEntities() {
        return findFormadorturmaEntities(true, -1, -1);
    }

    public List<Formadorturma> findFormadorturmaEntities(int maxResults, int firstResult) {
        return findFormadorturmaEntities(false, maxResults, firstResult);
    }

    private List<Formadorturma> findFormadorturmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formadorturma.class));
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

    public Formadorturma findFormadorturma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formadorturma.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormadorturmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formadorturma> rt = cq.from(Formadorturma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Formadorturma> prencherLocalidades(int p){
          List<Formadorturma> formandos = new FormadorturmaJpaController().findFormadorturmaEntities();//do banco
          List<Formadorturma> formandosselecionados = new ArrayList<Formadorturma>();//filtrada
         
         for (Formadorturma f : formandos) {
             if(f.getFormador().getIddistrito().getIdlocalidade()!=null){
                   if(f.getFormador().getIddistrito().getIdlocalidade()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
      public List<Formadorturma> prencherDistritos(int p){
          List<Formadorturma> formandos = new FormadorturmaJpaController().findFormadorturmaEntities();//do banco
          List<Formadorturma> formandosselecionados = new ArrayList<Formadorturma>();//filtrada
         
         for (Formadorturma f : formandos) {
             if(f.getFormador().getIddistrito().getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getFormador().getIddistrito().getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
//public List<Formadorturma> prencherCursos(int p){
//          List<Formadorturma> formandos = new FormadorturmaJpaController().findFormadorturmaEntities();//do banco
//          List<Formadorturma> formandosselecionados = new ArrayList<Formadorturma>();//filtrada
//         
//         for (Formadorturma f : formandos) {
//             if(f.getArealeccionar()!=null){
//                   if(f.getArealeccionar()==p){
//                  formandosselecionados.add(f);
//            }
//             }
//            
//        }
//               
//        return formandosselecionados;
//    }  
    
}
