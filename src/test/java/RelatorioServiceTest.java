
import org.junit.jupiter.api.Test;
import org.example.service.RelatorioService;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RelatorioServiceTest {

    private RelatorioService relatorioService = new RelatorioService();

    @Test
    void deveLancarExcecaoQuandoMinimoForInvalido() {
        assertThrows(
                RuntimeException.class,
                () -> relatorioService.gerarRelatorioUsuariosComMaisChamados(0)
        );
    }
}