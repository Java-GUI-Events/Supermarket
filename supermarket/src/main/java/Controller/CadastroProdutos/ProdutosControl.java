package Controller.CadastroProdutos;
import java.util.List;

import javax.swing.JOptionPane;
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
         tableModel.setRowCount(0);
         produtos = new ProdutosDAO().listarTodos();
         for (Produtos produto : produtos) {
             tableModel.addRow(new Object[] { produto.getNome(), produto.getCodigo(), produto.getQuantidade(), produto.getPreco()});
         }
     }

      // Método para cadastrar um novo produto no banco de dados
      public void cadastrar(String nome, String codigo, String quantidade, String preco) {
        if (validarNome(nome) && validarCodigo(codigo) && validarQuantidade(quantidade) && validarPreco(preco)) {
            new ProdutosDAO().cadastrar(nome, codigo, quantidade, preco);
            atualizarTabela();
        } else {
            System.out.println("Dados inválidos");
        }
    }

    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(String nome, String codigo, String quantidade, String preco) {
        if (validarNome(nome) && validarCodigo(codigo) && validarQuantidade(quantidade) && validarPreco(preco)) {
            new ProdutosDAO().atualizar(nome, codigo, quantidade, preco);
            atualizarTabela();
        } else {
            System.out.println("Dados inválidos.");
        }
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String codigo) {
        if (validarCodigo(codigo)) {
            new ProdutosDAO().apagar(codigo);
            atualizarTabela();
        } else {
            System.out.println("Código inválido. Por favor, insira um código válido para apagar o produto.");
        }
    }



    // MÉTODOS DE VALIDAÇÕES DE INPUT

    // Método para VALIDAR se o NOME contém apenas LETRAS
    private boolean validarNome(String nome) {
        if (nome.matches("[a-zA-ZÀ-ú\\s]+")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Nome inválido. Insira apenas letras.");
            return false;
        }
    }

    // Método para VALDIAR se o CÓDIGO possui no MÁXIMO 2 DIGÍTOS
    private boolean validarCodigo(String codigo) {
        if (codigo.matches("\\d{1,2}")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Código inválido. Insira um código com no máximo dois dígitos.");
            return false;
        }
    }

    // Método para VALIDAR se a QUANTIDADE tem no MÁXIMO 100
    private boolean validarQuantidade(String quantidade) {
        try {
            int valorQuantidade = Integer.parseInt(quantidade);
            if (valorQuantidade >= 0 && valorQuantidade <= 100) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade inválida. Insira um valor entre 0 e 100.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida. Insira um número válido para a quantidade.");
            return false;
        }
    }

    // Método para VALIDAR se o PREÇO contém APENAS NÚMEROS
    private boolean validarPreco(String preco) {
        if (preco.matches("^\\d+(.|,)\\d{1,2}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Preço inválido. Insira apenas números para o preço.");
            return false;
        }
    }
}
