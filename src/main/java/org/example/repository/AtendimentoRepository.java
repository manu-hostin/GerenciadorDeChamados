package org.example.repository;

import org.example.connection.Conexao;
import org.example.model.Atendimento;

import java.sql.*;
import java.time.LocalDateTime;

public class AtendimentoRepository {

    public Atendimento iniciarAtendimento (Atendimento atendimento) throws SQLException {
        String query = "INSERT INTO Atendimentos (chamadoId, dataInicio, tecnicoResponsavel, status) VALUES (?, ?, ?, 'EM_ATENDIMENTO')";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, atendimento.getChamadoId());
            stmt.setTimestamp(2, Timestamp.valueOf(atendimento.getDataInicio()));
            stmt.setString(3, atendimento.getTecnicoResponsavel());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                atendimento.setId(rs.getInt(1));
            }
        }
        return atendimento;
    }

    public void finalizar (LocalDateTime dataFim, String solucao, long atendimentoId) throws SQLException {
            String sql = """
            UPDATE Atendimentos
            SET dataFim = ?, solucao = ?, status = 'FECHADO'
            WHERE id = ?
            """;

            try (Connection conn = Conexao.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setTimestamp(1, Timestamp.valueOf(dataFim));
                stmt.setString(2, solucao);
                stmt.setLong(3, atendimentoId);

                stmt.executeUpdate();
            }
    }
    public Atendimento buscarPorId(long id) throws SQLException {

        String sql = """
        SELECT id,
               chamadoId,
               tecnicoResponsavel,
               dataInicio,
               dataFim,
               solucao
        FROM Atendimentos
        WHERE id = ?
    """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId(rs.getLong("id"));
                atendimento.setChamadoId(rs.getLong("chamadoId"));
                atendimento.setTecnicoResponsavel(rs.getString("tecnicoResponsavel"));
                atendimento.setDataInicio(
                        rs.getTimestamp("dataInicio").toLocalDateTime()
                );

                Timestamp dataFim = rs.getTimestamp("dataFim");
                atendimento.setSolucao(rs.getString("solucao"));

                return atendimento;
            }
        }
        return null;
    }


}
