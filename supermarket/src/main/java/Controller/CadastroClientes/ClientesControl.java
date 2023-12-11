package Controller.CadastroClientes;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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
    public void cadastrar(String nome, String cpf, String dataNascimento) {
        if (validarNome(nome) && validarCPF(cpf) && validarDataNascimento(dataNascimento)) {
            new ClientesDAO().cadastrar(nome, cpf, dataNascimento);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Por favor, verifique as informações inseridas.");
        }
    }
    // Método para atualizar os dados de um produto no banco de dados
    public void atualizar(String nome, String cpf, String dataNascimento) {
        if (validarNome(nome) && validarCPF(cpf) && validarDataNascimento(dataNascimento)) {
            new ClientesDAO().atualizar(nome, cpf, dataNascimento);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Por favor, verifique as informações inseridas.");
        }
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String cpf) {
        if (validarCPF(cpf)) {
            new ClientesDAO().apagar(cpf);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido. Por favor, insira um CPF válido para apagar o cliente.");
        }
    }

    // MÉTODOS PARA VALIDAÇÃO DOS INPUTS

    // Método para VALIDAÇÃO do NOME contendo apenas LETRAS
    private boolean validarNome(String nome) {
        if (nome.matches("[a-zA-ZÀ-ú\\s]+")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Nome inválido. Insira apenas letras.");
            return false;
        }
    }

    // Método para VALIDAÇÃO do CPF, utilizando os PADRÕES
   private boolean validarCPF(String cpf) {
        if (cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido. Insira um CPF válido no formato ###.###.###-##.");
            return false;
        }
    }

    // Método para VALIDAÇÃO da DATA, utilizando SIMPLEDATEFORMAT
    private boolean validarDataNascimento(String dataNascimento) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Não ACEITA datas inexistentes
            sdf.parse(dataNascimento);
            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Insira uma data válida no formato dd/MM/yyyy.");
            return false;
        }
    }

    public JFormattedTextField criarCampoCPFFormatado() {
        JFormattedTextField cpfField = null;
        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            cpfField = new JFormattedTextField(mf);
            cpfField.setPreferredSize(new Dimension(150, 25));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpfField;
    }

}
