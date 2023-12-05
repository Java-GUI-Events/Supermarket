package Controller.CadastroProdutos;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.*;

public class CadastroControl {
     // Atributos
     private List<CadastroProdutos> produtos;
     private DefaultTableModel tableModel;
     private JTable table;

     // construtor
     public CadastroControl(List<CadastroProdutos> produtos, DefaultTableModel tableModel, JTable table) {
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
    }



    // Método para atualizar a tabela de exibição com dados do banco de dados
     private void atualizarTabela() {
         tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
         produtos = new CadastroDAO().listarTodos();
         // Obtém os produtos atualizados do banco de dados
         for (CadastroProdutos produto : produtos) {
             // Adiciona os dados de cada produto como uma nova linha na tabela Swing
             tableModel.addRow(new Object[] { produto.getNomeProduto(), produto.getCodigoBarras(), produto.getMarca() });
         }
     }

      // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(String marca, String nomeProduto, String codigoBarras) {
        new CadastroDAO().cadastrar(marca, nomeProduto, codigoBarras);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(String marca, String nomeProduto, String codigoBarras) {
        new CadastroDAO().atualizar(marca, nomeProduto, codigoBarras);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String codigoBarras) {
        new CadastroDAO().apagar(codigoBarras);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
}
