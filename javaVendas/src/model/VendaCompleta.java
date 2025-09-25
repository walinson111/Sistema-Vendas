/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ryzen
 */
public class VendaCompleta {
    private int idVenda;
    private String nomeCliente;
    private String nomeProduto;
    private double precoUnitario;
    private int quantidade;
    private double valorTotal;

    public VendaCompleta(int idVenda, String nomeCliente, String nomeProduto, double precoUnitario, int quantidade, double valorTotal) {
        this.idVenda = idVenda;
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    // Adicione os métodos Getters para todos os campos
    public int getIdVenda() {
        return idVenda;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
}
