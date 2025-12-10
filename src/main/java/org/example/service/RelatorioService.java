package org.example.service;

import org.example.DTO.ChamadoResumoDTO;
import org.example.DTO.UsuarioContagemChamadosDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RelatorioService {

    List<ChamadoResumoDTO> listarChamadosAbertosPorPrioridade(String prioridade) throws SQLException {
        return null;
    }

    List<UsuarioContagemChamadosDTO> gerarRelatorioUsuariosComMaisChamados(int minimo) throws SQLException {

        return null;
    }

    Optional<ChamadoResumoDTO> buscarDetalhesChamado(long chamadoId) throws SQLException {

        return null;
    }

}
