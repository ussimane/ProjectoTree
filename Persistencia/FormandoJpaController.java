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
import Modelo.Actividadecontapropria;
import Modelo.Nivelprofissional;
import Modelo.Nivelhabilitacao;
import Modelo.Localidade;
import Modelo.Estadocivil;
import Modelo.Curso;
import Modelo.Areadeformacao;
import Modelo.Accaoformacao;
import Modelo.Distrito;
import Modelo.Formacaoturma;
import Modelo.Formando;
import Modelo.Posto;
import Modelo.Relnrformando;
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
public class FormandoJpaController implements Serializable {

     public FormandoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public void create(Formando formando) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accaoformacao accaoformacao = formando.getAccaoformacao();
            if (accaoformacao != null) {
                accaoformacao = em.getReference(accaoformacao.getClass(), accaoformacao.getIdformando());
                formando.setAccaoformacao(accaoformacao);
            }
            Actividadecontapropria actividadecontapropria = formando.getActividadecontapropria();
            if (actividadecontapropria != null) {
                actividadecontapropria = em.getReference(actividadecontapropria.getClass(), actividadecontapropria.getIdformando());
                formando.setActividadecontapropria(actividadecontapropria);
            }
            Areadeformacao areadeformacao = formando.getAreadeformacao();
            if (areadeformacao != null) {
                areadeformacao = em.getReference(areadeformacao.getClass(), areadeformacao.getIdformando());
                formando.setAreadeformacao(areadeformacao);
            }
            Nivelprofissional idnivelprof = formando.getIdnivelprof();
            if (idnivelprof != null) {
                idnivelprof = em.getReference(idnivelprof.getClass(), idnivelprof.getIdnivelprofissional());
                formando.setIdnivelprof(idnivelprof);
            }
            Nivelhabilitacao idnivelhabilitacao = formando.getIdnivelhabilitacao();
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao = em.getReference(idnivelhabilitacao.getClass(), idnivelhabilitacao.getIdnivelhabilitacao());
                formando.setIdnivelhabilitacao(idnivelhabilitacao);
            }
            Localidade idlocalidade = formando.getIdlocalidade();
            if (idlocalidade != null) {
                idlocalidade = em.getReference(idlocalidade.getClass(), idlocalidade.getIdlocalidade());
                formando.setIdlocalidade(idlocalidade);
            }
            Estadocivil idestadocivil = formando.getIdestadocivil();
            if (idestadocivil != null) {
                idestadocivil = em.getReference(idestadocivil.getClass(), idestadocivil.getIdestadocivil());
                formando.setIdestadocivil(idestadocivil);
            }
            Curso idcurso = formando.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdcurso());
                formando.setIdcurso(idcurso);
            }
            Formacaoturma formacaoturma = formando.getFormacaoturma();
            if (formacaoturma != null) {
                formacaoturma = em.getReference(formacaoturma.getClass(), formacaoturma.getIdformando());
                formando.setFormacaoturma(formacaoturma);
            }
            em.persist(formando);
            if (accaoformacao != null) {
                Formando oldFormandoOfAccaoformacao = accaoformacao.getFormando();
                if (oldFormandoOfAccaoformacao != null) {
                    oldFormandoOfAccaoformacao.setAccaoformacao(null);
                    oldFormandoOfAccaoformacao = em.merge(oldFormandoOfAccaoformacao);
                }
                accaoformacao.setFormando(formando);
                accaoformacao = em.merge(accaoformacao);
            }
            if (actividadecontapropria != null) {
                Formando oldFormandoOfActividadecontapropria = actividadecontapropria.getFormando();
                if (oldFormandoOfActividadecontapropria != null) {
                    oldFormandoOfActividadecontapropria.setActividadecontapropria(null);
                    oldFormandoOfActividadecontapropria = em.merge(oldFormandoOfActividadecontapropria);
                }
                actividadecontapropria.setFormando(formando);
                actividadecontapropria = em.merge(actividadecontapropria);
            }
            if (areadeformacao != null) {
                Formando oldFormandoOfAreadeformacao = areadeformacao.getFormando();
                if (oldFormandoOfAreadeformacao != null) {
                    oldFormandoOfAreadeformacao.setAreadeformacao(null);
                    oldFormandoOfAreadeformacao = em.merge(oldFormandoOfAreadeformacao);
                }
                areadeformacao.setFormando(formando);
                areadeformacao = em.merge(areadeformacao);
            }
            if (idnivelprof != null) {
                idnivelprof.getFormandoCollection().add(formando);
                idnivelprof = em.merge(idnivelprof);
            }
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao.getFormandoCollection().add(formando);
                idnivelhabilitacao = em.merge(idnivelhabilitacao);
            }
            if (idlocalidade != null) {
                idlocalidade.getFormandoCollection().add(formando);
                idlocalidade = em.merge(idlocalidade);
            }
            if (idestadocivil != null) {
                idestadocivil.getFormandoCollection().add(formando);
                idestadocivil = em.merge(idestadocivil);
            }
            if (idcurso != null) {
                idcurso.getFormandoCollection().add(formando);
                idcurso = em.merge(idcurso);
            }
            if (formacaoturma != null) {
                Formando oldFormandoOfFormacaoturma = formacaoturma.getFormando();
                if (oldFormandoOfFormacaoturma != null) {
                    oldFormandoOfFormacaoturma.setFormacaoturma(null);
                    oldFormandoOfFormacaoturma = em.merge(oldFormandoOfFormacaoturma);
                }
                formacaoturma.setFormando(formando);
                formacaoturma = em.merge(formacaoturma);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formando formando) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formando persistentFormando = em.find(Formando.class, formando.getIdformando());
            Accaoformacao accaoformacaoOld = persistentFormando.getAccaoformacao();
            Accaoformacao accaoformacaoNew = formando.getAccaoformacao();
            Actividadecontapropria actividadecontapropriaOld = persistentFormando.getActividadecontapropria();
            Actividadecontapropria actividadecontapropriaNew = formando.getActividadecontapropria();
            Areadeformacao areadeformacaoOld = persistentFormando.getAreadeformacao();
            Areadeformacao areadeformacaoNew = formando.getAreadeformacao();
            Nivelprofissional idnivelprofOld = persistentFormando.getIdnivelprof();
            Nivelprofissional idnivelprofNew = formando.getIdnivelprof();
            Nivelhabilitacao idnivelhabilitacaoOld = persistentFormando.getIdnivelhabilitacao();
            Nivelhabilitacao idnivelhabilitacaoNew = formando.getIdnivelhabilitacao();
            Localidade idlocalidadeOld = persistentFormando.getIdlocalidade();
            Localidade idlocalidadeNew = formando.getIdlocalidade();
            Estadocivil idestadocivilOld = persistentFormando.getIdestadocivil();
            Estadocivil idestadocivilNew = formando.getIdestadocivil();
            Curso idcursoOld = persistentFormando.getIdcurso();
            Curso idcursoNew = formando.getIdcurso();
            Formacaoturma formacaoturmaOld = persistentFormando.getFormacaoturma();
            Formacaoturma formacaoturmaNew = formando.getFormacaoturma();
            List<String> illegalOrphanMessages = null;
            if (accaoformacaoOld != null && !accaoformacaoOld.equals(accaoformacaoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Accaoformacao " + accaoformacaoOld + " since its formando field is not nullable.");
            }
            if (actividadecontapropriaOld != null && !actividadecontapropriaOld.equals(actividadecontapropriaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Actividadecontapropria " + actividadecontapropriaOld + " since its formando field is not nullable.");
            }
            if (areadeformacaoOld != null && !areadeformacaoOld.equals(areadeformacaoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Areadeformacao " + areadeformacaoOld + " since its formando field is not nullable.");
            }
            if (formacaoturmaOld != null && !formacaoturmaOld.equals(formacaoturmaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Formacaoturma " + formacaoturmaOld + " since its formando field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accaoformacaoNew != null) {
                accaoformacaoNew = em.getReference(accaoformacaoNew.getClass(), accaoformacaoNew.getIdformando());
                formando.setAccaoformacao(accaoformacaoNew);
            }
            if (actividadecontapropriaNew != null) {
                actividadecontapropriaNew = em.getReference(actividadecontapropriaNew.getClass(), actividadecontapropriaNew.getIdformando());
                formando.setActividadecontapropria(actividadecontapropriaNew);
            }
            if (areadeformacaoNew != null) {
                areadeformacaoNew = em.getReference(areadeformacaoNew.getClass(), areadeformacaoNew.getIdformando());
                formando.setAreadeformacao(areadeformacaoNew);
            }
            if (idnivelprofNew != null) {
                idnivelprofNew = em.getReference(idnivelprofNew.getClass(), idnivelprofNew.getIdnivelprofissional());
                formando.setIdnivelprof(idnivelprofNew);
            }
            if (idnivelhabilitacaoNew != null) {
                idnivelhabilitacaoNew = em.getReference(idnivelhabilitacaoNew.getClass(), idnivelhabilitacaoNew.getIdnivelhabilitacao());
                formando.setIdnivelhabilitacao(idnivelhabilitacaoNew);
            }
            if (idlocalidadeNew != null) {
                idlocalidadeNew = em.getReference(idlocalidadeNew.getClass(), idlocalidadeNew.getIdlocalidade());
                formando.setIdlocalidade(idlocalidadeNew);
            }
            if (idestadocivilNew != null) {
                idestadocivilNew = em.getReference(idestadocivilNew.getClass(), idestadocivilNew.getIdestadocivil());
                formando.setIdestadocivil(idestadocivilNew);
            }
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdcurso());
                formando.setIdcurso(idcursoNew);
            }
            if (formacaoturmaNew != null) {
                formacaoturmaNew = em.getReference(formacaoturmaNew.getClass(), formacaoturmaNew.getIdformando());
                formando.setFormacaoturma(formacaoturmaNew);
            }
            formando = em.merge(formando);
            if (accaoformacaoNew != null && !accaoformacaoNew.equals(accaoformacaoOld)) {
                Formando oldFormandoOfAccaoformacao = accaoformacaoNew.getFormando();
                if (oldFormandoOfAccaoformacao != null) {
                    oldFormandoOfAccaoformacao.setAccaoformacao(null);
                    oldFormandoOfAccaoformacao = em.merge(oldFormandoOfAccaoformacao);
                }
                accaoformacaoNew.setFormando(formando);
                accaoformacaoNew = em.merge(accaoformacaoNew);
            }
            if (actividadecontapropriaNew != null && !actividadecontapropriaNew.equals(actividadecontapropriaOld)) {
                Formando oldFormandoOfActividadecontapropria = actividadecontapropriaNew.getFormando();
                if (oldFormandoOfActividadecontapropria != null) {
                    oldFormandoOfActividadecontapropria.setActividadecontapropria(null);
                    oldFormandoOfActividadecontapropria = em.merge(oldFormandoOfActividadecontapropria);
                }
                actividadecontapropriaNew.setFormando(formando);
                actividadecontapropriaNew = em.merge(actividadecontapropriaNew);
            }
            if (areadeformacaoNew != null && !areadeformacaoNew.equals(areadeformacaoOld)) {
                Formando oldFormandoOfAreadeformacao = areadeformacaoNew.getFormando();
                if (oldFormandoOfAreadeformacao != null) {
                    oldFormandoOfAreadeformacao.setAreadeformacao(null);
                    oldFormandoOfAreadeformacao = em.merge(oldFormandoOfAreadeformacao);
                }
                areadeformacaoNew.setFormando(formando);
                areadeformacaoNew = em.merge(areadeformacaoNew);
            }
            if (idnivelprofOld != null && !idnivelprofOld.equals(idnivelprofNew)) {
                idnivelprofOld.getFormandoCollection().remove(formando);
                idnivelprofOld = em.merge(idnivelprofOld);
            }
            if (idnivelprofNew != null && !idnivelprofNew.equals(idnivelprofOld)) {
                idnivelprofNew.getFormandoCollection().add(formando);
                idnivelprofNew = em.merge(idnivelprofNew);
            }
            if (idnivelhabilitacaoOld != null && !idnivelhabilitacaoOld.equals(idnivelhabilitacaoNew)) {
                idnivelhabilitacaoOld.getFormandoCollection().remove(formando);
                idnivelhabilitacaoOld = em.merge(idnivelhabilitacaoOld);
            }
            if (idnivelhabilitacaoNew != null && !idnivelhabilitacaoNew.equals(idnivelhabilitacaoOld)) {
                idnivelhabilitacaoNew.getFormandoCollection().add(formando);
                idnivelhabilitacaoNew = em.merge(idnivelhabilitacaoNew);
            }
            if (idlocalidadeOld != null && !idlocalidadeOld.equals(idlocalidadeNew)) {
                idlocalidadeOld.getFormandoCollection().remove(formando);
                idlocalidadeOld = em.merge(idlocalidadeOld);
            }
            if (idlocalidadeNew != null && !idlocalidadeNew.equals(idlocalidadeOld)) {
                idlocalidadeNew.getFormandoCollection().add(formando);
                idlocalidadeNew = em.merge(idlocalidadeNew);
            }
            if (idestadocivilOld != null && !idestadocivilOld.equals(idestadocivilNew)) {
                idestadocivilOld.getFormandoCollection().remove(formando);
                idestadocivilOld = em.merge(idestadocivilOld);
            }
            if (idestadocivilNew != null && !idestadocivilNew.equals(idestadocivilOld)) {
                idestadocivilNew.getFormandoCollection().add(formando);
                idestadocivilNew = em.merge(idestadocivilNew);
            }
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getFormandoCollection().remove(formando);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getFormandoCollection().add(formando);
                idcursoNew = em.merge(idcursoNew);
            }
            if (formacaoturmaNew != null && !formacaoturmaNew.equals(formacaoturmaOld)) {
                Formando oldFormandoOfFormacaoturma = formacaoturmaNew.getFormando();
                if (oldFormandoOfFormacaoturma != null) {
                    oldFormandoOfFormacaoturma.setFormacaoturma(null);
                    oldFormandoOfFormacaoturma = em.merge(oldFormandoOfFormacaoturma);
                }
                formacaoturmaNew.setFormando(formando);
                formacaoturmaNew = em.merge(formacaoturmaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formando.getIdformando();
                if (findFormando(id) == null) {
                    throw new NonexistentEntityException("The formando with id " + id + " no longer exists.");
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
            Formando formando;
            try {
                formando = em.getReference(Formando.class, id);
                formando.getIdformando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formando with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Accaoformacao accaoformacaoOrphanCheck = formando.getAccaoformacao();
            if (accaoformacaoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formando (" + formando + ") cannot be destroyed since the Accaoformacao " + accaoformacaoOrphanCheck + " in its accaoformacao field has a non-nullable formando field.");
            }
            Actividadecontapropria actividadecontapropriaOrphanCheck = formando.getActividadecontapropria();
            if (actividadecontapropriaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formando (" + formando + ") cannot be destroyed since the Actividadecontapropria " + actividadecontapropriaOrphanCheck + " in its actividadecontapropria field has a non-nullable formando field.");
            }
            Areadeformacao areadeformacaoOrphanCheck = formando.getAreadeformacao();
            if (areadeformacaoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formando (" + formando + ") cannot be destroyed since the Areadeformacao " + areadeformacaoOrphanCheck + " in its areadeformacao field has a non-nullable formando field.");
            }
            Formacaoturma formacaoturmaOrphanCheck = formando.getFormacaoturma();
            if (formacaoturmaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formando (" + formando + ") cannot be destroyed since the Formacaoturma " + formacaoturmaOrphanCheck + " in its formacaoturma field has a non-nullable formando field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Nivelprofissional idnivelprof = formando.getIdnivelprof();
            if (idnivelprof != null) {
                idnivelprof.getFormandoCollection().remove(formando);
                idnivelprof = em.merge(idnivelprof);
            }
            Nivelhabilitacao idnivelhabilitacao = formando.getIdnivelhabilitacao();
            if (idnivelhabilitacao != null) {
                idnivelhabilitacao.getFormandoCollection().remove(formando);
                idnivelhabilitacao = em.merge(idnivelhabilitacao);
            }
            Localidade idlocalidade = formando.getIdlocalidade();
            if (idlocalidade != null) {
                idlocalidade.getFormandoCollection().remove(formando);
                idlocalidade = em.merge(idlocalidade);
            }
            Estadocivil idestadocivil = formando.getIdestadocivil();
            if (idestadocivil != null) {
                idestadocivil.getFormandoCollection().remove(formando);
                idestadocivil = em.merge(idestadocivil);
            }
            Curso idcurso = formando.getIdcurso();
            if (idcurso != null) {
                idcurso.getFormandoCollection().remove(formando);
                idcurso = em.merge(idcurso);
            }
            em.remove(formando);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Formando> findFormandoEntities() {
        return findFormandoEntities(true, -1, -1);
    }

    public List<Formando> findFormandoEntities(int maxResults, int firstResult) {
        return findFormandoEntities(false, maxResults, firstResult);
    }

    private List<Formando> findFormandoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formando.class));
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

    public Formando findFormando(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formando.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormandoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formando> rt = cq.from(Formando.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
     public List<Formando> formandoDistrito(int p){
          List<Formando> formandos = new FormandoJpaController().findFormandoEntities();//do banco
          List<Formando> formandosselecionados = new ArrayList<Formando>();//filtrada
         
         for (Formando f : formandos) {
             if(f.getIdlocalidade().getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getIdlocalidade().getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
    
     
      public List<Formando> formandoLocalidade(int p){
          List<Formando> formandos = new FormandoJpaController().findFormandoEntities();//do banco
          List<Formando> formandosselecionados = new ArrayList<Formando>();//filtrada
         
         for (Formando f : formandos) {
             if(f.getIdlocalidade().getIdlocalidade()!=null){
                   if(f.getIdlocalidade().getIdlocalidade()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
       public List<Formando> getformando(String n) {
        EntityManager em = getEntityManager();
        
        Query q = em.createNativeQuery("SELECT * from formando where formando.nome like ? ");
        
            q.setParameter(1, n);
           
        
        return q.getResultList();
    }

      public List<Formando> getRelatorioFormando(int l) {
        EntityManager em = getEntityManager();
        String par = "";
//        if (l != null) {
            par = "where f.idlocalidade = ?";
//        }
            //else if (p != null) {
//            par = "";
//        } else if (d != null) {
//            par = "where f.idlocalidade.idposto.iddistrito.iddistrito = ?";
//        }
        
        Query q = em.createNativeQuery("select * from Formando f "+par,Formando.class);
      //  if (l != null) {
            q.setParameter(1, l);
      //  } 
//        else if (p != null) {a    a
//            q.setParameter(1, p.getIdposto());
//        } else if (d != null) {
//            q.setParameter(1, d.getIddistrito());
//        }
        return q.getResultList();
    } 
      
      
     public List<Formando> getRelatorioFormandoCurso(Localidade l,int c) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            System.out.println(l.getIdlocalidade()+"");
            par = "and f.idlocalidade = ?";
        }
            //else if (p != null) {
//            par = "";
//        } else if (d != null) {
//            par = "where f.idlocalidade.idposto.iddistrito.iddistrito = ?";
//        }
        List<Formando> lf= new ArrayList<Formando>();
        Query q = em.createNativeQuery("select * from Formando f, Areadeformacao a where f.idformando = a.idformando"
                + " and (a.idcurso1 = ?)  "+par,Formando.class);
       if (l != null) {
            q.setParameter(1,1);// l.getIdlocalidade());
            q.setParameter(2, c);
        } else {
          q.setParameter(1, c);
        }
            lf.addAll(q.getResultList());
            q = em.createNativeQuery("select * from Formando f, Areadeformacao a where f.idformando = a.idformando"
                + " and (a.idcurso2 = ?)  "+par,Formando.class);
        if (l != null) {
            q.setParameter(1, 1);//l.getIdlocalidade());
            q.setParameter(2, c);
        } else {
          q.setParameter(1, c);
        }
            lf.addAll(q.getResultList());
            q = em.createNativeQuery("select * from Formando f, Areadeformacao a where f.idformando = a.idformando"
                + " and (a.idcurso3 = ? )  "+par,Formando.class);
      //  if (l != null) {
            if (l != null) {
            q.setParameter(1, 1);//l.getIdlocalidade());
            q.setParameter(2, c);
        } else {
          q.setParameter(1, c);
        }
      //  } 
//        else if (p != null) {a    a
//            q.setParameter(1, p.getIdposto());
//        } else if (d != null) {
//            q.setParameter(1, d.getIddistrito());
//        }
        return lf;
    } 
     
     
      public List<Formando> getprio1(int l,int  c){
          List<Formando> formandos = new FormandoJpaController().findFormandoEntities();//do banco
          List<Formando> formandosselecionados = new ArrayList<Formando>();//filtrada
         
         for (Formando f : formandos) {
             if(f.getIdlocalidade().getIdlocalidade()!=null 
                     && f.getAreadeformacao().getIdcurso1().getCurso()!=null){
                   if(f.getIdlocalidade().getIdlocalidade()==l 
                           && f.getAreadeformacao().getIdcurso1().getIdcurso()==c ){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
    public List<Formando> getprio2(int l,int  c){
          List<Formando> formandos = new FormandoJpaController().findFormandoEntities();//do banco
          List<Formando> formandosselecionados = new ArrayList<Formando>();//filtrada
         
         for (Formando f : formandos) {
             if(f.getIdlocalidade().getIdlocalidade()!=null 
                     && f.getAreadeformacao().getIdcurso2().getCurso()!=null){
                   if(f.getIdlocalidade().getIdlocalidade()==l 
                           && f.getAreadeformacao().getIdcurso2().getIdcurso()==c ){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
     public List<Formando> getprio3(int l,int  c){
          List<Formando> formandos = new FormandoJpaController().findFormandoEntities();//do banco
          List<Formando> formandosselecionados = new ArrayList<Formando>();//filtrada
         
         for (Formando f : formandos) {
             if(f.getIdlocalidade().getIdlocalidade()!=null 
                     && f.getAreadeformacao().getIdcurso3().getCurso()!=null){
                   if(f.getIdlocalidade().getIdlocalidade()==l 
                           && f.getAreadeformacao().getIdcurso3().getIdcurso()==c ){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
     public List<Formando> getturma1(int lo) {
        EntityManager em = getEntityManager();
        Query q = em.createNativeQuery(""
                +"select l.localidade,f.nome,f.sexo,f.contacto,f.idade,c.curso" 
                +"from formando f,localidade l,curso c"
                +"where f.idcurso=c.idcurso and f.idlocalidade=l.idlocalidade and f.idlocalidade=?" 
                +"order by f.idcurso ",Formando.class);
            q.setParameter(1, lo);
           
        return q.getResultList();
    }
}
