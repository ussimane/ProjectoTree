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
import Modelo.Mercado;
import Modelo.Investimento;
import Modelo.Actividadeprofissao;
import Modelo.Equipamentofalta;
import Modelo.Mestre;
import java.util.ArrayList;
import java.util.List;
import Modelo.Oficinaequipamento;
import Modelo.Oficinamestre;
import Modelo.Producao;
import Modelo.Trabalhador;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASSINGUE
 */
public class OficinamestreJpaController implements Serializable {

     public OficinamestreJpaController() {
        
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Oficinamestre oficinamestre) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (oficinamestre.getEquipamentofaltaCollection() == null) {
            oficinamestre.setEquipamentofaltaCollection(new ArrayList<Equipamentofalta>());
        }
        if (oficinamestre.getOficinaequipamentoCollection() == null) {
            oficinamestre.setOficinaequipamentoCollection(new ArrayList<Oficinaequipamento>());
        }
        List<String> illegalOrphanMessages = null;
        Mestre mestreOrphanCheck = oficinamestre.getMestre();
        if (mestreOrphanCheck != null) {
            Oficinamestre oldOficinamestreOfMestre = mestreOrphanCheck.getOficinamestre();
            if (oldOficinamestreOfMestre != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Mestre " + mestreOrphanCheck + " already has an item of type Oficinamestre whose mestre column cannot be null. Please make another selection for the mestre field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producao idproducao = oficinamestre.getIdproducao();
            if (idproducao != null) {
                idproducao = em.getReference(idproducao.getClass(), idproducao.getIdproducao());
                oficinamestre.setIdproducao(idproducao);
            }
            Trabalhador nrtrabalhador = oficinamestre.getNrtrabalhador();
            if (nrtrabalhador != null) {
                nrtrabalhador = em.getReference(nrtrabalhador.getClass(), nrtrabalhador.getIdtabalhador());
                oficinamestre.setNrtrabalhador(nrtrabalhador);
            }
            Mestre mestre = oficinamestre.getMestre();
            if (mestre != null) {
                mestre = em.getReference(mestre.getClass(), mestre.getIdmestre());
                oficinamestre.setMestre(mestre);
            }
            Mercado idmercado = oficinamestre.getIdmercado();
            if (idmercado != null) {
                idmercado = em.getReference(idmercado.getClass(), idmercado.getIdmercado());
                oficinamestre.setIdmercado(idmercado);
            }
            Investimento idanoinvestimentosequipamentos = oficinamestre.getIdanoinvestimentosequipamentos();
            if (idanoinvestimentosequipamentos != null) {
                idanoinvestimentosequipamentos = em.getReference(idanoinvestimentosequipamentos.getClass(), idanoinvestimentosequipamentos.getIdinvestimento());
                oficinamestre.setIdanoinvestimentosequipamentos(idanoinvestimentosequipamentos);
            }
            Actividadeprofissao idanosoficina = oficinamestre.getIdanosoficina();
            if (idanosoficina != null) {
                idanosoficina = em.getReference(idanosoficina.getClass(), idanosoficina.getIdactividadeprofissao());
                oficinamestre.setIdanosoficina(idanosoficina);
            }
            Actividadeprofissao idanosexperiencia = oficinamestre.getIdanosexperiencia();
            if (idanosexperiencia != null) {
                idanosexperiencia = em.getReference(idanosexperiencia.getClass(), idanosexperiencia.getIdactividadeprofissao());
                oficinamestre.setIdanosexperiencia(idanosexperiencia);
            }
            Collection<Equipamentofalta> attachedEquipamentofaltaCollection = new ArrayList<Equipamentofalta>();
            for (Equipamentofalta equipamentofaltaCollectionEquipamentofaltaToAttach : oficinamestre.getEquipamentofaltaCollection()) {
                equipamentofaltaCollectionEquipamentofaltaToAttach = em.getReference(equipamentofaltaCollectionEquipamentofaltaToAttach.getClass(), equipamentofaltaCollectionEquipamentofaltaToAttach.getEquipamentofaltaPK());
                attachedEquipamentofaltaCollection.add(equipamentofaltaCollectionEquipamentofaltaToAttach);
            }
            oficinamestre.setEquipamentofaltaCollection(attachedEquipamentofaltaCollection);
            Collection<Oficinaequipamento> attachedOficinaequipamentoCollection = new ArrayList<Oficinaequipamento>();
            for (Oficinaequipamento oficinaequipamentoCollectionOficinaequipamentoToAttach : oficinamestre.getOficinaequipamentoCollection()) {
                oficinaequipamentoCollectionOficinaequipamentoToAttach = em.getReference(oficinaequipamentoCollectionOficinaequipamentoToAttach.getClass(), oficinaequipamentoCollectionOficinaequipamentoToAttach.getOficinaequipamentoPK());
                attachedOficinaequipamentoCollection.add(oficinaequipamentoCollectionOficinaequipamentoToAttach);
            }
            oficinamestre.setOficinaequipamentoCollection(attachedOficinaequipamentoCollection);
            em.persist(oficinamestre);
            if (idproducao != null) {
                idproducao.getOficinamestreCollection().add(oficinamestre);
                idproducao = em.merge(idproducao);
            }
            if (nrtrabalhador != null) {
                nrtrabalhador.getOficinamestreCollection().add(oficinamestre);
                nrtrabalhador = em.merge(nrtrabalhador);
            }
            if (mestre != null) {
                mestre.setOficinamestre(oficinamestre);
                mestre = em.merge(mestre);
            }
            if (idmercado != null) {
                idmercado.getOficinamestreCollection().add(oficinamestre);
                idmercado = em.merge(idmercado);
            }
            if (idanoinvestimentosequipamentos != null) {
                idanoinvestimentosequipamentos.getOficinamestreCollection().add(oficinamestre);
                idanoinvestimentosequipamentos = em.merge(idanoinvestimentosequipamentos);
            }
            if (idanosoficina != null) {
                idanosoficina.getOficinamestreCollection().add(oficinamestre);
                idanosoficina = em.merge(idanosoficina);
            }
            if (idanosexperiencia != null) {
                idanosexperiencia.getOficinamestreCollection().add(oficinamestre);
                idanosexperiencia = em.merge(idanosexperiencia);
            }
            for (Equipamentofalta equipamentofaltaCollectionEquipamentofalta : oficinamestre.getEquipamentofaltaCollection()) {
                Oficinamestre oldOficinamestreOfEquipamentofaltaCollectionEquipamentofalta = equipamentofaltaCollectionEquipamentofalta.getOficinamestre();
                equipamentofaltaCollectionEquipamentofalta.setOficinamestre(oficinamestre);
                equipamentofaltaCollectionEquipamentofalta = em.merge(equipamentofaltaCollectionEquipamentofalta);
                if (oldOficinamestreOfEquipamentofaltaCollectionEquipamentofalta != null) {
                    oldOficinamestreOfEquipamentofaltaCollectionEquipamentofalta.getEquipamentofaltaCollection().remove(equipamentofaltaCollectionEquipamentofalta);
                    oldOficinamestreOfEquipamentofaltaCollectionEquipamentofalta = em.merge(oldOficinamestreOfEquipamentofaltaCollectionEquipamentofalta);
                }
            }
            for (Oficinaequipamento oficinaequipamentoCollectionOficinaequipamento : oficinamestre.getOficinaequipamentoCollection()) {
                Oficinamestre oldOficinamestreOfOficinaequipamentoCollectionOficinaequipamento = oficinaequipamentoCollectionOficinaequipamento.getOficinamestre();
                oficinaequipamentoCollectionOficinaequipamento.setOficinamestre(oficinamestre);
                oficinaequipamentoCollectionOficinaequipamento = em.merge(oficinaequipamentoCollectionOficinaequipamento);
                if (oldOficinamestreOfOficinaequipamentoCollectionOficinaequipamento != null) {
                    oldOficinamestreOfOficinaequipamentoCollectionOficinaequipamento.getOficinaequipamentoCollection().remove(oficinaequipamentoCollectionOficinaequipamento);
                    oldOficinamestreOfOficinaequipamentoCollectionOficinaequipamento = em.merge(oldOficinamestreOfOficinaequipamentoCollectionOficinaequipamento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOficinamestre(oficinamestre.getIdmestre()) != null) {
                throw new PreexistingEntityException("Oficinamestre " + oficinamestre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Oficinamestre oficinamestre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Oficinamestre persistentOficinamestre = em.find(Oficinamestre.class, oficinamestre.getIdmestre());
            Producao idproducaoOld = persistentOficinamestre.getIdproducao();
            Producao idproducaoNew = oficinamestre.getIdproducao();
            Trabalhador nrtrabalhadorOld = persistentOficinamestre.getNrtrabalhador();
            Trabalhador nrtrabalhadorNew = oficinamestre.getNrtrabalhador();
            Mestre mestreOld = persistentOficinamestre.getMestre();
            Mestre mestreNew = oficinamestre.getMestre();
            Mercado idmercadoOld = persistentOficinamestre.getIdmercado();
            Mercado idmercadoNew = oficinamestre.getIdmercado();
            Investimento idanoinvestimentosequipamentosOld = persistentOficinamestre.getIdanoinvestimentosequipamentos();
            Investimento idanoinvestimentosequipamentosNew = oficinamestre.getIdanoinvestimentosequipamentos();
            Actividadeprofissao idanosoficinaOld = persistentOficinamestre.getIdanosoficina();
            Actividadeprofissao idanosoficinaNew = oficinamestre.getIdanosoficina();
            Actividadeprofissao idanosexperienciaOld = persistentOficinamestre.getIdanosexperiencia();
            Actividadeprofissao idanosexperienciaNew = oficinamestre.getIdanosexperiencia();
            Collection<Equipamentofalta> equipamentofaltaCollectionOld = persistentOficinamestre.getEquipamentofaltaCollection();
            Collection<Equipamentofalta> equipamentofaltaCollectionNew = oficinamestre.getEquipamentofaltaCollection();
            Collection<Oficinaequipamento> oficinaequipamentoCollectionOld = persistentOficinamestre.getOficinaequipamentoCollection();
            Collection<Oficinaequipamento> oficinaequipamentoCollectionNew = oficinamestre.getOficinaequipamentoCollection();
            List<String> illegalOrphanMessages = null;
            if (mestreNew != null && !mestreNew.equals(mestreOld)) {
                Oficinamestre oldOficinamestreOfMestre = mestreNew.getOficinamestre();
                if (oldOficinamestreOfMestre != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Mestre " + mestreNew + " already has an item of type Oficinamestre whose mestre column cannot be null. Please make another selection for the mestre field.");
                }
            }
            for (Equipamentofalta equipamentofaltaCollectionOldEquipamentofalta : equipamentofaltaCollectionOld) {
                if (!equipamentofaltaCollectionNew.contains(equipamentofaltaCollectionOldEquipamentofalta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Equipamentofalta " + equipamentofaltaCollectionOldEquipamentofalta + " since its oficinamestre field is not nullable.");
                }
            }
            for (Oficinaequipamento oficinaequipamentoCollectionOldOficinaequipamento : oficinaequipamentoCollectionOld) {
                if (!oficinaequipamentoCollectionNew.contains(oficinaequipamentoCollectionOldOficinaequipamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Oficinaequipamento " + oficinaequipamentoCollectionOldOficinaequipamento + " since its oficinamestre field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idproducaoNew != null) {
                idproducaoNew = em.getReference(idproducaoNew.getClass(), idproducaoNew.getIdproducao());
                oficinamestre.setIdproducao(idproducaoNew);
            }
            if (nrtrabalhadorNew != null) {
                nrtrabalhadorNew = em.getReference(nrtrabalhadorNew.getClass(), nrtrabalhadorNew.getIdtabalhador());
                oficinamestre.setNrtrabalhador(nrtrabalhadorNew);
            }
            if (mestreNew != null) {
                mestreNew = em.getReference(mestreNew.getClass(), mestreNew.getIdmestre());
                oficinamestre.setMestre(mestreNew);
            }
            if (idmercadoNew != null) {
                idmercadoNew = em.getReference(idmercadoNew.getClass(), idmercadoNew.getIdmercado());
                oficinamestre.setIdmercado(idmercadoNew);
            }
            if (idanoinvestimentosequipamentosNew != null) {
                idanoinvestimentosequipamentosNew = em.getReference(idanoinvestimentosequipamentosNew.getClass(), idanoinvestimentosequipamentosNew.getIdinvestimento());
                oficinamestre.setIdanoinvestimentosequipamentos(idanoinvestimentosequipamentosNew);
            }
            if (idanosoficinaNew != null) {
                idanosoficinaNew = em.getReference(idanosoficinaNew.getClass(), idanosoficinaNew.getIdactividadeprofissao());
                oficinamestre.setIdanosoficina(idanosoficinaNew);
            }
            if (idanosexperienciaNew != null) {
                idanosexperienciaNew = em.getReference(idanosexperienciaNew.getClass(), idanosexperienciaNew.getIdactividadeprofissao());
                oficinamestre.setIdanosexperiencia(idanosexperienciaNew);
            }
            Collection<Equipamentofalta> attachedEquipamentofaltaCollectionNew = new ArrayList<Equipamentofalta>();
            for (Equipamentofalta equipamentofaltaCollectionNewEquipamentofaltaToAttach : equipamentofaltaCollectionNew) {
                equipamentofaltaCollectionNewEquipamentofaltaToAttach = em.getReference(equipamentofaltaCollectionNewEquipamentofaltaToAttach.getClass(), equipamentofaltaCollectionNewEquipamentofaltaToAttach.getEquipamentofaltaPK());
                attachedEquipamentofaltaCollectionNew.add(equipamentofaltaCollectionNewEquipamentofaltaToAttach);
            }
            equipamentofaltaCollectionNew = attachedEquipamentofaltaCollectionNew;
            oficinamestre.setEquipamentofaltaCollection(equipamentofaltaCollectionNew);
            Collection<Oficinaequipamento> attachedOficinaequipamentoCollectionNew = new ArrayList<Oficinaequipamento>();
            for (Oficinaequipamento oficinaequipamentoCollectionNewOficinaequipamentoToAttach : oficinaequipamentoCollectionNew) {
                oficinaequipamentoCollectionNewOficinaequipamentoToAttach = em.getReference(oficinaequipamentoCollectionNewOficinaequipamentoToAttach.getClass(), oficinaequipamentoCollectionNewOficinaequipamentoToAttach.getOficinaequipamentoPK());
                attachedOficinaequipamentoCollectionNew.add(oficinaequipamentoCollectionNewOficinaequipamentoToAttach);
            }
            oficinaequipamentoCollectionNew = attachedOficinaequipamentoCollectionNew;
            oficinamestre.setOficinaequipamentoCollection(oficinaequipamentoCollectionNew);
            oficinamestre = em.merge(oficinamestre);
            if (idproducaoOld != null && !idproducaoOld.equals(idproducaoNew)) {
                idproducaoOld.getOficinamestreCollection().remove(oficinamestre);
                idproducaoOld = em.merge(idproducaoOld);
            }
            if (idproducaoNew != null && !idproducaoNew.equals(idproducaoOld)) {
                idproducaoNew.getOficinamestreCollection().add(oficinamestre);
                idproducaoNew = em.merge(idproducaoNew);
            }
            if (nrtrabalhadorOld != null && !nrtrabalhadorOld.equals(nrtrabalhadorNew)) {
                nrtrabalhadorOld.getOficinamestreCollection().remove(oficinamestre);
                nrtrabalhadorOld = em.merge(nrtrabalhadorOld);
            }
            if (nrtrabalhadorNew != null && !nrtrabalhadorNew.equals(nrtrabalhadorOld)) {
                nrtrabalhadorNew.getOficinamestreCollection().add(oficinamestre);
                nrtrabalhadorNew = em.merge(nrtrabalhadorNew);
            }
            if (mestreOld != null && !mestreOld.equals(mestreNew)) {
                mestreOld.setOficinamestre(null);
                mestreOld = em.merge(mestreOld);
            }
            if (mestreNew != null && !mestreNew.equals(mestreOld)) {
                mestreNew.setOficinamestre(oficinamestre);
                mestreNew = em.merge(mestreNew);
            }
            if (idmercadoOld != null && !idmercadoOld.equals(idmercadoNew)) {
                idmercadoOld.getOficinamestreCollection().remove(oficinamestre);
                idmercadoOld = em.merge(idmercadoOld);
            }
            if (idmercadoNew != null && !idmercadoNew.equals(idmercadoOld)) {
                idmercadoNew.getOficinamestreCollection().add(oficinamestre);
                idmercadoNew = em.merge(idmercadoNew);
            }
            if (idanoinvestimentosequipamentosOld != null && !idanoinvestimentosequipamentosOld.equals(idanoinvestimentosequipamentosNew)) {
                idanoinvestimentosequipamentosOld.getOficinamestreCollection().remove(oficinamestre);
                idanoinvestimentosequipamentosOld = em.merge(idanoinvestimentosequipamentosOld);
            }
            if (idanoinvestimentosequipamentosNew != null && !idanoinvestimentosequipamentosNew.equals(idanoinvestimentosequipamentosOld)) {
                idanoinvestimentosequipamentosNew.getOficinamestreCollection().add(oficinamestre);
                idanoinvestimentosequipamentosNew = em.merge(idanoinvestimentosequipamentosNew);
            }
            if (idanosoficinaOld != null && !idanosoficinaOld.equals(idanosoficinaNew)) {
                idanosoficinaOld.getOficinamestreCollection().remove(oficinamestre);
                idanosoficinaOld = em.merge(idanosoficinaOld);
            }
            if (idanosoficinaNew != null && !idanosoficinaNew.equals(idanosoficinaOld)) {
                idanosoficinaNew.getOficinamestreCollection().add(oficinamestre);
                idanosoficinaNew = em.merge(idanosoficinaNew);
            }
            if (idanosexperienciaOld != null && !idanosexperienciaOld.equals(idanosexperienciaNew)) {
                idanosexperienciaOld.getOficinamestreCollection().remove(oficinamestre);
                idanosexperienciaOld = em.merge(idanosexperienciaOld);
            }
            if (idanosexperienciaNew != null && !idanosexperienciaNew.equals(idanosexperienciaOld)) {
                idanosexperienciaNew.getOficinamestreCollection().add(oficinamestre);
                idanosexperienciaNew = em.merge(idanosexperienciaNew);
            }
            for (Equipamentofalta equipamentofaltaCollectionNewEquipamentofalta : equipamentofaltaCollectionNew) {
                if (!equipamentofaltaCollectionOld.contains(equipamentofaltaCollectionNewEquipamentofalta)) {
                    Oficinamestre oldOficinamestreOfEquipamentofaltaCollectionNewEquipamentofalta = equipamentofaltaCollectionNewEquipamentofalta.getOficinamestre();
                    equipamentofaltaCollectionNewEquipamentofalta.setOficinamestre(oficinamestre);
                    equipamentofaltaCollectionNewEquipamentofalta = em.merge(equipamentofaltaCollectionNewEquipamentofalta);
                    if (oldOficinamestreOfEquipamentofaltaCollectionNewEquipamentofalta != null && !oldOficinamestreOfEquipamentofaltaCollectionNewEquipamentofalta.equals(oficinamestre)) {
                        oldOficinamestreOfEquipamentofaltaCollectionNewEquipamentofalta.getEquipamentofaltaCollection().remove(equipamentofaltaCollectionNewEquipamentofalta);
                        oldOficinamestreOfEquipamentofaltaCollectionNewEquipamentofalta = em.merge(oldOficinamestreOfEquipamentofaltaCollectionNewEquipamentofalta);
                    }
                }
            }
            for (Oficinaequipamento oficinaequipamentoCollectionNewOficinaequipamento : oficinaequipamentoCollectionNew) {
                if (!oficinaequipamentoCollectionOld.contains(oficinaequipamentoCollectionNewOficinaequipamento)) {
                    Oficinamestre oldOficinamestreOfOficinaequipamentoCollectionNewOficinaequipamento = oficinaequipamentoCollectionNewOficinaequipamento.getOficinamestre();
                    oficinaequipamentoCollectionNewOficinaequipamento.setOficinamestre(oficinamestre);
                    oficinaequipamentoCollectionNewOficinaequipamento = em.merge(oficinaequipamentoCollectionNewOficinaequipamento);
                    if (oldOficinamestreOfOficinaequipamentoCollectionNewOficinaequipamento != null && !oldOficinamestreOfOficinaequipamentoCollectionNewOficinaequipamento.equals(oficinamestre)) {
                        oldOficinamestreOfOficinaequipamentoCollectionNewOficinaequipamento.getOficinaequipamentoCollection().remove(oficinaequipamentoCollectionNewOficinaequipamento);
                        oldOficinamestreOfOficinaequipamentoCollectionNewOficinaequipamento = em.merge(oldOficinamestreOfOficinaequipamentoCollectionNewOficinaequipamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = oficinamestre.getIdmestre();
                if (findOficinamestre(id) == null) {
                    throw new NonexistentEntityException("The oficinamestre with id " + id + " no longer exists.");
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
            Oficinamestre oficinamestre;
            try {
                oficinamestre = em.getReference(Oficinamestre.class, id);
                oficinamestre.getIdmestre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The oficinamestre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Equipamentofalta> equipamentofaltaCollectionOrphanCheck = oficinamestre.getEquipamentofaltaCollection();
            for (Equipamentofalta equipamentofaltaCollectionOrphanCheckEquipamentofalta : equipamentofaltaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Oficinamestre (" + oficinamestre + ") cannot be destroyed since the Equipamentofalta " + equipamentofaltaCollectionOrphanCheckEquipamentofalta + " in its equipamentofaltaCollection field has a non-nullable oficinamestre field.");
            }
            Collection<Oficinaequipamento> oficinaequipamentoCollectionOrphanCheck = oficinamestre.getOficinaequipamentoCollection();
            for (Oficinaequipamento oficinaequipamentoCollectionOrphanCheckOficinaequipamento : oficinaequipamentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Oficinamestre (" + oficinamestre + ") cannot be destroyed since the Oficinaequipamento " + oficinaequipamentoCollectionOrphanCheckOficinaequipamento + " in its oficinaequipamentoCollection field has a non-nullable oficinamestre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producao idproducao = oficinamestre.getIdproducao();
            if (idproducao != null) {
                idproducao.getOficinamestreCollection().remove(oficinamestre);
                idproducao = em.merge(idproducao);
            }
            Trabalhador nrtrabalhador = oficinamestre.getNrtrabalhador();
            if (nrtrabalhador != null) {
                nrtrabalhador.getOficinamestreCollection().remove(oficinamestre);
                nrtrabalhador = em.merge(nrtrabalhador);
            }
            Mestre mestre = oficinamestre.getMestre();
            if (mestre != null) {
                mestre.setOficinamestre(null);
                mestre = em.merge(mestre);
            }
            Mercado idmercado = oficinamestre.getIdmercado();
            if (idmercado != null) {
                idmercado.getOficinamestreCollection().remove(oficinamestre);
                idmercado = em.merge(idmercado);
            }
            Investimento idanoinvestimentosequipamentos = oficinamestre.getIdanoinvestimentosequipamentos();
            if (idanoinvestimentosequipamentos != null) {
                idanoinvestimentosequipamentos.getOficinamestreCollection().remove(oficinamestre);
                idanoinvestimentosequipamentos = em.merge(idanoinvestimentosequipamentos);
            }
            Actividadeprofissao idanosoficina = oficinamestre.getIdanosoficina();
            if (idanosoficina != null) {
                idanosoficina.getOficinamestreCollection().remove(oficinamestre);
                idanosoficina = em.merge(idanosoficina);
            }
            Actividadeprofissao idanosexperiencia = oficinamestre.getIdanosexperiencia();
            if (idanosexperiencia != null) {
                idanosexperiencia.getOficinamestreCollection().remove(oficinamestre);
                idanosexperiencia = em.merge(idanosexperiencia);
            }
            em.remove(oficinamestre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Oficinamestre> findOficinamestreEntities() {
        return findOficinamestreEntities(true, -1, -1);
    }

    public List<Oficinamestre> findOficinamestreEntities(int maxResults, int firstResult) {
        return findOficinamestreEntities(false, maxResults, firstResult);
    }

    private List<Oficinamestre> findOficinamestreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Oficinamestre.class));
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

    public Oficinamestre findOficinamestre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Oficinamestre.class, id);
        } finally {
            em.close();
        }
    }

    public int getOficinamestreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Oficinamestre> rt = cq.from(Oficinamestre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Oficinamestre> mestreDistrito(int p){
          List<Oficinamestre> mestre = new OficinamestreJpaController().findOficinamestreEntities();//do banco
          List<Oficinamestre> formandosselecionados = new ArrayList<Oficinamestre>();//filtrada
         
         for (Oficinamestre f : mestre) {
             if(f.getMestre().getIdlocalidade().getIdposto().getIddistrito().getIddistrito()!=null){
                   if(f.getMestre().getIdlocalidade().getIdposto().getIddistrito().getIddistrito()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
    }  
     public List<Oficinamestre> mestrelocalidade(int p){
          List<Oficinamestre> mestre = new OficinamestreJpaController().findOficinamestreEntities();//do banco
          List<Oficinamestre> formandosselecionados = new ArrayList<Oficinamestre>();//filtrada
         
         for (Oficinamestre f : mestre) {
             if(f.getMestre().getIdlocalidade().getIdlocalidade()!=null){
                   if(f.getMestre().getIdlocalidade().getIdlocalidade()==p){
                  formandosselecionados.add(f);
            }
             }
            
        }
               
        return formandosselecionados;
     }
    
}
