/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Areadeformacao;
import Modelo.Formando;
import conexao.Conexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author MASSINGUE
 */
public class AreadeformacaoDao {
     private Connection conexao;
      public AreadeformacaoDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    } 
       public void create(Areadeformacao af) throws SQLException{
           CallableStatement stmt=this.conexao.prepareCall("call inserircursosformando(? ,? ,? ,?)");
           stmt.setInt(1, af.getFormando().getIdformando());
           stmt.setInt(2, af.getIdcurso1().getIdcurso());
           stmt.setInt(3, af.getIdcurso2().getIdcurso());
           stmt.setInt(4, af.getIdcurso3().getIdcurso());
           
           stmt.execute();
           stmt.close();
           conexao.close();
           
          
          
       }
       
       public void edit(Areadeformacao af) throws SQLException{
           CallableStatement stmt=this.conexao.prepareCall("call editareaformacao(? ,? ,? ,?)");
           stmt.setInt(1, af.getFormando().getIdformando());
           stmt.setInt(2, af.getIdcurso1().getIdcurso());
           stmt.setInt(3, af.getIdcurso2().getIdcurso());
           stmt.setInt(4, af.getIdcurso3().getIdcurso());
           
           stmt.execute();
           stmt.close();
           conexao.close();
           
          
          
       }
    
}
