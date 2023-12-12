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
    // Atributos
    private Connection connection;
    private List<Clientes> clientes;

    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

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
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clientes = new ArrayList<>();
    
        try {
            stmt = connection.prepareStatement("SELECT * FROM cadastro_clientes"); 
            rs = stmt.executeQuery(); 
            while (rs.next()) {
                Clientes cliente = new Clientes(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return clientes;
    }


    public void cadastrar(String nome, String cpf, String data_nascimento) {
        PreparedStatement stmt = null;
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


    public void atualizar(String nome, String cpf, String data_nascimento) {
        PreparedStatement stmt = null;
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
            String sql = "SELECT * FROM cadastro_clientes WHERE CPF = ?";
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
            if (cpfEncontrado) {
                ConnectionFactory.closeConnection(connection, stmt, rs);
            }
            }
        return cpfEncontrado;
    }
    



}
