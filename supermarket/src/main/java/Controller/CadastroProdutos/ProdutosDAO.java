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
    // Atributos
    private Connection connection;
    private List<Produtos> produtos;

    public ProdutosDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Criar Tabela
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

    // Consultando os PRODUTOS existentes no BANCO DE DADOS
    public List<Produtos> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        produtos = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM cadastro_produtos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produtos produto = new Produtos(
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getString("quantidade"),
                        rs.getString("preco"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println(ex); 
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos;
    }

    // CADASTRANDO os produtos no BANCO DE DADOS
    public void cadastrar(String nome, String codigo, String quantidade, String preco) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO cadastro_produtos (nome, codigo, quantidade, preco) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, codigo);
            stmt.setString(3, quantidade);
            stmt.setString(4, preco);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // ATUALIZANDO os produtos no BANCO DE DADOS
    public void atualizar(String nome, String codigo, String quantidade, String preco) {
        PreparedStatement stmt = null;
        String sql = "UPDATE cadastro_produtos SET nome = ?, quantidade = ?, preco = ? WHERE codigo = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, quantidade);
            stmt.setString(3, preco);
            stmt.setString(4, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cadastro de produtos no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para ATUALIZAR a QUANTIDADE de produtos APÓS UM CLIENTE REALIZAR UMA COMPRA
    public void atualizarQuantidade(String codigo, int quantidade) {
        PreparedStatement stmt = null;
        PreparedStatement stmtUpdate = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cadastro_produtos WHERE codigo = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

            // ATUALIZANDO a QUANTIDADE no BANCO DE DADOS
            String sqlUpdate = "UPDATE cadastro_produtos SET quantidade = ? WHERE codigo = ?";
            stmtUpdate = connection.prepareStatement(sqlUpdate);
             stmtUpdate.setString(1, codigo);
            stmtUpdate.setInt(2, quantidade);
            stmtUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
             throw new RuntimeException("Erro ao atualizar a quantidade de ITENS no banco de dados.", e); }
        // } finally {
        //     ConnectionFactory.closeConnection(connection, stmt, rs);
        // }

    }

     // APAGANDO os produtos no BANCO DE DADOS
     public void apagar(String codigo) {
        PreparedStatement stmt = null;
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

    // Método para BUSCAR o produto através do CÓDIGO
    public Produtos buscarProduto(String codigo) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produtos produto = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM cadastro_produtos WHERE codigo = ?");
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                produto = new Produtos(
                        rs.getString("nome"),
                        rs.getString("codigo"),
                        rs.getString("quantidade"),
                        rs.getString("preco"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produto;
    }

}
