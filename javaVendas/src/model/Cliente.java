package model;


/**
 *
 * @author Walinson Pereira
 */
public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String enderecoCliente;
    private String emailCliente;
    private String telefoneCliente;
    
    public int getidCliente(){
        return(idCliente);
    }
    
    public String getNomeCliente(){
        return(nomeCliente);
    }
    
    public String getEnderecoCliente(){
        return(enderecoCliente);
    }
    
    public String getEmailCliente(){
        return(emailCliente);
    }
    
    public String getTelefoneCliente(){
        return(telefoneCliente);
    }
    
    public void setIdCliente(int idCli){
        idCliente = idCli;
    }
    
    public void setNomeCliente(String nCli){
        nomeCliente = nCli;
    }
    
    public void setEnderecoCliente(String enCli){
        enderecoCliente = enCli;
    }
    
    public void setEmailCliente(String emCli){
        emailCliente = emCli;
    }
    
    public void setTelefoneCliente(String tCli){
        telefoneCliente = tCli;
    }
    
    public String toString() {
        return getNomeCliente(); 
}

    
}
