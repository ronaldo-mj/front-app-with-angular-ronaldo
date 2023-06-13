
package br.com.garage.model;


public class Pessoa {
    private Integer idPessoa;
    private String nomePessoa;
    private String cpfCnpjPessoa;
    private String rgIePessoa;
    private String dataNascimento;

    public Pessoa() {
    }

    public Pessoa(Integer idPessoa, String nomePessoa, String cpfCnpjPessoa, String rgIePessoa, String dataNascimento) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.cpfCnpjPessoa = cpfCnpjPessoa;
        this.rgIePessoa = rgIePessoa;
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpfCnpjPessoa() {
        return cpfCnpjPessoa;
    }

    public void setCpfCnpjPessoa(String cpfCnpjPessoa) {
        this.cpfCnpjPessoa = cpfCnpjPessoa;
    }

    public String getRgIePessoa() {
        return rgIePessoa;
    }

    public void setRgIePessoa(String rgIePessoa) {
        this.rgIePessoa = rgIePessoa;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    

        
}
