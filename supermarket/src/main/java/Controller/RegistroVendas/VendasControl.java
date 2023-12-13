package Controller.RegistroVendas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.CadastroClientes.ClientesDAO;
import Controller.CadastroProdutos.ProdutosDAO;
import Model.Vendas;


public class VendasControl {
    // Atributos
     private List<Vendas> vendas;
     private DefaultTableModel tableModel;
     private JTable table;

     // construtor
     public VendasControl(List<Vendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
     private void atualizarTabela() {
         tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
         //vendas = new VendasDAO().listarTodos();
         // Obtém os produtos atualizados do banco de dados
         for (Vendas venda : vendas) {
             // Adiciona os dados de cada produto como uma nova linha na tabela Swing
             tableModel.addRow(new Object[] { venda.getIdVenda(), venda.getCpf(), venda.getDataVenda(), venda.getValor()});
         }
     }

      // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(String idVenda, String cpf, String dataVenda, String valor) {
        new ProdutosDAO().cadastrar(idVenda, cpf, dataVenda, valor);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    

    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(String idVenda, String cpf, String dataVenda, String valor) {
      
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String idvenda) {
        new ProdutosDAO().apagar(idvenda);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
    }
    
