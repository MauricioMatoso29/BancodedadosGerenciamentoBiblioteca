/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entidades.Devoluçao;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author T-Gamer
 */
public class DAODevolucao {
      private Connection con;

    public DAODevolucao() {
        this.con = new ConnectionFactory().getConnection();
    }
    
     public void inserir(Devoluçao dev) {

        String sql = "insert into devolucao "
                + "(iddevolucao, datad, atrasado, valormulta, idemprestimo, idusuario,idfuncionario,idexemplar)"
                + " values (?,?,?,?,?,?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setInt(1, dev.getIddevolucao());
            stmt.setString(2, dev.getDatad());
            stmt.setString(3, dev.getAtrasado());
            stmt.setFloat(4, dev.getValormulta());
            stmt.setInt(5, dev.getIdemprestimo());
            stmt.setString(6, dev.getIdusuario());
            stmt.setString(7, dev.getIdfuncionario());
            stmt.setInt(8, dev.getIdexemplar());
            
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
    
    public boolean getDados(Devoluçao dev) throws SQLException{
        String sql = "SELECT iddevolucao,datad,atrasado,valormulta,idemprestimo,idusuario,idfuncionario,idexemplar FROM devolucao where iddevolucao = ?";
        String datad = null,atrasado=null;
        String valorm=null,idfuncionario=null,idusuario=null;
        int idexemplar=0;
        int iddevolucao=0;
        int idemprestimo=0;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,dev.getIddevolucao());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            idemprestimo = rs.getInt("idemprestimo"); datad = rs.getString("datad"); iddevolucao = rs.getInt("iddevolucao"); idusuario=rs.getString("idusuario"); idexemplar=rs.getInt("idexemplar"); idfuncionario=rs.getString("idfuncionario");valorm=rs.getString("valormulta");atrasado=rs.getString("atrasado");
        }
        dev.setAtrasado(atrasado);
        dev.setDatad(datad);
        dev.setIddevolucao(iddevolucao);
        dev.setIdemprestimo(idemprestimo);
        dev.setIdexemplar(idexemplar);
        dev.setIdfuncionario(idfuncionario);
        dev.setIdusuario(idusuario);
        float mu=Float.parseFloat(valorm);
        dev.setValormulta(mu);
          
        if(validaLogin(dev,iddevolucao)){
            return true;
        }             
        return false;
        
      
    }
    
    public boolean validaLogin(Devoluçao dev,int login){
        if(dev.getIddevolucao()==login){
            return true;
        }
        return false;
        
    }
    
    public long calculaTempAtraso(String d){
       LocalDate agora = LocalDate.now();                //data atual
       LocalDate prazo = LocalDate.parse(d);//data do prazo de devoluçao
       
       if(agora.compareTo(prazo)>0){
        long dife = (ChronoUnit.DAYS.between(agora, prazo))*-1;           
            return dife;
        }
       
      return 0;
      
    }
    
    
}
