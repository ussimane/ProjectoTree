/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Distrito;
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
public class DistritoDao {
    private Connection conexao;
      public DistritoDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
     public List<Distrito> mostrarDistritos() throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call distritolista()");
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Distrito> divolve = new ArrayList<Distrito>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Distrito d=new Distrito();
                
           
               d.setIddistrito(rs.getInt(1));
               d.setDistrito(rs.getString(2));
                
               
    
                
               
                divolve.add(d);
            }
            return divolve;
        }else{
            return null;
            
            
        }
        
     }
    
}
