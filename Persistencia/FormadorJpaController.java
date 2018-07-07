/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Areaespecializacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Distrito;
import Modelo.Formacaoturma;
import Modelo.Formador;
import Modelo.Formadorturma;
import Modelo.Localidade;
import Persistencia.exceptions.IllegalOrphanException;
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
public class FormadorJpaController implements Serializable {

     public FormadorJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Formador formador) {
        if (formador.getFormacaoturmaCollection() == null) {
            formador.setFormacaoturmaCollection(new ArrayList<Formacaoturma>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadorturma formadorturma = formador.getFormadorturma();
            if (formadorturma != null) {
                formadorturma = em.getReference(formadorturma.getClass(), formadorturma.getIdformador());
                formador.setFormadorturma(formadorturma);
            }
            Localidade iddistrito = formador.getIddistrito();
            if (iddistrito != null) {
                iddistrito = em.getReference(iddistrito.getClass(), iddistrito.getIdlocalidade());
                formador.setIddistrito(iddistrito);
            }
            Areaespecializacao areaespecializacao = formador.getAreaespecializacao();
            if (areaespecializacao != null) {
                areaespecializacao = em.getReference(areaespecializacao.getClass(), areaespecializacao.getIdformador());
                formador.setAreaespecializacao(areaespecializacao);
            }
            Collection<Formacaoturma> attachedFormacaoturmaCollection = new ArrayList<Formacaoturma>();
            for (Formacaoturma formacaoturmaCollectionFormacaoturmaToAttach : formador.getFormacaoturmaCollection()) {
                formacaoturmaCollectionFormacaoturmaToAttach = em.getReference(formacaoturmaCollectionFormacaoturmaToAttach.getClass(), formacaoturmaCollectionFormacaoturmaToAttach.getIdformando());
                attachedFormacaoturmaCollection.add(formacaoturmaCollectionFormacaoturmaToAttach);
            }
            formador.setFormacaoturmaCollection(attachedFormacaoturmaCollection);
            em.persist(formador);
            if (formadorturma != null) {
                Formador oldFormadorOfFormadorturma = formadorturma.getFormador();
                if (oldFormadorOfFormadorturma != null) {
                    oldFormadorOfFormadorturma.setFormadorturma(null);
                    oldFormadorOfFormadorturma = em.merge(oldFormadorOfFormadorturma);
                }
                formadorturma.setFormador(formador);
                formadorturma = em.merge(formadorturma);
            }
            if (iddistrito != null) {
                iddistrito.getFormadorCollection().add(formador);
                iddistrito = em.merge(iddistrito);
            }
            if (areaespecializacao != null) {
                Formador oldFormadorOfAreaespecializacao = areaespecializacao.getFormador();
                if (oldFormadorOfAreaespecializacao != null) {
                    oldFormadorOfAreaespecializacao.setAreaespecializacao(null);
                    oldFormadorOfAreaespecializacao = em.merge(oldFormadorOfAreaespecializacao);
                }
                areaespecializacao.setFormador(formador);
                areaespecializacao = em.merge(areaespecializacao);
            }
            for (Formacaoturma formacaoturmaCollectionFormacaoturma : formador.getFormacaoturmaCollection()) {
                Formador oldIdformadorOfFormacaoturmaCollectionFormacaoturma = formacaoturmaCollectionFormacaoturma.getIdformador();
                formacaoturmaCollectionFormacaoturma.setIdformador(formador);
                formacaoturmaCollectionFormacaoturma = em.merge(formacaoturmaCollectionFormacaoturma);
                if (oldIdformadorOfFormacaoturmaCollectionFormacaoturma != null) {
                    oldIdformadorOfFormacaoturmaCollectionFormacaoturma.getFormacaoturmaCollection().remove(formacaoturmaCollectionFormacaoturma);
                    oldIdformadorOfFormacaoturmaCollectionFormacaoturma = em.merge(oldIdformadorOfFormacaoturmaCollectionFormacaoturma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formador formador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formador persistentFormador = em.find(Formador.class, formador.getIdformador());
            Formadorturma formadorturmaOld = persistentFormador.getFormadorturma();
            Formadorturma formadorturmaNew = formador.getFormadorturma();
            Localidade iddistritoOld = persistentFormador.getIddistrito();
            Localidade iddistritoNew = formador.getIddistrito();
            Areaespecializacao areaespecializacaoOld = persistentFormador.getAreaespecializacao();
            Areaespecializacao areaespecializacaoNew = formador.getAreaespecializacao();
            Collection<Formacaoturma> formacaoturmaCollectionOld = persistentFormador.getFormacaoturmaCollection();
            Collection<Formacaoturma> formacaoturmaCollectionNew = formador.getFormacaoturmaCollection();
            List<String> illegalOrphanMessages = null;
            if (formadorturmaOld != null && !formadorturmaOld.equals(formadorturmaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Formadorturma " + formadorturmaOld + " since its formador field is not nullable.");
            }
            if (areaespecializacaoOld != null && !areaespecializacaoOld.equals(areaespecializacaoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Areaespecializacao " + areaespecializacaoOld + " since its formador field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formadorturmaNew != null) {
                formadorturmaNew = em.getReference(formadorturmaNew.getClass(), formadorturmaNew.getIdformador());
                formador.setFormadorturma(formadorturmaNew);
            }
            if (iddistritoNew != null) {
                iddistritoNew = em.getReference(iddistritoNew.getClass(), iddistritoNew.getIdlocalidade());
                formador.setIddistrito(iddistritoNew);
            }
            if (areaespecializacaoNew != null) {
                areaespecializacaoNew = em.getReference(areaespecializacaoNew.getClass(), areaespecializacaoNew.getIdformador());
                formador.setAreaespecializacao(areaespecializacaoNew);
            }
            Collection<Formacaoturma> attachedFormacaoturmaCollectionNew = new ArrayList<Formacaoturma>();
            for (Formacaoturma formacaoturmaCollectionNewFormacaoturmaToAttach : formacaoturmaCollectionNew) {
                formacaoturmaCollectionNewFormacaoturmaToAttach = em.getReference(formacaoturmaCollectionNewFormacaoturmaToAttach.getClass(), formacaoturmaCollectionNewFormacaoturmaToAttach.getIdformando());
                attachedFormacaoturmaCollectionNew.add(formacaoturmaCollectionNewFormacaoturmaToAttach);
            }
            formacaoturmaCollectionNew = attachedFormacaoturmaCollectionNew;
            formador.setFormacaoturmaCollection(formacaoturmaCollectionNew);
            formador = em.merge(formador);
            if (formadorturmaNew != null && !formadorturmaNew.equals(formadorturmaOld)) {
                Formador oldFormadorOfFormadorturma = formadorturmaNew.getFormador();
                if (oldFormadorOfFormadorturma != null) {
                    oldFormadorOfFormadorturma.setFormadorturma(null);
                    oldFormadorOfFormadorturma = em.merge(oldFormadorOfFormadorturma);
                }
                formadorturmaNew.setFormador(formador);
                formadorturmaNew = em.merge(formadorturmaNew);
            }
            if (iddistritoOld != null && !iddistritoOld.equals(iddistritoNew)) {
                iddistritoOld.getFormadorCollection().remove(formador);
                iddistritoOld = em.merge(iddistritoOld);
            }
            if (iddistritoNew != null && !iddistritoNew.equals(iddistritoOld)) {
                iddistritoNew.getFormadorCollection().add(formador);
                iddistritoNew = em.merge(iddistritoNew);
            }
            if (areaespecializacaoNew != null && !areaespecializacaoNew.equals(areaespecializacaoOld)) {
                Formador oldFormadorOfAreaespecializacao = areaespecializacaoNew.getFormador();
                if (oldFormadorOfAreaespecializacao != null) {
                    oldFormadorOfAreaespecializacao.setAreaespecializacao(null);
                    oldFormadorOfAreaespecializacao = em.merge(oldFormadorOfAreaespecializacao);
                }
                areaespecializacaoNew.setFormador(formador);
                areaespecializacaoNew = em.merge(areaespecializacaoNew);
            }
            for (Formacaoturma formacaoturmaCollectionOldFormacaoturma : formacaoturmaCollectionOld) {
                if (!formacaoturmaCollectionNew.contains(formacaoturmaCollectionOldFormacaoturma)) {
                    formacaoturmaCollectionOldFormacaoturma.setIdformador(null);
                    formacaoturmaCollectionOldFormacaoturma = em.merge(formacaoturmaCollectionOldFormacaoturma);
                }
            }
            for (Formacaoturma formacaoturmaCollectionNewFormacaoturma : formacaoturmaCollectionNew) {
                if (!formacaoturmaCollectionOld.contains(formacaoturmaCollectionNewFormacaoturma)) {
                    Formador oldIdformadorOfFormacaoturmaCollectionNewFormacaoturma = formacaoturmaCollectionNewFormacaoturma.getIdformador();
                    formacaoturmaCollectionNewFormacaoturma.setIdformador(formador);
                    formacaoturmaCollectionNewFormacaoturma = em.merge(formacaoturmaCollectionNewFormacaoturma);
                    if (oldIdformadorOfFormacaoturmaCollectionNewFormacaoturma != null && !oldIdformadorOfFormacaoturmaCollectionNewFormacaoturma.equals(formador)) {
                        oldIdformadorOfFormacaoturmaCollectionNewFormacaoturma.getFormacaoturmaCollection().remove(formacaoturmaCollectionNewFormacaoturma);
                        oldIdformadorOfFormacaoturmaCollectionNewFormacaoturma = em.merge(oldIdformadorOfFormacaoturmaCollectionNewFormacaoturma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formador.getIdformador();
                if (findFormador(id) == null) {
                    throw new NonexistentEntityException("The formador with id " + id + " no longer exists.");
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
            Formador formador;
            try {
                formador = em.getReference(Formador.class, id);
                formador.getIdformador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Formadorturma formadorturmaOrphanCheck = formador.getFormadorturma();
            if (formadorturmaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formador (" + formador + ") cannot be destroyed since the Formadorturma " + formadorturmaOrphanCheck + " in its formadorturma field has a non-nullable formador field.");
            }
            Areaespecializacao areaespecializacaoOrphanCheck = formador.getAreaespecializacao();
            if (areaespecializacaoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formador (" + formador + ") cannot be destroyed since the Areaespecializacao " + areaespecializacaoOrphanCheck + " in its areaespecializacao field has a non-nullable formador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Localidade iddistrito = formador.getIddistrito();
            if (iddistrito != null) {
                iddistrito.getFormadorCollection().remove(formador);
                iddistrito = em.merge(iddistrito);
            }
            Collection<Formacaoturma> formacaoturmaCollection = formador.getFormacaoturmaCollection();
            for (Formacaoturma formacaoturmaCollectionFormacaoturma : formacaoturmaCollection) {
                formacaoturmaCollectionFormacaoturma.setIdformador(null);
                formacaoturmaCollectionFormacaoturma = em.merge(formacaoturmaCollectionFormacaoturma);
            }
            em.remove(formador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formador> findFormadorEntities() {
        return findFormadorEntities(true, -1, -1);
    }

    public List<Formador> findFormadorEntities(int maxResults, int firstResult) {
        return findFormadorEntities(false, maxResults, firstResult);
    }

    private List<Formador> findFormadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formador.class));
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
    public Formador findFormador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            
              Formador f = em.find(Formador.class, id);
              em.refresh(f);
              return f;
        } finally {
            em.close();
        }
    }

    public int getFormadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formador> rt = cq.from(Formador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Formador> formadorLocalidade(int p){
          List<Formador> formandos = new FormadorJpaController().findFormadorEntities();//do banco
          List<Formador> formandosselecionados = new ArrayList<Formador>();//filtrada
         
         for (Formador f : formandos) {
             if(f.getIddistrito().getIdlocalidade()!=null){
                   if(f.getIddistrito().getIdlocalidade()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
     
      public List<Formador> formadorDistrito(int p){
          List<Formador> formandos = new FormadorJpaController().findFormadorEntities();//do banco
          List<Formador> formandosselecionados = new ArrayList<Formador>();//filtrada
         
         for (Formador f : formandos) {
             if(f.getIddistrito().getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getIddistrito().getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
     
      public List<Formador> getformandor(String n) {
        List<Formador> lista = new ArrayList<Formador>();
        EntityManager em = getEntityManager();
        
        Query q = em.createNativeQuery("SELECT * from formador where formador.formador like ?", Formador.class);
        
            q.setParameter(1, n);
            //  q.setParameter(1, l.getIdlocalidade());
     
        return q.getResultList();
    }
     
     
     
    
}
