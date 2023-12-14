package View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CadastroProdutos.ProdutosDAO;
import Controller.RegistroVendas.VendasDAO;
import Model.Produtos;
import Controller.CadastroClientes.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroVendasView extends JPanel {
    // Atributos

    // JTextField
    private JFormattedTextField inputCPF;
    private JTextField inputProduto;
    private JTextField valorTotal;
    private JTextField nomeCliente;

    // JLabel
    private JLabel labelCPF;
    private JLabel labelProduto;
    private JLabel labelValorTotal;

    // JButton
    private JButton btnPesquisar;
    private JButton btnProduto;
    private JButton btnPagar;
    private JButton btnApagar;

    // JTable - Tabela
    private DefaultTableModel tableModel;
    private JTable table;

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

        nomeCliente = new JTextField(20);
        nomeCliente.setEditable(false);
        nomeCliente.setVisible(false);

        // Definindo a escrita dos JLabel
        labelCPF = new JLabel("CPF");
        labelProduto = new JLabel("CÓDIGO PRODUTO");
        labelValorTotal = new JLabel("Valor Total: ");

        // Definindo os botões JButton
        btnPesquisar = new JButton("Pesquisar Cliente");
        btnProduto = new JButton("Adicionar Produto");
        btnPagar = new JButton("Fechar Pedido");
        btnApagar = new JButton("Apagar");

        // Adicionando os JLabel e os JTextField ao inputPanel
        pesquisaPanel.add(labelCPF);
        pesquisaPanel.add(inputCPF);
        pesquisaPanel.add(btnPesquisar);

        pesquisaPanel.add(labelProduto);
        pesquisaPanel.add(inputProduto);
        pesquisaPanel.add(btnProduto);
        pesquisaPanel.add(nomeCliente);

        pagarPanel.add(labelValorTotal);
        pagarPanel.add(valorTotal);
        pagarPanel.add(btnPagar);
        pagarPanel.add(btnApagar);

        // Adicionando os JButton ao btnPanel

        // Definindo o mainPanel
        this.add(mainPanel);
        // mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pesquisaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(pagarPanel, BorderLayout.SOUTH);

        // Criando a tabela no banco de dados
        new VendasDAO().criaTabela();

        // Tratamento de evento para o botão de ADICIONAR os produtos pelo código
        btnProduto.addActionListener(e -> {
            String codigoProduto = inputProduto.getText();
            ProdutosDAO produtos = new ProdutosDAO();
            Produtos produto = produtos.buscarProduto(codigoProduto);
            if (Integer.parseInt(produto.getQuantidade()) >= 1) {
                // int novaQuantidade = Integer.parseInt(produto.getQuantidade())-1;
                // produtos.atualizarQuantidade(codigoProduto, novaQuantidade);
                tabelaPreenchida(produto);
            }
        });

        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCPF.setText("");
                inputProduto.setText("");
                valorTotal.setText("");
                tableModel.setRowCount(0);
            }
        });

        // Tratamento de evento para o botão de pesquisar se o cliente está CADASTRADO
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = inputCPF.getText();
                boolean cpfEncontrado = clientesDAO.verificarCPF(cpf);

                // String nome = clientesDAO.buscarNomePorCPF(cpf);

                if (cpfEncontrado) {
                    JOptionPane.showMessageDialog(null, "CPF encontrado no banco de dados!");
                    nomeCliente.setVisible(true);
                    nomeCliente.setText("Bem-Vindo Cliente VIP");
                    labelValorTotal.setText("Valor Total (VIP)");
                } else {
                    JOptionPane.showMessageDialog(null, "CPF não encontrado no banco de dados.");
                    inputCPF.setText("");
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
                // LocalDate dataDaVenda = LocalDate.now();
                String dataDaVenda = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                // System.out.println(localDateTime);

                // Métodos de Pagamento
                String[] opcoesPagamento = { "Cartão de Crédito", "Cartão de Débito", "Dinheiro", "PIX" };
                String metodoPagamento = (String) JOptionPane.showInputDialog(null,
                        "Selecione o método de pagamento:",
                        "Método de Pagamento",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesPagamento,
                        opcoesPagamento[0]);
                if (metodoPagamento != null) {
                    VendasDAO vendasDAO = new VendasDAO();
                    vendasDAO.cadastrarVenda(cpfDoCliente, dataDaVenda, totalVenda);
                    JOptionPane.showMessageDialog(null,
                            "NOTA FISCAL!\nMétodo de pagamento: " + metodoPagamento +
                                    "\n CPF: " + cpfDoCliente + "\n Preço Total: " + totalVenda + "\n Data da Venda: "
                                    + dataDaVenda);
                } else {
                    JOptionPane.showMessageDialog(null, "Operação deu erro.");
                }
            }
        });
    }

    // Suponha que você tenha uma instância de ClientesDAO chamada clientesDAO

    // Método para buscar o nome do cliente pelo CPF e definir o texto em um JTextField


    // Somatório dos Produtos
    private void TotalProdutos() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double precoTotal = 0.0;
        ClientesDAO clientesDAO = new ClientesDAO();
        String cpfClienteVip = inputCPF.getText();
        boolean cpfEncontrado = clientesDAO.verificarCPF(cpfClienteVip);

        for (int i = 0; i < model.getRowCount(); i++) {
            String precoString = model.getValueAt(i, 3).toString();
            double preco = Double.parseDouble(precoString);
            precoTotal += preco;
        }
        // String precoTotalFormatado = String.format("%.2f", precoTotal);

        if (cpfEncontrado) {
            double precoVip = precoTotal - (precoTotal * 0.1);
            // String precoVipFormatado = String.format("%.2f", precoVip);
            valorTotal.setText(String.valueOf(precoVip));
            valorTotal.setForeground(Color.BLUE);
            valorTotal.setBackground(Color.lightGray);
            valorTotal.setFont(new Font("Arial", Font.PLAIN, 16));
        } else {
            valorTotal.setText(String.valueOf(precoTotal));
            valorTotal.setFont(new Font("Arial", Font.PLAIN, 16));
        }
    }

    private void tabelaPreenchida(Produtos produto) {
        int quantidadeProdutos = 1;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Adicionando o produto a tabela
        Object[] listProducts = {
                produto.getNome(),
                produto.getCodigo(),
                quantidadeProdutos,
                produto.getPreco()
        };
        model.addRow(listProducts);
        TotalProdutos();
    }

}