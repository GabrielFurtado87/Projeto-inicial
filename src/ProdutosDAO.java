/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (NOME,VALOR,ESTATUS) VALUES (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + sQLException);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void venderProduto(int id){
        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement("UPDATE produtos SET ESTATUS = 'vendido' WHERE ID = ?");
            prep.setInt(1, id);
                        
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto "+ id + "Vendido com sucesso!");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + sQLException);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
       
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            conn = new conectaDAO().connectDB();            
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO produtos = new ProdutosDTO();
                
                produtos.setId(resultset.getInt("id"));
                produtos.setNome(resultset.getString("NOME"));
                produtos.setValor(resultset.getInt("VALOR"));
                produtos.setStatus(resultset.getString("ESTATUS"));
                
                listagem.add(produtos);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         return listagem;
    }
    
    
    
        
}

