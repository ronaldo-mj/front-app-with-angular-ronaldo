
package br.com.garage.model;

public class Veiculo {
    private Integer idVeiculo;
    private String nomeVeiculo;
    private int anoFabricacaoVeiculo;
    private Double valorVeiculo;
    private Marca marca;

    public Veiculo(Integer idVeiculo, String nomeVeiculo, int anoFabricacaoVeiculo, Double valorVeiculo, Marca marca) {
        this.idVeiculo = idVeiculo;
        this.nomeVeiculo = nomeVeiculo;
        this.anoFabricacaoVeiculo = anoFabricacaoVeiculo;
        this.valorVeiculo = valorVeiculo;
        this.marca = marca;
    }

    public Veiculo() {
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public int getAnoFabricacaoVeiculo() {
        return anoFabricacaoVeiculo;
    }

    public void setAnoFabricacaoVeiculo(int anoFabricacaoVeiculo) {
        this.anoFabricacaoVeiculo = anoFabricacaoVeiculo;
    }

    public Double getValorVeiculo() {
        return valorVeiculo;
    }

    public void setValorVeiculo(Double valorVeiculo) {
        this.valorVeiculo = valorVeiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setMarca(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
