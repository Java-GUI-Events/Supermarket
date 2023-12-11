package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CadastroProdutos.ProdutosDAO;
import Controller.RegistroVendas.VendasControl;
import Controller.RegistroVendas.VendasDAO;
import Model.Produtos;
import Model.Vendas;
import Controller.CadastroClientes.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

import java.time.LocalDate;

public class RegistroVendasView extends JPanel {
    // Atributos

    // JTextField
    private JFormattedTextField inputCPF;
    private JTextField inputProduto;
    private JTextField valorTotal;

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
        ClientesDAO clientesDAO = new ClientesDAO();
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
        table.setDefaultEditor(Object.class, null);

        // Definindo o tamanho dos JTextField
        ClientesControl clientesControl = new ClientesControl(null, tableModel, table);
        inputCPF = clientesControl.criarCampoCPFFormatado();
        inputProduto = new JTextField(20);
        valorTotal = new JTextField(10);
        valorTotal.setEditable(false);

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

        pagarPanel.add(valorTotal);
        pagarPanel.add(btnPagar);

        // Adicionando os JButton ao btnPanel

        // Definindo o mainPanel
        this.add(mainPanel);
        // mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pesquisaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pagarPanel, BorderLayout.SOUTH);


        // Criando a tabela no banco de dados
        new VendasDAO().criaTabela();

        VendasControl operacoes = new VendasControl(vendas, tableModel, table);

        // Tratamento de evento para o botão de ADICIONAR os produtos pelo código
        btnProduto.addActionListener(e -> {
            String codigoProduto = inputProduto.getText();
            ProdutosDAO produtos = new ProdutosDAO();
            Produtos produto = produtos.buscarProduto(codigoProduto);
            tabelaPreenchida(produto);
        });


        // Tratamento de evento para o botão de pesquisar se o cliente está CADASTRADO
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = inputCPF.getText();
                boolean cpfEncontrado = clientesDAO.verificarCPF(cpf);

                if (cpfEncontrado) {
                    JOptionPane.showMessageDialog(null, "CPF encontrado no banco de dados!");
                } else {
                    JOptionPane.showMessageDialog(null, "CPF não encontrado no banco de dados.");
                }
            }
        });

        // Tratamento de evento para o botão de PAGAR, que irá REGISTRAR a venda no BANCO DE DADOS
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // id = autoincremental no banco de dados
                String cpfDoCliente = inputCPF.getText();
                double totalVenda = Double.parseDouble(valorTotal.getText());
                LocalDate dataDaVenda = LocalDate.now();
            }
        });
    }

    private void TotalProdutos() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double precoTotal = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            String precoString = model.getValueAt(i, 3).toString();
            double preco = Double.parseDouble(precoString);
            precoTotal += preco;
        }
        valorTotal.setText(String.valueOf(precoTotal));
    }

    private void tabelaPreenchida(Produtos produto) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Adicionando o produto a tabela
        Object[] listProducts = {
                produto.getNome(),
                produto.getCodigo(),
                produto.getQuantidade(),
                produto.getPreco()
        };
        model.addRow(listProducts);
        TotalProdutos();
    }


}
