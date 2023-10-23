
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    private final String Driver = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost/leiloes";
    private final String User = "root";
    private final String Pass = "";
    Connection conn = null;
    public Connection connectDB(){
               
        try {
        
            conn = DriverManager.getConnection(URL,User,Pass);
            System.out.println("Conectado!!");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
   //public void close(){
    //    try {
    //        conn.close();
    //    } catch (SQLException ex) {
    //        Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
    //    }
   //}
    
}
