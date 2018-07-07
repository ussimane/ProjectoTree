/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Curso;
import Modelo.Estadocivil;
import conexao.Conexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MASSINGUE
 */
public class CursoDao {
     private Connection conexao;
      public CursoDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
       public List<Curso> prencherCurso() throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call prenchercurso()");
     //stmt.setInt(1, id);
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Curso> divolve = new ArrayList<Curso>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Curso c=new Curso();
                c.setIdcurso(rs.getInt(1));
                c.setCurso(rs.getString(2));
             
                divolve.add(c);
            }
            return divolve;
        }else{
            return null;
            
            
        }
    
}
       
        public void editcurso(Curso c) throws SQLException{
             String sql="update curso c"
                     +" set c.curso=?"
                     + "where c.idcurso=?";
             PreparedStatement stmt=(PreparedStatement) this.conexao.prepareCall(sql);
             stmt.setInt(1, c.getIdcurso());
             stmt.setString(2, c.getCurso());
             
             stmt.execute();
             stmt.close();
             conexao.close();
          
       }
    
}
