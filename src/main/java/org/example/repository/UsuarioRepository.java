package org.example.repository;

import org.example.connection.Conexao;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.sql.*;

public class UsuarioRepository {

    public Usuario criarUsuario (Usuario user) throws SQLException {
        String query = "INSERT INTO Usuarios (nome, email, setor, ativo) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSetor());
            stmt.setString(4, String.valueOf(user.isAtivo()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
        return user;
    }

    public boolean verificarEmailExistente (String email) throws SQLException {
        String query = "SELECT COUNT(0) FROM Usuarios WHERE email = ?";

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, email);
            stmt.executeUpdate();

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuarioID (long id) throws SQLException {
        String query = "SELECT nome, email, setor, ativo FROM Usuarios WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        id,
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("setor"),
                        rs.getBoolean("ativo"));
            }
        }
        return null;
    }
}
