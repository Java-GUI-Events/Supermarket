package Controller.CadastroClientes;
import java.util.List;

import javax.swing.JOptionPane;
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

public class ClientesDAO {
     // códigos para o banco de dados
    // atributo
    private Connection connection;
    private List<Clientes> clientes;

    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS cadastro_clientes (NOME VARCHAR(255), CPF VARCHAR(255) PRIMARY KEY, DATA_NASCIMENTO VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de Cadastro de Clientes criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de Cadastro de Clientes: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null; // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null; // Declaração do objeto ResultSet para armazenar os resultados da consulta
        clientes = new ArrayList<>(); // Cria uma lista para armazenar os carros recuperados do banco de dados
    
        try {
            stmt = connection.prepareStatement("SELECT * FROM cadastro_clientes"); 
            // consulta SQL para selecionar todos as rows da tabela
            rs = stmt.executeQuery(); 
            // Executa a consulta e armazena os resultados no ResultSet
    
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do registro
                Clientes cliente = new Clientes(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento")
                );
                clientes.add(cliente); // Adiciona o objeto Carros à lista de carros
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs); // Fecha todas as conexões aberta anteriormente
        }
        return clientes; // Retorna a lista de carros recuperados do banco de dados
    }


    // Cadastrar Carro no banco de dados
    public void cadastrar(String nome, String cpf, String data_nascimento) {
        PreparedStatement stmt = null;
        // Consulta SQL para cadastrar na tabela
        String sql = "INSERT INTO cadastro_clientes (nome, cpf, data_nascimento) VALUES (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, data_nascimento);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
            JOptionPane.showMessageDialog(null, "Você Cadastrou!");
        } catch (SQLException e) {
                throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection,stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String nome, String cpf, String data_nascimento) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE cadastro_clientes SET nome = ?, data_nascimento = ? WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, data_nascimento);
            stmt.setString(3, cpf);
            stmt.executeUpdate();
            System.out.println("Cadastro de Cliente atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cadastro de clientes no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

     // Apagar dados do banco
     public void apagar(String cpf) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM cadastro_clientes WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Cliente apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar cliente no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public boolean verificarCPF(String cpf) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean cpfEncontrado = false;
        try {
            // Consulta SQL para buscar um cliente pelo CPF
            String sql = "SELECT CPF FROM cadastro_clientes WHERE CPF = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cpfEncontrado = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar CPF no banco de dados." + e.getMessage(), e);
            // quando eu recebo a busca a conexão fecha e não permite a busca por mais de um cpf

        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    
        return cpfEncontrado;
    }
    



}
