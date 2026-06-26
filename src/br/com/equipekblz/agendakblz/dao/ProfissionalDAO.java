package br.com.equipekblz.agendakblz.dao;

import br.com.equipekblz.agendakblz.conexao.ConexaoMySQL;
import br.com.equipekblz.agendakblz.modelo.Profissional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO {
    private final ConexaoMySQL conexaoMySQL = new ConexaoMySQL();

    public void cadastrar(Profissional profissional) throws SQLException {
        String sql = "INSERT INTO profissionais (nome, especialidade, telefone) VALUES (?, ?, ?)";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, profissional.getNome());
            comando.setString(2, profissional.getEspecialidade());
            comando.setString(3, profissional.getTelefone());
            comando.executeUpdate();
        }
    }

    public List<Profissional> listar() throws SQLException {
        String sql = "SELECT id, nome, especialidade, telefone FROM profissionais ORDER BY nome";
        List<Profissional> profissionais = new ArrayList<>();

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                profissionais.add(new Profissional(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("especialidade"),
                        resultado.getString("telefone")
                ));
            }
        }

        return profissionais;
    }

    public void atualizar(Profissional profissional) throws SQLException {
        String sql = "UPDATE profissionais SET nome = ?, especialidade = ?, telefone = ? WHERE id = ?";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, profissional.getNome());
            comando.setString(2, profissional.getEspecialidade());
            comando.setString(3, profissional.getTelefone());
            comando.setInt(4, profissional.getId());
            comando.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM profissionais WHERE id = ?";

        try (Connection conexao = conexaoMySQL.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            comando.executeUpdate();
        }
    }
}

