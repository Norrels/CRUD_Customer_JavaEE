package gestao.dao;

import gestao.database.DatabaseConnection;
import gestao.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, email, telefone, cpf, endereco) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConnection.getConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());

            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ?, cpf = ?, endereco = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConnection.getConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getId());

            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConnection.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                return cliente;
            }

            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM cliente ORDER BY id";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                clientes.add(cliente);
            }

            return clientes;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            DatabaseConnection.closeConnection(connection);
        }
    }
}