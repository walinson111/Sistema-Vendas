package modelDAO;


import model.Conexao;
import model.Produto;
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
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProdutoDAO(){
        conexao = new Conexao();
        conn = conexao.getConexao();
    }
    
    public void inserir(Produto produto){
        String sql = "  INSERT INTO produto (nome, descrição, preco, quantidade_estoque) VALUES (?,?,?, ?);";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setDouble(3, produto.getPrecoProduto());
            stmt.setInt(4, produto.getQtdeProduto());
            
            stmt.execute();
        }catch (SQLException ex){
            System.out.println("Erro ao inserir produto: " + ex.getMessage());
        }
    }
    
    public Produto getProduto(int id){
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Produto p = new Produto();
            
            rs.first();
            p.setIdProduto(id);
            p.setNomeProduto(rs.getString("nome"));
            p.setDescricaoProduto(rs.getString("descrição"));
            p.setPrecoProduto(rs.getDouble("preco"));
            p.setQtdeProduto(rs.getInt("quantidade_estoque"));
            return p;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }   
    
    public List<Produto> buscarTodos() {
        String sql = "SELECT id_produto, nome, descrição, preco, quantidade_estoque FROM produto;";
        List<Produto> produtos = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id_produto"));
                p.setNomeProduto(rs.getString("nome"));
                p.setDescricaoProduto(rs.getString("descrição"));
                p.setPrecoProduto(rs.getDouble("preco"));
                p.setQtdeProduto(rs.getInt("quantidade_estoque"));
                produtos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produtos: " + ex.getMessage());
        }
        
        return produtos;
    }
    
    
    public Produto buscarPeloNome(String nome) {
        String sql = "SELECT id_produto, preco FROM produto WHERE nome = ?";
        Produto produto = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setIdProduto(rs.getInt("id_produto"));
                    produto.setPrecoProduto(rs.getDouble("preco"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produto pelo nome: " + ex.getMessage());
        }

        return produto;
    }
   
    
    public void deletar(int id){
        try{
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir produto: " + ex.getMessage());
        }
    }

    public void editar(Produto produto) {
        try {
            String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id_produto = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getNomeProduto());
                stmt.setString(2, produto.getDescricaoProduto());
                stmt.setDouble(3, produto.getPrecoProduto());
                stmt.setInt(4, produto.getQtdeProduto());
                stmt.setInt(5, produto.getIdProduto());
                
                stmt.executeUpdate();
            }

            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto: " + ex.getMessage());
        }
    }
    
    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setNomeProduto(rs.getString("nome"));
                produto.setDescricaoProduto(rs.getString("descricao"));
                produto.setPrecoProduto(rs.getDouble("preco"));
                produto.setQtdeProduto(rs.getInt("quantidade"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
    }
        return produtos;
    }
}
