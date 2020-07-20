
package DAOs;

import Entidades.Usuario;
import java.sql.*;

public class DAOUsuario2 {
    private Connection con;

    public DAOUsuario2() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     *
     * @param u
     */
    public void inserir(Usuario u) {

        String sql = "insert into usuario "
                + "(idusuario, unome, ucpf, sexo, telefone, senha)"
                + " values (?,?,?,?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, u.getIdusuario());
            stmt.setString(2, u.getUnome());
            stmt.setString(3, u.getUcpf());
            stmt.setString(4, u.getSexo());
            stmt.setString(5, u.getTel());
            stmt.setString(6, u.getSenha());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getDados(Usuario u) throws SQLException{
        String sql = "SELECT idusuario,senha,unome,ucpf,sexo,telefone FROM usuario where idusuario = ?";
        String idusuario = null,senha = null,unome=null;
        String ucpf=null,sexo=null,telefone=null;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,u.getIdusuario().toString());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            idusuario = rs.getString("idusuario"); senha = rs.getString("senha"); unome = rs.getString("unome"); ucpf=rs.getString("ucpf"); sexo=rs.getString("sexo"); telefone=rs.getString("telefone");
        }
        u.setUnome(unome);
        u.setUcpf(ucpf);
        u.setSexo(sexo);
        u.setTel(telefone);
        u.setSenha(senha);
          
        if(validaLogin(u,idusuario)){
            return true;
        }             
        return false;
        
      
    }
    
    public boolean validaLogin(Usuario u,String login){
        if(u.getIdusuario().toString().equals(login)){
            return true;
        }
        return false;
        
    }
    
        public void update(Usuario u) {

        String sql = "UPDATE usuario SET unome=? ,telefone=? ,senha=?,sexo=? WHERE idusuario=?";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, u.getUnome());
            stmt.setString(2, u.getTel());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getSexo());
            stmt.setString(5, u.getIdusuario());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
