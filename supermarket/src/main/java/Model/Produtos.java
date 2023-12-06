package Model;

public class Produtos {
    // atributos
    private String nomeProduto;
    private String codigoBarras; // chave primária 
    private String marca;
   
   // construtor
    public Produtos(String nomeProduto, String codigoBarras, String marca) {
        this.nomeProduto = nomeProduto;
        this.codigoBarras = codigoBarras;
        this.marca = marca;
    }

    // getters and setters
    public String getNomeProduto() {
        return nomeProduto;
    }


    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }


    public String getCodigoBarras() {
        return codigoBarras;
    }


    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }

    
}
