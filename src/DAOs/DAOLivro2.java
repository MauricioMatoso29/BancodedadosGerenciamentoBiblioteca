package DAOs;

import Entidades.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author T-Gamer
 */
public class DAOLivro2 {
     private Connection con;

    public DAOLivro2() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void inserir(Livro l) {

        String sql = "insert into livro "
                + "(idlivro, titulo, autor, editora, ano, descricao, totexemplares, exemplaresdisp)"
                + " values (?,?,?,?,?,?,?,?)";
        try {
// prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);
// seta os valores
            stmt.setString(1, l.getIdlivro());
            stmt.setString(2, l.getTitulo());
            stmt.setString(3, l.getAutor());
            stmt.setString(4, l.getEditora());
            stmt.setString(5, l.getAno());
            stmt.setString(6, l.getDescricao());
            stmt.setInt(7, l.getTotexemplares());
            stmt.setInt(8, l.getExemplaresdisp());
// executa
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public boolean getDados(Livro l) throws SQLException{
        String sql = "SELECT idlivro,titulo,autor,editora,ano,descricao,totexemplares,exemplaresdisp FROM livro where idlivro = ?";
        String idlivro = null,titulo = null,autor=null,editora=null,ano=null;
        String descricao=null;
        int totexemplares=0,exemplaresdisp = 0;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,l.getIdlivro());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            idlivro = rs.getString("idlivro"); titulo = rs.getString("titulo"); autor = rs.getString("autor"); editora=rs.getString("editora"); ano=rs.getString("ano"); descricao=rs.getString("descricao"); totexemplares=rs.getInt("totexemplares"); exemplaresdisp=rs.getInt("exemplaresdisp");
        }
        l.setTitulo(titulo);
        l.setAutor(autor);
        l.setEditora(editora);
        l.setAno(ano);
        l.setDescricao(descricao);
        l.setExemplaresdisp(exemplaresdisp);
        l.setTotexemplares(totexemplares);
        
          
        if(validaLogin(l,idlivro)){
            return true;
        }             
        return false;
        
      
    }
    
    public boolean validaLogin(Livro l,String login){
        if(l.getIdlivro().toString().equals(login)){
            return true;
        }
        return false;
        
    }
    
    public void update(Livro l) {

        String sql = "UPDATE livro SET titulo=? ,autor=? ,editora =?,exemplaresdisp =? WHERE idlivro=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setString(3, l.getEditora());
            stmt.setInt(4, l.getExemplaresdisp());
            stmt.setString(5, l.getIdlivro());

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
     public void updateExemDisp(Livro l) {

        String sql = "UPDATE livro SET exemplaresdisp =? WHERE idlivro=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            int atual=l.getExemplaresdisp()-1;
            
            stmt.setInt(1, atual);
            stmt.setString(2, l.getIdlivro());

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
