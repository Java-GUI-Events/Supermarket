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

import Controller.CadastroProdutos.CadastroControl;
import Controller.CadastroProdutos.CadastroDAO;
import DAO.ProdutosDAO;
import Model.Produtos;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

public class CadastroProdutosView extends JPanel {
    // Atributos

    // JTextField
    private JTextField inputProduto;
    private JTextField inputCodigo;
    private JTextField inputMarca;

    // JLabel
    private JLabel labelProduto;
    private JLabel labelCodigo;
    private JLabel labelMarca;

    // JButton
    private JButton btnCadastrar;
    private JButton btnApagar;
    private JButton btnAtualizar;

    // JTable - Tabela
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Produtos> produtos = new ArrayList<>();
    private int linhaSelecionada = -1;

    // Construtor
    public CadastroProdutosView() {
        // JPanel - Painéis
        JPanel mainPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        // Layout dos Painéis
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new BorderLayout());

        // Construindo a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Produto");
        tableModel.addColumn("Código");
        tableModel.addColumn("Marca");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        // Definindo o tamanho dos JTextField
        inputProduto = new JTextField(20);
        inputCodigo = new JTextField(20);
        inputMarca = new JTextField(20);

        // Definindo a escrita dos JLabel
        labelProduto = new JLabel("Produto");
        labelCodigo = new JLabel("Codigo");
        labelMarca = new JLabel("Marca");

        // Definindo os botões JButton
        btnCadastrar = new JButton("Cadastrar");
        btnApagar = new JButton("Apagar");
        btnAtualizar = new JButton("Atualizar");

        // Adicionando os JLabel e os JTextField ao inputPanel
        inputPanel.add(labelProduto);
        inputPanel.add(inputProduto);
        inputPanel.add(labelCodigo);
        inputPanel.add(inputCodigo);
        inputPanel.add(labelMarca);
        inputPanel.add(inputMarca);

        // Adicionando os JButton ao btnPanel
        btnPanel.add(btnCadastrar);
        btnPanel.add(btnApagar);
        btnPanel.add(btnAtualizar);

        // Definindo o mainPanel
        this.add(mainPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // Criando o banco de dados
        new CadastroDAO().criaTabela();

        // atualizando a tabela
        atualizarTabela();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    inputProduto.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputCodigo.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputMarca.setText((String) table.getValueAt(linhaSelecionada, 2));
                }
            }
        });

        CadastroControl operacoes = new CadastroControl(produtos, tableModel, table);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.cadastrar(inputProduto.getText(), inputCodigo.getText(), inputMarca.getText());
                inputProduto.setText("");
                inputCodigo.setText("");
                inputMarca.setText("");
            }
        });

    }

     private void atualizarTabela() {
            // atualizar tabela pelo banco de dados
            tableModel.setRowCount(0);
            produtos = new CadastroDAO().listarTodos();
            for (Produtos produto : produtos) {
                tableModel.addRow(new Object[] {produto.getNomeProduto(), produto.getCodigoBarras(), produto.getMarca()});
            }
    
        }

}
