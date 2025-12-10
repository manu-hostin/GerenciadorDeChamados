import org.example.model.Usuario;
import org.example.service.UsuarioService;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService = new UsuarioService();

    @Test
    void deveCriarUsuarioComSucesso() throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana");
        usuario.setEmail("ana@email.com");

        Usuario resultado = usuarioService.criarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNome());
        assertEquals("ana@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoAoCriarUsuarioSemNome() {
        Usuario usuario = new Usuario();
        usuario.setEmail("email@email.com");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usuarioService.criarUsuario(usuario)
        );

        assertEquals("Nome do usuário é obrigatório!", exception.getMessage());
    }

    @Test
    void deveBuscarUsuarioPorIdExistente() throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");

        Usuario criado = usuarioService.criarUsuario(usuario);

        Usuario buscado = usuarioService.buscarUsuarioPorId(criado.getId());

        assertEquals(criado.getId(), buscado.getId());
    }

    @Test
    void deveLancarExcecaoAoBuscarUsuarioInexistente() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usuarioService.buscarUsuarioPorId(999)
        );

        assertEquals("Usuário não encontrado!", exception.getMessage());
    }
}
