
package DAOs;

import Entidades.Devoluçao;
import Entidades.Reserva;
import Entidades.Usuario;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DAOReserva {
       private Connection con;

    public DAOReserva() {
        this.con = new ConnectionFactory().getConnection();
    }

    public boolean getDados(Reserva rev) throws SQLException{
        String sql = "SELECT idreserva,idusuario,idlivro,datar FROM reserva where idreserva = ?";
        String idusu = null,idlivro=null,datar=null;
        int id =0;
        int compara = rev.getIdreserva();
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rev.getIdreserva());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            id = rs.getInt("idreserva"); idusu = rs.getString("idusuario"); idlivro = rs.getString("idlivro"); datar=rs.getString("datar");
        }
        
        rev.setDatar(datar);
        rev.setIdlivro(idlivro);
        rev.setIdreserva(id);
        rev.setIsusuario(idusu);
        
        }catch (SQLException erro) {
             throw new RuntimeException(erro);       
        }
        if(compara==id){
            return true;
        }
       return false;
    }
    
    
     public void inserir(Reserva rev) {

        String sql = "insert into reserva "
                + "(idusuario, idlivro, datar)"
                + " values (?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, rev.getIsusuario());
            stmt.setString(2, rev.getIdlivro());
            stmt.setString(3, rev.getDatar());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     
    public void excluir(Reserva rev) throws SQLException{
         
       String sql = "DELETE FROM reserva where idreserva = ?";
       PreparedStatement stmt = con.prepareStatement(sql);
       stmt.setInt(1, rev.getIdreserva());
       stmt.execute();
            stmt.close();
            con.close();
    }
    


    
}
