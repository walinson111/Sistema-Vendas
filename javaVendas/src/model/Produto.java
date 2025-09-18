package model;


/**
 *
 * @author Walinson Pereira
 */
public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double precoProduto;
    private int qtdeProduto;

    public int getIdProduto() {
        return (idProduto);
    }

    public void setIdProduto(int idP) {
        idProduto = idP;
    }

    public String getNomeProduto() {
        return (nomeProduto);
    }

    public void setNomeProduto(String nP) {
        nomeProduto = nP;
    }

    public String getDescricaoProduto() {
        return (descricaoProduto);
    }

    public void setDescricaoProduto(String dscP) {
        descricaoProduto = dscP;
    }

    public double getPrecoProduto() {
        return (precoProduto);
    }

    public void setPrecoProduto(double prcP) {
        precoProduto = prcP;
    }

    public int getQtdeProduto() {
        return (qtdeProduto);
    }

    public void setQtdeProduto(int qtdeP) {
        qtdeProduto = qtdeP;
    }

    
    
    
}
