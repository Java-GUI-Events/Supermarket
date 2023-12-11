package Controller.RegistroVendas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Produtos;
import Model.Vendas;

public class VendasDAO {
    // códigos para o banco de dados
    // atributo
    private Connection connection;
    private List<Vendas> vendas;

    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS registro_vendas (ID_VENDA SERIAL PRIMARY KEY, CPF VARCHAR(255), DATA_VENDA DATE, VALOR DECIMAL(10, 2))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de Registro de Vendas criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de regsitro de vendas: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public void cadastrarVenda(String cpfCliente, LocalDate dataVenda, double valorTotal) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO registro_vendas (CPF, DATA_VENDA, VALOR) VALUES (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpfCliente);
            stmt.setDate(2, Date.valueOf(dataVenda));
            stmt.setDouble(3, valorTotal);
            stmt.executeUpdate();
            System.out.println("Venda Registrada com Sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar a venda: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }




}
