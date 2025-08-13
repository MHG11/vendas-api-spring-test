package io.github.manoelcampos.vendas.api.feature.cidade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CidadeRepositoryTest {
    @Autowired
    private CidadeRepository repository;


    @Test
    void deleteById() {
        long id = 10;
        repository.deleteById(id);

        assertTrue(repository.findById(id).isEmpty());
    }

    @Test
    void findById() {
        long id = 10;
        var cidade = repository.findById(id);
        System.out.println(cidade);
        assertTrue(cidade.isPresent());
    }

    @Test
    void findByDescricaoLike() {
        List<Cidade> lista = repository.findByDescricaoLike("C%");
        assertEquals(3, lista.size());

    }
}