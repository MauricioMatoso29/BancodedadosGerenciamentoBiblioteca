/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entidades.Funcionario;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author T-Gamer
 */
public class DAOFuncionario {
     private Connection con;

    public DAOFuncionario() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     *
     * @param u
     */
    public void inserir(Funcionario f) {

        String sql = "insert into funcionario "
                + "(idfuncionario, fnome, fcpf, senha)"
                + " values (?,?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, f.getIdfuncionario());
            stmt.setString(2, f.getFnome());
            stmt.setString(3, f.getFcpf());
            stmt.setString(4, f.getFsenha());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getDados(Funcionario f) throws SQLException{
        String sql = "SELECT idfuncionario,fnome,fcpf,senha FROM funcionario where idfuncionario = ?";
        String idfuncionario = null,nome = null,cpf=null;
        String senha=null;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,f.getIdfuncionario().toString());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            idfuncionario = rs.getString("idfuncionario"); nome = rs.getString("fnome"); cpf = rs.getString("fcpf"); senha=rs.getString("senha");
        }
        f.setFnome(nome);
        f.setFcpf(cpf);
        f.setIdfuncionario(idfuncionario);
        f.setFsenha(senha);
          
        if(validaLogin(f,idfuncionario)){
            return true;
        }             
        return false;
        
      
    }
    
    public boolean validaLogin(Funcionario f,String login){
        if(f.getIdfuncionario().toString().equals(login)){
            return true;
        }
        return false;
        
    }
    
        public void update(Funcionario f) {

        String sql = "UPDATE funcionario SET fnome=? ,fcpf=? ,senha=? WHERE idfuncionario=?";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, f.getFnome());
            stmt.setString(2, f.getFcpf());
            stmt.setString(3, f.getFsenha());
            stmt.setString(4, f.getIdfuncionario());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
