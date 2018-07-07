/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import Modelo.Curso;
import Modelo.Cursoformacao;
import Modelo.Distrito;
import Modelo.Formacaoturma;
import Modelo.Formando;
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
import Persistencia.CursoformacaoJpaController;
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
public class RelatorioCurso {

    public RelatorioCurso() {
        emf = Persistence.createEntityManagerFactory("TREEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Relnrformando> getNrFormando(Distrito d, Posto p, Localidade l, int curso) {
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
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is not true and formando.idcurso = ?"
                + " group by distrito.distrito, posto.posto, localidade.localidade"
                + " union select null,null,null,0) as mm, "
                + "( select distrito.distrito as d, posto.posto as p,localidade.localidade as l, count(formando.idformando) as qh "
                + "from distrito, localidade, posto, formando "
                + "where distrito.iddistrito = posto.iddistrito and posto.idposto = localidade.idposto"
                + " and localidade.idlocalidade = formando.idlocalidade and formando.sexo is true and formando.idcurso = ?"
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
        q.setParameter(1, curso);
        q.setParameter(2, curso);
        if (l != null) {
            q.setParameter(3, l.getIdlocalidade());
            //  q.setParameter(1, l.getIdlocalidade());
        } else if (p != null) {
            q.setParameter(3, p.getIdposto());
            //   q.setParameter(1, p.getIdposto());
        } else if (d != null) {
            q.setParameter(3, d.getIddistrito());
            // q.setParameter(1, d.getIddistrito());
        }
        return q.getResultList();
    }
    
    public List<Cursoformacao> getCursoformacao(Localidade l, Curso curso) {
        List<Relnrformando> lista = new ArrayList<Relnrformando>();
        EntityManager em = getEntityManager();
        String par = "";
//        if (l != null) {
//            par = "and localidade.idlocalidade = ?";
//        }
// new CursoformacaoJpaController().findCursoformacaoEntities();
//        if(cf!=null)em.(cf);
        Query q = em.createNativeQuery(""
                + "SELECT 0 as idcursoformacao, hh.qh as matriculadoH, mm.qm as matriculadoM,hh.qh+mm.qm as totalmatriculado, "
                + "aprm.aprm as formadosM, aprh.aprh as formadosH, aprm.aprm+aprh.aprh as totalformados, repm.repm as reprovadoM, reph.reph as reprovadoH, repm.repm+reph.reph as totalreprovados"
                + " ,desm.desm as desistenteM, desh.desh as desistenteH, desm.desm+desh.desh as totaldesistente"
                + " from ("
                + " select localidade.idlocalidade as l, count(formacaoturma.idformando) as qm "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formando.idformando = formacaoturma.idformando"
                + " and formando.sexo is not true and formando.idcurso = ? and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as mm, "
                + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as qh "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is true and formando.idcurso = ? and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as hh,"
                 + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as aprm "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is false and formando.idcurso = ? and formacaoturma.aprovacao is true and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as aprm,"
                 + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as aprh "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is true and formando.idcurso = ? and formacaoturma.aprovacao is true and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as aprh,"
                 + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as repm "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is not true and formando.idcurso = ? and formacaoturma.aprovacao is not true and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as repm,"
                 + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as reph "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is true and formando.idcurso = ? and formacaoturma.aprovacao is not true and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as reph,"
                 + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as desm "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is false and formando.idcurso = ? and formacaoturma.desistencia is true and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as desm,"
                 + "( select localidade.idlocalidade as l, count(formacaoturma.idformando) as desh "
                + "from localidade, formando, formacaoturma "
                + "where localidade.idlocalidade = formando.idlocalidade and formacaoturma.idformando = formando.idformando and "
                + "formando.sexo is true and formando.idcurso = ? and formacaoturma.desistencia is true and localidade.idlocalidade = ?"
                + " group by localidade.idlocalidade"
                + " union select null,0) as desh,"
                + " localidade where (mm.l is not null or mm.l is null)"
                + " group by localidade.idlocalidade order by totalmatriculado", Cursoformacao.class);
        q.setHint("eclipselink.refresh", true);
        q.setParameter(1, curso.getIdcurso());
         q.setParameter(2, l.getIdlocalidade());
        q.setParameter(3, curso.getIdcurso());
         q.setParameter(4, l.getIdlocalidade());
        q.setParameter(5, curso.getIdcurso());
         q.setParameter(6, l.getIdlocalidade());
        q.setParameter(7, curso.getIdcurso());
         q.setParameter(8, l.getIdlocalidade());
        q.setParameter(9, curso.getIdcurso());
         q.setParameter(10, l.getIdlocalidade());
        q.setParameter(11, curso.getIdcurso());
         q.setParameter(12, l.getIdlocalidade());
        q.setParameter(13, curso.getIdcurso());
         q.setParameter(14, l.getIdlocalidade());
        q.setParameter(15, curso.getIdcurso());
        q.setParameter(16, l.getIdlocalidade());
//        if (l != null) {
//           // q.setParameter(16, l.getIdlocalidade());
//        }
//        List<Cursoformacao> lc = 
//         System.out.println(lc.get(0).getTotalmatriculado()+" "+l.getIdlocalidade()+" "+curso.getIdcurso());
        return q.getResultList();
    }
    
     public List<Cursoformacao> getCursoformacaoLocalidade(Localidade l) {
        List<Relnrformando> lista = new ArrayList<Relnrformando>();
        EntityManager em = getEntityManager();
        Query q = em.createQuery(""
                + "from Cursoformacao cf where cf.idlocalidade = :l");
      
         q.setParameter("l", l);
       
        return q.getResultList();
    }

   

}
