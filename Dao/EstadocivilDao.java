/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Estadocivil;
import Modelo.Nivelhabilitacao;
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
public class EstadocivilDao {
     private Connection conexao;
      public EstadocivilDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
       public List<Estadocivil> prencherstadocivil() throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call prencherestado()");
     //stmt.setInt(1, id);
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Estadocivil> divolve = new ArrayList<Estadocivil>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Estadocivil ec=new Estadocivil();
                ec.setIdestadocivil(rs.getInt(1));
                ec.setEstado(rs.getString(2));
             
                divolve.add(ec);
            }
            return divolve;
        }else{
            return null;
            
            
        }
    
}
    
}
