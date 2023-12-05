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

public class CadastroDAO {
    // códigos para o banco de dados
    // atributo
    private Connection connection;
    private List<CadastroProdutos> produtos;

    public CadastroDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS cadastro_de_produtos (MARCA\r\n" + //
                "VARCHAR(255),NOMEPRODUTO VARCHAR(255),CODIGOBARRAS VARCHAR(255) PRIMARY KEY";
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
    public List<CadastroProdutos> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        produtos = new ArrayList<>();
        // Cria uma lista para armazenar os produtos recuperados do banco de dados
        try {
            stmt = connection.prepareStatement("SELECT * FROM cadastro_de_produtos");
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Produtos com os valores do
                // registro

                CadastroProdutos produto = new CadastroProdutos(
                        rs.getString("marca"),
                        rs.getString("nome"),
                        rs.getString("código"));
                produtos.add(produto); // Adiciona o objeto Produtos à lista de produtos
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);

            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return produtos; // Retorna a lista de produtos recuperados do banco de dados
    }

    // Cadastrar Produto no banco
    public void cadastrar(String marca, String nomeProduto, String codigoBarras) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO cadastro_de_produtos (marca, nomeProduto, codigoBarras (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca);
            stmt.setString(2, nomeProduto);
            stmt.setString(3, codigoBarras);
            stmt.executeUpdate();
            System.out.println("Cadastro de Produtos inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String marca, String nomeProduto, String codigoBarras) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE cadastro_de_produtos SET marca = ?, nomeProduto = ?, WHERE codigoBarras = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca);
            stmt.setString(2, nomeProduto);
            stmt.setString(3, codigoBarras);
            stmt.executeUpdate();
            System.out.println("Cadastro de Produtos atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

     // Apagar dados do banco
     public void apagar(String codigoBarras) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pelo código de barras
        String sql = "DELETE FROM cadastro_de_produtos WHERE codigoBarras = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigoBarras);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Cadastro de Produtos apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

}
