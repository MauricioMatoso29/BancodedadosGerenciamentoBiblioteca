
package DAOs;

import DAOs.ConnectionFactory;
import java.sql.*;

public class Teste {

    public static void main(String[] args) {
// conectando
        Connection con = new ConnectionFactory().getConnection();
            if(con != null){
                System.out.println("Conectado com sucesso");
            }else{
                System.out.println("conectado sem sucesso");
            }
        
//con.close();
    }
}
