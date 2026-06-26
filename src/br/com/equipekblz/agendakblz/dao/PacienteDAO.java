package br.com.equipekblz.agendakblz.dao;

import br.com.equipekblz.agendakblz.conexao.ConexaoMySQL;
import br.com.equipekblz.agendakblz.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private final ConexaoMySQL conexaoMySQL = new ConexaoMySQL();

    public void cadastrar(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO pacientes (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, paciente.getNome());
            comando.setString(2, paciente.getTelefone());
            comando.setString(3, paciente.getEmail());
            comando.executeUpdate();
        }
    }

    public List<Paciente> listar() throws SQLException {
        String sql = "SELECT id, nome, telefone, email FROM pacientes ORDER BY nome";
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                pacientes.add(new Paciente(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                ));
            }
        }

        return pacientes;
    }

    public void atualizar(Paciente paciente) throws SQLException {
        String sql = "UPDATE pacientes SET nome = ?, telefone = ?, email = ? WHERE id = ?";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, paciente.getNome());
            comando.setString(2, paciente.getTelefone());
            comando.setString(3, paciente.getEmail());
            comando.setInt(4, paciente.getId());
            comando.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            comando.executeUpdate();
        }
    }
}

