package Controller.ClienteVIP;
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

public class ClienteDAO {
     // códigos para o banco de dados
    // atributo
    private Connection connection;
    private List<ClienteVIP> vip;

    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS cadastro_de_clientes_vips (MARCA\r\n" + //
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

}
