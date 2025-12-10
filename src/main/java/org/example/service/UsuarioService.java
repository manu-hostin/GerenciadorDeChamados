package org.example.service;

import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;

import java.sql.SQLException;

public class UsuarioService {

    UsuarioRepository userRepo = new UsuarioRepository();

    public Usuario criarUsuario (Usuario usuario) throws SQLException {

        usuario.setAtivo(true);
        if (usuario.getEmail().isEmpty() || usuario.getNome().isEmpty()){
            throw new RuntimeException("Email ou nome inválido.");
        }
        if (userRepo.verificarEmailExistente(usuario.getEmail())){
            throw new RuntimeException("Email já existente.");
        }

        return userRepo.criarUsuario(usuario);

    }

    public Usuario buscarUsuarioPorId(long id) throws SQLException {

        if (userRepo.buscarUsuarioID(id) == null) {
            throw new RuntimeException("Usuário não encontrado!");
        } else {
            return userRepo.buscarUsuarioID(id);
        }
    }
}
