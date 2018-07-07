/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Nivelhabilitacao;
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
public class NivelHabilitacoesDao {
     private Connection conexao;
      public NivelHabilitacoesDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    }
       public List<Nivelhabilitacao> prenchernivel() throws SQLException{
       
     CallableStatement stmt= this.conexao.prepareCall("call prenchernivelhabilitacao()");
     //stmt.setInt(1, id);
     
    
       ResultSet rs = stmt.executeQuery();
       
      List<Nivelhabilitacao> divolve = new ArrayList<Nivelhabilitacao>();
       
        if (rs!=null) {
            while (rs.next()) {                
                Nivelhabilitacao nh=new Nivelhabilitacao();
                nh.setIdnivelhabilitacao(rs.getInt(1));
                nh.setNivelhabilitacao(rs.getString(2));
             
                divolve.add(nh);
            }
            return divolve;
        }else{
            return null;
            
            
        }
    
}
}
