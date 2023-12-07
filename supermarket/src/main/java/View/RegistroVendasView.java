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

public class RegistroVendasView extends JPanel {
    // Atributos

    // JTextField
    private JTextField inputCPF;
    private JTextField inputProduto;

    // JLabel
    private JLabel labelCPF;
    private JLabel labelProduto;

    // JButton
    private JButton btnPesquisar;
    private JButton btnProduto;
    private JButton btnPagar;

    // JTable - Tabela
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Vendas> vendas = new ArrayList<>();
    private int linhaSelecionada = -1;

    // Construtor
    public RegistroVendasView() {
        // JPanel - Painéis
        JPanel mainPanel = new JPanel();
        JPanel pesquisarPanel = new JPanel();
        JPanel produtoPanel = new JPanel();
        JPanel pagarPanel = new JPanel();

        // Layout dos Painéis
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new BorderLayout());

        // Construindo a tabela
        // tableModel = new DefaultTableModel();
        // tableModel.addColumn("ID Venda");
        // tableModel.addColumn("CPF");
        // tableModel.addColumn("Data Venda");
        // tableModel.addColumn("Valor");
        // table = new JTable(tableModel);
        // JScrollPane scrollPane = new JScrollPane();
        // scrollPane.setViewportView(table);

        // Definindo o tamanho dos JTextField
        inputCPF = new JTextField(20);
        inputProduto = new JTextField(20);

        // Definindo a escrita dos JLabel
        labelCPF = new JLabel("CPF");
        labelProduto = new JLabel("CÓDIGO PRODUTO");

        // Definindo os botões JButton
        btnPesquisar = new JButton("Pesquisar Cliente");
        btnProduto = new JButton("Pesquisar Produto");
        btnPagar = new JButton("Fechar Pedido");

        // Adicionando os JLabel e os JTextField ao inputPanel
        pesquisarPanel.add(labelCPF);
        pesquisarPanel.add(inputCPF);
        pesquisarPanel.add(btnPesquisar);

        produtoPanel.add(labelProduto);
        produtoPanel.add(inputProduto);
        produtoPanel.add(btnProduto);

        pagarPanel.add(btnPagar);


        // Adicionando os JButton ao btnPanel

        // Definindo o mainPanel
        this.add(mainPanel);
        //mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pesquisarPanel, BorderLayout.NORTH);
        mainPanel.add(produtoPanel, BorderLayout.CENTER);
        mainPanel.add(pagarPanel, BorderLayout.SOUTH);
        
    }

}
