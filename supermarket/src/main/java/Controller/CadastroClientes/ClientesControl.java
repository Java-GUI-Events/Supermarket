package Controller.CadastroClientes;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Clientes;

public class ClientesControl {
    // Atributos
     private List<Clientes> clientes;
     private DefaultTableModel tableModel;
     private JTable table;

     // construtor
     public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }
     

    // Método para atualizar a tabela de exibição com dados do banco de dados
     private void atualizarTabela() {
         tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
         clientes = new ClientesDAO().listarTodos();
         // Obtém os produtos atualizados do banco de dados
         for (Clientes cliente : clientes) {
             // Adiciona os dados de cada produto como uma nova linha na tabela Swing
             tableModel.addRow(new Object[] { cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento()});
         }
     }


    // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(String nome, String cpf, String nascimento) {
        new ClientesDAO().cadastrar(nome, cpf, nascimento);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(String nome, String cpf, String nascimento) {
        new ClientesDAO().atualizar(nome, cpf, nascimento);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String cpf) {
        new ClientesDAO().apagar(cpf);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
    
}
