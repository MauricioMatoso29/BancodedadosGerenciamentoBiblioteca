
package DAOs;

import Entidades.Emprestimo;
import Entidades.Reserva;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOEmprestimo2 {
     private Connection con;

    public DAOEmprestimo2() {
        this.con = new ConnectionFactory().getConnection();
    }

    public boolean getDados(Emprestimo emp) throws SQLException{
        String sql = "SELECT idemprestimo,dataemp,datadev,idusuario,idexemplar,idfuncionario FROM emprestimo where idemprestimo = ?";
        String dataemp = null,datadev=null;
        String idusario=null,idfuncionario=null;
        int id=0,ide=0;
        int compara=emp.getIdemprestimo();
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, emp.getIdemprestimo());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            id = rs.getInt("idemprestimo"); dataemp = rs.getString("dataemp"); datadev = rs.getString("datadev"); idusario=rs.getString("idusuario"); ide=rs.getInt("idexemplar"); idfuncionario=rs.getString("idfuncionario");
        }
        emp.setDataD(datadev);
        emp.setDataE(dataemp);
        emp.setIdexemplar(ide);
        emp.setIdemprestimo(id);
        emp.setIdfuncionario(idfuncionario);
        emp.setIdusuario(idusario);
          
       }catch (SQLException erro) {
             throw new RuntimeException(erro);       
        }
        if(compara==id){
            return true;
        }
       return false;
        
      
    }
    
      public void inserir(Emprestimo emp) {
    //falta ajeitar esse metodo//
        String sql = "insert into emprestimo "
                + "(dataemp, datadev, idusuario, idexemplar,idfuncionario )"
                + " values (?,?,?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, emp.getDataE());
            stmt.setString(2, emp.getDataD());
            stmt.setString(3, emp.getIdusuario());
            stmt.setInt(4, emp.getIdexemplar());
            stmt.setString(5, emp.getIdfuncionario());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Emprestimo getDadosParaDev(Emprestimo emp) throws SQLException{
        String sql = "SELECT idemprestimo,dataemp,datadev FROM emprestimo where idusuario = ? AND idexemplar=? AND idfuncionario=?";
        String dataemp = null,datadev=null;
        int id=0;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, emp.getIdusuario());
        stmt.setInt(2, emp.getIdexemplar());
        stmt.setString(3, emp.getIdfuncionario());
        
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            id = rs.getInt("idemprestimo"); dataemp = rs.getString("dataemp"); datadev = rs.getString("datadev");
        }
        
        emp.setDataD(datadev);
        emp.setDataE(dataemp);
        emp.setIdexemplar(emp.getIdexemplar());
        emp.setIdemprestimo(id);
        emp.setIdusuario(emp.getIdusuario());
        emp.setIdfuncionario(emp.getIdfuncionario());
        
        return emp;
      
    }
    
    public void excluir(Emprestimo em) throws SQLException{
         
       String sql = "DELETE FROM emprestimo where idemprestimo = ?";
       PreparedStatement stmt = con.prepareStatement(sql);
       stmt.setInt(1, em.getIdemprestimo());
       stmt.execute();
            stmt.close();
            con.close();
    }
    
    
}
