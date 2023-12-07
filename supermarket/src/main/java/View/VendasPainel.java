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

import Model.Vendas;

import java.awt.*;

public class VendasPainel extends JPanel {
    // Atributos

    // JTextField
    private JTextField inputCliente;
    private JTextField inputProduto;
    private JTextField inputData;
    private JTextField inputValor;

    // JLabel
    private JLabel labelCliente;
    private JLabel labelProduto;
    private JLabel labelData;
    private JLabel labelValor;

    // JButton
    private JButton btnCadastrar;

    // JTable - Tabela
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Vendas> vendas = new ArrayList<>();
    private int linhaSelecionada = -1;

    // Construtor
    public VendasPainel() {
        // JPanel - Painéis
        JPanel mainPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        // Layout dos Painéis
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new BorderLayout());

        // Construindo a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Produto");
        tableModel.addColumn("Data");
        tableModel.addColumn("Valor");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        // Definindo o tamanho dos JTextField
        inputCliente = new JTextField(20);
        inputProduto = new JTextField(20);
        inputData = new JTextField(20);
        inputValor = new JTextField(20);

        // Definindo a escrita dos JLabel
        labelCliente = new JLabel("Cliente");
        labelProduto = new JLabel("Produto");
        labelData = new JLabel("Data");
        labelValor = new JLabel("Valor");

        // Definindo os botões JButton
        btnCadastrar = new JButton("Cadastrar");

        // Adicionando os JLabel e os JTextField ao inputPanel
        inputPanel.add(labelCliente);
        inputPanel.add(inputCliente);
        inputPanel.add(labelProduto);
        inputPanel.add(inputProduto);
        inputPanel.add(labelData);
        inputPanel.add(inputData);
        inputPanel.add(labelValor);
        inputPanel.add(inputValor);

        // Adicionando os JButton ao btnPanel
        btnPanel.add(btnCadastrar);

        // Definindo o mainPanel
        this.add(mainPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
    }

}
