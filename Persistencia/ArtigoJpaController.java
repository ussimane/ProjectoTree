/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Artigo;
import Modelo.ArtigoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Formacaoturma;
import Modelo.Formando;
import Modelo.Mes;
import Modelo.Produtos;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class ArtigoJpaController implements Serializable {

    public ArtigoJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Artigo artigo) throws PreexistingEntityException, Exception {
        if (artigo.getArtigoPK() == null) {
            artigo.setArtigoPK(new ArtigoPK());
        }
        artigo.getArtigoPK().setIdformando(artigo.getFormacaoturma().getIdformando());
        artigo.getArtigoPK().setIdproduto(artigo.getProdutos().getIdproduto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mes idmes = artigo.getIdmes();
            if (idmes != null) {
                idmes = em.getReference(idmes.getClass(), idmes.getIdmes());
                artigo.setIdmes(idmes);
            }
            Produtos produtos = artigo.getProdutos();
            if (produtos != null) {
                produtos = em.getReference(produtos.getClass(), produtos.getIdproduto());
                artigo.setProdutos(produtos);
            }
            Formacaoturma formacaoturma = artigo.getFormacaoturma();
            if (formacaoturma != null) {
                formacaoturma = em.getReference(formacaoturma.getClass(), formacaoturma.getIdformando());
                artigo.setFormacaoturma(formacaoturma);
            }
            em.persist(artigo);
            if (idmes != null) {
                idmes.getArtigoCollection().add(artigo);
                idmes = em.merge(idmes);
            }
            if (produtos != null) {
                produtos.getArtigoCollection().add(artigo);
                produtos = em.merge(produtos);
            }
            if (formacaoturma != null) {
                formacaoturma.getArtigoCollection().add(artigo);
                formacaoturma = em.merge(formacaoturma);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtigo(artigo.getArtigoPK()) != null) {
                throw new PreexistingEntityException("Artigo " + artigo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artigo artigo) throws NonexistentEntityException, Exception {
        artigo.getArtigoPK().setIdformando(artigo.getFormacaoturma().getIdformando());
        artigo.getArtigoPK().setIdproduto(artigo.getProdutos().getIdproduto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artigo persistentArtigo = em.find(Artigo.class, artigo.getArtigoPK());
            Mes idmesOld = persistentArtigo.getIdmes();
            Mes idmesNew = artigo.getIdmes();
            Produtos produtosOld = persistentArtigo.getProdutos();
            Produtos produtosNew = artigo.getProdutos();
            Formacaoturma formacaoturmaOld = persistentArtigo.getFormacaoturma();
            Formacaoturma formacaoturmaNew = artigo.getFormacaoturma();
            if (idmesNew != null) {
                idmesNew = em.getReference(idmesNew.getClass(), idmesNew.getIdmes());
                artigo.setIdmes(idmesNew);
            }
            if (produtosNew != null) {
                produtosNew = em.getReference(produtosNew.getClass(), produtosNew.getIdproduto());
                artigo.setProdutos(produtosNew);
            }
            if (formacaoturmaNew != null) {
                formacaoturmaNew = em.getReference(formacaoturmaNew.getClass(), formacaoturmaNew.getIdformando());
                artigo.setFormacaoturma(formacaoturmaNew);
            }
            artigo = em.merge(artigo);
            if (idmesOld != null && !idmesOld.equals(idmesNew)) {
                idmesOld.getArtigoCollection().remove(artigo);
                idmesOld = em.merge(idmesOld);
            }
            if (idmesNew != null && !idmesNew.equals(idmesOld)) {
                idmesNew.getArtigoCollection().add(artigo);
                idmesNew = em.merge(idmesNew);
            }
            if (produtosOld != null && !produtosOld.equals(produtosNew)) {
                produtosOld.getArtigoCollection().remove(artigo);
                produtosOld = em.merge(produtosOld);
            }
            if (produtosNew != null && !produtosNew.equals(produtosOld)) {
                produtosNew.getArtigoCollection().add(artigo);
                produtosNew = em.merge(produtosNew);
            }
            if (formacaoturmaOld != null && !formacaoturmaOld.equals(formacaoturmaNew)) {
                formacaoturmaOld.getArtigoCollection().remove(artigo);
                formacaoturmaOld = em.merge(formacaoturmaOld);
            }
            if (formacaoturmaNew != null && !formacaoturmaNew.equals(formacaoturmaOld)) {
                formacaoturmaNew.getArtigoCollection().add(artigo);
                formacaoturmaNew = em.merge(formacaoturmaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ArtigoPK id = artigo.getArtigoPK();
                if (findArtigo(id) == null) {
                    throw new NonexistentEntityException("The artigo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ArtigoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artigo artigo;
            try {
                artigo = em.getReference(Artigo.class, id);
                artigo.getArtigoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artigo with id " + id + " no longer exists.", enfe);
            }
            Mes idmes = artigo.getIdmes();
            if (idmes != null) {
                idmes.getArtigoCollection().remove(artigo);
                idmes = em.merge(idmes);
            }
            Produtos produtos = artigo.getProdutos();
            if (produtos != null) {
                produtos.getArtigoCollection().remove(artigo);
                produtos = em.merge(produtos);
            }
            Formacaoturma formacaoturma = artigo.getFormacaoturma();
            if (formacaoturma != null) {
                formacaoturma.getArtigoCollection().remove(artigo);
                formacaoturma = em.merge(formacaoturma);
            }
            em.remove(artigo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Artigo> findArtigoEntities() {
        return findArtigoEntities(true, -1, -1);
    }

    public List<Artigo> findArtigoEntities(int maxResults, int firstResult) {
        return findArtigoEntities(false, maxResults, firstResult);
    }

    private List<Artigo> findArtigoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artigo.class));
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

    public Artigo findArtigo(ArtigoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artigo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtigoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artigo> rt = cq.from(Artigo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
      public List<Artigo> prencherartigo(int p){
          List<Artigo> formandos = new ArtigoJpaController().findArtigoEntities();//do banco
          List<Artigo> formandosselecionados = new ArrayList<Artigo>();//filtrada
         
         for (Artigo f : formandos) {
             if(f.getFormacaoturma().getIdformando()!=null){
                   if(f.getFormacaoturma().getIdformando()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
}
