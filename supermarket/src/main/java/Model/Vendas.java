package Model;

public class Vendas {
    // atributos
    private String idVenda;
    private String dataVenda;
    private String valor;
    private String cpf;

    // construtor
    public Vendas(String idVenda, String dataVenda, String valor, String cpf) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.valor = valor;
        this.cpf = cpf;
    }

    // getters and setters
    public String getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
