/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Artigofinal;
import Modelo.Curso;
import Modelo.Distrito;
import Modelo.Formacaoturma;
import Modelo.Formador;
import Modelo.Localidade;
import Modelo.Mestre;
import Modelo.Nivelhabilitacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Nivelprofissional;
import Modelo.Oficinamestre;
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
public class MestreJpaController implements Serializable {

    public MestreJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mestre mestre) {
        if (mestre.getFormacaoturmaCollection() == null) {
            mestre.setFormacaoturmaCollection(new ArrayList<Formacaoturma>());
        }
        if (mestre.getArtigofinalCollection() == null) {
            mestre.setArtigofinalCollection(new ArrayList<Artigofinal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oficinamestre oficinamestre = mestre.getOficinamestre();
            if (oficinamestre != null) {
                oficinamestre = em.getReference(oficinamestre.getClass(), oficinamestre.getIdmestre());
                mestre.setOficinamestre(oficinamestre);
            }
            Nivelprofissional idnivelprof = mestre.getIdnivelprof();
            if (idnivelprof != null) {
                idnivelprof = em.getReference(idnivelprof.getClass(), idnivelprof.getIdnivelprofissional());
                mestre.setIdnivelprof(idnivelprof);
            }
            Nivelhabilitacao idnivelhabilitacao = mestre.getIdnivelhabilitacao();
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao = em.getReference(idnivelhabilitacao.getClass(), idnivelhabilitacao.getIdnivelhabilitacao());
                mestre.setIdnivelhabilitacao(idnivelhabilitacao);
            }
            Localidade idlocalidade = mestre.getIdlocalidade();
            if (idlocalidade != null) {
                idlocalidade = em.getReference(idlocalidade.getClass(), idlocalidade.getIdlocalidade());
                mestre.setIdlocalidade(idlocalidade);
            }
            Curso idcurso = mestre.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdcurso());
                mestre.setIdcurso(idcurso);
            }
            Collection<Formacaoturma> attachedFormacaoturmaCollection = new ArrayList<Formacaoturma>();
            for (Formacaoturma formacaoturmaCollectionFormacaoturmaToAttach : mestre.getFormacaoturmaCollection()) {
                formacaoturmaCollectionFormacaoturmaToAttach = em.getReference(formacaoturmaCollectionFormacaoturmaToAttach.getClass(), formacaoturmaCollectionFormacaoturmaToAttach.getIdformando());
                attachedFormacaoturmaCollection.add(formacaoturmaCollectionFormacaoturmaToAttach);
            }
            mestre.setFormacaoturmaCollection(attachedFormacaoturmaCollection);
            Collection<Artigofinal> attachedArtigofinalCollection = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionArtigofinalToAttach : mestre.getArtigofinalCollection()) {
                artigofinalCollectionArtigofinalToAttach = em.getReference(artigofinalCollectionArtigofinalToAttach.getClass(), artigofinalCollectionArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollection.add(artigofinalCollectionArtigofinalToAttach);
            }
            mestre.setArtigofinalCollection(attachedArtigofinalCollection);
            em.persist(mestre);
            if (oficinamestre != null) {
                Mestre oldMestreOfOficinamestre = oficinamestre.getMestre();
                if (oldMestreOfOficinamestre != null) {
                    oldMestreOfOficinamestre.setOficinamestre(null);
                    oldMestreOfOficinamestre = em.merge(oldMestreOfOficinamestre);
                }
                oficinamestre.setMestre(mestre);
                oficinamestre = em.merge(oficinamestre);
            }
            if (idnivelprof != null) {
                idnivelprof.getMestreCollection().add(mestre);
                idnivelprof = em.merge(idnivelprof);
            }
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao.getMestreCollection().add(mestre);
                idnivelhabilitacao = em.merge(idnivelhabilitacao);
            }
            if (idlocalidade != null) {
                idlocalidade.getMestreCollection().add(mestre);
                idlocalidade = em.merge(idlocalidade);
            }
            if (idcurso != null) {
                idcurso.getMestreCollection().add(mestre);
                idcurso = em.merge(idcurso);
            }
            for (Formacaoturma formacaoturmaCollectionFormacaoturma : mestre.getFormacaoturmaCollection()) {
                Mestre oldIdmestreOfFormacaoturmaCollectionFormacaoturma = formacaoturmaCollectionFormacaoturma.getIdmestre();
                formacaoturmaCollectionFormacaoturma.setIdmestre(mestre);
                formacaoturmaCollectionFormacaoturma = em.merge(formacaoturmaCollectionFormacaoturma);
                if (oldIdmestreOfFormacaoturmaCollectionFormacaoturma != null) {
                    oldIdmestreOfFormacaoturmaCollectionFormacaoturma.getFormacaoturmaCollection().remove(formacaoturmaCollectionFormacaoturma);
                    oldIdmestreOfFormacaoturmaCollectionFormacaoturma = em.merge(oldIdmestreOfFormacaoturmaCollectionFormacaoturma);
                }
            }
            for (Artigofinal artigofinalCollectionArtigofinal : mestre.getArtigofinalCollection()) {
                Mestre oldIdmestreOfArtigofinalCollectionArtigofinal = artigofinalCollectionArtigofinal.getIdmestre();
                artigofinalCollectionArtigofinal.setIdmestre(mestre);
                artigofinalCollectionArtigofinal = em.merge(artigofinalCollectionArtigofinal);
                if (oldIdmestreOfArtigofinalCollectionArtigofinal != null) {
                    oldIdmestreOfArtigofinalCollectionArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionArtigofinal);
                    oldIdmestreOfArtigofinalCollectionArtigofinal = em.merge(oldIdmestreOfArtigofinalCollectionArtigofinal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mestre mestre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mestre persistentMestre = em.find(Mestre.class, mestre.getIdmestre());
            Oficinamestre oficinamestreOld = persistentMestre.getOficinamestre();
            Oficinamestre oficinamestreNew = mestre.getOficinamestre();
            Nivelprofissional idnivelprofOld = persistentMestre.getIdnivelprof();
            Nivelprofissional idnivelprofNew = mestre.getIdnivelprof();
            Nivelhabilitacao idnivelhabilitacaoOld = persistentMestre.getIdnivelhabilitacao();
            Nivelhabilitacao idnivelhabilitacaoNew = mestre.getIdnivelhabilitacao();
            Localidade idlocalidadeOld = persistentMestre.getIdlocalidade();
            Localidade idlocalidadeNew = mestre.getIdlocalidade();
            Curso idcursoOld = persistentMestre.getIdcurso();
            Curso idcursoNew = mestre.getIdcurso();
            Collection<Formacaoturma> formacaoturmaCollectionOld = persistentMestre.getFormacaoturmaCollection();
            Collection<Formacaoturma> formacaoturmaCollectionNew = mestre.getFormacaoturmaCollection();
            Collection<Artigofinal> artigofinalCollectionOld = persistentMestre.getArtigofinalCollection();
            Collection<Artigofinal> artigofinalCollectionNew = mestre.getArtigofinalCollection();
            List<String> illegalOrphanMessages = null;
            if (oficinamestreOld != null && !oficinamestreOld.equals(oficinamestreNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Oficinamestre " + oficinamestreOld + " since its mestre field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (oficinamestreNew != null) {
                oficinamestreNew = em.getReference(oficinamestreNew.getClass(), oficinamestreNew.getIdmestre());
                mestre.setOficinamestre(oficinamestreNew);
            }
            if (idnivelprofNew != null) {
                idnivelprofNew = em.getReference(idnivelprofNew.getClass(), idnivelprofNew.getIdnivelprofissional());
                mestre.setIdnivelprof(idnivelprofNew);
            }
            if (idnivelhabilitacaoNew != null) {
                idnivelhabilitacaoNew = em.getReference(idnivelhabilitacaoNew.getClass(), idnivelhabilitacaoNew.getIdnivelhabilitacao());
                mestre.setIdnivelhabilitacao(idnivelhabilitacaoNew);
            }
            if (idlocalidadeNew != null) {
                idlocalidadeNew = em.getReference(idlocalidadeNew.getClass(), idlocalidadeNew.getIdlocalidade());
                mestre.setIdlocalidade(idlocalidadeNew);
            }
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdcurso());
                mestre.setIdcurso(idcursoNew);
            }
            Collection<Formacaoturma> attachedFormacaoturmaCollectionNew = new ArrayList<Formacaoturma>();
            for (Formacaoturma formacaoturmaCollectionNewFormacaoturmaToAttach : formacaoturmaCollectionNew) {
                formacaoturmaCollectionNewFormacaoturmaToAttach = em.getReference(formacaoturmaCollectionNewFormacaoturmaToAttach.getClass(), formacaoturmaCollectionNewFormacaoturmaToAttach.getIdformando());
                attachedFormacaoturmaCollectionNew.add(formacaoturmaCollectionNewFormacaoturmaToAttach);
            }
            formacaoturmaCollectionNew = attachedFormacaoturmaCollectionNew;
            mestre.setFormacaoturmaCollection(formacaoturmaCollectionNew);
            Collection<Artigofinal> attachedArtigofinalCollectionNew = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionNewArtigofinalToAttach : artigofinalCollectionNew) {
                artigofinalCollectionNewArtigofinalToAttach = em.getReference(artigofinalCollectionNewArtigofinalToAttach.getClass(), artigofinalCollectionNewArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollectionNew.add(artigofinalCollectionNewArtigofinalToAttach);
            }
            artigofinalCollectionNew = attachedArtigofinalCollectionNew;
            mestre.setArtigofinalCollection(artigofinalCollectionNew);
            mestre = em.merge(mestre);
            if (oficinamestreNew != null && !oficinamestreNew.equals(oficinamestreOld)) {
                Mestre oldMestreOfOficinamestre = oficinamestreNew.getMestre();
                if (oldMestreOfOficinamestre != null) {
                    oldMestreOfOficinamestre.setOficinamestre(null);
                    oldMestreOfOficinamestre = em.merge(oldMestreOfOficinamestre);
                }
                oficinamestreNew.setMestre(mestre);
                oficinamestreNew = em.merge(oficinamestreNew);
            }
            if (idnivelprofOld != null && !idnivelprofOld.equals(idnivelprofNew)) {
                idnivelprofOld.getMestreCollection().remove(mestre);
                idnivelprofOld = em.merge(idnivelprofOld);
            }
            if (idnivelprofNew != null && !idnivelprofNew.equals(idnivelprofOld)) {
                idnivelprofNew.getMestreCollection().add(mestre);
                idnivelprofNew = em.merge(idnivelprofNew);
            }
            if (idnivelhabilitacaoOld != null && !idnivelhabilitacaoOld.equals(idnivelhabilitacaoNew)) {
                idnivelhabilitacaoOld.getMestreCollection().remove(mestre);
                idnivelhabilitacaoOld = em.merge(idnivelhabilitacaoOld);
            }
            if (idnivelhabilitacaoNew != null && !idnivelhabilitacaoNew.equals(idnivelhabilitacaoOld)) {
                idnivelhabilitacaoNew.getMestreCollection().add(mestre);
                idnivelhabilitacaoNew = em.merge(idnivelhabilitacaoNew);
            }
            if (idlocalidadeOld != null && !idlocalidadeOld.equals(idlocalidadeNew)) {
                idlocalidadeOld.getMestreCollection().remove(mestre);
                idlocalidadeOld = em.merge(idlocalidadeOld);
            }
            if (idlocalidadeNew != null && !idlocalidadeNew.equals(idlocalidadeOld)) {
                idlocalidadeNew.getMestreCollection().add(mestre);
                idlocalidadeNew = em.merge(idlocalidadeNew);
            }
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getMestreCollection().remove(mestre);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getMestreCollection().add(mestre);
                idcursoNew = em.merge(idcursoNew);
            }
            for (Formacaoturma formacaoturmaCollectionOldFormacaoturma : formacaoturmaCollectionOld) {
                if (!formacaoturmaCollectionNew.contains(formacaoturmaCollectionOldFormacaoturma)) {
                    formacaoturmaCollectionOldFormacaoturma.setIdmestre(null);
                    formacaoturmaCollectionOldFormacaoturma = em.merge(formacaoturmaCollectionOldFormacaoturma);
                }
            }
            for (Formacaoturma formacaoturmaCollectionNewFormacaoturma : formacaoturmaCollectionNew) {
                if (!formacaoturmaCollectionOld.contains(formacaoturmaCollectionNewFormacaoturma)) {
                    Mestre oldIdmestreOfFormacaoturmaCollectionNewFormacaoturma = formacaoturmaCollectionNewFormacaoturma.getIdmestre();
                    formacaoturmaCollectionNewFormacaoturma.setIdmestre(mestre);
                    formacaoturmaCollectionNewFormacaoturma = em.merge(formacaoturmaCollectionNewFormacaoturma);
                    if (oldIdmestreOfFormacaoturmaCollectionNewFormacaoturma != null && !oldIdmestreOfFormacaoturmaCollectionNewFormacaoturma.equals(mestre)) {
                        oldIdmestreOfFormacaoturmaCollectionNewFormacaoturma.getFormacaoturmaCollection().remove(formacaoturmaCollectionNewFormacaoturma);
                        oldIdmestreOfFormacaoturmaCollectionNewFormacaoturma = em.merge(oldIdmestreOfFormacaoturmaCollectionNewFormacaoturma);
                    }
                }
            }
            for (Artigofinal artigofinalCollectionOldArtigofinal : artigofinalCollectionOld) {
                if (!artigofinalCollectionNew.contains(artigofinalCollectionOldArtigofinal)) {
                    artigofinalCollectionOldArtigofinal.setIdmestre(null);
                    artigofinalCollectionOldArtigofinal = em.merge(artigofinalCollectionOldArtigofinal);
                }
            }
            for (Artigofinal artigofinalCollectionNewArtigofinal : artigofinalCollectionNew) {
                if (!artigofinalCollectionOld.contains(artigofinalCollectionNewArtigofinal)) {
                    Mestre oldIdmestreOfArtigofinalCollectionNewArtigofinal = artigofinalCollectionNewArtigofinal.getIdmestre();
                    artigofinalCollectionNewArtigofinal.setIdmestre(mestre);
                    artigofinalCollectionNewArtigofinal = em.merge(artigofinalCollectionNewArtigofinal);
                    if (oldIdmestreOfArtigofinalCollectionNewArtigofinal != null && !oldIdmestreOfArtigofinalCollectionNewArtigofinal.equals(mestre)) {
                        oldIdmestreOfArtigofinalCollectionNewArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionNewArtigofinal);
                        oldIdmestreOfArtigofinalCollectionNewArtigofinal = em.merge(oldIdmestreOfArtigofinalCollectionNewArtigofinal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mestre.getIdmestre();
                if (findMestre(id) == null) {
                    throw new NonexistentEntityException("The mestre with id " + id + " no longer exists.");
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
            Mestre mestre;
            try {
                mestre = em.getReference(Mestre.class, id);
                mestre.getIdmestre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mestre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Oficinamestre oficinamestreOrphanCheck = mestre.getOficinamestre();
            if (oficinamestreOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mestre (" + mestre + ") cannot be destroyed since the Oficinamestre " + oficinamestreOrphanCheck + " in its oficinamestre field has a non-nullable mestre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Nivelprofissional idnivelprof = mestre.getIdnivelprof();
            if (idnivelprof != null) {
                idnivelprof.getMestreCollection().remove(mestre);
                idnivelprof = em.merge(idnivelprof);
            }
            Nivelhabilitacao idnivelhabilitacao = mestre.getIdnivelhabilitacao();
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao.getMestreCollection().remove(mestre);
                idnivelhabilitacao = em.merge(idnivelhabilitacao);
            }
            Localidade idlocalidade = mestre.getIdlocalidade();
            if (idlocalidade != null) {
                idlocalidade.getMestreCollection().remove(mestre);
                idlocalidade = em.merge(idlocalidade);
            }
            Curso idcurso = mestre.getIdcurso();
            if (idcurso != null) {
                idcurso.getMestreCollection().remove(mestre);
                idcurso = em.merge(idcurso);
            }
            Collection<Formacaoturma> formacaoturmaCollection = mestre.getFormacaoturmaCollection();
            for (Formacaoturma formacaoturmaCollectionFormacaoturma : formacaoturmaCollection) {
                formacaoturmaCollectionFormacaoturma.setIdmestre(null);
                formacaoturmaCollectionFormacaoturma = em.merge(formacaoturmaCollectionFormacaoturma);
            }
            Collection<Artigofinal> artigofinalCollection = mestre.getArtigofinalCollection();
            for (Artigofinal artigofinalCollectionArtigofinal : artigofinalCollection) {
                artigofinalCollectionArtigofinal.setIdmestre(null);
                artigofinalCollectionArtigofinal = em.merge(artigofinalCollectionArtigofinal);
            }
            em.remove(mestre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mestre> findMestreEntities() {
        return findMestreEntities(true, -1, -1);
    }

    public List<Mestre> findMestreEntities(int maxResults, int firstResult) {
        return findMestreEntities(false, maxResults, firstResult);
    }

    private List<Mestre> findMestreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mestre.class));
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

    public Mestre findMestre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Mestre m =em.find(Mestre.class, id);
            em.refresh(m);
            return m;
        } finally {
            em.close();
        }
    }

    public int getMestreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mestre> rt = cq.from(Mestre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Mestre> getmestre(String n) {
        List<Mestre> lista = new ArrayList<Mestre>();
        EntityManager em = getEntityManager();
        
        Query q = em.createNativeQuery("SELECT * from mestre where mestre.nome like ?", Mestre.class);
        
            q.setParameter(1, n);
            //  q.setParameter(1, l.getIdlocalidade());
     
        return q.getResultList();
    }
     public List<Mestre> mestreDistrito(int p){
          List<Mestre> mestre = new MestreJpaController().findMestreEntities();//do banco
          List<Mestre> formandosselecionados = new ArrayList<Mestre>();//filtrada
         
         for (Mestre f : mestre) {
             if(f.getIdlocalidade().getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getIdlocalidade().getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
     public List<Mestre> mestrelocalidade(int p){
          List<Mestre> mestre = new MestreJpaController().findMestreEntities();//do banco
          List<Mestre> formandosselecionados = new ArrayList<Mestre>();//filtrada
         
         for (Mestre f : mestre) {
             if(f.getIdlocalidade().getIdlocalidade()!=null){
                   if(f.getIdlocalidade().getIdlocalidade()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }
     
//     public List<Mestre> mestreDistrito(int p){
//          List<Mestre> mestre = new MestreJpaController().findMestreEntities();//do banco
//          List<Mestre> formandosselecionados = new ArrayList<Mestre>();//filtrada
//         
//         for (Mestre f : mestre) {
//             if(f.getIdlocalidade().getIdposto().getIddistrito().getIddistrito()!=null){
//                   if(f.getIdlocalidade().getIdposto().getIddistrito().getIddistrito()==p){
//                  formandosselecionados.add(f);
//            }
//             }
//            
//        }
//               
//        return formandosselecionados;
//    }
    
}
