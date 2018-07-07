/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Modelo.Utilizador;
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
public class ValidarUtilizador {
    private Connection conexao;
     
    public ValidarUtilizador() throws SQLException{
        
        conexao= Conexao.getConexao();
    }  
       public List<Utilizador> validarUtilizador() throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call validarutilizador()");
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Utilizador> divolve = new ArrayList<Utilizador>();
       
        if (rs!=null) {
            while (rs.next()) {                
               Utilizador u=new Utilizador();
                
           
               u.setIdutilizador(rs.getInt(1));
               u.setNome(rs.getString(2));
               u.setUtilizador(rs.getString(3));
               u.setSenha(rs.getString(4));
               
               
                divolve.add(u);
            }
            return divolve;
        }else{
            return null;
            
            
        }
        
     } 
       
       public Utilizador validar(String u,String s) throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call validar(?,?)");
     stmt.setString(1, u);
     stmt.setString(2, s);
    
       ResultSet rs = stmt.executeQuery();
       Utilizador ut=new Utilizador();
   
        if (rs!=null) {
            while (rs.next()) {                
               
               ut.setIdutilizador(rs.getInt(1));
               ut.setNome(rs.getString(2));
               ut.setUtilizador(rs.getString(3));
               ut.setSenha(rs.getString(4));
             
            }
            
        }
            return ut;      
        } 
    
}
