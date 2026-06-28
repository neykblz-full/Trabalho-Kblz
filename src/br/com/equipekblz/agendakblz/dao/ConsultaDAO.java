package br.com.equipekblz.agendakblz.dao;

import br.com.equipekblz.agendakblz.conexao.ConexaoMySQL;
import br.com.equipekblz.agendakblz.modelo.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {
    private final ConexaoMySQL conexaoMySQL = new ConexaoMySQL();

    public void cadastrar(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consultas "
                + "(paciente_id, profissional_id, data_consulta, horario, observacao, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, consulta.getPacienteId());
            comando.setInt(2, consulta.getProfissionalId());
            comando.setString(3, consulta.getDataConsulta());
            comando.setString(4, consulta.getHorario());
            comando.setString(5, consulta.getObservacao());
            comando.setString(6, consulta.getStatus());
            comando.executeUpdate();
        }
    }

    public List<Consulta> listar() throws SQLException {
        String sql = "SELECT id, paciente_id, profissional_id, data_consulta, horario, observacao, status "
                + "FROM consultas ORDER BY data_consulta, horario";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                consultas.add(new Consulta(
                        resultado.getInt("id"),
                        resultado.getInt("paciente_id"),
                        resultado.getInt("profissional_id"),
                        resultado.getString("data_consulta"),
                        resultado.getString("horario"),
                        resultado.getString("observacao"),
                        resultado.getString("status")
                ));
            }
        }

        return consultas;
    }

    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE consultas SET paciente_id = ?, profissional_id = ?, "
                + "data_consulta = ?, horario = ?, observacao = ?, status = ? WHERE id = ?";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, consulta.getPacienteId());
            comando.setInt(2, consulta.getProfissionalId());
            comando.setString(3, consulta.getDataConsulta());
            comando.setString(4, consulta.getHorario());
            comando.setString(5, consulta.getObservacao());
            comando.setString(6, consulta.getStatus());
            comando.setInt(7, consulta.getId());
            comando.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM consultas WHERE id = ?";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            comando.executeUpdate();
        }
    }
}

