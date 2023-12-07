package Model;

public class Produtos {
    // atributos
    private String nomeProduto;
    private String codigoBarras; // chave primária 
    private String quantidade;
    private String preco;


    // construtor
    public Produtos(String nomeProduto, String codigoBarras, String quantidade, String preco) {
        this.nomeProduto = nomeProduto;
        this.codigoBarras = codigoBarras;
        this.quantidade = quantidade;
        this.preco = preco;
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
    public String getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }


    
}
