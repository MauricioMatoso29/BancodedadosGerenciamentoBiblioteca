/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entidades.Exemplar;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author T-Gamer
 */
public class DAOExemplar2 {
     private Connection con;

    public DAOExemplar2() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     *
     * @param u
     */
    public void inserir(Exemplar ex) {

        String sql = "insert into exemplar "
                + "(idexemplar, situaçao, idlivro)"
                + " values (?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setInt(1, ex.getIdexemplar());
            stmt.setString(2, ex.getSituação());
            stmt.setString(3, ex.getIdlivro());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getDados(Exemplar ex) throws SQLException{
        String sql = "SELECT idexemplar,situaçao,idlivro FROM exemplar where idexemplar = ?";
        String senha = null,unome=null;
        int idexem = 0;
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,ex.getIdexemplar());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            idexem = rs.getInt("idexemplar"); senha = rs.getString("situaçao"); unome = rs.getString("idlivro");
        }
        ex.setIdexemplar(idexem);
        ex.setSituação(senha);
        ex.setIdlivro(unome);
     
          
        if(validaLogin(ex,idexem)){
            return true;
        }             
        return false;
        
      
    }
    
    public boolean validaLogin(Exemplar ex,int login){
        if(ex.getIdexemplar()==login){
            return true;
        }
        return false;
        
    }
    
        public void update(Exemplar ex) {

        String sql = "UPDATE exemplar SET situaçao=? ,idlivro=? WHERE idexemplar=?";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, ex.getSituação());
            stmt.setString(2, ex.getIdlivro());
            stmt.setInt(3, ex.getIdexemplar());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
