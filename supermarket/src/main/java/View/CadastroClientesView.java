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

import Controller.CadastroClientes.ClientesControl;
import Controller.CadastroClientes.ClientesDAO;
import Controller.CadastroProdutos.ProdutosDAO;
import Model.Clientes;
import Model.Produtos;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

public class CadastroClientesView extends JPanel{
    // Atributos

    // JTextField
    private JTextField inputNome;
    private JTextField inputCPF;
    private JTextField inputDataNasc;
    
    // JLabel
    private JLabel labelNome;
    private JLabel labelCPF;
    private JLabel labelDataNasc;

    // JButton
    private JButton btnCadastrar;
    private JButton btnApagar;
    private JButton btnAtualizar;

    // JTable - Tabela
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Clientes> clientes = new ArrayList<>();
    private int linhaSelecionada = -1;

    // Construtor
    public CadastroClientesView() {
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
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        // Definindo o tamanho dos JTextField
        inputNome = new JTextField(20);
        inputCPF = new JTextField(20);
        inputDataNasc = new JTextField(20);

        // Definindo a escrita dos JLabel
        labelNome = new JLabel("Nome");
        labelCPF = new JLabel("CPF");
        labelDataNasc = new JLabel("Data de Nascimento");

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
        new ClientesDAO().criaTabela();

        // atualizando a tabela
        atualizarTabela();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    inputNome.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputCPF.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputDataNasc.setText((String) table.getValueAt(linhaSelecionada, 2));
                }
            }
        });

        ClientesControl operacoes = new ClientesControl(clientes, tableModel, table);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.cadastrar(inputNome.getText(), inputCPF.getText(), inputDataNasc.getText());
                inputNome.setText("");
                inputCPF.setText("");
                inputDataNasc.setText("");
            }
        });

    }

     private void atualizarTabela() {
            // atualizar tabela pelo banco de dados
            tableModel.setRowCount(0);
            clientes = new ClientesDAO().listarTodos();
            for (Clientes cliente : clientes) {
                tableModel.addRow(new Object[] {cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento()});
            }
    
        }
    }
