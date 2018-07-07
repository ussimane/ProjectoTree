/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Accaoformacao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Formando;
import java.util.ArrayList;
import java.util.List;
import Modelo.Inscritos;
import Modelo.Areadeformacao;
import Modelo.Curso;
import Modelo.Cursoformacao;
import Modelo.Localidade;
import Modelo.Mestre;
import Modelo.Pontofocal;
import Modelo.Produtos;
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
public class CursoJpaController implements Serializable {

   public CursoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Curso curso) {
        if (curso.getAccaoformacaoCollection() == null) {
            curso.setAccaoformacaoCollection(new ArrayList<Accaoformacao>());
        }
        if (curso.getCursoformacaoCollection() == null) {
            curso.setCursoformacaoCollection(new ArrayList<Cursoformacao>());
        }
        if (curso.getProdutosCollection() == null) {
            curso.setProdutosCollection(new ArrayList<Produtos>());
        }
        if (curso.getInscritosCollection() == null) {
            curso.setInscritosCollection(new ArrayList<Inscritos>());
        }
        if (curso.getMestreCollection() == null) {
            curso.setMestreCollection(new ArrayList<Mestre>());
        }
        if (curso.getAreadeformacaoCollection() == null) {
            curso.setAreadeformacaoCollection(new ArrayList<Areadeformacao>());
        }
        if (curso.getAreadeformacaoCollection1() == null) {
            curso.setAreadeformacaoCollection1(new ArrayList<Areadeformacao>());
        }
        if (curso.getAreadeformacaoCollection2() == null) {
            curso.setAreadeformacaoCollection2(new ArrayList<Areadeformacao>());
        }
        if (curso.getFormandoCollection() == null) {
            curso.setFormandoCollection(new ArrayList<Formando>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Accaoformacao> attachedAccaoformacaoCollection = new ArrayList<Accaoformacao>();
            for (Accaoformacao accaoformacaoCollectionAccaoformacaoToAttach : curso.getAccaoformacaoCollection()) {
                accaoformacaoCollectionAccaoformacaoToAttach = em.getReference(accaoformacaoCollectionAccaoformacaoToAttach.getClass(), accaoformacaoCollectionAccaoformacaoToAttach.getIdformando());
                attachedAccaoformacaoCollection.add(accaoformacaoCollectionAccaoformacaoToAttach);
            }
            curso.setAccaoformacaoCollection(attachedAccaoformacaoCollection);
            Collection<Cursoformacao> attachedCursoformacaoCollection = new ArrayList<Cursoformacao>();
            for (Cursoformacao cursoformacaoCollectionCursoformacaoToAttach : curso.getCursoformacaoCollection()) {
                cursoformacaoCollectionCursoformacaoToAttach = em.getReference(cursoformacaoCollectionCursoformacaoToAttach.getClass(), cursoformacaoCollectionCursoformacaoToAttach.getIdcursoformacao());
                attachedCursoformacaoCollection.add(cursoformacaoCollectionCursoformacaoToAttach);
            }
            curso.setCursoformacaoCollection(attachedCursoformacaoCollection);
            Collection<Produtos> attachedProdutosCollection = new ArrayList<Produtos>();
            for (Produtos produtosCollectionProdutosToAttach : curso.getProdutosCollection()) {
                produtosCollectionProdutosToAttach = em.getReference(produtosCollectionProdutosToAttach.getClass(), produtosCollectionProdutosToAttach.getIdproduto());
                attachedProdutosCollection.add(produtosCollectionProdutosToAttach);
            }
            curso.setProdutosCollection(attachedProdutosCollection);
            Collection<Inscritos> attachedInscritosCollection = new ArrayList<Inscritos>();
            for (Inscritos inscritosCollectionInscritosToAttach : curso.getInscritosCollection()) {
                inscritosCollectionInscritosToAttach = em.getReference(inscritosCollectionInscritosToAttach.getClass(), inscritosCollectionInscritosToAttach.getIdinscritos());
                attachedInscritosCollection.add(inscritosCollectionInscritosToAttach);
            }
            curso.setInscritosCollection(attachedInscritosCollection);
            Collection<Mestre> attachedMestreCollection = new ArrayList<Mestre>();
            for (Mestre mestreCollectionMestreToAttach : curso.getMestreCollection()) {
                mestreCollectionMestreToAttach = em.getReference(mestreCollectionMestreToAttach.getClass(), mestreCollectionMestreToAttach.getIdmestre());
                attachedMestreCollection.add(mestreCollectionMestreToAttach);
            }
            curso.setMestreCollection(attachedMestreCollection);
            Collection<Areadeformacao> attachedAreadeformacaoCollection = new ArrayList<Areadeformacao>();
            for (Areadeformacao areadeformacaoCollectionAreadeformacaoToAttach : curso.getAreadeformacaoCollection()) {
                areadeformacaoCollectionAreadeformacaoToAttach = em.getReference(areadeformacaoCollectionAreadeformacaoToAttach.getClass(), areadeformacaoCollectionAreadeformacaoToAttach.getIdformando());
                attachedAreadeformacaoCollection.add(areadeformacaoCollectionAreadeformacaoToAttach);
            }
            curso.setAreadeformacaoCollection(attachedAreadeformacaoCollection);
            Collection<Areadeformacao> attachedAreadeformacaoCollection1 = new ArrayList<Areadeformacao>();
            for (Areadeformacao areadeformacaoCollection1AreadeformacaoToAttach : curso.getAreadeformacaoCollection1()) {
                areadeformacaoCollection1AreadeformacaoToAttach = em.getReference(areadeformacaoCollection1AreadeformacaoToAttach.getClass(), areadeformacaoCollection1AreadeformacaoToAttach.getIdformando());
                attachedAreadeformacaoCollection1.add(areadeformacaoCollection1AreadeformacaoToAttach);
            }
            curso.setAreadeformacaoCollection1(attachedAreadeformacaoCollection1);
            Collection<Areadeformacao> attachedAreadeformacaoCollection2 = new ArrayList<Areadeformacao>();
            for (Areadeformacao areadeformacaoCollection2AreadeformacaoToAttach : curso.getAreadeformacaoCollection2()) {
                areadeformacaoCollection2AreadeformacaoToAttach = em.getReference(areadeformacaoCollection2AreadeformacaoToAttach.getClass(), areadeformacaoCollection2AreadeformacaoToAttach.getIdformando());
                attachedAreadeformacaoCollection2.add(areadeformacaoCollection2AreadeformacaoToAttach);
            }
            curso.setAreadeformacaoCollection2(attachedAreadeformacaoCollection2);
            Collection<Formando> attachedFormandoCollection = new ArrayList<Formando>();
            for (Formando formandoCollectionFormandoToAttach : curso.getFormandoCollection()) {
                formandoCollectionFormandoToAttach = em.getReference(formandoCollectionFormandoToAttach.getClass(), formandoCollectionFormandoToAttach.getIdformando());
                attachedFormandoCollection.add(formandoCollectionFormandoToAttach);
            }
            curso.setFormandoCollection(attachedFormandoCollection);
            em.persist(curso);
            for (Accaoformacao accaoformacaoCollectionAccaoformacao : curso.getAccaoformacaoCollection()) {
                Curso oldIdcursoOfAccaoformacaoCollectionAccaoformacao = accaoformacaoCollectionAccaoformacao.getIdcurso();
                accaoformacaoCollectionAccaoformacao.setIdcurso(curso);
                accaoformacaoCollectionAccaoformacao = em.merge(accaoformacaoCollectionAccaoformacao);
                if (oldIdcursoOfAccaoformacaoCollectionAccaoformacao != null) {
                    oldIdcursoOfAccaoformacaoCollectionAccaoformacao.getAccaoformacaoCollection().remove(accaoformacaoCollectionAccaoformacao);
                    oldIdcursoOfAccaoformacaoCollectionAccaoformacao = em.merge(oldIdcursoOfAccaoformacaoCollectionAccaoformacao);
                }
            }
            for (Cursoformacao cursoformacaoCollectionCursoformacao : curso.getCursoformacaoCollection()) {
                Curso oldIdcursoOfCursoformacaoCollectionCursoformacao = cursoformacaoCollectionCursoformacao.getIdcurso();
                cursoformacaoCollectionCursoformacao.setIdcurso(curso);
                cursoformacaoCollectionCursoformacao = em.merge(cursoformacaoCollectionCursoformacao);
                if (oldIdcursoOfCursoformacaoCollectionCursoformacao != null) {
                    oldIdcursoOfCursoformacaoCollectionCursoformacao.getCursoformacaoCollection().remove(cursoformacaoCollectionCursoformacao);
                    oldIdcursoOfCursoformacaoCollectionCursoformacao = em.merge(oldIdcursoOfCursoformacaoCollectionCursoformacao);
                }
            }
            for (Produtos produtosCollectionProdutos : curso.getProdutosCollection()) {
                Curso oldIdcursoOfProdutosCollectionProdutos = produtosCollectionProdutos.getIdcurso();
                produtosCollectionProdutos.setIdcurso(curso);
                produtosCollectionProdutos = em.merge(produtosCollectionProdutos);
                if (oldIdcursoOfProdutosCollectionProdutos != null) {
                    oldIdcursoOfProdutosCollectionProdutos.getProdutosCollection().remove(produtosCollectionProdutos);
                    oldIdcursoOfProdutosCollectionProdutos = em.merge(oldIdcursoOfProdutosCollectionProdutos);
                }
            }
            for (Inscritos inscritosCollectionInscritos : curso.getInscritosCollection()) {
                Curso oldIdcursoOfInscritosCollectionInscritos = inscritosCollectionInscritos.getIdcurso();
                inscritosCollectionInscritos.setIdcurso(curso);
                inscritosCollectionInscritos = em.merge(inscritosCollectionInscritos);
                if (oldIdcursoOfInscritosCollectionInscritos != null) {
                    oldIdcursoOfInscritosCollectionInscritos.getInscritosCollection().remove(inscritosCollectionInscritos);
                    oldIdcursoOfInscritosCollectionInscritos = em.merge(oldIdcursoOfInscritosCollectionInscritos);
                }
            }
            for (Mestre mestreCollectionMestre : curso.getMestreCollection()) {
                Curso oldIdcursoOfMestreCollectionMestre = mestreCollectionMestre.getIdcurso();
                mestreCollectionMestre.setIdcurso(curso);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
                if (oldIdcursoOfMestreCollectionMestre != null) {
                    oldIdcursoOfMestreCollectionMestre.getMestreCollection().remove(mestreCollectionMestre);
                    oldIdcursoOfMestreCollectionMestre = em.merge(oldIdcursoOfMestreCollectionMestre);
                }
            }
            for (Areadeformacao areadeformacaoCollectionAreadeformacao : curso.getAreadeformacaoCollection()) {
                Curso oldIdcurso3OfAreadeformacaoCollectionAreadeformacao = areadeformacaoCollectionAreadeformacao.getIdcurso3();
                areadeformacaoCollectionAreadeformacao.setIdcurso3(curso);
                areadeformacaoCollectionAreadeformacao = em.merge(areadeformacaoCollectionAreadeformacao);
                if (oldIdcurso3OfAreadeformacaoCollectionAreadeformacao != null) {
                    oldIdcurso3OfAreadeformacaoCollectionAreadeformacao.getAreadeformacaoCollection().remove(areadeformacaoCollectionAreadeformacao);
                    oldIdcurso3OfAreadeformacaoCollectionAreadeformacao = em.merge(oldIdcurso3OfAreadeformacaoCollectionAreadeformacao);
                }
            }
            for (Areadeformacao areadeformacaoCollection1Areadeformacao : curso.getAreadeformacaoCollection1()) {
                Curso oldIdcurso2OfAreadeformacaoCollection1Areadeformacao = areadeformacaoCollection1Areadeformacao.getIdcurso2();
                areadeformacaoCollection1Areadeformacao.setIdcurso2(curso);
                areadeformacaoCollection1Areadeformacao = em.merge(areadeformacaoCollection1Areadeformacao);
                if (oldIdcurso2OfAreadeformacaoCollection1Areadeformacao != null) {
                    oldIdcurso2OfAreadeformacaoCollection1Areadeformacao.getAreadeformacaoCollection1().remove(areadeformacaoCollection1Areadeformacao);
                    oldIdcurso2OfAreadeformacaoCollection1Areadeformacao = em.merge(oldIdcurso2OfAreadeformacaoCollection1Areadeformacao);
                }
            }
            for (Areadeformacao areadeformacaoCollection2Areadeformacao : curso.getAreadeformacaoCollection2()) {
                Curso oldIdcurso1OfAreadeformacaoCollection2Areadeformacao = areadeformacaoCollection2Areadeformacao.getIdcurso1();
                areadeformacaoCollection2Areadeformacao.setIdcurso1(curso);
                areadeformacaoCollection2Areadeformacao = em.merge(areadeformacaoCollection2Areadeformacao);
                if (oldIdcurso1OfAreadeformacaoCollection2Areadeformacao != null) {
                    oldIdcurso1OfAreadeformacaoCollection2Areadeformacao.getAreadeformacaoCollection2().remove(areadeformacaoCollection2Areadeformacao);
                    oldIdcurso1OfAreadeformacaoCollection2Areadeformacao = em.merge(oldIdcurso1OfAreadeformacaoCollection2Areadeformacao);
                }
            }
            for (Formando formandoCollectionFormando : curso.getFormandoCollection()) {
                Curso oldIdcursoOfFormandoCollectionFormando = formandoCollectionFormando.getIdcurso();
                formandoCollectionFormando.setIdcurso(curso);
                formandoCollectionFormando = em.merge(formandoCollectionFormando);
                if (oldIdcursoOfFormandoCollectionFormando != null) {
                    oldIdcursoOfFormandoCollectionFormando.getFormandoCollection().remove(formandoCollectionFormando);
                    oldIdcursoOfFormandoCollectionFormando = em.merge(oldIdcursoOfFormandoCollectionFormando);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getIdcurso());
            Collection<Accaoformacao> accaoformacaoCollectionOld = persistentCurso.getAccaoformacaoCollection();
            Collection<Accaoformacao> accaoformacaoCollectionNew = curso.getAccaoformacaoCollection();
            Collection<Cursoformacao> cursoformacaoCollectionOld = persistentCurso.getCursoformacaoCollection();
            Collection<Cursoformacao> cursoformacaoCollectionNew = curso.getCursoformacaoCollection();
            Collection<Produtos> produtosCollectionOld = persistentCurso.getProdutosCollection();
            Collection<Produtos> produtosCollectionNew = curso.getProdutosCollection();
            Collection<Inscritos> inscritosCollectionOld = persistentCurso.getInscritosCollection();
            Collection<Inscritos> inscritosCollectionNew = curso.getInscritosCollection();
            Collection<Mestre> mestreCollectionOld = persistentCurso.getMestreCollection();
            Collection<Mestre> mestreCollectionNew = curso.getMestreCollection();
            Collection<Areadeformacao> areadeformacaoCollectionOld = persistentCurso.getAreadeformacaoCollection();
            Collection<Areadeformacao> areadeformacaoCollectionNew = curso.getAreadeformacaoCollection();
            Collection<Areadeformacao> areadeformacaoCollection1Old = persistentCurso.getAreadeformacaoCollection1();
            Collection<Areadeformacao> areadeformacaoCollection1New = curso.getAreadeformacaoCollection1();
            Collection<Areadeformacao> areadeformacaoCollection2Old = persistentCurso.getAreadeformacaoCollection2();
            Collection<Areadeformacao> areadeformacaoCollection2New = curso.getAreadeformacaoCollection2();
            Collection<Formando> formandoCollectionOld = persistentCurso.getFormandoCollection();
            Collection<Formando> formandoCollectionNew = curso.getFormandoCollection();
            List<String> illegalOrphanMessages = null;
            for (Formando formandoCollectionOldFormando : formandoCollectionOld) {
                if (!formandoCollectionNew.contains(formandoCollectionOldFormando)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Formando " + formandoCollectionOldFormando + " since its idcurso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Accaoformacao> attachedAccaoformacaoCollectionNew = new ArrayList<Accaoformacao>();
            for (Accaoformacao accaoformacaoCollectionNewAccaoformacaoToAttach : accaoformacaoCollectionNew) {
                accaoformacaoCollectionNewAccaoformacaoToAttach = em.getReference(accaoformacaoCollectionNewAccaoformacaoToAttach.getClass(), accaoformacaoCollectionNewAccaoformacaoToAttach.getIdformando());
                attachedAccaoformacaoCollectionNew.add(accaoformacaoCollectionNewAccaoformacaoToAttach);
            }
            accaoformacaoCollectionNew = attachedAccaoformacaoCollectionNew;
            curso.setAccaoformacaoCollection(accaoformacaoCollectionNew);
            Collection<Cursoformacao> attachedCursoformacaoCollectionNew = new ArrayList<Cursoformacao>();
            for (Cursoformacao cursoformacaoCollectionNewCursoformacaoToAttach : cursoformacaoCollectionNew) {
                cursoformacaoCollectionNewCursoformacaoToAttach = em.getReference(cursoformacaoCollectionNewCursoformacaoToAttach.getClass(), cursoformacaoCollectionNewCursoformacaoToAttach.getIdcursoformacao());
                attachedCursoformacaoCollectionNew.add(cursoformacaoCollectionNewCursoformacaoToAttach);
            }
            cursoformacaoCollectionNew = attachedCursoformacaoCollectionNew;
            curso.setCursoformacaoCollection(cursoformacaoCollectionNew);
            Collection<Produtos> attachedProdutosCollectionNew = new ArrayList<Produtos>();
            for (Produtos produtosCollectionNewProdutosToAttach : produtosCollectionNew) {
                produtosCollectionNewProdutosToAttach = em.getReference(produtosCollectionNewProdutosToAttach.getClass(), produtosCollectionNewProdutosToAttach.getIdproduto());
                attachedProdutosCollectionNew.add(produtosCollectionNewProdutosToAttach);
            }
            produtosCollectionNew = attachedProdutosCollectionNew;
            curso.setProdutosCollection(produtosCollectionNew);
            Collection<Inscritos> attachedInscritosCollectionNew = new ArrayList<Inscritos>();
            for (Inscritos inscritosCollectionNewInscritosToAttach : inscritosCollectionNew) {
                inscritosCollectionNewInscritosToAttach = em.getReference(inscritosCollectionNewInscritosToAttach.getClass(), inscritosCollectionNewInscritosToAttach.getIdinscritos());
                attachedInscritosCollectionNew.add(inscritosCollectionNewInscritosToAttach);
            }
            inscritosCollectionNew = attachedInscritosCollectionNew;
            curso.setInscritosCollection(inscritosCollectionNew);
            Collection<Mestre> attachedMestreCollectionNew = new ArrayList<Mestre>();
            for (Mestre mestreCollectionNewMestreToAttach : mestreCollectionNew) {
                mestreCollectionNewMestreToAttach = em.getReference(mestreCollectionNewMestreToAttach.getClass(), mestreCollectionNewMestreToAttach.getIdmestre());
                attachedMestreCollectionNew.add(mestreCollectionNewMestreToAttach);
            }
            mestreCollectionNew = attachedMestreCollectionNew;
            curso.setMestreCollection(mestreCollectionNew);
            Collection<Areadeformacao> attachedAreadeformacaoCollectionNew = new ArrayList<Areadeformacao>();
            for (Areadeformacao areadeformacaoCollectionNewAreadeformacaoToAttach : areadeformacaoCollectionNew) {
                areadeformacaoCollectionNewAreadeformacaoToAttach = em.getReference(areadeformacaoCollectionNewAreadeformacaoToAttach.getClass(), areadeformacaoCollectionNewAreadeformacaoToAttach.getIdformando());
                attachedAreadeformacaoCollectionNew.add(areadeformacaoCollectionNewAreadeformacaoToAttach);
            }
            areadeformacaoCollectionNew = attachedAreadeformacaoCollectionNew;
            curso.setAreadeformacaoCollection(areadeformacaoCollectionNew);
            Collection<Areadeformacao> attachedAreadeformacaoCollection1New = new ArrayList<Areadeformacao>();
            for (Areadeformacao areadeformacaoCollection1NewAreadeformacaoToAttach : areadeformacaoCollection1New) {
                areadeformacaoCollection1NewAreadeformacaoToAttach = em.getReference(areadeformacaoCollection1NewAreadeformacaoToAttach.getClass(), areadeformacaoCollection1NewAreadeformacaoToAttach.getIdformando());
                attachedAreadeformacaoCollection1New.add(areadeformacaoCollection1NewAreadeformacaoToAttach);
            }
            areadeformacaoCollection1New = attachedAreadeformacaoCollection1New;
            curso.setAreadeformacaoCollection1(areadeformacaoCollection1New);
            Collection<Areadeformacao> attachedAreadeformacaoCollection2New = new ArrayList<Areadeformacao>();
            for (Areadeformacao areadeformacaoCollection2NewAreadeformacaoToAttach : areadeformacaoCollection2New) {
                areadeformacaoCollection2NewAreadeformacaoToAttach = em.getReference(areadeformacaoCollection2NewAreadeformacaoToAttach.getClass(), areadeformacaoCollection2NewAreadeformacaoToAttach.getIdformando());
                attachedAreadeformacaoCollection2New.add(areadeformacaoCollection2NewAreadeformacaoToAttach);
            }
            areadeformacaoCollection2New = attachedAreadeformacaoCollection2New;
            curso.setAreadeformacaoCollection2(areadeformacaoCollection2New);
            Collection<Formando> attachedFormandoCollectionNew = new ArrayList<Formando>();
            for (Formando formandoCollectionNewFormandoToAttach : formandoCollectionNew) {
                formandoCollectionNewFormandoToAttach = em.getReference(formandoCollectionNewFormandoToAttach.getClass(), formandoCollectionNewFormandoToAttach.getIdformando());
                attachedFormandoCollectionNew.add(formandoCollectionNewFormandoToAttach);
            }
            formandoCollectionNew = attachedFormandoCollectionNew;
            curso.setFormandoCollection(formandoCollectionNew);
            curso = em.merge(curso);
            for (Accaoformacao accaoformacaoCollectionOldAccaoformacao : accaoformacaoCollectionOld) {
                if (!accaoformacaoCollectionNew.contains(accaoformacaoCollectionOldAccaoformacao)) {
                    accaoformacaoCollectionOldAccaoformacao.setIdcurso(null);
                    accaoformacaoCollectionOldAccaoformacao = em.merge(accaoformacaoCollectionOldAccaoformacao);
                }
            }
            for (Accaoformacao accaoformacaoCollectionNewAccaoformacao : accaoformacaoCollectionNew) {
                if (!accaoformacaoCollectionOld.contains(accaoformacaoCollectionNewAccaoformacao)) {
                    Curso oldIdcursoOfAccaoformacaoCollectionNewAccaoformacao = accaoformacaoCollectionNewAccaoformacao.getIdcurso();
                    accaoformacaoCollectionNewAccaoformacao.setIdcurso(curso);
                    accaoformacaoCollectionNewAccaoformacao = em.merge(accaoformacaoCollectionNewAccaoformacao);
                    if (oldIdcursoOfAccaoformacaoCollectionNewAccaoformacao != null && !oldIdcursoOfAccaoformacaoCollectionNewAccaoformacao.equals(curso)) {
                        oldIdcursoOfAccaoformacaoCollectionNewAccaoformacao.getAccaoformacaoCollection().remove(accaoformacaoCollectionNewAccaoformacao);
                        oldIdcursoOfAccaoformacaoCollectionNewAccaoformacao = em.merge(oldIdcursoOfAccaoformacaoCollectionNewAccaoformacao);
                    }
                }
            }
            for (Cursoformacao cursoformacaoCollectionOldCursoformacao : cursoformacaoCollectionOld) {
                if (!cursoformacaoCollectionNew.contains(cursoformacaoCollectionOldCursoformacao)) {
                    cursoformacaoCollectionOldCursoformacao.setIdcurso(null);
                    cursoformacaoCollectionOldCursoformacao = em.merge(cursoformacaoCollectionOldCursoformacao);
                }
            }
            for (Cursoformacao cursoformacaoCollectionNewCursoformacao : cursoformacaoCollectionNew) {
                if (!cursoformacaoCollectionOld.contains(cursoformacaoCollectionNewCursoformacao)) {
                    Curso oldIdcursoOfCursoformacaoCollectionNewCursoformacao = cursoformacaoCollectionNewCursoformacao.getIdcurso();
                    cursoformacaoCollectionNewCursoformacao.setIdcurso(curso);
                    cursoformacaoCollectionNewCursoformacao = em.merge(cursoformacaoCollectionNewCursoformacao);
                    if (oldIdcursoOfCursoformacaoCollectionNewCursoformacao != null && !oldIdcursoOfCursoformacaoCollectionNewCursoformacao.equals(curso)) {
                        oldIdcursoOfCursoformacaoCollectionNewCursoformacao.getCursoformacaoCollection().remove(cursoformacaoCollectionNewCursoformacao);
                        oldIdcursoOfCursoformacaoCollectionNewCursoformacao = em.merge(oldIdcursoOfCursoformacaoCollectionNewCursoformacao);
                    }
                }
            }
            for (Produtos produtosCollectionOldProdutos : produtosCollectionOld) {
                if (!produtosCollectionNew.contains(produtosCollectionOldProdutos)) {
                    produtosCollectionOldProdutos.setIdcurso(null);
                    produtosCollectionOldProdutos = em.merge(produtosCollectionOldProdutos);
                }
            }
            for (Produtos produtosCollectionNewProdutos : produtosCollectionNew) {
                if (!produtosCollectionOld.contains(produtosCollectionNewProdutos)) {
                    Curso oldIdcursoOfProdutosCollectionNewProdutos = produtosCollectionNewProdutos.getIdcurso();
                    produtosCollectionNewProdutos.setIdcurso(curso);
                    produtosCollectionNewProdutos = em.merge(produtosCollectionNewProdutos);
                    if (oldIdcursoOfProdutosCollectionNewProdutos != null && !oldIdcursoOfProdutosCollectionNewProdutos.equals(curso)) {
                        oldIdcursoOfProdutosCollectionNewProdutos.getProdutosCollection().remove(produtosCollectionNewProdutos);
                        oldIdcursoOfProdutosCollectionNewProdutos = em.merge(oldIdcursoOfProdutosCollectionNewProdutos);
                    }
                }
            }
            for (Inscritos inscritosCollectionOldInscritos : inscritosCollectionOld) {
                if (!inscritosCollectionNew.contains(inscritosCollectionOldInscritos)) {
                    inscritosCollectionOldInscritos.setIdcurso(null);
                    inscritosCollectionOldInscritos = em.merge(inscritosCollectionOldInscritos);
                }
            }
            for (Inscritos inscritosCollectionNewInscritos : inscritosCollectionNew) {
                if (!inscritosCollectionOld.contains(inscritosCollectionNewInscritos)) {
                    Curso oldIdcursoOfInscritosCollectionNewInscritos = inscritosCollectionNewInscritos.getIdcurso();
                    inscritosCollectionNewInscritos.setIdcurso(curso);
                    inscritosCollectionNewInscritos = em.merge(inscritosCollectionNewInscritos);
                    if (oldIdcursoOfInscritosCollectionNewInscritos != null && !oldIdcursoOfInscritosCollectionNewInscritos.equals(curso)) {
                        oldIdcursoOfInscritosCollectionNewInscritos.getInscritosCollection().remove(inscritosCollectionNewInscritos);
                        oldIdcursoOfInscritosCollectionNewInscritos = em.merge(oldIdcursoOfInscritosCollectionNewInscritos);
                    }
                }
            }
            for (Mestre mestreCollectionOldMestre : mestreCollectionOld) {
                if (!mestreCollectionNew.contains(mestreCollectionOldMestre)) {
                    mestreCollectionOldMestre.setIdcurso(null);
                    mestreCollectionOldMestre = em.merge(mestreCollectionOldMestre);
                }
            }
            for (Mestre mestreCollectionNewMestre : mestreCollectionNew) {
                if (!mestreCollectionOld.contains(mestreCollectionNewMestre)) {
                    Curso oldIdcursoOfMestreCollectionNewMestre = mestreCollectionNewMestre.getIdcurso();
                    mestreCollectionNewMestre.setIdcurso(curso);
                    mestreCollectionNewMestre = em.merge(mestreCollectionNewMestre);
                    if (oldIdcursoOfMestreCollectionNewMestre != null && !oldIdcursoOfMestreCollectionNewMestre.equals(curso)) {
                        oldIdcursoOfMestreCollectionNewMestre.getMestreCollection().remove(mestreCollectionNewMestre);
                        oldIdcursoOfMestreCollectionNewMestre = em.merge(oldIdcursoOfMestreCollectionNewMestre);
                    }
                }
            }
            for (Areadeformacao areadeformacaoCollectionOldAreadeformacao : areadeformacaoCollectionOld) {
                if (!areadeformacaoCollectionNew.contains(areadeformacaoCollectionOldAreadeformacao)) {
                    areadeformacaoCollectionOldAreadeformacao.setIdcurso3(null);
                    areadeformacaoCollectionOldAreadeformacao = em.merge(areadeformacaoCollectionOldAreadeformacao);
                }
            }
            for (Areadeformacao areadeformacaoCollectionNewAreadeformacao : areadeformacaoCollectionNew) {
                if (!areadeformacaoCollectionOld.contains(areadeformacaoCollectionNewAreadeformacao)) {
                    Curso oldIdcurso3OfAreadeformacaoCollectionNewAreadeformacao = areadeformacaoCollectionNewAreadeformacao.getIdcurso3();
                    areadeformacaoCollectionNewAreadeformacao.setIdcurso3(curso);
                    areadeformacaoCollectionNewAreadeformacao = em.merge(areadeformacaoCollectionNewAreadeformacao);
                    if (oldIdcurso3OfAreadeformacaoCollectionNewAreadeformacao != null && !oldIdcurso3OfAreadeformacaoCollectionNewAreadeformacao.equals(curso)) {
                        oldIdcurso3OfAreadeformacaoCollectionNewAreadeformacao.getAreadeformacaoCollection().remove(areadeformacaoCollectionNewAreadeformacao);
                        oldIdcurso3OfAreadeformacaoCollectionNewAreadeformacao = em.merge(oldIdcurso3OfAreadeformacaoCollectionNewAreadeformacao);
                    }
                }
            }
            for (Areadeformacao areadeformacaoCollection1OldAreadeformacao : areadeformacaoCollection1Old) {
                if (!areadeformacaoCollection1New.contains(areadeformacaoCollection1OldAreadeformacao)) {
                    areadeformacaoCollection1OldAreadeformacao.setIdcurso2(null);
                    areadeformacaoCollection1OldAreadeformacao = em.merge(areadeformacaoCollection1OldAreadeformacao);
                }
            }
            for (Areadeformacao areadeformacaoCollection1NewAreadeformacao : areadeformacaoCollection1New) {
                if (!areadeformacaoCollection1Old.contains(areadeformacaoCollection1NewAreadeformacao)) {
                    Curso oldIdcurso2OfAreadeformacaoCollection1NewAreadeformacao = areadeformacaoCollection1NewAreadeformacao.getIdcurso2();
                    areadeformacaoCollection1NewAreadeformacao.setIdcurso2(curso);
                    areadeformacaoCollection1NewAreadeformacao = em.merge(areadeformacaoCollection1NewAreadeformacao);
                    if (oldIdcurso2OfAreadeformacaoCollection1NewAreadeformacao != null && !oldIdcurso2OfAreadeformacaoCollection1NewAreadeformacao.equals(curso)) {
                        oldIdcurso2OfAreadeformacaoCollection1NewAreadeformacao.getAreadeformacaoCollection1().remove(areadeformacaoCollection1NewAreadeformacao);
                        oldIdcurso2OfAreadeformacaoCollection1NewAreadeformacao = em.merge(oldIdcurso2OfAreadeformacaoCollection1NewAreadeformacao);
                    }
                }
            }
            for (Areadeformacao areadeformacaoCollection2OldAreadeformacao : areadeformacaoCollection2Old) {
                if (!areadeformacaoCollection2New.contains(areadeformacaoCollection2OldAreadeformacao)) {
                    areadeformacaoCollection2OldAreadeformacao.setIdcurso1(null);
                    areadeformacaoCollection2OldAreadeformacao = em.merge(areadeformacaoCollection2OldAreadeformacao);
                }
            }
            for (Areadeformacao areadeformacaoCollection2NewAreadeformacao : areadeformacaoCollection2New) {
                if (!areadeformacaoCollection2Old.contains(areadeformacaoCollection2NewAreadeformacao)) {
                    Curso oldIdcurso1OfAreadeformacaoCollection2NewAreadeformacao = areadeformacaoCollection2NewAreadeformacao.getIdcurso1();
                    areadeformacaoCollection2NewAreadeformacao.setIdcurso1(curso);
                    areadeformacaoCollection2NewAreadeformacao = em.merge(areadeformacaoCollection2NewAreadeformacao);
                    if (oldIdcurso1OfAreadeformacaoCollection2NewAreadeformacao != null && !oldIdcurso1OfAreadeformacaoCollection2NewAreadeformacao.equals(curso)) {
                        oldIdcurso1OfAreadeformacaoCollection2NewAreadeformacao.getAreadeformacaoCollection2().remove(areadeformacaoCollection2NewAreadeformacao);
                        oldIdcurso1OfAreadeformacaoCollection2NewAreadeformacao = em.merge(oldIdcurso1OfAreadeformacaoCollection2NewAreadeformacao);
                    }
                }
            }
            for (Formando formandoCollectionNewFormando : formandoCollectionNew) {
                if (!formandoCollectionOld.contains(formandoCollectionNewFormando)) {
                    Curso oldIdcursoOfFormandoCollectionNewFormando = formandoCollectionNewFormando.getIdcurso();
                    formandoCollectionNewFormando.setIdcurso(curso);
                    formandoCollectionNewFormando = em.merge(formandoCollectionNewFormando);
                    if (oldIdcursoOfFormandoCollectionNewFormando != null && !oldIdcursoOfFormandoCollectionNewFormando.equals(curso)) {
                        oldIdcursoOfFormandoCollectionNewFormando.getFormandoCollection().remove(formandoCollectionNewFormando);
                        oldIdcursoOfFormandoCollectionNewFormando = em.merge(oldIdcursoOfFormandoCollectionNewFormando);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = curso.getIdcurso();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getIdcurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Formando> formandoCollectionOrphanCheck = curso.getFormandoCollection();
            for (Formando formandoCollectionOrphanCheckFormando : formandoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Formando " + formandoCollectionOrphanCheckFormando + " in its formandoCollection field has a non-nullable idcurso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Accaoformacao> accaoformacaoCollection = curso.getAccaoformacaoCollection();
            for (Accaoformacao accaoformacaoCollectionAccaoformacao : accaoformacaoCollection) {
                accaoformacaoCollectionAccaoformacao.setIdcurso(null);
                accaoformacaoCollectionAccaoformacao = em.merge(accaoformacaoCollectionAccaoformacao);
            }
            Collection<Cursoformacao> cursoformacaoCollection = curso.getCursoformacaoCollection();
            for (Cursoformacao cursoformacaoCollectionCursoformacao : cursoformacaoCollection) {
                cursoformacaoCollectionCursoformacao.setIdcurso(null);
                cursoformacaoCollectionCursoformacao = em.merge(cursoformacaoCollectionCursoformacao);
            }
            Collection<Produtos> produtosCollection = curso.getProdutosCollection();
            for (Produtos produtosCollectionProdutos : produtosCollection) {
                produtosCollectionProdutos.setIdcurso(null);
                produtosCollectionProdutos = em.merge(produtosCollectionProdutos);
            }
            Collection<Inscritos> inscritosCollection = curso.getInscritosCollection();
            for (Inscritos inscritosCollectionInscritos : inscritosCollection) {
                inscritosCollectionInscritos.setIdcurso(null);
                inscritosCollectionInscritos = em.merge(inscritosCollectionInscritos);
            }
            Collection<Mestre> mestreCollection = curso.getMestreCollection();
            for (Mestre mestreCollectionMestre : mestreCollection) {
                mestreCollectionMestre.setIdcurso(null);
                mestreCollectionMestre = em.merge(mestreCollectionMestre);
            }
            Collection<Areadeformacao> areadeformacaoCollection = curso.getAreadeformacaoCollection();
            for (Areadeformacao areadeformacaoCollectionAreadeformacao : areadeformacaoCollection) {
                areadeformacaoCollectionAreadeformacao.setIdcurso3(null);
                areadeformacaoCollectionAreadeformacao = em.merge(areadeformacaoCollectionAreadeformacao);
            }
            Collection<Areadeformacao> areadeformacaoCollection1 = curso.getAreadeformacaoCollection1();
            for (Areadeformacao areadeformacaoCollection1Areadeformacao : areadeformacaoCollection1) {
                areadeformacaoCollection1Areadeformacao.setIdcurso2(null);
                areadeformacaoCollection1Areadeformacao = em.merge(areadeformacaoCollection1Areadeformacao);
            }
            Collection<Areadeformacao> areadeformacaoCollection2 = curso.getAreadeformacaoCollection2();
            for (Areadeformacao areadeformacaoCollection2Areadeformacao : areadeformacaoCollection2) {
                areadeformacaoCollection2Areadeformacao.setIdcurso1(null);
                areadeformacaoCollection2Areadeformacao = em.merge(areadeformacaoCollection2Areadeformacao);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
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

    public Curso findCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Curso> cursolocalidade(int p){
          List<Curso> curso = new CursoJpaController().findCursoEntities();//do banco
          List<Curso> cursoosselecionados = new ArrayList<Curso>();//filtrada
         
//         for (Curso c : curso) {
//             if(c.getIdlocalidade().getIdlocalidade()!=null){
//                   if(c.getIdlocalidade().getIdlocalidade()==p){
//                  cursoosselecionados.add(c);
//            }
//             }
//            
//        }
               
        return cursoosselecionados;
    }
}
