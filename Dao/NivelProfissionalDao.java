/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Curso;
import Modelo.Nivelprofissional;
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
public class NivelProfissionalDao {
     private Connection conexao;
      public NivelProfissionalDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
       public List<Nivelprofissional> prenchernivelprofissional() throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call prenchernivelprofissional()");
     //stmt.setInt(1, id);
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Nivelprofissional> divolve = new ArrayList<Nivelprofissional>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Nivelprofissional c=new Nivelprofissional();
                c.setIdnivelprofissional(rs.getInt(1));
                c.setNivelprofissional(rs.getString(2));
             
                divolve.add(c);
            }
            return divolve;
        }else{
            return null;
            
            
        }
    
}
    
}
