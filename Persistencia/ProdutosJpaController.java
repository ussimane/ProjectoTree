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
import Modelo.Produtos;
import Modelo.Artigofinal;
import java.util.ArrayList;
import java.util.List;
import Modelo.Artigo;
import Modelo.Curso;
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
public class ProdutosJpaController implements Serializable {

   
    public ProdutosJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Produtos produtos) {
        if (produtos.getArtigofinalCollection() == null) {
            produtos.setArtigofinalCollection(new ArrayList<Artigofinal>());
        }
        if (produtos.getArtigoCollection() == null) {
            produtos.setArtigoCollection(new ArrayList<Artigo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso idcurso = produtos.getIdcurso();
            if (idcurso != null) {
                idcurso = em.getReference(idcurso.getClass(), idcurso.getIdcurso());
                produtos.setIdcurso(idcurso);
            }
            Collection<Artigofinal> attachedArtigofinalCollection = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionArtigofinalToAttach : produtos.getArtigofinalCollection()) {
                artigofinalCollectionArtigofinalToAttach = em.getReference(artigofinalCollectionArtigofinalToAttach.getClass(), artigofinalCollectionArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollection.add(artigofinalCollectionArtigofinalToAttach);
            }
            produtos.setArtigofinalCollection(attachedArtigofinalCollection);
            Collection<Artigo> attachedArtigoCollection = new ArrayList<Artigo>();
            for (Artigo artigoCollectionArtigoToAttach : produtos.getArtigoCollection()) {
                artigoCollectionArtigoToAttach = em.getReference(artigoCollectionArtigoToAttach.getClass(), artigoCollectionArtigoToAttach.getArtigoPK());
                attachedArtigoCollection.add(artigoCollectionArtigoToAttach);
            }
            produtos.setArtigoCollection(attachedArtigoCollection);
            em.persist(produtos);
            if (idcurso != null) {
                idcurso.getProdutosCollection().add(produtos);
                idcurso = em.merge(idcurso);
            }
            for (Artigofinal artigofinalCollectionArtigofinal : produtos.getArtigofinalCollection()) {
                Produtos oldProdutosOfArtigofinalCollectionArtigofinal = artigofinalCollectionArtigofinal.getProdutos();
                artigofinalCollectionArtigofinal.setProdutos(produtos);
                artigofinalCollectionArtigofinal = em.merge(artigofinalCollectionArtigofinal);
                if (oldProdutosOfArtigofinalCollectionArtigofinal != null) {
                    oldProdutosOfArtigofinalCollectionArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionArtigofinal);
                    oldProdutosOfArtigofinalCollectionArtigofinal = em.merge(oldProdutosOfArtigofinalCollectionArtigofinal);
                }
            }
            for (Artigo artigoCollectionArtigo : produtos.getArtigoCollection()) {
                Produtos oldProdutosOfArtigoCollectionArtigo = artigoCollectionArtigo.getProdutos();
                artigoCollectionArtigo.setProdutos(produtos);
                artigoCollectionArtigo = em.merge(artigoCollectionArtigo);
                if (oldProdutosOfArtigoCollectionArtigo != null) {
                    oldProdutosOfArtigoCollectionArtigo.getArtigoCollection().remove(artigoCollectionArtigo);
                    oldProdutosOfArtigoCollectionArtigo = em.merge(oldProdutosOfArtigoCollectionArtigo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produtos produtos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produtos persistentProdutos = em.find(Produtos.class, produtos.getIdproduto());
            Curso idcursoOld = persistentProdutos.getIdcurso();
            Curso idcursoNew = produtos.getIdcurso();
            Collection<Artigofinal> artigofinalCollectionOld = persistentProdutos.getArtigofinalCollection();
            Collection<Artigofinal> artigofinalCollectionNew = produtos.getArtigofinalCollection();
            Collection<Artigo> artigoCollectionOld = persistentProdutos.getArtigoCollection();
            Collection<Artigo> artigoCollectionNew = produtos.getArtigoCollection();
            List<String> illegalOrphanMessages = null;
            for (Artigofinal artigofinalCollectionOldArtigofinal : artigofinalCollectionOld) {
                if (!artigofinalCollectionNew.contains(artigofinalCollectionOldArtigofinal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artigofinal " + artigofinalCollectionOldArtigofinal + " since its produtos field is not nullable.");
                }
            }
            for (Artigo artigoCollectionOldArtigo : artigoCollectionOld) {
                if (!artigoCollectionNew.contains(artigoCollectionOldArtigo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artigo " + artigoCollectionOldArtigo + " since its produtos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcursoNew != null) {
                idcursoNew = em.getReference(idcursoNew.getClass(), idcursoNew.getIdcurso());
                produtos.setIdcurso(idcursoNew);
            }
            Collection<Artigofinal> attachedArtigofinalCollectionNew = new ArrayList<Artigofinal>();
            for (Artigofinal artigofinalCollectionNewArtigofinalToAttach : artigofinalCollectionNew) {
                artigofinalCollectionNewArtigofinalToAttach = em.getReference(artigofinalCollectionNewArtigofinalToAttach.getClass(), artigofinalCollectionNewArtigofinalToAttach.getArtigofinalPK());
                attachedArtigofinalCollectionNew.add(artigofinalCollectionNewArtigofinalToAttach);
            }
            artigofinalCollectionNew = attachedArtigofinalCollectionNew;
            produtos.setArtigofinalCollection(artigofinalCollectionNew);
            Collection<Artigo> attachedArtigoCollectionNew = new ArrayList<Artigo>();
            for (Artigo artigoCollectionNewArtigoToAttach : artigoCollectionNew) {
                artigoCollectionNewArtigoToAttach = em.getReference(artigoCollectionNewArtigoToAttach.getClass(), artigoCollectionNewArtigoToAttach.getArtigoPK());
                attachedArtigoCollectionNew.add(artigoCollectionNewArtigoToAttach);
            }
            artigoCollectionNew = attachedArtigoCollectionNew;
            produtos.setArtigoCollection(artigoCollectionNew);
            produtos = em.merge(produtos);
            if (idcursoOld != null && !idcursoOld.equals(idcursoNew)) {
                idcursoOld.getProdutosCollection().remove(produtos);
                idcursoOld = em.merge(idcursoOld);
            }
            if (idcursoNew != null && !idcursoNew.equals(idcursoOld)) {
                idcursoNew.getProdutosCollection().add(produtos);
                idcursoNew = em.merge(idcursoNew);
            }
            for (Artigofinal artigofinalCollectionNewArtigofinal : artigofinalCollectionNew) {
                if (!artigofinalCollectionOld.contains(artigofinalCollectionNewArtigofinal)) {
                    Produtos oldProdutosOfArtigofinalCollectionNewArtigofinal = artigofinalCollectionNewArtigofinal.getProdutos();
                    artigofinalCollectionNewArtigofinal.setProdutos(produtos);
                    artigofinalCollectionNewArtigofinal = em.merge(artigofinalCollectionNewArtigofinal);
                    if (oldProdutosOfArtigofinalCollectionNewArtigofinal != null && !oldProdutosOfArtigofinalCollectionNewArtigofinal.equals(produtos)) {
                        oldProdutosOfArtigofinalCollectionNewArtigofinal.getArtigofinalCollection().remove(artigofinalCollectionNewArtigofinal);
                        oldProdutosOfArtigofinalCollectionNewArtigofinal = em.merge(oldProdutosOfArtigofinalCollectionNewArtigofinal);
                    }
                }
            }
            for (Artigo artigoCollectionNewArtigo : artigoCollectionNew) {
                if (!artigoCollectionOld.contains(artigoCollectionNewArtigo)) {
                    Produtos oldProdutosOfArtigoCollectionNewArtigo = artigoCollectionNewArtigo.getProdutos();
                    artigoCollectionNewArtigo.setProdutos(produtos);
                    artigoCollectionNewArtigo = em.merge(artigoCollectionNewArtigo);
                    if (oldProdutosOfArtigoCollectionNewArtigo != null && !oldProdutosOfArtigoCollectionNewArtigo.equals(produtos)) {
                        oldProdutosOfArtigoCollectionNewArtigo.getArtigoCollection().remove(artigoCollectionNewArtigo);
                        oldProdutosOfArtigoCollectionNewArtigo = em.merge(oldProdutosOfArtigoCollectionNewArtigo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produtos.getIdproduto();
                if (findProdutos(id) == null) {
                    throw new NonexistentEntityException("The produtos with id " + id + " no longer exists.");
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
            Produtos produtos;
            try {
                produtos = em.getReference(Produtos.class, id);
                produtos.getIdproduto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produtos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Artigofinal> artigofinalCollectionOrphanCheck = produtos.getArtigofinalCollection();
            for (Artigofinal artigofinalCollectionOrphanCheckArtigofinal : artigofinalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produtos (" + produtos + ") cannot be destroyed since the Artigofinal " + artigofinalCollectionOrphanCheckArtigofinal + " in its artigofinalCollection field has a non-nullable produtos field.");
            }
            Collection<Artigo> artigoCollectionOrphanCheck = produtos.getArtigoCollection();
            for (Artigo artigoCollectionOrphanCheckArtigo : artigoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produtos (" + produtos + ") cannot be destroyed since the Artigo " + artigoCollectionOrphanCheckArtigo + " in its artigoCollection field has a non-nullable produtos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Curso idcurso = produtos.getIdcurso();
            if (idcurso != null) {
                idcurso.getProdutosCollection().remove(produtos);
                idcurso = em.merge(idcurso);
            }
            em.remove(produtos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produtos> findProdutosEntities() {
        return findProdutosEntities(true, -1, -1);
    }

    public List<Produtos> findProdutosEntities(int maxResults, int firstResult) {
        return findProdutosEntities(false, maxResults, firstResult);
    }

    private List<Produtos> findProdutosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produtos.class));
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

    public Produtos findProdutos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produtos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produtos> rt = cq.from(Produtos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Produtos> getcurso(int p){
          List<Produtos> formandos = new ProdutosJpaController().findProdutosEntities();//do banco
          List<Produtos> formandosselecionados = new ArrayList<Produtos>();//filtrada
         
         for (Produtos f : formandos) {
             if(f.getIdcurso().getIdcurso()!=null){
                   if(f.getIdcurso().getIdcurso()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
    
}
