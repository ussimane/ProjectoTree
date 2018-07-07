/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Artigo;
import Modelo.Artigofinal;
import Modelo.ArtigofinalPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Mestre;
import Modelo.Formadorturma;
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
public class ArtigofinalJpaController implements Serializable {

     public ArtigofinalJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artigofinal artigofinal) throws PreexistingEntityException, Exception {
        if (artigofinal.getArtigofinalPK() == null) {
            artigofinal.setArtigofinalPK(new ArtigofinalPK());
        }
        artigofinal.getArtigofinalPK().setIdproduto(artigofinal.getProdutos().getIdproduto());
        artigofinal.getArtigofinalPK().setIdformador(artigofinal.getFormadorturma().getIdformador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mes idmes = artigofinal.getIdmes();
            if (idmes != null) {
                idmes = em.getReference(idmes.getClass(), idmes.getIdmes());
                artigofinal.setIdmes(idmes);
            }
            Mestre idmestre = artigofinal.getIdmestre();
            if (idmestre != null) {
                idmestre = em.getReference(idmestre.getClass(), idmestre.getIdmestre());
                artigofinal.setIdmestre(idmestre);
            }
            Produtos produtos = artigofinal.getProdutos();
            if (produtos != null) {
                produtos = em.getReference(produtos.getClass(), produtos.getIdproduto());
                artigofinal.setProdutos(produtos);
            }
            Formadorturma formadorturma = artigofinal.getFormadorturma();
            if (formadorturma != null) {
                formadorturma = em.getReference(formadorturma.getClass(), formadorturma.getIdformador());
                artigofinal.setFormadorturma(formadorturma);
            }
            em.persist(artigofinal);
            if (idmes != null) {
                idmes.getArtigofinalCollection().add(artigofinal);
                idmes = em.merge(idmes);
            }
            if (idmestre != null) {
                idmestre.getArtigofinalCollection().add(artigofinal);
                idmestre = em.merge(idmestre);
            }
            if (produtos != null) {
                produtos.getArtigofinalCollection().add(artigofinal);
                produtos = em.merge(produtos);
            }
            if (formadorturma != null) {
                formadorturma.getArtigofinalCollection().add(artigofinal);
                formadorturma = em.merge(formadorturma);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtigofinal(artigofinal.getArtigofinalPK()) != null) {
                throw new PreexistingEntityException("Artigofinal " + artigofinal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artigofinal artigofinal) throws NonexistentEntityException, Exception {
        artigofinal.getArtigofinalPK().setIdproduto(artigofinal.getProdutos().getIdproduto());
        artigofinal.getArtigofinalPK().setIdformador(artigofinal.getFormadorturma().getIdformador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artigofinal persistentArtigofinal = em.find(Artigofinal.class, artigofinal.getArtigofinalPK());
            Mes idmesOld = persistentArtigofinal.getIdmes();
            Mes idmesNew = artigofinal.getIdmes();
            Mestre idmestreOld = persistentArtigofinal.getIdmestre();
            Mestre idmestreNew = artigofinal.getIdmestre();
            Produtos produtosOld = persistentArtigofinal.getProdutos();
            Produtos produtosNew = artigofinal.getProdutos();
            Formadorturma formadorturmaOld = persistentArtigofinal.getFormadorturma();
            Formadorturma formadorturmaNew = artigofinal.getFormadorturma();
            if (idmesNew != null) {
                idmesNew = em.getReference(idmesNew.getClass(), idmesNew.getIdmes());
                artigofinal.setIdmes(idmesNew);
            }
            if (idmestreNew != null) {
                idmestreNew = em.getReference(idmestreNew.getClass(), idmestreNew.getIdmestre());
                artigofinal.setIdmestre(idmestreNew);
            }
            if (produtosNew != null) {
                produtosNew = em.getReference(produtosNew.getClass(), produtosNew.getIdproduto());
                artigofinal.setProdutos(produtosNew);
            }
            if (formadorturmaNew != null) {
                formadorturmaNew = em.getReference(formadorturmaNew.getClass(), formadorturmaNew.getIdformador());
                artigofinal.setFormadorturma(formadorturmaNew);
            }
            artigofinal = em.merge(artigofinal);
            if (idmesOld != null && !idmesOld.equals(idmesNew)) {
                idmesOld.getArtigofinalCollection().remove(artigofinal);
                idmesOld = em.merge(idmesOld);
            }
            if (idmesNew != null && !idmesNew.equals(idmesOld)) {
                idmesNew.getArtigofinalCollection().add(artigofinal);
                idmesNew = em.merge(idmesNew);
            }
            if (idmestreOld != null && !idmestreOld.equals(idmestreNew)) {
                idmestreOld.getArtigofinalCollection().remove(artigofinal);
                idmestreOld = em.merge(idmestreOld);
            }
            if (idmestreNew != null && !idmestreNew.equals(idmestreOld)) {
                idmestreNew.getArtigofinalCollection().add(artigofinal);
                idmestreNew = em.merge(idmestreNew);
            }
            if (produtosOld != null && !produtosOld.equals(produtosNew)) {
                produtosOld.getArtigofinalCollection().remove(artigofinal);
                produtosOld = em.merge(produtosOld);
            }
            if (produtosNew != null && !produtosNew.equals(produtosOld)) {
                produtosNew.getArtigofinalCollection().add(artigofinal);
                produtosNew = em.merge(produtosNew);
            }
            if (formadorturmaOld != null && !formadorturmaOld.equals(formadorturmaNew)) {
                formadorturmaOld.getArtigofinalCollection().remove(artigofinal);
                formadorturmaOld = em.merge(formadorturmaOld);
            }
            if (formadorturmaNew != null && !formadorturmaNew.equals(formadorturmaOld)) {
                formadorturmaNew.getArtigofinalCollection().add(artigofinal);
                formadorturmaNew = em.merge(formadorturmaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ArtigofinalPK id = artigofinal.getArtigofinalPK();
                if (findArtigofinal(id) == null) {
                    throw new NonexistentEntityException("The artigofinal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ArtigofinalPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artigofinal artigofinal;
            try {
                artigofinal = em.getReference(Artigofinal.class, id);
                artigofinal.getArtigofinalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artigofinal with id " + id + " no longer exists.", enfe);
            }
            Mes idmes = artigofinal.getIdmes();
            if (idmes != null) {
                idmes.getArtigofinalCollection().remove(artigofinal);
                idmes = em.merge(idmes);
            }
            Mestre idmestre = artigofinal.getIdmestre();
            if (idmestre != null) {
                idmestre.getArtigofinalCollection().remove(artigofinal);
                idmestre = em.merge(idmestre);
            }
            Produtos produtos = artigofinal.getProdutos();
            if (produtos != null) {
                produtos.getArtigofinalCollection().remove(artigofinal);
                produtos = em.merge(produtos);
            }
            Formadorturma formadorturma = artigofinal.getFormadorturma();
            if (formadorturma != null) {
                formadorturma.getArtigofinalCollection().remove(artigofinal);
                formadorturma = em.merge(formadorturma);
            }
            em.remove(artigofinal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artigofinal> findArtigofinalEntities() {
        return findArtigofinalEntities(true, -1, -1);
    }

    public List<Artigofinal> findArtigofinalEntities(int maxResults, int firstResult) {
        return findArtigofinalEntities(false, maxResults, firstResult);
    }

    private List<Artigofinal> findArtigofinalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artigofinal.class));
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

    public Artigofinal findArtigofinal(ArtigofinalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artigofinal.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtigofinalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artigofinal> rt = cq.from(Artigofinal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Artigofinal> prencherartigo(int p){
          List<Artigofinal> formandos = new ArtigofinalJpaController().findArtigofinalEntities();//do banco
          List<Artigofinal> formandosselecionados = new ArrayList<Artigofinal>();//filtrada
         
         for (Artigofinal f : formandos) {
             if(f.getFormadorturma().getIdformador()!=null){
                   if(f.getFormadorturma().getIdformador()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    } 
}
