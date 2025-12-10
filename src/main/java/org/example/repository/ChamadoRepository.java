package org.example.repository;

import org.example.connection.Conexao;
import org.example.model.Chamado;

import java.sql.*;

public class ChamadoRepository {

    public Chamado abrirChamado (Chamado chamado) throws SQLException {
        String query = "INSERT INTO Chamado (usuarioId, dataAbertura, descricao, prioridade, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, chamado.getUsuarioId());
            stmt.setTimestamp(2, Timestamp.valueOf(chamado.getDataAbertura()));
            stmt.setString(3, chamado.getDescricao());
            stmt.setString(4, chamado.getPrioridade());
            stmt.setString(5, chamado.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                chamado.setId(rs.getLong(1));
            }
        }
        return chamado;
    }
    public boolean verificarExistenciaUser (long id) throws SQLException {
        String query = "SELECT COUNT(0) FROM Usuarios WHERE ativo = true AND id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }
    public Chamado buscarChamadoPorId(long id) throws SQLException {
        String query  = "SELECT id, usuarioId, dataAbertura, descricao, prioridade, status FROM Chamados WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Chamado (
                        id,
                        rs.getInt("usuarioId"),
                        rs.getTimestamp("dataAbertura").toLocalDateTime(),
                        rs.getString("prioridade"),
                        rs.getString("descricao"),
                        rs.getString("status")
                );
            }
        }
        return null;
    }
}
