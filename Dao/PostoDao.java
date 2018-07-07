/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Distrito;
import Modelo.Posto;
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
public class PostoDao {
     private Connection conexao;
      public PostoDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
     public List<Posto> mostrarPosto(int id) throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call postolista(?)");
     stmt.setInt(1, id);
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Posto> divolve = new ArrayList<Posto>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Posto p=new Posto();
                
           
               p.setIdposto(rs.getInt(1));
               p.setPosto(rs.getString(2));
                
               
    
                
               
                divolve.add(p);
            }
            return divolve;
        }else{
            return null;
            
            
        }
        
     }
    
    
}
