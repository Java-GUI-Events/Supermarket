package Controller.CadastroProdutos;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Connection.ConnectionFactory;

public class ProdutosDAO {
    // códigos para o banco de dados
    // atributo
    private Connection connection;
    private List<Produtos> produtos;

    public ProdutosDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS cadastro_produtos (NOME VARCHAR(255),CODIGO VARCHAR(255) PRIMARY KEY, QUANTIDADE VARCHAR(255), PRECO VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de Cadastro de Produtos criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de cadastro de produtos: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Produtos> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        produtos = new ArrayList<>();
        // Cria uma lista para armazenar os produtos recuperados do banco de dados
        try {
            stmt = connection.prepareStatement("SELECT * FROM cadastro_produtos");
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Produtos com os valores do
                // registro

                Produtos produto = new Produtos(
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getString("quantidade"),
                        rs.getString("preco"));
                produtos.add(produto); // Adiciona o objeto Produtos à lista de produtos
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos; // Retorna a lista de produtos recuperados do banco de dados
    }

    // Cadastrar Produto no banco
    public void cadastrar(String nome, String codigo, String quantidade, String preco) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO cadastro_produtos (nome, codigo, quantidade, preco) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, codigo);
            stmt.setString(3, quantidade);
            stmt.setString(4, preco);
            stmt.executeUpdate();
            System.out.println("Produtos Cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String nome, String codigo, String quantidade, String preco) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE cadastro_produtos SET nome = ?, quantidade = ?, preco = ? WHERE codigo = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, quantidade);
            stmt.setString(3, preco);
            stmt.setString(4, codigo);
            stmt.executeUpdate();
            System.out.println("Cadastro de Produtos atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

     // Apagar dados do banco
     public void apagar(String codigo) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pelo código de barras
        String sql = "DELETE FROM cadastro_produtos WHERE codigo = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Produto apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar produto no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

}
