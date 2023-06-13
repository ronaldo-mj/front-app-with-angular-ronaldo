
package br.com.garage.model;

public class Marca {
    private Integer idMarca;
    private String nomeMarca;
    private String descricaoMarca;

    public Marca(Integer idMarca, String nomeMarca, String descricaoMarca) {
        this.idMarca = idMarca;
        this.nomeMarca = nomeMarca;
        this.descricaoMarca = descricaoMarca;
    }

    public Marca() {
    }

    public Marca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Marca(Integer idMarca, String nomeMarca) {
        this.idMarca = idMarca;
        this.nomeMarca = nomeMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public String getDescricaoMarca() {
        return descricaoMarca;
    }

    public void setDescricaoMarca(String descricaoMarca) {
        this.descricaoMarca = descricaoMarca;
    }
    
}
