/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INFOSAT
 */
public class Conexao {
    public static Connection getConexao() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
         //   System.out.println("Conetado ao Banco");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ptree","root","root");
        
        
    
    
}
}
