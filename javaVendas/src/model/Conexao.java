package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Walinson Pereira
 */
public class Conexao {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/javavendas?useTimezone=true&serverTimezone=UTC",
            "root","");
            
            System.out.println("Conexão realizada com sucesso!");
            
            return conn;
        }
        catch(Exception e){
            System.out.println("Erro ao conectar no BD" + e);
            
            return null;
        }
    }
}
