package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CadastroProdutos.ProdutosDAO;
import Model.Produtos;
import Model.Vendas;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

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
        JPanel pagarPanel = new JPanel();
        JPanel pesquisaPanel = new JPanel();

        // Layout dos Painéis
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new BorderLayout());

        // Construindo a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Código");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Preço");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        // Definindo o tamanho dos JTextField
        inputCPF = new JTextField(20);
        inputProduto = new JTextField(20);

        // Definindo a escrita dos JLabel
        labelCPF = new JLabel("CPF");
        labelProduto = new JLabel("CÓDIGO PRODUTO");

        // Definindo os botões JButton
        btnPesquisar = new JButton("Pesquisar Cliente");
        btnProduto = new JButton("Adicionar Produto");
        btnPagar = new JButton("Fechar Pedido");

        // Adicionando os JLabel e os JTextField ao inputPanel
        pesquisaPanel.add(labelCPF);
        pesquisaPanel.add(inputCPF);
        pesquisaPanel.add(btnPesquisar);

        pesquisaPanel.add(labelProduto);
        pesquisaPanel.add(inputProduto);
        pesquisaPanel.add(btnProduto);

        pagarPanel.add(btnPagar);

        // Adicionando os JButton ao btnPanel

        // Definindo o mainPanel
        this.add(mainPanel);
        // mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pesquisaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pagarPanel, BorderLayout.SOUTH);



        btnProduto.addActionListener(e -> {
            String codigoProduto = inputProduto.getText();
            ProdutosDAO produtosDAO = new ProdutosDAO();
            Produtos produto = produtosDAO.buscarPorCodigo(codigoProduto);
            preencherTabelaProduto(produto); // Adiciona o produto na tabela
        });

    }

    private void preencherTabelaProduto(Produtos produto) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Adicionando o produto a tabela
        Object[] listProducts = {
                produto.getNome(),
                produto.getCodigo(),
                produto.getQuantidade(),
                produto.getPreco()
        };
        model.addRow(listProducts);
    }

}
