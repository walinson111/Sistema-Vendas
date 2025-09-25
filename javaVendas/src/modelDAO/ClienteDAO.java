package modelDAO;


import model.Cliente;
import model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "  INSERT INTO cliente (nome, endereco, email, telefone) VALUES (?,?,?,?);";
        
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
        String sql = "SELECT * FROM cliente WHERE id_ciente = ?"; 
        
        try{ PreparedStatement stmt = conn.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
        
            stmt.setInt(1, id); 
            ResultSet rs = stmt.executeQuery(); 

            Cliente c = new Cliente(); rs.first(); 
            c.setIdCliente(id); 
            c.setNomeCliente(rs.getString("nome")); 
            c.setEnderecoCliente(rs.getString("endereco")); 
            c.setEmailCliente(rs.getString("email")); 
            c.setTelefoneCliente("telefone"); 

            return c; } catch (SQLException ex) { 
            System.out.println("Erro ao consultar cliente: " + ex.getMessage()); 
            
            return null; } 
    }
    
    public List<Cliente> buscarTodos() {
        String sql = "SELECT id_ciente, nome, endereco, email, telefone FROM cliente;";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_ciente"));
                c.setNomeCliente(rs.getString("nome"));
                c.setEnderecoCliente(rs.getString("endereco"));
                c.setEmailCliente(rs.getString("email"));
                c.setTelefoneCliente(rs.getString("telefone"));
                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar clientes: " + ex.getMessage());
        }
        
        return clientes;
    }
    
    public Cliente buscarPorNome(String nomeCliente) {
        String sql = "SELECT id_ciente, nome, endereco, email, telefone FROM cliente WHERE nome = ?";
        Cliente cliente = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_ciente"));
                    cliente.setNomeCliente(rs.getString("nome"));
                    cliente.setEnderecoCliente(rs.getString("endereco"));
                    cliente.setEmailCliente(rs.getString("email"));
                    cliente.setTelefoneCliente(rs.getString("telefone"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar cliente por nome: " + ex.getMessage());
        }

        return cliente;
    }

    
    public void editar(Cliente cliente){
        try{
            String sql = "UPDATE cliente set nome=?, endereco=?, email=?, telefone=? WHERE id_ciente=?";
            
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
    
    public void deletar(int id){
        try{
            String sql = "delete from cliente WHERE id_cliente=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao excluir cliente: " + ex.getMessage());
        }
    }
    
    
}
