/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Formador;
import conexao.Conexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author MASSINGUE
 */
public class FormadorDao {
     private Connection conexao;
     
    public FormadorDao() throws SQLException{
        
        conexao= Conexao.getConexao();
    } 
    
    public void edit(Formador f) throws SQLException{
//           CallableStatement stmt=this.conexao.prepareCall("call editarformador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
             CallableStatement stmt=this.conexao.prepareCall("call editarformador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
             stmt.setInt(1, f.getIdformador());
             stmt.setString(2, f.getFormador());
             stmt.setBoolean(3, f.getSexo());
             stmt.setString(4, f.getMorada());
             stmt.setObject(5, f.getIddistrito().getIdlocalidade());
             stmt.setString(6, f.getEmail());
             stmt.setInt(7, f.getTelefone());
             stmt.setInt(8, f.getTelemovel());
             stmt.setString(9, f.getBi());
             stmt.setInt(10, f.getNuit());
        //     stmt.setDate(11, f.getDatanasc());
             stmt.setString(12, f.getProfissao());
             stmt.setInt(13, f.getAnosexperiencia());
             stmt.setBoolean(14, f.getEmpregado());
             stmt.setString(15, f.getEntidadeempregadora());
             stmt.setBoolean(16, f.getEmpresario());
             stmt.setString(17, f.getNomeempresa());
             stmt.setString(18, f.getOutrosituacao());
             stmt.setBoolean(19, f.getLicenciatura());
             stmt.setString(20, f.getAreaformacao());
             stmt.setBoolean(21, f.getSecundario());
             stmt.setString(22, f.getNivel());
             stmt.setBoolean(23, f.getTecnico());
             stmt.setString(24, f.getCurso());
             stmt.setBoolean(25, f.getOutro());
             stmt.setString(26, f.getEspecifique());
             stmt.setBoolean(27, f.getPsicopedagogia());
             stmt.setBoolean(28, f.getFormadorINEFP());
            // stmt.setByte(29, f.getCurricula());
             stmt.setInt(29, f.getExperienciaformador());
           stmt.execute();
           stmt.close();
           conexao.close();
          
       }
  
}
