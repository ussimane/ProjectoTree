/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Curso;
import Modelo.Distrito;
import Modelo.Formando;
import Modelo.Localidade;
import com.mysql.jdbc.PreparedStatement;
import conexao.Conexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MASSINGUE
 */
public class FormandoDao {
     private Connection conexao;
      public FormandoDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    } 
       public void create(Formando f) throws SQLException{
           CallableStatement stmt=this.conexao.prepareCall("call adicionarFormando(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,? ,?,? ,? ,? ,? ,? ,?)");
           stmt.setString(1, f.getNome());
           stmt.setInt(2, f.getContacto());
           stmt.setBoolean(3, f.getSexo());
           stmt.setInt(4, f.getIdade());
           stmt.setObject(5, f.getIdestadocivil().getIdestadocivil());
           stmt.setObject(6, f.getIdlocalidade().getIdlocalidade());
           stmt.setInt(7, f.getNragregado());
           stmt.setBoolean(8, f.getChefefamilia());
           stmt.setBoolean(9, f.getFilho());
           stmt.setInt(10, f.getNrfilhos());
           stmt.setBoolean(11, f.getCasapropria());
           stmt.setBoolean(12, f.getDificiente());
           stmt.setString(13, f.getTipodificiencia());
           stmt.setObject(14, f.getIdnivelhabilitacao().getIdnivelhabilitacao());
           stmt.setBoolean(15, f.getDiplomainefp());
           stmt.setString(16, f.getCurso());
           stmt.setBoolean(17, f.getDiplomaprof());
           stmt.setObject(18, f.getIdnivelprof().getIdnivelprofissional());
           stmt.setString(19, f.getOutrahabilidades());
           stmt.setBoolean(20, f.getActividadeprofissional());
           stmt.setString(21, f.getAreaactividade());
           stmt.setBoolean(22, f.getEmpregado());
           stmt.setString(23, f.getEmpregador());
           stmt.setObject(24, f.getIdcurso().getIdcurso());
           stmt.setInt(25, f.getRendimentomedio());
           stmt.execute();
           stmt.close();
           conexao.close();
          
       }
       
        public void teste(Formando f) throws SQLException{
           CallableStatement stmt=this.conexao.prepareCall("call adicionar(? ,?,?,?,?,? )");
           stmt.setString(1, f.getNome());
           stmt.setInt(2, f.getContacto());
           stmt.setBoolean(3, f.getSexo());
           stmt.setInt(4, f.getIdade());
           
           stmt.setObject(5, f.getIdestadocivil().getIdestadocivil());
          
           stmt.setObject(6, f.getIdcurso().getIdcurso());
           
           stmt.execute();
           stmt.close();
           conexao.close();
          
       }
       
      public int buscarIDCadastro() throws SQLException{
       int codigo=0;
       
      Formando f=new Formando();
       CallableStatement stmt= this.conexao.prepareCall("call lastID()");
      ResultSet rs = stmt.executeQuery();
      rs.last();
      
      codigo=rs.getInt("idformando");
       
       
            return codigo;
        }
      
       public List<Formando> listarFormandopesquisado(String n) throws SQLException{
     CallableStatement stmt= this.conexao.prepareCall("call prencherFormandoNome(?)");
     
        stmt.setString(1, n);
    
       ResultSet rs = stmt.executeQuery();
       
       List<Formando> divolve = new ArrayList<Formando>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Formando f=new Formando();
                Localidade l=new Localidade();
                Distrito d=new Distrito();
                Curso c=new Curso();
                
                 f.setIdformando(rs.getInt(1));
                 f.setNome(rs.getString(2));
                 f.setSexo(rs.getBoolean(3));
                 f.setIdade(rs.getInt(4));
                 l.setLocalidade(rs.getString(5));
                 f.setIdlocalidade(l);
                 d.setDistrito(rs.getString(6));
                 f.setOutrahabilidades(rs.getString(6));
                 c.setCurso(rs.getString(7));
                 f.setIdcurso(c);
              
                 divolve.add(f);
            }
            return divolve;
        }else{
            return null;
            
            
        }
     }
       
        public List<Formando> listarFormando() throws SQLException{
     CallableStatement stmt= this.conexao.prepareCall("call prencherFormando()");
     
        
    
       ResultSet rs = stmt.executeQuery();
       
       List<Formando> divolve = new ArrayList<Formando>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Formando f=new Formando();
                Localidade l=new Localidade();
                Distrito d=new Distrito();
                Curso c=new Curso();
                
                 f.setIdformando(rs.getInt(1));
                 f.setNome(rs.getString(2));
                 f.setSexo(rs.getBoolean(3));
                 f.setIdade(rs.getInt(4));
                 l.setLocalidade(rs.getString(5));
                 f.setIdlocalidade(l);
                 f.setOutrahabilidades(rs.getString(6));
                 c.setCurso(rs.getString(7));
                 f.setIdcurso(c);
              
                 divolve.add(f);
            }
            return divolve;
        }else{
            return null;
            
            
        }
     }
         public void destroy(Formando f) throws SQLException{
             String sql="DELETE FROM formando where formando.idformando=?";
             PreparedStatement stmt=(PreparedStatement) this.conexao.prepareCall(sql);
             stmt.setInt(1, f.getIdformando());
          
           stmt.execute();
           stmt.close();
           conexao.close();
          
       }
         
//          public void edit(Formando f) throws SQLException{
//             String sql="update formando f"
//                     +" set f.idformando=?," +" f.nome=?," + " f.contacto=?," + " f.sexo=?," + " f.idade=?," +" f.idestadocivil=?," +" f.idlocalidade=?," +" f.nragregado=?,"
//                     + "f.chefefamilia=?," +" f.filho=?,"+ " f.nrfilhos=?,"+ " f.casapropria=?,"+ " f.dificiente=?,"+ " f.tipodificiencia=?,"+ " f.idnivelhabilitacao=?,"
//                     + " f.diplomainefp=?,"+ " f.curso=?,"+ " f.diplomaprof=?," +" f.idnivelprof=?,"+ " f.outrahabilidades=?,"+ " f.actividadeprofissional=?,"
//                     + " f.areaactividade=?,"+ " f.empregado=?,"+ " f.empregador=?,"+ " f.idcurso=?,"+ " f.rendimentomedio=?"
//                     + "where f.idformando=?";
//             PreparedStatement stmt=(PreparedStatement) this.conexao.prepareCall(sql);
//             stmt.setInt(1, f.getIdformando());
//             stmt.setString(2, f.getNome());
//             stmt.setInt(3, f.getContacto());
//             stmt.setBoolean(4, f.getSexo());
//             stmt.setInt(5, f.getIdade());
//             stmt.setObject(6, f.getIdestadocivil().getIdestadocivil());
//             stmt.setObject(7, f.getIdlocalidade().getIdlocalidade());
//             stmt.setInt(8, f.getNragregado());
//             stmt.setBoolean(9, f.getChefefamilia());
//             stmt.setBoolean(10, f.getFilho());
//             stmt.setInt(11, f.getNrfilhos());
//             stmt.setBoolean(12, f.getCasapropria());
//             stmt.setBoolean(13, f.getDificiente());
//             stmt.setString(14, f.getTipodificiencia());
//             stmt.setObject(15, f.getIdnivelhabilitacao().getIdnivelhabilitacao());
//             stmt.setBoolean(16, f.getDiplomainefp());
//             stmt.setString(17, f.getCurso());
//             stmt.setBoolean(18, f.getDiplomaprof());
//             stmt.setObject(19, f.getIdnivelprof().getIdnivelprofissional());
//             stmt.setString(20, f.getOutrahabilidades());
//             stmt.setBoolean(21, f.getActividadeprofissional());
//             stmt.setString(22, f.getAreaactividade());
//             stmt.setBoolean(23, f.getEmpregado());
//             stmt.setString(24, f.getEmpregador());
//             stmt.setObject(25, f.getIdcurso().getIdcurso());
//             stmt.setInt(26, f.getRendimentomedio());
//             stmt.setInt(27, f.getIdformando());
//             stmt.execute();
//             stmt.close();
//             conexao.close();
//          
//       }
         
          public void edit(Formando f) throws SQLException{
//             CallableStatement stmt= this.conexao.prepareCall("call editarFormando(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
             CallableStatement stmt= this.conexao.prepareCall("call editarFormando(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
             stmt.setInt(1, f.getIdformando());
             stmt.setString(2, f.getNome());
             stmt.setInt(3, f.getContacto());
             stmt.setBoolean(4, f.getSexo());
             stmt.setInt(5, f.getIdade());
             stmt.setObject(6, f.getIdestadocivil().getIdestadocivil());
             stmt.setObject(7, f.getIdlocalidade().getIdlocalidade());
             stmt.setInt(8, f.getNragregado());
             stmt.setBoolean(9, f.getChefefamilia());
             stmt.setBoolean(10, f.getFilho());
             stmt.setInt(11, f.getNrfilhos());
             stmt.setBoolean(12, f.getCasapropria());
             stmt.setBoolean(13, f.getDificiente());
             stmt.setString(14, f.getTipodificiencia());
             stmt.setObject(15, f.getIdnivelhabilitacao().getIdnivelhabilitacao());
             stmt.setBoolean(16, f.getDiplomainefp());
             stmt.setString(17, f.getCurso());
             stmt.setBoolean(18, f.getDiplomaprof());
             //stmt.setObject(19, f.getIdnivelprof().getIdnivelprofissional());
//             stmt.setString(20, f.getOutrahabilidades());
//             stmt.setBoolean(21, f.getActividadeprofissional());
//             stmt.setString(22, f.getAreaactividade());
//             stmt.setBoolean(23, f.getEmpregado());
//             stmt.setString(24, f.getEmpregador());
//             stmt.setObject(25, f.getIdcurso().getIdcurso());
//             stmt.setInt(26, f.getRendimentomedio());
             stmt.setString(19, f.getOutrahabilidades());
             stmt.setBoolean(20, f.getActividadeprofissional());
             stmt.setString(21, f.getAreaactividade());
             stmt.setBoolean(22, f.getEmpregado());
             stmt.setString(23, f.getEmpregador());
             stmt.setObject(24, f.getIdcurso().getIdcurso());
             stmt.setInt(25, f.getRendimentomedio());
            // stmt.setInt(27, f.getIdformando());
             stmt.execute();
             stmt.close();
             conexao.close();
          
       }
          
          
           public List<Formando> listarTurma(int lo) throws SQLException{
            CallableStatement stmt= this.conexao.prepareCall("call listaturma(?)");
            stmt.setInt(1, lo);
            ResultSet rs = stmt.executeQuery();
       
               List<Formando> divolve = new ArrayList<Formando>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Formando f=new Formando();
                Localidade l=new Localidade();
                Distrito d=new Distrito();
                Curso c=new Curso();
                
                 l.setLocalidade(rs.getString(1));
                 f.setIdlocalidade(l);
                 f.setNome(rs.getString(2));
                 f.setSexo(rs.getBoolean(3));
                 f.setIdade(rs.getInt(4));
                 f.setContacto(rs.getInt(5));
                 f.setIdlocalidade(l);
                // f.setOutrahabilidades(rs.getString(6));
                 c.setCurso(rs.getString(6));
                 f.setIdcurso(c);
              
                 divolve.add(f);
            }
            return divolve;
        }else{
            return null;
            
            
        }
     }
      public List<Formando> listarTurma2(int lo) throws SQLException{
            CallableStatement stmt= this.conexao.prepareCall("call listaturma2(?)");
            stmt.setInt(1, lo);
            ResultSet rs = stmt.executeQuery();
       
               List<Formando> divolve = new ArrayList<Formando>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Formando f=new Formando();
                Localidade l=new Localidade();
                Distrito d=new Distrito();
                Curso c=new Curso();
                
                 l.setLocalidade(rs.getString(1));
                 f.setIdlocalidade(l);
                 f.setNome(rs.getString(2));
                 f.setSexo(rs.getBoolean(3));
                 f.setIdade(rs.getInt(4));
                 f.setContacto(rs.getInt(5));
                 f.setIdlocalidade(l);
                // f.setOutrahabilidades(rs.getString(6));
                 c.setCurso(rs.getString(6));
                 f.setIdcurso(c);
              
                 divolve.add(f);
            }
            return divolve;
        }else{
            return null;
            
            
        }
     }     
    
}
