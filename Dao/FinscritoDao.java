/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Finscritos;
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
public class FinscritoDao {
     private Connection conexao;
      public FinscritoDao() throws SQLException{
    
        conexao= Conexao.getConexao();
    } 
    
    public boolean verificarcursodisponivel(Finscritos fs) throws SQLException{
          CallableStatement stmt= this.conexao.prepareCall("call procurarlocalidade(?)");
          stmt.setInt(1, fs.getIdlocalidade());
         
          ResultSet rs = stmt.executeQuery();
      
          Finscritos m = new Finscritos();
      
          List<Finscritos> divolve = new ArrayList<Finscritos>();
       
       
          if (rs.next()) {
              Finscritos es=new Finscritos();
              es.setIdlocalidade(rs.getInt(1));
               
               rs.close();
               stmt.close();
               
               return true;
          }else
          {
               
              rs.close();
               stmt.close(); 
            return false;
        }          
               
        }  
    
    public void actaulizaInscritos(Finscritos m)throws SQLException{
          CallableStatement stmt=this.conexao.prepareCall("call actualizarFinscritos(?,?,?)");
          
          stmt.setInt(1, m.getIdlocalidade());
          stmt.setInt(2, m.getQhomem());
          stmt.setInt(3, m.getQmulher());
          
          stmt.execute();
          stmt.close();
          conexao.close();
     
      
      }
    
    public void inscrever(Finscritos m)throws SQLException{
          CallableStatement stmt=this.conexao.prepareCall("call inserirFinscritos(?,?,?)");
          
          stmt.setInt(1, m.getIdlocalidade());
          stmt.setInt(2, m.getQhomem());
          stmt.setInt(3, m.getQmulher());
          
          stmt.execute();
          stmt.close();
          conexao.close();
     
      
      }
    
}
