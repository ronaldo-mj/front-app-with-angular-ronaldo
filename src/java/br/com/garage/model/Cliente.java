package br.com.garage.model;



public class Cliente extends Pessoa {
    
    private Integer IdCliente;
    private String emailCliente;
    private String senhaCliente;
    private String obsCliente;
    private Integer nivelCliente;

    public Cliente(Integer IdCliente, String emailCliente, String senhaCliente, String obsCliente, Integer nivelCliente) {
        this.IdCliente = IdCliente;
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
        this.obsCliente = obsCliente;
        this.nivelCliente = nivelCliente;
    }

    public Cliente() {
    }

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public String getObsCliente() {
        return obsCliente;
    }

    public void setObsCliente(String obsCliente) {
        this.obsCliente = obsCliente;
    }

    public Integer getNivelCliente() {
        return nivelCliente;
    }

    public void setNivelCliente(Integer nivelCliente) {
        this.nivelCliente = nivelCliente;
    }

   

}