package org.example.service;

import org.example.model.Chamado;
import org.example.repository.ChamadoRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ChamadoService {

    ChamadoRepository chamadoRepo = new ChamadoRepository();

    public Chamado abrirChamado(Chamado chamado) throws SQLException {

        if (!chamadoRepo.verificarExistenciaUser(chamado.getUsuarioId())){
            throw new RuntimeException ("Usuário não existe.");
        }
        if (chamado.getDescricao().isEmpty()) {
            throw new RuntimeException ("Descrição inválida.");
        }
        if (!chamado.getPrioridade().equals("BAIXA") ||
                    !chamado.getPrioridade().equals("MEDIA") ||
                    !chamado.getPrioridade().equals("ALTA") ||
                    !chamado.getPrioridade().equals("URGENTE")){

            throw new RuntimeException ("Prioridade inválida");
        }

        chamado.setDataAbertura(LocalDateTime.now());
        chamado.setStatus("ABERTO");

        chamadoRepo.abrirChamado(chamado);

        return chamado;
    }

    public Chamado buscarChamadoPorId(long id) throws SQLException {
        if (chamadoRepo.buscarChamadoPorId(id) == null){
            throw new RuntimeException("Chamado não encontrado!");
        }
        return chamadoRepo.buscarChamadoPorId(id);
    }
}
