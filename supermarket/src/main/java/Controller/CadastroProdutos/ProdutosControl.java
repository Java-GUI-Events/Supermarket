package Controller.CadastroProdutos;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Produtos;

public class ProdutosControl {
     // Atributos
     private List<Produtos> produtos;
     private DefaultTableModel tableModel;
     private JTable table;

     // construtor
     public ProdutosControl(List<Produtos> produtos, DefaultTableModel tableModel, JTable table) {
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
    }



    // Método para atualizar a tabela de exibição com dados do banco de dados
     private void atualizarTabela() {
         tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
         produtos = new ProdutosDAO().listarTodos();
         // Obtém os produtos atualizados do banco de dados
         for (Produtos produto : produtos) {
             // Adiciona os dados de cada produto como uma nova linha na tabela Swing
             tableModel.addRow(new Object[] { produto.getNome(), produto.getCodigo(), produto.getQuantidade(), produto.getPreco()});
         }
     }

      // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(String nome, String codigo, String quantidade, String preco) {
        new ProdutosDAO().cadastrar(nome, codigo, quantidade, preco);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(String nome, String codigo, String quantidade, String preco) {
        new ProdutosDAO().atualizar(nome, codigo, quantidade, preco);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String codigo) {
        new ProdutosDAO().apagar(codigo);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
}
