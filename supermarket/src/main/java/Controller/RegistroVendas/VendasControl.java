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

      // Método para cadastrar venda
    public void cadastrar(String idVenda, String cpf, String dataVenda, String valor) {
        new ProdutosDAO().cadastrar(idVenda, cpf, dataVenda, valor);
        // Chama o método de cadastro no banco de dados
    }
    }
    
