package Model;

public class EstoqueVendas {
    // atributos
    private String nomeComprador;
    private String tipoProduto;
    private String dataVenda;
    private String valor;

    // getters and setters
    public String getNomeComprador() {
        return nomeComprador;
    }
    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }
    public String getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public String getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

     // construtor
    public EstoqueVendas(String nomeComprador, String tipoProduto, String dataVenda, String valor) {
        this.nomeComprador = nomeComprador;
        this.tipoProduto = tipoProduto;
        this.dataVenda = dataVenda;
        this.valor = valor;
    } 
}
