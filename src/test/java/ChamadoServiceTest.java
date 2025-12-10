import org.example.model.Chamado;
import org.example.service.ChamadoService;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class ChamadoServiceTest {

    private ChamadoService chamadoService = new ChamadoService();

    @Test
    void deveCriarChamadoComStatusAberto() throws SQLException {
        Chamado chamado = new Chamado();
        chamado.setUsuarioId(1);
        chamado.setDescricao("Internet não funciona");
        chamado.setPrioridade("ALTA");
        chamado.setDataAbertura(LocalDateTime.now());

        Chamado resultado = chamadoService.abrirChamado(chamado);

        assertEquals("ABERTO", resultado.getStatus());
    }

    @Test
    void deveLancarExcecaoAoBuscarChamadoInexistente() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> chamadoService.buscarChamadoPorId(999)
        );

        assertEquals("Chamado não encontrado!", exception.getMessage());
    }
}
