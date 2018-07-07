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
import Modelo.Mestre;
import Modelo.Formador;
import Modelo.Formando;
import Modelo.Artigo;
import Modelo.Cursoformacao;
import Modelo.Formacaoturma;
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
public class FormacaoturmaJpaController implements Serializable {

    public FormacaoturmaJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formacaoturma formacaoturma) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (formacaoturma.getArtigoCollection() == null) {
            formacaoturma.setArtigoCollection(new ArrayList<Artigo>());
        }
        List<String> illegalOrphanMessages = null;
        Formando formandoOrphanCheck = formacaoturma.getFormando();
        if (formandoOrphanCheck != null) {
            Formacaoturma oldFormacaoturmaOfFormando = formandoOrphanCheck.getFormacaoturma();
            if (oldFormacaoturmaOfFormando != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Formando " + formandoOrphanCheck + " already has an item of type Formacaoturma whose formando column cannot be null. Please make another selection for the formando field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursoformacao idcursoformacao = formacaoturma.getIdcursoformacao();
            if (idcursoformacao != null) {
                idcursoformacao = em.getReference(idcursoformacao.getClass(), idcursoformacao.getIdcursoformacao());
                formacaoturma.setIdcursoformacao(idcursoformacao);
            }
            Mestre idmestre = formacaoturma.getIdmestre();
            if (idmestre != null) {
                idmestre = em.getReference(idmestre.getClass(), idmestre.getIdmestre());
                formacaoturma.setIdmestre(idmestre);
            }
            Formador idformador = formacaoturma.getIdformador();
            if (idformador != null) {
                idformador = em.getReference(idformador.getClass(), idformador.getIdformador());
                formacaoturma.setIdformador(idformador);
            }
            Formando formando = formacaoturma.getFormando();
            if (formando != null) {
                formando = em.getReference(formando.getClass(), formando.getIdformando());
                formacaoturma.setFormando(formando);
            }
            Collection<Artigo> attachedArtigoCollection = new ArrayList<Artigo>();
            for (Artigo artigoCollectionArtigoToAttach : formacaoturma.getArtigoCollection()) {
                artigoCollectionArtigoToAttach = em.getReference(artigoCollectionArtigoToAttach.getClass(), artigoCollectionArtigoToAttach.getArtigoPK());
                attachedArtigoCollection.add(artigoCollectionArtigoToAttach);
            }
            formacaoturma.setArtigoCollection(attachedArtigoCollection);
            em.persist(formacaoturma);
            if (idcursoformacao != null) {
                idcursoformacao.getFormacaoturmaCollection().add(formacaoturma);
                idcursoformacao = em.merge(idcursoformacao);
            }
            if (idmestre != null) {
                idmestre.getFormacaoturmaCollection().add(formacaoturma);
                idmestre = em.merge(idmestre);
            }
            if (idformador != null) {
                idformador.getFormacaoturmaCollection().add(formacaoturma);
                idformador = em.merge(idformador);
            }
            if (formando != null) {
                formando.setFormacaoturma(formacaoturma);
                formando = em.merge(formando);
            }
            for (Artigo artigoCollectionArtigo : formacaoturma.getArtigoCollection()) {
                Formacaoturma oldFormacaoturmaOfArtigoCollectionArtigo = artigoCollectionArtigo.getFormacaoturma();
                artigoCollectionArtigo.setFormacaoturma(formacaoturma);
                artigoCollectionArtigo = em.merge(artigoCollectionArtigo);
                if (oldFormacaoturmaOfArtigoCollectionArtigo != null) {
                    oldFormacaoturmaOfArtigoCollectionArtigo.getArtigoCollection().remove(artigoCollectionArtigo);
                    oldFormacaoturmaOfArtigoCollectionArtigo = em.merge(oldFormacaoturmaOfArtigoCollectionArtigo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormacaoturma(formacaoturma.getIdformando()) != null) {
                throw new PreexistingEntityException("Formacaoturma " + formacaoturma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formacaoturma formacaoturma) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formacaoturma persistentFormacaoturma = em.find(Formacaoturma.class, formacaoturma.getIdformando());
            Cursoformacao idcursoformacaoOld = persistentFormacaoturma.getIdcursoformacao();
            Cursoformacao idcursoformacaoNew = formacaoturma.getIdcursoformacao();
            Mestre idmestreOld = persistentFormacaoturma.getIdmestre();
            Mestre idmestreNew = formacaoturma.getIdmestre();
            Formador idformadorOld = persistentFormacaoturma.getIdformador();
            Formador idformadorNew = formacaoturma.getIdformador();
            Formando formandoOld = persistentFormacaoturma.getFormando();
            Formando formandoNew = formacaoturma.getFormando();
            Collection<Artigo> artigoCollectionOld = persistentFormacaoturma.getArtigoCollection();
            Collection<Artigo> artigoCollectionNew = formacaoturma.getArtigoCollection();
            List<String> illegalOrphanMessages = null;
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                Formacaoturma oldFormacaoturmaOfFormando = formandoNew.getFormacaoturma();
                if (oldFormacaoturmaOfFormando != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Formando " + formandoNew + " already has an item of type Formacaoturma whose formando column cannot be null. Please make another selection for the formando field.");
                }
            }
            for (Artigo artigoCollectionOldArtigo : artigoCollectionOld) {
                if (!artigoCollectionNew.contains(artigoCollectionOldArtigo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artigo " + artigoCollectionOldArtigo + " since its formacaoturma field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcursoformacaoNew != null) {
                idcursoformacaoNew = em.getReference(idcursoformacaoNew.getClass(), idcursoformacaoNew.getIdcursoformacao());
                formacaoturma.setIdcursoformacao(idcursoformacaoNew);
            }
            if (idmestreNew != null) {
                idmestreNew = em.getReference(idmestreNew.getClass(), idmestreNew.getIdmestre());
                formacaoturma.setIdmestre(idmestreNew);
            }
            if (idformadorNew != null) {
                idformadorNew = em.getReference(idformadorNew.getClass(), idformadorNew.getIdformador());
                formacaoturma.setIdformador(idformadorNew);
            }
            if (formandoNew != null) {
                formandoNew = em.getReference(formandoNew.getClass(), formandoNew.getIdformando());
                formacaoturma.setFormando(formandoNew);
            }
            Collection<Artigo> attachedArtigoCollectionNew = new ArrayList<Artigo>();
            for (Artigo artigoCollectionNewArtigoToAttach : artigoCollectionNew) {
                artigoCollectionNewArtigoToAttach = em.getReference(artigoCollectionNewArtigoToAttach.getClass(), artigoCollectionNewArtigoToAttach.getArtigoPK());
                attachedArtigoCollectionNew.add(artigoCollectionNewArtigoToAttach);
            }
            artigoCollectionNew = attachedArtigoCollectionNew;
            formacaoturma.setArtigoCollection(artigoCollectionNew);
            formacaoturma = em.merge(formacaoturma);
            if (idcursoformacaoOld != null && !idcursoformacaoOld.equals(idcursoformacaoNew)) {
                idcursoformacaoOld.getFormacaoturmaCollection().remove(formacaoturma);
                idcursoformacaoOld = em.merge(idcursoformacaoOld);
            }
            if (idcursoformacaoNew != null && !idcursoformacaoNew.equals(idcursoformacaoOld)) {
                idcursoformacaoNew.getFormacaoturmaCollection().add(formacaoturma);
                idcursoformacaoNew = em.merge(idcursoformacaoNew);
            }
            if (idmestreOld != null && !idmestreOld.equals(idmestreNew)) {
                idmestreOld.getFormacaoturmaCollection().remove(formacaoturma);
                idmestreOld = em.merge(idmestreOld);
            }
            if (idmestreNew != null && !idmestreNew.equals(idmestreOld)) {
                idmestreNew.getFormacaoturmaCollection().add(formacaoturma);
                idmestreNew = em.merge(idmestreNew);
            }
            if (idformadorOld != null && !idformadorOld.equals(idformadorNew)) {
                idformadorOld.getFormacaoturmaCollection().remove(formacaoturma);
                idformadorOld = em.merge(idformadorOld);
            }
            if (idformadorNew != null && !idformadorNew.equals(idformadorOld)) {
                idformadorNew.getFormacaoturmaCollection().add(formacaoturma);
                idformadorNew = em.merge(idformadorNew);
            }
            if (formandoOld != null && !formandoOld.equals(formandoNew)) {
                formandoOld.setFormacaoturma(null);
                formandoOld = em.merge(formandoOld);
            }
            if (formandoNew != null && !formandoNew.equals(formandoOld)) {
                formandoNew.setFormacaoturma(formacaoturma);
                formandoNew = em.merge(formandoNew);
            }
            for (Artigo artigoCollectionNewArtigo : artigoCollectionNew) {
                if (!artigoCollectionOld.contains(artigoCollectionNewArtigo)) {
                    Formacaoturma oldFormacaoturmaOfArtigoCollectionNewArtigo = artigoCollectionNewArtigo.getFormacaoturma();
                    artigoCollectionNewArtigo.setFormacaoturma(formacaoturma);
                    artigoCollectionNewArtigo = em.merge(artigoCollectionNewArtigo);
                    if (oldFormacaoturmaOfArtigoCollectionNewArtigo != null && !oldFormacaoturmaOfArtigoCollectionNewArtigo.equals(formacaoturma)) {
                        oldFormacaoturmaOfArtigoCollectionNewArtigo.getArtigoCollection().remove(artigoCollectionNewArtigo);
                        oldFormacaoturmaOfArtigoCollectionNewArtigo = em.merge(oldFormacaoturmaOfArtigoCollectionNewArtigo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formacaoturma.getIdformando();
                if (findFormacaoturma(id) == null) {
                    throw new NonexistentEntityException("The formacaoturma with id " + id + " no longer exists.");
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
            Formacaoturma formacaoturma;
            try {
                formacaoturma = em.getReference(Formacaoturma.class, id);
                formacaoturma.getIdformando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacaoturma with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Artigo> artigoCollectionOrphanCheck = formacaoturma.getArtigoCollection();
            for (Artigo artigoCollectionOrphanCheckArtigo : artigoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formacaoturma (" + formacaoturma + ") cannot be destroyed since the Artigo " + artigoCollectionOrphanCheckArtigo + " in its artigoCollection field has a non-nullable formacaoturma field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cursoformacao idcursoformacao = formacaoturma.getIdcursoformacao();
            if (idcursoformacao != null) {
                idcursoformacao.getFormacaoturmaCollection().remove(formacaoturma);
                idcursoformacao = em.merge(idcursoformacao);
            }
            Mestre idmestre = formacaoturma.getIdmestre();
            if (idmestre != null) {
                idmestre.getFormacaoturmaCollection().remove(formacaoturma);
                idmestre = em.merge(idmestre);
            }
            Formador idformador = formacaoturma.getIdformador();
            if (idformador != null) {
                idformador.getFormacaoturmaCollection().remove(formacaoturma);
                idformador = em.merge(idformador);
            }
            Formando formando = formacaoturma.getFormando();
            if (formando != null) {
                formando.setFormacaoturma(null);
                formando = em.merge(formando);
            }
            em.remove(formacaoturma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Formacaoturma> findFormacaoturmaEntities() {
        return findFormacaoturmaEntities(true, -1, -1);
    }

    public List<Formacaoturma> findFormacaoturmaEntities(int maxResults, int firstResult) {
        return findFormacaoturmaEntities(false, maxResults, firstResult);
    }

    private List<Formacaoturma> findFormacaoturmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formacaoturma.class));
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

    public Formacaoturma findFormacaoturma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formacaoturma.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacaoturmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formacaoturma> rt = cq.from(Formacaoturma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Formacaoturma> prencherLocalidades(int p){
          List<Formacaoturma> formandos = new FormacaoturmaJpaController().findFormacaoturmaEntities();//do banco
          List<Formacaoturma> formandosselecionados = new ArrayList<Formacaoturma>();//filtrada
         
         for (Formacaoturma f : formandos) {
             if(f.getFormando().getIdlocalidade().getIdlocalidade()!=null){
                   if(f.getFormando().getIdlocalidade().getIdlocalidade()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
      public List<Formacaoturma> prencherDistritos(int p){
          List<Formacaoturma> formandos = new FormacaoturmaJpaController().findFormacaoturmaEntities();//do banco
          List<Formacaoturma> formandosselecionados = new ArrayList<Formacaoturma>();//filtrada
         
         for (Formacaoturma f : formandos) {
             if(f.getFormando().getIdlocalidade().getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getFormando().getIdlocalidade().getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
public List<Formacaoturma> prencherCursos(int l,int c){
          List<Formacaoturma> formandos = new FormacaoturmaJpaController().findFormacaoturmaEntities();//do banco
          List<Formacaoturma> formandosselecionados = new ArrayList<Formacaoturma>();//filtrada
         
         for (Formacaoturma f : formandos) {
             if(f.getFormando().getIdlocalidade().getIdlocalidade()!=null && f.getIdcursoformacao().getIdcurso().getIdcurso()!=null){
                   if(f.getFormando().getAreadeformacao().getIdcurso1().getIdcurso()==c && f.getIdcursoformacao().getIdcurso().getIdcurso()==c){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  

}
