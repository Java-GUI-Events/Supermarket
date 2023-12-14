package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;

import java.awt.*;


public class FramePrincipal extends JFrame{

    ImageIcon imagem = new ImageIcon(getClass().getResource("telaInicial1.png"));
    
    public FramePrincipal(){
        super("Supermercado");
        setDefaultCloseOperation(2);

        // Criação dos Painéis
        CardLayout cardLayout = new CardLayout();
        JPanel cardsPainel = new JPanel(cardLayout);
        JPanel inicialPainel = new JPanel(new BorderLayout());

        // Criação dos Componentes do Painel Inicial
        JLabel labelImagem = new JLabel(imagem);
        // JButton btnVenda = new JButton("VENDAS");
        JButton btnEstoque = new JButton("MERCADO ->");
        btnEstoque.setBackground(Color.black);
        btnEstoque.setForeground(Color.white);
        btnEstoque.setFont(new Font("Monospaced", Font.BOLD, 30));

        // Ação do Botão 
        // ação para trocar os cards
         btnEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardLayout.next(cardsPainel);
            }
        });

        // btnVenda.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(java.awt.event.ActionEvent e) {
        //         cardLayout.previous(cardsPainel);
        //     }
        // });



        // Adicionando os Componentes ao Painel Inicial
        inicialPainel.add(labelImagem, BorderLayout.NORTH);
        // inicialPainel.add(btnVenda, BorderLayout.WEST);
        inicialPainel.add(btnEstoque, BorderLayout.CENTER);

        // Configuração do TabbedPane
        cardsPainel.add(inicialPainel, "Painel Inicial");
        //cardsPainel.add(vendasPainel, "Estoque");

        JTabbedPane abas = new JTabbedPane();
        abas.add("Cadastro de Produtos", new CadastroProdutosView());
        abas.add("Cadastro de Clientes", new CadastroClientesView());
        abas.add("Realizar Compra", new RegistroVendasView());
        cardsPainel.add(abas, "TabbedPane");
        add(cardsPainel);

        JTabbedPane abasVendas = new JTabbedPane();
        cardsPainel.add(abasVendas, "TabbedAbas");
        add(cardsPainel);

        setBounds(300, 250, 1400, 600);
    }

    public void run() {
        setVisible(true);
        // this.pack();
    }

}
