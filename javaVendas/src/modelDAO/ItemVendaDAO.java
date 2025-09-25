package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Conexao;
import model.ItemVenda;

public class ItemVendaDAO {
    
    private Conexao conexao;
    private Connection conn;
    
    public ItemVendaDAO(){
        conexao = new Conexao();
        conn = conexao.getConexao();
    }

    public void salvarItens(int idVenda, List<ItemVenda> itens) {
        String sql = "INSERT INTO itens_venda (id_venda, id_produto, quantidade_vendida, preco_uni) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql))
              {
            
            for (ItemVenda item : itens) {
                stmt.setInt(1, idVenda);
                stmt.setInt(2, item.getIdProduto());
                stmt.setInt(3, item.getQuantidadeVendida());
                stmt.setDouble(4, item.getPrecoUnitario());
                
                stmt.addBatch();
            }
            stmt.executeBatch();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<ItemVenda> buscarPorVendaId(int idVenda) {
        String sql = "SELECT * FROM itens_venda WHERE id_venda = ?";
        List<ItemVenda> itens = new ArrayList<>();

        try (Connection conn = new Conexao().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemVenda item = new ItemVenda();
                    item.setIdItem(rs.getInt("id_item"));
                    item.setIdVenda(rs.getInt("id_venda"));
                    item.setIdProduto(rs.getInt("id_produto"));
                    item.setQuantidadeVendida(rs.getInt("quantidade_vendida"));
                    item.setPrecoUnitario(rs.getDouble("preco_uni"));

                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    public void deletarPorVendaId(int idVenda) {
        String sql = "DELETE FROM itens_venda WHERE id_venda = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql))
              {
            
            stmt.setInt(1, idVenda);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}