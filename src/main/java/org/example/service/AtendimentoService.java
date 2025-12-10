package org.example.service;

import org.example.model.Atendimento;
import org.example.model.Chamado;
import org.example.repository.AtendimentoRepository;
import org.example.repository.ChamadoRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class AtendimentoService {

    AtendimentoRepository atendimentoRepo = new AtendimentoRepository();
    ChamadoRepository chamadoRepo = new ChamadoRepository();

    public Atendimento iniciarAtendimento (Atendimento atendimento) throws SQLException {

        Chamado chamado = chamadoRepo.buscarChamadoPorId(atendimento.getChamadoId());

        if(chamado == null || !chamado.getStatus().equals("ABERTO")){
            throw new RuntimeException("Chamado não existe ou não está ABERTO");
        }

        atendimento.setDataInicio(LocalDateTime.now());

        return atendimentoRepo.iniciarAtendimento(atendimento);
    }

    public void finalizarAtendimento(long atendimentoId, String solucao, LocalDateTime dataFim) throws SQLException {

        if (solucao == null) {
            throw new RuntimeException("Solução obrigatória");
        }

        Atendimento atendimento = atendimentoRepo.buscarPorId(atendimentoId);

        if (atendimento == null) {
            throw new RuntimeException("Atendimento não encontrado");
        }
        dataFim = LocalDateTime.now();
        atendimentoRepo.finalizar(dataFim, solucao, atendimentoId);
    }

}
