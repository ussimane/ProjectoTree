/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Localidade;
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
public class LocalidadeDao {
     private Connection conexao;
      public LocalidadeDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
     public List<Localidade> mostrarLocalidade(int id) throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call localidadelista(?)");
     stmt.setInt(1, id);
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Localidade> divolve = new ArrayList<Localidade>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Localidade l=new Localidade();
                
           
               l.setIdlocalidade(rs.getInt(1));
               l.setLocalidade(rs.getString(2));
                
               
    
                
               
                divolve.add(l);
            }
            return divolve;
        }else{
            return null;
            
            
        }
        
     }
    
    
}
