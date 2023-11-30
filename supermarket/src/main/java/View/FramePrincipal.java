package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javafx.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;


public class FramePrincipal extends JFrame{

    ImageIcon imagem = new ImageIcon(getClass().getResource("telaInicial.png"));
    
    public FramePrincipal(){
        super("Supermercado");
        setDefaultCloseOperation(2);

        // Criação dos Painéis
        CardLayout cardLayout = new CardLayout();
        JPanel cardsPainel = new JPanel(cardLayout);
        JPanel inicialPainel = new JPanel(new BorderLayout());

        // Criação dos Componentes do Painel Inicial
        JLabel labelImagem = new JLabel(imagem);
        JButton btnCadastro = new JButton("CADASTRAR");
        JButton btnEstoque = new JButton("ESTOQUE");
        JButton btnVendas = new JButton("VENDAS");

        // Ação do Botão 
                // ação para trocar os cards
         btnEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardLayout.next(cardsPainel);
            }
        });

        // btnCadastro.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(java.awt.event.ActionEvent e) {

        //     }
            
        // });

        // Adicionando os Componentes ao Painel Inicial
        inicialPainel.add(labelImagem, BorderLayout.NORTH);
        inicialPainel.add(btnCadastro, BorderLayout.WEST);
        inicialPainel.add(btnEstoque, BorderLayout.EAST);
        inicialPainel.add(btnVendas, BorderLayout.CENTER);

        // Configuração do TabbedPane
        cardsPainel.add(inicialPainel, "Painel Inicial");
        JTabbedPane abas = new JTabbedPane();
        abas.add("Lista de Produtos", new ListaProdutos());
        abas.add("Cadastro de Produtos", new CadastroProdutos());
        cardsPainel.add(abas, "TabbedPane");
        add(cardsPainel);

        setBounds(300, 250, 415, 500);
    }

    public void run() {
        setVisible(true);
    }

}
