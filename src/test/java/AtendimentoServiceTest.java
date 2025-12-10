
import org.junit.jupiter.api.Test;
import org.example.service.AtendimentoService;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AtendimentoServiceTest {

    private AtendimentoService atendimentoService = new AtendimentoService();

    @Test
    void naoDeveIniciarAtendimentoSemChamado() {
        assertThrows(
                RuntimeException.class,
                () -> atendimentoService.iniciarAtendimento(null)
        );
    }
}