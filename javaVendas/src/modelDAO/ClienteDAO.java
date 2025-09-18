package modelDAO;


import model.Cliente;
import model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Walinson Pereira
 */
public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ClienteDAO(){
        conexao = new Conexao();
        conn = conexao.getConexao();
    }
    
    public void inserir(Cliente cliente){
        String sql = "  INSERT INTO cliente (nome_Cliente, endereco_Cliente, email_Cliente, telefone_Cliente) VALUES (?,?,?,?);";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getEnderecoCliente());
            stmt.setString(3, cliente.getEmailCliente());
            stmt.setString(4, cliente.getTelefoneCliente());
            
            stmt.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao inserir cliente: " + ex.getMessage());
        }
    }
    
    public Cliente getCliente(int id){ 
        String sql = "SELECT * FROM cliente WHERE id = ?"; 
        
        try{ PreparedStatement stmt = conn.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
        
        stmt.setInt(1, id); 
        ResultSet rs = stmt.executeQuery(); 
        
        Cliente c = new Cliente(); rs.first(); 
        c.setIdCliente(id); 
        c.setNomeCliente(rs.getString("nome_Cliente")); 
        c.setEnderecoCliente(rs.getString("endereco_Cliente")); 
        c.setEmailCliente(rs.getString("email_Cliente")); 
        c.setTelefoneCliente("telefone_Cliente"); 
        
        return c; } 
        
        catch (SQLException ex) { 
            System.out.println("Erro ao consultar cliente: " + ex.getMessage()); 
            
            return null; } }

    
    public void editar(Cliente cliente){
        try{
            String sql = "UPDATE cliente set nome=?, endereco=?, email=?, telefone=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getEnderecoCliente());
            stmt.setString(3, cliente.getEmailCliente());
            stmt.setString(4, cliente.getTelefoneCliente());
            stmt.setInt(5, cliente.getidCliente());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao atualizar cliente: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            String sql = "delete from cliente WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao excluir cliente: " + ex.getMessage());
        }
    }
}
