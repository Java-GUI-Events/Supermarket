package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import Model.ClientesVIP;

public class CadastroClientes extends JPanel{
    // Atributos

    // JTextField
    private JTextField inputNome;
    private JTextField inputCPF;
    private JTextField inputDataNasc;
    private JTextField inputTelefone;
    private JTextField inputCEP;
    
    // JLabel
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelDataNasc;
    private JLabel labelTelefone;
    private JLabel labelCEP;

    // JButton
    private JButton btnCadastrar;
    private JButton btnApagar;
    private JButton btnAtualizar;

    // JTable - Tabela
    private DefaultTableModel tableModel;
    private JTable table;
    private List<ClientesVIP> clientesVIP = new ArrayList<>();
    private int linhaSelecionada = -1;

    // Construtor
    public CadastroClientes() {
        // JPanel - Painéis
        JPanel mainPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        // Layout dos Painéis
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new BorderLayout());

        // Construindo a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Data de Nascimento");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("CEP");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        // Definindo o tamanho dos JTextField
        inputNome = new JTextField(20);
        inputCPF = new JTextField(20);
        inputDataNasc = new JTextField(20);
        inputTelefone = new JTextField(20);
        inputCEP = new JTextField(20);

        // Definindo a escrita dos JLabel
        labelNome = new JLabel("Nome");
        labelCPF = new JLabel("CPF");
        labelDataNasc = new JLabel("Data de Nascimento");
        labelTelefone = new JLabel("Telefone");
        labelCEP = new JLabel("CEP");

        // Definindo os botões JButton
        btnCadastrar = new JButton("Cadastrar");
        btnApagar = new JButton("Apagar");
        btnAtualizar = new JButton("Atualizar");

        // Adicionando os JLabel e os JTextField ao inputPanel
        inputPanel.add(labelNome);
        inputPanel.add(inputNome);
        inputPanel.add(labelCPF);
        inputPanel.add(inputCPF);
        inputPanel.add(labelDataNasc);
        inputPanel.add(inputDataNasc);
        inputPanel.add(labelTelefone);
        inputPanel.add(inputTelefone);
        inputPanel.add(labelCEP);
        inputPanel.add(inputCEP);

        // Adicionando os JButton ao btnPanel
        btnPanel.add(btnCadastrar);
        btnPanel.add(btnApagar);
        btnPanel.add(btnAtualizar);

        // Definindo o mainPanel
        this.add(mainPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
    }

    
}
