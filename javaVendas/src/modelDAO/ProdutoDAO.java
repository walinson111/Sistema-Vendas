package modelDAO;


import model.Conexao;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author Walinson Pereira
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProdutoDAO(){
        conexao = new Conexao();
        conn = conexao.getConexao();
    }
    
    public void inserir(Produto produto){
        String sql = "  INSERT INTO cliente (nome, descricao, preco, quantidade) VALUES (?,?,?, ?);";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, produto.getIdProduto());
            stmt.setString(2, produto.getNomeProduto());
            stmt.setString(3, produto.getDescricaoProduto());
            stmt.setDouble(4, produto.getPrecoProduto());
            stmt.setInt(5, produto.getQtdeProduto());
            
            stmt.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao inserir produto: " + ex.getMessage());
        }
    }
    
    public Produto getproduto(int id){
        String sql = "SELECT * FROM produto WHERE id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Produto p = new Produto();
            
            rs.first();
            p.setIdProduto(id);
            p.setNomeProduto(rs.getString("nome"));
            p.setDescricaoProduto(rs.getString("endereco"));
            p.getPrecoProduto();
            p.setQtdeProduto(id);
            return p;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }
}
