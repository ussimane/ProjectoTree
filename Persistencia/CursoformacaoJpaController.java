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
import Modelo.Pontofocal;
import Modelo.Localidade;
import Modelo.Curso;
import Modelo.Cursoformacao;
import Modelo.Supervisao;
import java.util.ArrayList;
import java.util.Collection;
import Modelo.Formacaoturma;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class CursoformacaoJpaController implements Serializable {

     public CursoformacaoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cursoformacao cursoformacao) {
        if (cursoformacao.getSupervisaoCollection() == null) {
            cursoformacao.setSupervisaoCollection(new ArrayList<Supervisao>());
        }
        if (cursoformacao.getFormacaoturmaCollection() == null) {
            cursoformacao.setFormacaoturmaCollection(new ArrayList<Formacaoturma>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pontofocal idpontofocal = cursoformacao.getIdpontofocal();
            if (idpontofocal != null) {
                idpontofocal = em.getReference(idpontofocal.getClass(), idpontofocal.getIdpontofocal());
                cursoformacao.setIdpontofocal(idpontofocal);
            }
            Localidade idlocalidade = cursoformacao.getIdlocalidade();
            if (idlocalidade != null) {
                idlocalidade = em.getReference(idlocalidade.getClass(), idlocalidade.getIdlocalidade());
                cursoformacao.setIdlocalidade(idlocalidade);
            }
            Curso idcurso = cursoformacao.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdcurso());
                cursoformacao.setIdcurso(idcurso);
            }
            Collection<Supervisao> attachedSupervisaoCollection = new ArrayList<Supervisao>();
            for (Supervisao supervisaoCollectionSupervisaoToAttach : cursoformacao.getSupervisaoCollection()) {
                supervisaoCollectionSupervisaoToAttach = em.getReference(supervisaoCollectionSupervisaoToAttach.getClass(), supervisaoCollectionSupervisaoToAttach.getIdsupervisao());
                attachedSupervisaoCollection.add(supervisaoCollectionSupervisaoToAttach);
            }
            cursoformacao.setSupervisaoCollection(attachedSupervisaoCollection);
            Collection<Formacaoturma> attachedFormacaoturmaCollection = new ArrayList<Formacaoturma>();
            for (Formacaoturma formacaoturmaCollectionFormacaoturmaToAttach : cursoformacao.getFormacaoturmaCollection()) {
                formacaoturmaCollectionFormacaoturmaToAttach = em.getReference(formacaoturmaCollectionFormacaoturmaToAttach.getClass(), formacaoturmaCollectionFormacaoturmaToAttach.getIdformando());
                attachedFormacaoturmaCollection.add(formacaoturmaCollectionFormacaoturmaToAttach);
            }
            cursoformacao.setFormacaoturmaCollection(attachedFormacaoturmaCollection);
            em.persist(cursoformacao);
            if (idpontofocal != null) {
                idpontofocal.getCursoformacaoCollection().add(cursoformacao);
                idpontofocal = em.merge(idpontofocal);
            }
            if (idlocalidade != null) {
                idlocalidade.getCursoformacaoCollection().add(cursoformacao);
                idlocalidade = em.merge(idlocalidade);
            }
            if (idcurso != null) {
                idcurso.getCursoformacaoCollection().add(cursoformacao);
                idcurso = em.merge(idcurso);
            }
            for (Supervisao supervisaoCollectionSupervisao : cursoformacao.getSupervisaoCollection()) {
                Cursoformacao oldIdcursoformacaoOfSupervisaoCollectionSupervisao = supervisaoCollectionSupervisao.getIdcursoformacao();
                supervisaoCollectionSupervisao.setIdcursoformacao(cursoformacao);
                supervisaoCollectionSupervisao = em.merge(supervisaoCollectionSupervisao);
                if (oldIdcursoformacaoOfSupervisaoCollectionSupervisao != null) {
                    oldIdcursoformacaoOfSupervisaoCollectionSupervisao.getSupervisaoCollection().remove(supervisaoCollectionSupervisao);
                    oldIdcursoformacaoOfSupervisaoCollectionSupervisao = em.merge(oldIdcursoformacaoOfSupervisaoCollectionSupervisao);
                }
            }
            for (Formacaoturma formacaoturmaCollectionFormacaoturma : cursoformacao.getFormacaoturmaCollection()) {
                Cursoformacao oldIdcursoformacaoOfFormacaoturmaCollectionFormacaoturma = formacaoturmaCollectionFormacaoturma.getIdcursoformacao();
                formacaoturmaCollectionFormacaoturma.setIdcursoformacao(cursoformacao);
                formacaoturmaCollectionFormacaoturma = em.merge(formacaoturmaCollectionFormacaoturma);
                if (oldIdcursoformacaoOfFormacaoturmaCollectionFormacaoturma != null) {
                    oldIdcursoformacaoOfFormacaoturmaCollectionFormacaoturma.getFormacaoturmaCollection().remove(formacaoturmaCollectionFormacaoturma);
                    oldIdcursoformacaoOfFormacaoturmaCollectionFormacaoturma = em.merge(oldIdcursoformacaoOfFormacaoturmaCollectionFormacaoturma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cursoformacao cursoformacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cursoformacao persistentCursoformacao = em.find(Cursoformacao.class, cursoformacao.getIdcursoformacao());
            Pontofocal idpontofocalOld = persistentCursoformacao.getIdpontofocal();
            Pontofocal idpontofocalNew = cursoformacao.getIdpontofocal();
            Localidade idlocalidadeOld = persistentCursoformacao.getIdlocalidade();
            Localidade idlocalidadeNew = cursoformacao.getIdlocalidade();
            Curso idcursoOld = persistentCursoformacao.getIdcurso();
            Curso idcursoNew = cursoformacao.getIdcurso();
            Collection<Supervisao> supervisaoCollectionOld = persistentCursoformacao.getSupervisaoCollection();
            Collection<Supervisao> supervisaoCollectionNew = cursoformacao.getSupervisaoCollection();
            Collection<Formacaoturma> formacaoturmaCollectionOld = persistentCursoformacao.getFormacaoturmaCollection();
            Collection<Formacaoturma> formacaoturmaCollectionNew = cursoformacao.getFormacaoturmaCollection();
            if (idpontofocalNew != null) {
                idpontofocalNew = em.getReference(idpontofocalNew.getClass(), idpontofocalNew.getIdpontofocal());
                cursoformacao.setIdpontofocal(idpontofocalNew);
            }
            if (idlocalidadeNew != null) {
                idlocalidadeNew = em.getReference(idlocalidadeNew.getClass(), idlocalidadeNew.getIdlocalidade());
                cursoformacao.setIdlocalidade(idlocalidadeNew);
            }
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdcurso());
                cursoformacao.setIdcurso(idcursoNew);
            }
            Collection<Supervisao> attachedSupervisaoCollectionNew = new ArrayList<Supervisao>();
            for (Supervisao supervisaoCollectionNewSupervisaoToAttach : supervisaoCollectionNew) {
                supervisaoCollectionNewSupervisaoToAttach = em.getReference(supervisaoCollectionNewSupervisaoToAttach.getClass(), supervisaoCollectionNewSupervisaoToAttach.getIdsupervisao());
                attachedSupervisaoCollectionNew.add(supervisaoCollectionNewSupervisaoToAttach);
            }
            supervisaoCollectionNew = attachedSupervisaoCollectionNew;
            cursoformacao.setSupervisaoCollection(supervisaoCollectionNew);
            Collection<Formacaoturma> attachedFormacaoturmaCollectionNew = new ArrayList<Formacaoturma>();
            for (Formacaoturma formacaoturmaCollectionNewFormacaoturmaToAttach : formacaoturmaCollectionNew) {
                formacaoturmaCollectionNewFormacaoturmaToAttach = em.getReference(formacaoturmaCollectionNewFormacaoturmaToAttach.getClass(), formacaoturmaCollectionNewFormacaoturmaToAttach.getIdformando());
                attachedFormacaoturmaCollectionNew.add(formacaoturmaCollectionNewFormacaoturmaToAttach);
            }
            formacaoturmaCollectionNew = attachedFormacaoturmaCollectionNew;
            cursoformacao.setFormacaoturmaCollection(formacaoturmaCollectionNew);
            cursoformacao = em.merge(cursoformacao);
            if (idpontofocalOld != null && !idpontofocalOld.equals(idpontofocalNew)) {
                idpontofocalOld.getCursoformacaoCollection().remove(cursoformacao);
                idpontofocalOld = em.merge(idpontofocalOld);
            }
            if (idpontofocalNew != null && !idpontofocalNew.equals(idpontofocalOld)) {
                idpontofocalNew.getCursoformacaoCollection().add(cursoformacao);
                idpontofocalNew = em.merge(idpontofocalNew);
            }
            if (idlocalidadeOld != null && !idlocalidadeOld.equals(idlocalidadeNew)) {
                idlocalidadeOld.getCursoformacaoCollection().remove(cursoformacao);
                idlocalidadeOld = em.merge(idlocalidadeOld);
            }
            if (idlocalidadeNew != null && !idlocalidadeNew.equals(idlocalidadeOld)) {
                idlocalidadeNew.getCursoformacaoCollection().add(cursoformacao);
                idlocalidadeNew = em.merge(idlocalidadeNew);
            }
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getCursoformacaoCollection().remove(cursoformacao);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getCursoformacaoCollection().add(cursoformacao);
                idcursoNew = em.merge(idcursoNew);
            }
            for (Supervisao supervisaoCollectionOldSupervisao : supervisaoCollectionOld) {
                if (!supervisaoCollectionNew.contains(supervisaoCollectionOldSupervisao)) {
                    supervisaoCollectionOldSupervisao.setIdcursoformacao(null);
                    supervisaoCollectionOldSupervisao = em.merge(supervisaoCollectionOldSupervisao);
                }
            }
            for (Supervisao supervisaoCollectionNewSupervisao : supervisaoCollectionNew) {
                if (!supervisaoCollectionOld.contains(supervisaoCollectionNewSupervisao)) {
                    Cursoformacao oldIdcursoformacaoOfSupervisaoCollectionNewSupervisao = supervisaoCollectionNewSupervisao.getIdcursoformacao();
                    supervisaoCollectionNewSupervisao.setIdcursoformacao(cursoformacao);
                    supervisaoCollectionNewSupervisao = em.merge(supervisaoCollectionNewSupervisao);
                    if (oldIdcursoformacaoOfSupervisaoCollectionNewSupervisao != null && !oldIdcursoformacaoOfSupervisaoCollectionNewSupervisao.equals(cursoformacao)) {
                        oldIdcursoformacaoOfSupervisaoCollectionNewSupervisao.getSupervisaoCollection().remove(supervisaoCollectionNewSupervisao);
                        oldIdcursoformacaoOfSupervisaoCollectionNewSupervisao = em.merge(oldIdcursoformacaoOfSupervisaoCollectionNewSupervisao);
                    }
                }
            }
            for (Formacaoturma formacaoturmaCollectionOldFormacaoturma : formacaoturmaCollectionOld) {
                if (!formacaoturmaCollectionNew.contains(formacaoturmaCollectionOldFormacaoturma)) {
                    formacaoturmaCollectionOldFormacaoturma.setIdcursoformacao(null);
                    formacaoturmaCollectionOldFormacaoturma = em.merge(formacaoturmaCollectionOldFormacaoturma);
                }
            }
            for (Formacaoturma formacaoturmaCollectionNewFormacaoturma : formacaoturmaCollectionNew) {
                if (!formacaoturmaCollectionOld.contains(formacaoturmaCollectionNewFormacaoturma)) {
                    Cursoformacao oldIdcursoformacaoOfFormacaoturmaCollectionNewFormacaoturma = formacaoturmaCollectionNewFormacaoturma.getIdcursoformacao();
                    formacaoturmaCollectionNewFormacaoturma.setIdcursoformacao(cursoformacao);
                    formacaoturmaCollectionNewFormacaoturma = em.merge(formacaoturmaCollectionNewFormacaoturma);
                    if (oldIdcursoformacaoOfFormacaoturmaCollectionNewFormacaoturma != null && !oldIdcursoformacaoOfFormacaoturmaCollectionNewFormacaoturma.equals(cursoformacao)) {
                        oldIdcursoformacaoOfFormacaoturmaCollectionNewFormacaoturma.getFormacaoturmaCollection().remove(formacaoturmaCollectionNewFormacaoturma);
                        oldIdcursoformacaoOfFormacaoturmaCollectionNewFormacaoturma = em.merge(oldIdcursoformacaoOfFormacaoturmaCollectionNewFormacaoturma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cursoformacao.getIdcursoformacao();
                if (findCursoformacao(id) == null) {
                    throw new NonexistentEntityException("The cursoformacao with id " + id + " no longer exists.");
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
            Cursoformacao cursoformacao;
            try {
                cursoformacao = em.getReference(Cursoformacao.class, id);
                cursoformacao.getIdcursoformacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoformacao with id " + id + " no longer exists.", enfe);
            }
            Pontofocal idpontofocal = cursoformacao.getIdpontofocal();
            if (idpontofocal != null) {
                idpontofocal.getCursoformacaoCollection().remove(cursoformacao);
                idpontofocal = em.merge(idpontofocal);
            }
            Localidade idlocalidade = cursoformacao.getIdlocalidade();
            if (idlocalidade != null) {
                idlocalidade.getCursoformacaoCollection().remove(cursoformacao);
                idlocalidade = em.merge(idlocalidade);
            }
            Curso idcurso = cursoformacao.getIdcurso();
            if (idcurso != null) {
                idcurso.getCursoformacaoCollection().remove(cursoformacao);
                idcurso = em.merge(idcurso);
            }
            Collection<Supervisao> supervisaoCollection = cursoformacao.getSupervisaoCollection();
            for (Supervisao supervisaoCollectionSupervisao : supervisaoCollection) {
                supervisaoCollectionSupervisao.setIdcursoformacao(null);
                supervisaoCollectionSupervisao = em.merge(supervisaoCollectionSupervisao);
            }
            Collection<Formacaoturma> formacaoturmaCollection = cursoformacao.getFormacaoturmaCollection();
            for (Formacaoturma formacaoturmaCollectionFormacaoturma : formacaoturmaCollection) {
                formacaoturmaCollectionFormacaoturma.setIdcursoformacao(null);
                formacaoturmaCollectionFormacaoturma = em.merge(formacaoturmaCollectionFormacaoturma);
            }
            em.remove(cursoformacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cursoformacao> findCursoformacaoEntities() {
        return findCursoformacaoEntities(true, -1, -1);
    }

    public List<Cursoformacao> findCursoformacaoEntities(int maxResults, int firstResult) {
        return findCursoformacaoEntities(false, maxResults, firstResult);
    }

    private List<Cursoformacao> findCursoformacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cursoformacao.class));
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

    public Cursoformacao findCursoformacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cursoformacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoformacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cursoformacao> rt = cq.from(Cursoformacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
      public List<Cursoformacao> cursolocalidade(int p){
          List<Cursoformacao> curso = new CursoformacaoJpaController().findCursoformacaoEntities();//do banco
          List<Cursoformacao> cursoosselecionados = new ArrayList<Cursoformacao>();//filtrada
         
         for (Cursoformacao c : curso) {
             if(c.getIdlocalidade().getIdlocalidade()!=null){
                   if(c.getIdlocalidade().getIdlocalidade()==p){
                  cursoosselecionados.add(c);
            }
             }
            
        }
               
        return cursoosselecionados;
    }
       public List<Cursoformacao> getCurso(int p){
          List<Cursoformacao> curso = new CursoformacaoJpaController().findCursoformacaoEntities();//do banco
          List<Cursoformacao> cursoosselecionados = new ArrayList<Cursoformacao>();//filtrada
         
         for (Cursoformacao c : curso) {
             if(c.getIdcursoformacao()!=null){
                   if(c.getIdcurso().getIdcurso()==p){
                  cursoosselecionados.add(c);
            }
             }
            
        }
               
        return cursoosselecionados;
    }
//      public List<Cursoformacao> getCurso(Curso curso,int id) {
//        EntityManager em = getEntityManager();
//        Query q = em.createNativeQuery(""
//                +"select c.curso" 
//                +"from cursoformacao cf f,localidade l,curso c"
//                +"where cf.idcurso=c.idcurso and f.idlocalidade=l.idlocalidade and cf.idlocalidade=?" 
//                +"order by f.idcurso ",Cursoformacao.class);
//            if (curso != null) {
//            q.setParameter(1, c);
//            //  q.setParameter(1, l.getIdlocalidade());
//        } else if (p != null) {
//            q.setParameter(3, p.getIdposto());
//            //   q.setParameter(1, p.getIdposto());
//        } else if (d != null) {
//            q.setParameter(3, d.getIddistrito());
//            // q.setParameter(1, d.getIddistrito());
//        }
//        return q.getResultList();
//           
//      
//    }
}
