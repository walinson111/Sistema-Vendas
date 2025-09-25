package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Conexao;
import model.ItemVenda;
import model.Venda;
import model.VendaCompleta;

public class VendaDAO {
    
    private Conexao conexao;
    private Connection conn;
    
    public VendaDAO(){
        conexao = new Conexao();
        conn = conexao.getConexao();
    }

    public int salvar(Venda venda) {
        String sql = "INSERT INTO vendas (id_cliente, data_venda, total_venda) VALUES (?, ?, ?)";
        int idVendaGerado = -1;

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            
            stmt.setInt(1, venda.getIdCliente());
            stmt.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setDouble(3, venda.getTotalVenda());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idVendaGerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idVendaGerado;
    }
    
    public Venda buscarPorId(int idVenda) {
    String sql = "SELECT * FROM vendas WHERE id_venda = ?";
    Venda venda = null;

    try (Connection conn = new Conexao().getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idVenda);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                venda = new Venda();
                venda.setIdVenda(rs.getInt("id_venda"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setTotalVenda(rs.getDouble("total_venda"));

                ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
                List<ItemVenda> itens = itemVendaDAO.buscarPorVendaId(idVenda);
                venda.setItens(itens);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return venda;
}

    
    public List<VendaCompleta> buscarTodasNotasFiscais() {
    List<VendaCompleta> notasFiscais = new ArrayList<>();
    
    String sql = "SELECT v.id_venda, "
               + "c.nome AS nome_cliente, "
               + "p.nome AS nome_produto, "
               + "iv.preco_uni, "
               + "iv.quantidade_vendida, "
               + "v.total_venda "
  
               + "FROM vendas v "
               + "JOIN cliente c ON v.id_cliente = c.id_ciente "
               + "JOIN itens_venda iv ON v.id_venda = iv.id_venda "
               + "JOIN produto p ON iv.id_produto = p.id_produto";
    
    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
         
        while (rs.next()) {
            VendaCompleta nota = new VendaCompleta(
                rs.getInt("id_venda"),
                rs.getString("nome_cliente"),
                rs.getString("nome_produto"),
                rs.getDouble("preco_uni"),
                rs.getInt("quantidade_vendida"),
                rs.getDouble("total_venda")
            );
            notasFiscais.add(nota);
        }
        
    } catch (SQLException e) {
        System.err.println("Erro ao buscar notas fiscais: " + e.getMessage());
    }
    
    return notasFiscais;
}
    
    public void editar(Venda venda) {
        String sql = "UPDATE vendas SET id_cliente = ?, data_venda = ?, total_venda = ? WHERE id_venda = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql))
              {
            
            stmt.setInt(1, venda.getIdCliente());
            stmt.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setDouble(3, venda.getTotalVenda());
            stmt.setInt(4, venda.getIdVenda());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletar(int idVenda) {
        String sql = "DELETE FROM vendas WHERE id_venda = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql))
           {
            
            stmt.setInt(1, idVenda);
            stmt.executeUpdate();
            
            ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
            itemVendaDAO.deletarPorVendaId(idVenda);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
