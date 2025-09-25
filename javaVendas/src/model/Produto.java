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

    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public void setQtdeProduto(int qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }
    
    public int getIdProduto() {
        return (idProduto);
    }

    public String getNomeProduto() {
        return (nomeProduto);
    }


    public String getDescricaoProduto() {
        return (descricaoProduto);
    }


    public double getPrecoProduto() {
        return (precoProduto);
    }


    public int getQtdeProduto() {
        return (qtdeProduto);
    }
   
    
}
