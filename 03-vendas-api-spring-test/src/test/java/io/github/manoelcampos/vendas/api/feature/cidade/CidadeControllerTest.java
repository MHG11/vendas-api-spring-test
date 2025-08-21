package io.github.manoelcampos.vendas.api.feature.cidade;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

//testa apenas o controller, sem rodar a aplicação.

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(CidadeController.class)
class CidadeControllerTest {
    @Autowired
    private MockMvcTester client;

    //@Autowired private CidadeRepository repository;
    /*
    * @Autowired pede pro spring instanciar o objeto mas não da pra usar aqui por não
    * vai existir uma conexão com o banco.
    * se não tem conexão com o banco não tem como o repositorio funcionar
    * também pois ele depende do repositorio
    * */

    /*
    * Mock tambem significa "objeto fake"
    * neste caso estamos criando um service falso, que finge que é um service real
    * ele é falso pois também vai fingir que acesso o baco, mas não acessou pois no slice teste não existe
    * conexão com o baco.
    * mock apenas finge que esta fazendo as coisas
    * é como uma pessoa que te da uma cedula de dinheiro falsa.
    * um objeto mock é como um fantoche
    * você que controla o funcionamento dele.
    * o@MockitoBean é uma anotação que substitui @MockBean.
    * esta anotação tambem injeta o serviço no controller ou seja, ao instanciar o controller*/

    @MockitoBean
    private CidadeService service;

    @Test
    void findByIdValido() {
        final long id = 1;
        final var cidadeEsperada = new Cidade(id);
        Mockito.when(service.findById(id)).thenReturn(Optional.of(cidadeEsperada));
        final var response = client.get().uri("/cidade/{id}", id)
                .exchange()
                .assertThat()
                .hasStatusOk()
                .bodyJson()
                .convertTo(Cidade.class)
                .isEqualTo(cidadeEsperada);
    }

    @Test
    void findByIdInvalido() {
        final long idInvalido = 0;
        final var response = client.get().uri("/cidade/{idInvalido}", idInvalido)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.BAD_REQUEST );
    }
    @Test
    void deleteByIdValido() {
        final long id = 1;
        final var response = client.delete().uri("/cidade/{id}", id)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.BAD_REQUEST);

    }

    @Test
    void delete() {
        final long id = 1;
        Mockito.when(service.deleteById(id)).thenReturn(true);
        final var response = client.delete().uri("/cidade/{id}", id)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.NO_CONTENT);
    }
}