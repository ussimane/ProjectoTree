/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Curso;
import Modelo.Cursoformacao;
import Modelo.Formador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Posto;
import Modelo.Formando;
import java.util.ArrayList;
import java.util.List;
import Modelo.Inscritos;
import Modelo.Localidade;
import Modelo.Mestre;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class LocalidadeJpaController implements Serializable {

    public LocalidadeJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Localidade localidade) {
        if (localidade.getCursoformacaoCollection() == null) {
            localidade.setCursoformacaoCollection(new ArrayList<Cursoformacao>());
        }
        if (localidade.getMestreCollection() == null) {
            localidade.setMestreCollection(new ArrayList<Mestre>());
        }
        if (localidade.getFormadorCollection() == null) {
            localidade.setFormadorCollection(new ArrayList<Formador>());
        }
        if (localidade.getFormandoCollection() == null) {
            localidade.setFormandoCollection(new ArrayList<Formando>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posto idposto = localidade.getIdposto();
            if (idposto != null) {
                idposto = em.getReference(idposto.getClass(), idposto.getIdposto());
                localidade.setIdposto(idposto);
            }
            Collection<Cursoformacao> attachedCursoformacaoCollection = new ArrayList<Cursoformacao>();
            for (Cursoformacao cursoformacaoCollectionCursoformacaoToAttach : localidade.getCursoformacaoCollection()) {
                cursoformacaoCollectionCursoformacaoToAttach = em.getReference(cursoformacaoCollectionCursoformacaoToAttach.getClass(), cursoformacaoCollectionCursoformacaoToAttach.getIdcursoformacao());
                attachedCursoformacaoCollection.add(cursoformacaoCollectionCursoformacaoToAttach);
            }
            localidade.setCursoformacaoCollection(attachedCursoformacaoCollection);
            Collection<Mestre> attachedMestreCollection = new ArrayList<Mestre>();
            for (Mestre mestreCollectionMestreToAttach : localidade.getMestreCollection()) {
                mestreCollectionMestreToAttach = em.getReference(mestreCollectionMestreToAttach.getClass(), mestreCollectionMestreToAttach.getIdmestre());
                attachedMestreCollection.add(mestreCollectionMestreToAttach);
            }
            localidade.setMestreCollection(attachedMestreCollection);
            Collection<Formador> attachedFormadorCollection = new ArrayList<Formador>();
            for (Formador formadorCollectionFormadorToAttach : localidade.getFormadorCollection()) {
                formadorCollectionFormadorToAttach = em.getReference(formadorCollectionFormadorToAttach.getClass(), formadorCollectionFormadorToAttach.getIdformador());
                attachedFormadorCollection.add(formadorCollectionFormadorToAttach);
            }
            localidade.setFormadorCollection(attachedFormadorCollection);
            Collection<Formando> attachedFormandoCollection = new ArrayList<Formando>();
            for (Formando formandoCollectionFormandoToAttach : localidade.getFormandoCollection()) {
                formandoCollectionFormandoToAttach = em.getReference(formandoCollectionFormandoToAttach.getClass(), formandoCollectionFormandoToAttach.getIdformando());
                attachedFormandoCollection.add(formandoCollectionFormandoToAttach);
            }
            localidade.setFormandoCollection(attachedFormandoCollection);
            em.persist(localidade);
            if (idposto != null) {
                idposto.getLocalidadeCollection().add(localidade);
                idposto = em.merge(idposto);
            }
            for (Cursoformacao cursoformacaoCollectionCursoformacao : localidade.getCursoformacaoCollection()) {
                Localidade oldIdlocalidadeOfCursoformacaoCollectionCursoformacao = cursoformacaoCollectionCursoformacao.getIdlocalidade();
                cursoformacaoCollectionCursoformacao.setIdlocalidade(localidade);
                cursoformacaoCollectionCursoformacao = em.merge(cursoformacaoCollectionCursoformacao);
                if (oldIdlocalidadeOfCursoformacaoCollectionCursoformacao != null) {
                    oldIdlocalidadeOfCursoformacaoCollectionCursoformacao.getCursoformacaoCollection().remove(cursoformacaoCollectionCursoformacao);
                    oldIdlocalidadeOfCursoformacaoCollectionCursoformacao = em.merge(oldIdlocalidadeOfCursoformacaoCollectionCursoformacao);
                }
            }
            for (Mestre mestreCollectionMestre : localidade.getMestreCollection()) {
                Localidade oldIdlocalidadeOfMestreCollectionMestre = mestreCollectionMestre.getIdlocalidade();
                mestreCollectionMestre.setIdlocalidade(localidade);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
                if (oldIdlocalidadeOfMestreCollectionMestre != null) {
                    oldIdlocalidadeOfMestreCollectionMestre.getMestreCollection().remove(mestreCollectionMestre);
                    oldIdlocalidadeOfMestreCollectionMestre = em.merge(oldIdlocalidadeOfMestreCollectionMestre);
                }
            }
            for (Formador formadorCollectionFormador : localidade.getFormadorCollection()) {
                Localidade oldIddistritoOfFormadorCollectionFormador = formadorCollectionFormador.getIddistrito();
                formadorCollectionFormador.setIddistrito(localidade);
                formadorCollectionFormador = em.merge(formadorCollectionFormador);
                if (oldIddistritoOfFormadorCollectionFormador != null) {
                    oldIddistritoOfFormadorCollectionFormador.getFormadorCollection().remove(formadorCollectionFormador);
                    oldIddistritoOfFormadorCollectionFormador = em.merge(oldIddistritoOfFormadorCollectionFormador);
                }
            }
            for (Formando formandoCollectionFormando : localidade.getFormandoCollection()) {
                Localidade oldIdlocalidadeOfFormandoCollectionFormando = formandoCollectionFormando.getIdlocalidade();
                formandoCollectionFormando.setIdlocalidade(localidade);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
                if (oldIdlocalidadeOfFormandoCollectionFormando != null) {
                    oldIdlocalidadeOfFormandoCollectionFormando.getFormandoCollection().remove(formandoCollectionFormando);
                    oldIdlocalidadeOfFormandoCollectionFormando = em.merge(oldIdlocalidadeOfFormandoCollectionFormando);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidade localidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidade persistentLocalidade = em.find(Localidade.class, localidade.getIdlocalidade());
            Posto idpostoOld = persistentLocalidade.getIdposto();
            Posto idpostoNew = localidade.getIdposto();
            Collection<Cursoformacao> cursoformacaoCollectionOld = persistentLocalidade.getCursoformacaoCollection();
            Collection<Cursoformacao> cursoformacaoCollectionNew = localidade.getCursoformacaoCollection();
            Collection<Mestre> mestreCollectionOld = persistentLocalidade.getMestreCollection();
            Collection<Mestre> mestreCollectionNew = localidade.getMestreCollection();
            Collection<Formador> formadorCollectionOld = persistentLocalidade.getFormadorCollection();
            Collection<Formador> formadorCollectionNew = localidade.getFormadorCollection();
            Collection<Formando> formandoCollectionOld = persistentLocalidade.getFormandoCollection();
            Collection<Formando> formandoCollectionNew = localidade.getFormandoCollection();
            if (idpostoNew != null) {
                idpostoNew = em.getReference(idpostoNew.getClass(), idpostoNew.getIdposto());
                localidade.setIdposto(idpostoNew);
            }
            Collection<Cursoformacao> attachedCursoformacaoCollectionNew = new ArrayList<Cursoformacao>();
            for (Cursoformacao cursoformacaoCollectionNewCursoformacaoToAttach : cursoformacaoCollectionNew) {
                cursoformacaoCollectionNewCursoformacaoToAttach = em.getReference(cursoformacaoCollectionNewCursoformacaoToAttach.getClass(), cursoformacaoCollectionNewCursoformacaoToAttach.getIdcursoformacao());
                attachedCursoformacaoCollectionNew.add(cursoformacaoCollectionNewCursoformacaoToAttach);
            }
            cursoformacaoCollectionNew = attachedCursoformacaoCollectionNew;
            localidade.setCursoformacaoCollection(cursoformacaoCollectionNew);
            Collection<Mestre> attachedMestreCollectionNew = new ArrayList<Mestre>();
            for (Mestre mestreCollectionNewMestreToAttach : mestreCollectionNew) {
                mestreCollectionNewMestreToAttach = em.getReference(mestreCollectionNewMestreToAttach.getClass(), mestreCollectionNewMestreToAttach.getIdmestre());
                attachedMestreCollectionNew.add(mestreCollectionNewMestreToAttach);
            }
            mestreCollectionNew = attachedMestreCollectionNew;
            localidade.setMestreCollection(mestreCollectionNew);
            Collection<Formador> attachedFormadorCollectionNew = new ArrayList<Formador>();
            for (Formador formadorCollectionNewFormadorToAttach : formadorCollectionNew) {
                formadorCollectionNewFormadorToAttach = em.getReference(formadorCollectionNewFormadorToAttach.getClass(), formadorCollectionNewFormadorToAttach.getIdformador());
                attachedFormadorCollectionNew.add(formadorCollectionNewFormadorToAttach);
            }
            formadorCollectionNew = attachedFormadorCollectionNew;
            localidade.setFormadorCollection(formadorCollectionNew);
            Collection<Formando> attachedFormandoCollectionNew = new ArrayList<Formando>();
            for (Formando formandoCollectionNewFormandoToAttach : formandoCollectionNew) {
                formandoCollectionNewFormandoToAttach = em.getReference(formandoCollectionNewFormandoToAttach.getClass(), formandoCollectionNewFormandoToAttach.getIdformando());
                attachedFormandoCollectionNew.add(formandoCollectionNewFormandoToAttach);
            }
            formandoCollectionNew = attachedFormandoCollectionNew;
            localidade.setFormandoCollection(formandoCollectionNew);
            localidade = em.merge(localidade);
            if (idpostoOld != null && !idpostoOld.equals(idpostoNew)) {
                idpostoOld.getLocalidadeCollection().remove(localidade);
                idpostoOld = em.merge(idpostoOld);
            }
            if (idpostoNew != null && !idpostoNew.equals(idpostoOld)) {
                idpostoNew.getLocalidadeCollection().add(localidade);
                idpostoNew = em.merge(idpostoNew);
            }
            for (Cursoformacao cursoformacaoCollectionOldCursoformacao : cursoformacaoCollectionOld) {
                if (!cursoformacaoCollectionNew.contains(cursoformacaoCollectionOldCursoformacao)) {
                    cursoformacaoCollectionOldCursoformacao.setIdlocalidade(null);
                    cursoformacaoCollectionOldCursoformacao = em.merge(cursoformacaoCollectionOldCursoformacao);
                }
            }
            for (Cursoformacao cursoformacaoCollectionNewCursoformacao : cursoformacaoCollectionNew) {
                if (!cursoformacaoCollectionOld.contains(cursoformacaoCollectionNewCursoformacao)) {
                    Localidade oldIdlocalidadeOfCursoformacaoCollectionNewCursoformacao = cursoformacaoCollectionNewCursoformacao.getIdlocalidade();
                    cursoformacaoCollectionNewCursoformacao.setIdlocalidade(localidade);
                    cursoformacaoCollectionNewCursoformacao = em.merge(cursoformacaoCollectionNewCursoformacao);
                    if (oldIdlocalidadeOfCursoformacaoCollectionNewCursoformacao != null && !oldIdlocalidadeOfCursoformacaoCollectionNewCursoformacao.equals(localidade)) {
                        oldIdlocalidadeOfCursoformacaoCollectionNewCursoformacao.getCursoformacaoCollection().remove(cursoformacaoCollectionNewCursoformacao);
                        oldIdlocalidadeOfCursoformacaoCollectionNewCursoformacao = em.merge(oldIdlocalidadeOfCursoformacaoCollectionNewCursoformacao);
                    }
                }
            }
            for (Mestre mestreCollectionOldMestre : mestreCollectionOld) {
                if (!mestreCollectionNew.contains(mestreCollectionOldMestre)) {
                    mestreCollectionOldMestre.setIdlocalidade(null);
                    mestreCollectionOldMestre = em.merge(mestreCollectionOldMestre);
                }
            }
            for (Mestre mestreCollectionNewMestre : mestreCollectionNew) {
                if (!mestreCollectionOld.contains(mestreCollectionNewMestre)) {
                    Localidade oldIdlocalidadeOfMestreCollectionNewMestre = mestreCollectionNewMestre.getIdlocalidade();
                    mestreCollectionNewMestre.setIdlocalidade(localidade);
                    mestreCollectionNewMestre = em.merge(mestreCollectionNewMestre);
                    if (oldIdlocalidadeOfMestreCollectionNewMestre != null && !oldIdlocalidadeOfMestreCollectionNewMestre.equals(localidade)) {
                        oldIdlocalidadeOfMestreCollectionNewMestre.getMestreCollection().remove(mestreCollectionNewMestre);
                        oldIdlocalidadeOfMestreCollectionNewMestre = em.merge(oldIdlocalidadeOfMestreCollectionNewMestre);
                    }
                }
            }
            for (Formador formadorCollectionOldFormador : formadorCollectionOld) {
                if (!formadorCollectionNew.contains(formadorCollectionOldFormador)) {
                    formadorCollectionOldFormador.setIddistrito(null);
                    formadorCollectionOldFormador = em.merge(formadorCollectionOldFormador);
                }
            }
            for (Formador formadorCollectionNewFormador : formadorCollectionNew) {
                if (!formadorCollectionOld.contains(formadorCollectionNewFormador)) {
                    Localidade oldIddistritoOfFormadorCollectionNewFormador = formadorCollectionNewFormador.getIddistrito();
                    formadorCollectionNewFormador.setIddistrito(localidade);
                    formadorCollectionNewFormador = em.merge(formadorCollectionNewFormador);
                    if (oldIddistritoOfFormadorCollectionNewFormador != null && !oldIddistritoOfFormadorCollectionNewFormador.equals(localidade)) {
                        oldIddistritoOfFormadorCollectionNewFormador.getFormadorCollection().remove(formadorCollectionNewFormador);
                        oldIddistritoOfFormadorCollectionNewFormador = em.merge(oldIddistritoOfFormadorCollectionNewFormador);
                    }
                }
            }
            for (Formando formandoCollectionOldFormando : formandoCollectionOld) {
                if (!formandoCollectionNew.contains(formandoCollectionOldFormando)) {
                    formandoCollectionOldFormando.setIdlocalidade(null);
                    formandoCollectionOldFormando = em.merge(formandoCollectionOldFormando);
                }
            }
            for (Formando formandoCollectionNewFormando : formandoCollectionNew) {
                if (!formandoCollectionOld.contains(formandoCollectionNewFormando)) {
                    Localidade oldIdlocalidadeOfFormandoCollectionNewFormando = formandoCollectionNewFormando.getIdlocalidade();
                    formandoCollectionNewFormando.setIdlocalidade(localidade);
                    formandoCollectionNewFormando = em.merge(formandoCollectionNewFormando);
                    if (oldIdlocalidadeOfFormandoCollectionNewFormando != null && !oldIdlocalidadeOfFormandoCollectionNewFormando.equals(localidade)) {
                        oldIdlocalidadeOfFormandoCollectionNewFormando.getFormandoCollection().remove(formandoCollectionNewFormando);
                        oldIdlocalidadeOfFormandoCollectionNewFormando = em.merge(oldIdlocalidadeOfFormandoCollectionNewFormando);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = localidade.getIdlocalidade();
                if (findLocalidade(id) == null) {
                    throw new NonexistentEntityException("The localidade with id " + id + " no longer exists.");
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
            Localidade localidade;
            try {
                localidade = em.getReference(Localidade.class, id);
                localidade.getIdlocalidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidade with id " + id + " no longer exists.", enfe);
            }
            Posto idposto = localidade.getIdposto();
            if (idposto != null) {
                idposto.getLocalidadeCollection().remove(localidade);
                idposto = em.merge(idposto);
            }
            Collection<Cursoformacao> cursoformacaoCollection = localidade.getCursoformacaoCollection();
            for (Cursoformacao cursoformacaoCollectionCursoformacao : cursoformacaoCollection) {
                cursoformacaoCollectionCursoformacao.setIdlocalidade(null);
                cursoformacaoCollectionCursoformacao = em.merge(cursoformacaoCollectionCursoformacao);
            }
            Collection<Mestre> mestreCollection = localidade.getMestreCollection();
            for (Mestre mestreCollectionMestre : mestreCollection) {
                mestreCollectionMestre.setIdlocalidade(null);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
            }
            Collection<Formador> formadorCollection = localidade.getFormadorCollection();
            for (Formador formadorCollectionFormador : formadorCollection) {
                formadorCollectionFormador.setIddistrito(null);
                formadorCollectionFormador = em.merge(formadorCollectionFormador);
            }
            Collection<Formando> formandoCollection = localidade.getFormandoCollection();
            for (Formando formandoCollectionFormando : formandoCollection) {
                formandoCollectionFormando.setIdlocalidade(null);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
            }
            em.remove(localidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidade> findLocalidadeEntities() {
        return findLocalidadeEntities(true, -1, -1);
    }

    public List<Localidade> findLocalidadeEntities(int maxResults, int firstResult) {
        return findLocalidadeEntities(false, maxResults, firstResult);
    }

    private List<Localidade> findLocalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidade.class));
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

    public Localidade findLocalidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidade> rt = cq.from(Localidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public List<Localidade> getloLocalidades(int p){
          List<Localidade> formandos = new LocalidadeJpaController().findLocalidadeEntities();//do banco
          List<Localidade> formandosselecionados = new ArrayList<Localidade>();//filtrada
         
         for (Localidade f : formandos) {
             if(f.getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
    
     public List<Localidade> getloLocalidadesDist(int n) {
        EntityManager em = getEntityManager();
        
        Query q = em.createNativeQuery("SELECT * from localidade l,posto p,distrito d" +
                                          "where l.idposto= p.idposto and p.iddistrito=d.iddistrito and d.iddistrito=? ");
        
            q.setParameter(1, n);
           
        
        return q.getResultList();
    }
    
}
