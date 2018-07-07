/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import Modelo.Distrito;
import Modelo.Localidade;
import Modelo.Posto;
import Modelo.Relactividadecontapropria;
import Modelo.Relactividadeprof;
import Modelo.Relareaprioridade;
import Modelo.Relcasapropria;
import Modelo.Relchefefamilia;
import Modelo.Reldeficiente;
import Modelo.Reldiplocursoinefp;
import Modelo.Reldiplocursoprof;
import Modelo.Relempregocontaoutrem;
import Modelo.Relescalao;
import Modelo.Relestadocivil;
import Modelo.Relfaixaetaria;
import Modelo.Relfilhos;
import Modelo.Relfreqcursoprof;
import Modelo.Relhabilitacao;
import Modelo.Relnrformando;
import Modelo.Relrendimentodiario;
import Modelo.Relrendimentomes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author MASSINGUE
 */
public class RelatorioCtr {

    public RelatorioCtr() {
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Relnrformando> getRNrformando(Distrito d, Posto p, Localidade l) {
        List<Relnrformando> lista = new ArrayList<Relnrformando>();
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT 1 as idr, distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "hh.qh as qhome, mm.qm as qmulher,hh.qh+mm.qm as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as qm "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as mm, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as qh "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as hh,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (mm.l is not null and localidade.localidade = mm.l or mm.l is null)"
                + " and (mm.p is not null and posto.posto = mm.p or mm.p is null) and"
                + " ( mm.d is not null and distrito.distrito = mm.d or mm.d is null)"
                + " and (hh.l is not null and localidade.localidade = hh.l or hh.l is null)"
                + " and (hh.p is not null and posto.posto =hh.p or hh.p is null) and"
                + " ( hh.d is not null and distrito.distrito = hh.d or hh.d is null) and (hh.qh <> 0 or mm.qm <> 0) " + par
                + " group by idr, distrito.distrito, posto.posto, localidade.localidade", Relnrformando.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            //  q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            // q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relestadocivil> getREstadocivil(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tcasado, ss.t as tsolteiro,vv.t as tviuvo,dd.t as tdivorciado,oo.t as toutro, cc.t+ss.t+vv.t+dd.t+oo.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idestadocivil = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idestadocivil = 2"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idestadocivil = 3"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idestadocivil = 4"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idestadocivil = 5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relestadocivil.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            //   q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //  q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            // q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relfaixaetaria> getRFaixaetaria(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as quinzeh, ss.t as quinzem,cc.t+ss.t as quinzet,dd.t as desanoveh,oo.t as desanovem,dd.t+oo.t as desanovet,"
                + "vv.t as vintecincoh, qq.t as vintecincom, vv.t+qq.t as vintecincot"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idade) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idade >= 15 and formando.idade<=18 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idade) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idade >= 15 and formando.idade<=18 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idade >= 19 and formando.idade<=24 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idade >= 19 and formando.idade<=24 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idade >= 25 and formando.idade<=29 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idade >= 25 and formando.idade<=29 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as qq,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                 + " and (oo.l is not null and localidade.localidade = oo.l or dd.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (qq.l is not null and localidade.localidade = qq.l or qq.l is null)"
                + " and (qq.p is not null and posto.posto =qq.p or qq.p is null) and"
                + " ( qq.d is not null and distrito.distrito = qq.d or qq.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or oo.t <> 0 or dd.t <> 0 or qq.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relfaixaetaria.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relescalao> getREscalao(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as zeroum, ss.t as umcinco,vv.t as maiscinco,cc.t+ss.t+vv.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idade) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nrfilhos >= 0 and formando.nrfilhos<=1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nrfilhos >= 1 and formando.nrfilhos<=5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nrfilhos > 5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relescalao.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relempregocontaoutrem> getREmpregocontaoutrem(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.empregado = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.empregado = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.empregado = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.empregado = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.empregado = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.empregado = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relempregocontaoutrem.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relactividadeprof> getRActividadeprof(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.actividadeprofissional = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.actividadeprofissional = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.actividadeprofissional = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.actividadeprofissional = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.actividadeprofissional = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.actividadeprofissional = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relactividadeprof.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relcasapropria> getRCasapropria(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.casapropria = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.casapropria= 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.casapropria = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.casapropria = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.casapropria = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.casapropria = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relcasapropria.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relchefefamilia> getRChefefamilia(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.chefefamilia = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.chefefamilia= 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.chefefamilia = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.chefefamilia = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.chefefamilia = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.chefefamilia = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relchefefamilia.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Reldeficiente> getRDeficiente(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.dificiente = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.dificiente= 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.dificiente = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.dificiente = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.dificiente = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.dificiente = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Reldeficiente.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Reldiplocursoinefp> getRDiplocursoinefp(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.diplomainefp = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.diplomainefp= 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.diplomainefp = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.diplomainefp = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.diplomainefp = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.diplomainefp = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Reldiplocursoinefp.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Reldiplocursoprof> getRDiplocursoprof(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.diplomaprof = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.diplomaprof= 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.diplomaprof = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.diplomaprof = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.diplomaprof = 1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.diplomaprof = 0"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Reldiplocursoprof.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relactividadecontapropria> getRActividadecontapopria(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando in (select a.idformando from Actividadecontapropria a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando not in (select a.idformando from Actividadecontapropria a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.idformando in (select a.idformando from Actividadecontapropria a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.idformando not in (select a.idformando from Actividadecontapropria a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idformando in (select a.idformando from Actividadecontapropria a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idformando not in (select a.idformando from Actividadecontapropria a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade",Relactividadecontapropria.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relfilhos> getRFilhos(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as zeroum, ss.t as umcinco,vv.t as maiscinco,cc.t+ss.t+vv.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idade) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nrfilhos >= 0 and formando.nrfilhos<=1"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nrfilhos >= 2 and formando.nrfilhos<=5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nrfilhos > 5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relfilhos.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relfreqcursoprof> getRFreqcursoprof(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as tsh, ss.t as tnh,vv.t as tsm,dd.t as tnm,oo.t as tsim,pp.t as tnao, oo.t+pp.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando in (select a.idformando from Accaoformacao a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando not in (select a.idformando from Accaoformacao a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.idformando in (select a.idformando from Accaoformacao a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.idformando not in (select a.idformando from Accaoformacao a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idformando in (select a.idformando from Accaoformacao a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idformando not in (select a.idformando from Accaoformacao a) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relfreqcursoprof.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    //terminado
    public List<Relhabilitacao> getRHabilitacao(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as naosabelerh,pp.t as naosabelerm,cc.t+pp.t as naosabelert, ss.t as basicocompletoh, qq.t as basicocompletom, ss.t+qq.t as basicocompletot"
                + ",vv.t as basicoincompletoh,rr.t as basicoincompletom, vv.t+rr.t as basicoincompletot"
                + ",dd.t as secundariocompletoh,tt.t as secundariocompletom,dd.t+tt.t as secundariocompletot"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idade) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 1 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idade) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 1 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 2 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 2 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as qq,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 3 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"+ "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 3 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as rr,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 4 and formando.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.idnivelhabilitacao = 4 and formando.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as tt,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto = pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (qq.l is not null and localidade.localidade = qq.l or qq.l is null)"
                + " and (qq.p is not null and posto.posto =qq.p or qq.p is null) and"
                + " ( qq.d is not null and distrito.distrito = qq.d or qq.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (rr.l is not null and localidade.localidade = rr.l or rr.l is null)"
                + " and (rr.p is not null and posto.posto =rr.p or rr.p is null) and"
                + " ( rr.d is not null and distrito.distrito = rr.d or rr.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (tt.l is not null and localidade.localidade = tt.l or tt.l is null)"
                + " and (tt.p is not null and posto.posto =tt.p or tt.p is null) and"
                + " ( tt.d is not null and distrito.distrito = tt.d or tt.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or pp.t <> 0 or qq.t <> 0 or rr.t <> 0 or tt.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relhabilitacao.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relrendimentodiario> getRRendimentodiario(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as zerocem, ss.t as centoumtrezento,vv.t as trezentoumquinhento,dd.t as quinhentoummil,oo.t as maisdemil, cc.t+ss.t+vv.t+dd.t+oo.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 0 and formando.rendimentomedio<=100"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 101 and formando.rendimentomedio<=300"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 301 and formando.rendimentomedio<=500"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 500 and formando.rendimentomedio<=1000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio > 1000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relrendimentodiario.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }

    public List<Relrendimentomes> getRRendimentomes(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as zeroquinhento, ss.t as quinhentotresmil,vv.t as tresmilumquinhento,dd.t as cincomilumdezmil,oo.t as maisdedezmil, cc.t+ss.t+vv.t+dd.t+oo.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 0 and formando.rendimentomedio<=500"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 501 and formando.rendimentomedio<=3000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 3001 and formando.rendimentomedio<=5000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 5001 and formando.rendimentomedio<=10000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio > 10000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relrendimentomes.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }
    
    public List<Relrendimentomes> getRRendimentomes2(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as zeroquinhento, ss.t as quinhentotresmil,vv.t as tresmilumquinhento,dd.t as cincomilumdezmil,oo.t as maisdedezmil, cc.t+ss.t+vv.t+dd.t+oo.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio<2000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 501 and formando.rendimentomedio<=3000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio >= 2000 and formando.rendimentomedio<=5000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio > 5000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.rendimentomedio > 10000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relrendimentomes.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }
    
    public List<Relrendimentomes> getRAgregado(Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade, "
                + "cc.t as zeroquinhento, ss.t as quinhentotresmil,vv.t as tresmilumquinhento,dd.t as cincomilumdezmil,oo.t as maisdedezmil, cc.t+vv.t+dd.t as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nragregado<2"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nragregado >= 501 and formando.nragregado<=3000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nragregado >= 2 and formando.nragregado<=5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nragregado > 5"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.nragregado > 10000"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relrendimentomes.class);
        if (l != null) {
            q.setParameter(1, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(1, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(1, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }
    
    public List<Relareaprioridade> getRAreaprioridade(int cur, Distrito d, Posto p, Localidade l) {
        EntityManager em = getEntityManager();
        String par = "";
        if (l != null) {
            par = "and localidade.idlocalidade = ?";
        } else if (p != null) {
            par = "and posto.idposto = ?";
        } else if (d != null) {
            par = "and distrito.iddistrito = ?";
        }
        Query q = em.createNativeQuery(""
                + "SELECT distrito.distrito as distrito,posto.posto as posto,localidade.localidade as localidade,"
                + "cc.t as p1h, ss.t as p1m, cc.t+ss.t as p1t, vv.t as p2h, dd.t as p2m, vv.t+dd.t as p2t"
                + ",oo.t as p3h, pp.t as p3m, oo.t+pp.t as p3t "
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando in (select a.idformando from Areadeformacao a where a.idcurso1=?) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is false and formando.idformando in (select a.idformando from Areadeformacao a where a.idcurso1=?) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando in (select a.idformando from Areadeformacao a where a.idcurso2=?) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is false and formando.idformando in (select a.idformando from Areadeformacao a where a.idcurso2=?) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idformando in (select a.idformando from Areadeformacao a where a.idcurso3=?) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as t "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is false and formando.idformando in (select a.idformando from Areadeformacao a where a.idcurso3=?) "
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp, "
                + " distrito, localidade, posto"
                + " where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto "
                + " and (cc.l is not null and localidade.localidade = cc.l or cc.l is null)"
                + " and (cc.p is not null and posto.posto = cc.p or cc.p is null) and"
                + " ( cc.d is not null and distrito.distrito = cc.d or cc.d is null)"
                + " and (ss.l is not null and localidade.localidade = ss.l or ss.l is null)"
                + " and (ss.p is not null and posto.posto =ss.p or ss.p is null) and"
                + " ( ss.d is not null and distrito.distrito = ss.d or ss.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (dd.l is not null and localidade.localidade = dd.l or dd.l is null)"
                + " and (dd.p is not null and posto.posto =dd.p or dd.p is null) and"
                + " ( dd.d is not null and distrito.distrito = dd.d or dd.d is null)"
                + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                 + " and (pp.l is not null and localidade.localidade = pp.l or pp.l is null)"
                + " and (pp.p is not null and posto.posto =pp.p or pp.p is null) and"
                + " ( pp.d is not null and distrito.distrito = pp.d or pp.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or oo.t <> 0 or pp.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relareaprioridade.class);
        q.setHint("eclipselink.refresh", true);
        q.setParameter(1, cur);
        q.setParameter(2, cur);
        q.setParameter(3, cur);
        q.setParameter(4, cur);
        q.setParameter(5, cur);
        q.setParameter(6, cur);
        if (l != null) {
            q.setParameter(7, l.getIdlocalidade());
            // q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(7, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(7, d.getIddistrito());
            //  q.setParameter(1, d.getIddistrito());
        }
        
        return q.getResultList();
    }

}
