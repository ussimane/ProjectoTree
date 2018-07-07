/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import Modelo.Distrito;
import Modelo.Formador;
import Modelo.Formando;
import Modelo.Localidade;
import Modelo.Posto;
import Modelo.Relanivelhabilidade;
import Modelo.Relemprego;
import Modelo.Relentidade;
import Modelo.Relfaixaetaria;
import Modelo.Relformador;
import Modelo.Relhabilitacao;
import Modelo.Relidade;
import Modelo.Relnrformando;
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
public class RelatorioFormandoCTR {
     public RelatorioFormandoCTR() {
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public List<Relformador> getRNrformador(Distrito d, Posto p, Localidade l) {
        List<Relformador> lista = new ArrayList<Relformador>();
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
                + "hh.qh as qhomen, mm.qm as qmulher,hh.qh+mm.qm as total"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as qm "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is not true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as mm, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as qh "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is true"
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
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relformador.class);
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
     
     public List<Relanivelhabilidade> getRHabilitacao(Distrito d, Posto p, Localidade l) {
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
                + "cc.t as liceh,pp.t as licem,cc.t+pp.t as lict, ss.t as sech, qq.t as secm, ss.t+qq.t as sect"
                + ",vv.t as bash,rr.t as basm, vv.t+rr.t as bast"
                + ",dd.t as tech,tt.t as tecm,dd.t+tt.t as tect, aa.t as outro"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is true and formador.Licenciatura is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is false  and formador.Licenciatura is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is true and formador.secundario is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is false and formador.secundario is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as qq,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is true and formador.tecnico is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito  and formador.sexo is false and formador.tecnico is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as rr,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito  and formador.sexo is true and formador.basico is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito  and formador.sexo is false and formador.basico is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as tt,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.outro is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as aa,"
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
                + " and (aa.l is not null and localidade.localidade = aa.l or aa.l is null)"
                + " and (aa.p is not null and posto.posto =aa.p or aa.p is null) and"
                + " ( aa.d is not null and distrito.distrito = aa.d or aa.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or dd.t <> 0 or pp.t <> 0 or qq.t <> 0 or rr.t <> 0 or tt.t <> 0 or aa.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relanivelhabilidade.class);
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
     
      public List<Relidade> getRAnosexperiencia(Distrito d, Posto p, Localidade l) {
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
                + "cc.t as menos1h,aa.t as menos1m,cc.t+aa.t as meno1t, ss.t as 1a5h, bb.t as 1a5m, ss.t + bb.t as 1a5t"
                + ",dd.t as 6a10h,oo.t as 6a10m,dd.t+oo.t as 6a10t,"
                + "vv.t as mais10h, qq.t as mais10m, vv.t+qq.t as mais10t"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia < 1 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia < 1 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as aa, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia >= 1 and formador.anosexperiencia<=5 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss, "
                 + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia >= 1 and formador.anosexperiencia<=5 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as bb, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia >= 6 and formador.anosexperiencia<=10 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia >= 6 and formador.anosexperiencia<=10 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia > 10 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.anosexperiencia > 10 and formador.sexo is false"
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
                + " and (aa.l is not null and localidade.localidade = aa.l or aa.l is null)"
                + " and (aa.p is not null and posto.posto =aa.p or aa.p is null) and"
                + " ( aa.d is not null and distrito.distrito = aa.d or aa.d is null)"
                 + " and (bb.l is not null and localidade.localidade = bb.l or bb.l is null)"
                + " and (bb.p is not null and posto.posto =bb.p or bb.p is null) and"
                + " ( bb.d is not null and distrito.distrito = bb.d or bb.d is null)"
                 + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (qq.l is not null and localidade.localidade = qq.l or qq.l is null)"
                + " and (qq.p is not null and posto.posto =qq.p or qq.p is null) and"
                + " ( qq.d is not null and distrito.distrito = qq.d or qq.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or oo.t <> 0 or dd.t <> 0 or qq.t <> 0 or aa.t <> 0 or bb.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relidade.class);
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
      
       public List<Relemprego> getREmprego(Distrito d, Posto p, Localidade l) {
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
                + "cc.t as tcoh,pp.t as tcom,cc.t+pp.t as tcot, ss.t as tcph, qq.t as tcm, ss.t+qq.t as tcpt"
                + ",vv.t as outro"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is true and formador.empregado is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is false  and formador.empregado is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as pp, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is true and formador.empresario is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.sexo is false and formador.empresario is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as qq,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.outrosituacao is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
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
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or pp.t <> 0 or qq.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relemprego.class);
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
                + "vv.t as vintecincoh, qq.t as vintecincom, vv.t+qq.t as vintecincot, aa.t as maish, bb.t as maism, aa.t+bb.t as maist "
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 19 and year(now())-year(formador.datanasc)<=24 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 19 and year(now())-year(formador.datanasc)<=24 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 25 and year(now())-year(formador.datanasc)<=35 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 25 and year(now())-year(formador.datanasc)<=35 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 36 and year(now())-year(formador.datanasc)<=50 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 36 and year(now())-year(formador.datanasc)<=50 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as qq,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 36 and year(now())-year(formador.datanasc)<=50 and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as aa,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and year(now())-year(formador.datanasc) >= 36 and year(now())-year(formador.datanasc)<=50 and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as bb,"
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
                 + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (qq.l is not null and localidade.localidade = qq.l or qq.l is null)"
                + " and (qq.p is not null and posto.posto =qq.p or qq.p is null) and"
                + " ( qq.d is not null and distrito.distrito = qq.d or qq.d is null)"
                + " and (aa.l is not null and localidade.localidade = aa.l or aa.l is null)"
                + " and (aa.p is not null and posto.posto =aa.p or aa.p is null) and"
                + " ( aa.d is not null and distrito.distrito = aa.d or aa.d is null)"
                + " and (bb.l is not null and localidade.localidade = bb.l or bb.l is null)"
                + " and (bb.p is not null and posto.posto =bb.p or bb.p is null) and"
                + " ( bb.d is not null and distrito.distrito = bb.d or bb.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or oo.t <> 0 or dd.t <> 0 or qq.t <> 0 or aa.t <> 0 or bb.t <> 0) " + par
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
       
       public List<Relentidade> getREntidade(Distrito d, Posto p, Localidade l) {
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
                + "cc.t as inefph,aa.t as inefpm,cc.t+aa.t as inefpt, ss.t as iabilh, bb.t as iabilm, ss.t + bb.t as iabilt"
                + ",dd.t as coreh,oo.t as corem,dd.t+oo.t as coret,"
                + "vv.t as idpph, qq.t as idppm, vv.t+qq.t as idppt, kk.t as outro"
                + " from ("
                + " select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'INEFP' and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as cc, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'INEFP' and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as aa, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'IABIL' and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as ss, "
                 + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'IABIL' and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as bb, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'CORE' and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as dd,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'CORE' and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as oo,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'IDPP' and formador.sexo is true"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as vv,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'IDPP' and formador.sexo is false"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as qq,"
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formador.idformador) as t "
                + "from distrito, localidade, posto, formador "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formador.iddistrito and formador.entidadeempregadora = 'Outro'"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as kk,"
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
                + " and (aa.l is not null and localidade.localidade = aa.l or aa.l is null)"
                + " and (aa.p is not null and posto.posto =aa.p or aa.p is null) and"
                + " ( aa.d is not null and distrito.distrito = aa.d or aa.d is null)"
                 + " and (bb.l is not null and localidade.localidade = bb.l or bb.l is null)"
                + " and (bb.p is not null and posto.posto =bb.p or bb.p is null) and"
                + " ( bb.d is not null and distrito.distrito = bb.d or bb.d is null)"
                 + " and (oo.l is not null and localidade.localidade = oo.l or oo.l is null)"
                + " and (oo.p is not null and posto.posto =oo.p or oo.p is null) and"
                + " ( oo.d is not null and distrito.distrito = oo.d or oo.d is null)"
                + " and (vv.l is not null and localidade.localidade = vv.l or vv.l is null)"
                + " and (vv.p is not null and posto.posto =vv.p or vv.p is null) and"
                + " ( vv.d is not null and distrito.distrito = vv.d or vv.d is null)"
                + " and (qq.l is not null and localidade.localidade = qq.l or qq.l is null)"
                + " and (qq.p is not null and posto.posto =qq.p or qq.p is null) and"
                + " ( qq.d is not null and distrito.distrito = qq.d or qq.d is null)"
                 + " and (kk.l is not null and localidade.localidade = kk.l or kk.l is null)"
                + " and (kk.p is not null and posto.posto =kk.p or kk.p is null) and"
                + " ( kk.d is not null and distrito.distrito = kk.d or kk.d is null)"
                + " and (cc.t <> 0 or ss.t <> 0 or vv.t <> 0 or oo.t <> 0 or dd.t <> 0 or qq.t <> 0 or aa.t <> 0 or bb.t <> 0 or kk.t <> 0) " + par
                + " group by distrito.distrito, posto.posto, localidade.localidade", Relentidade.class);
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
    
}
