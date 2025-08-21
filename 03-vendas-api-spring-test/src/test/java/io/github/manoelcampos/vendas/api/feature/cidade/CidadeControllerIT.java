package io.github.manoelcampos.vendas.api.feature.cidade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CidadeControllerIT {
    @Autowired
    private WebTestClient client;

    @Test
    void findById(){
        findById(1);

    }

    private void findById(int id) {
        var cidadeEsperada = new Cidade(1);
        client.get().uri("/cidade/{id}", id).exchange()
                .expectStatus().isOk().expectBody(Cidade.class).isEqualTo(cidadeEsperada);
    }

    @Test
    void findByIdInexistente(){
        findByIdInexistente(9999);
    }

    private void findByIdInexistente(int idInexistente) {
        client.get().uri("/cidade/{id}", idInexistente).exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void deleteById(){
        int id = 10;
        client.delete().uri("/cidade/{id}",id).exchange()
                .expectStatus().isNoContent();

        findByIdInexistente(id);
    }

    @Test
    void deleteByIdNaoExclui(){
        int id = 1;
        client.delete().uri("/cidade/{id}",id).exchange()
                .expectStatus().isEqualTo(409);

        //findByIdInexistente(id);
    }
}