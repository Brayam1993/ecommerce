package com.example.ecomerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.ecomerce.entity.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository paisRepository;

    private Pais pais;

    @BeforeEach
    void setup(){
        pais = Pais.builder()
                .nombre("España")
                .dominio("ES")
                .build();
    }

    @DisplayName("Test para guardar un pais")
    @Test
    void testGruardarPais(){
        // given
        Pais pais1 = Pais.builder()
                .nombre("Brasil")
                .dominio("BR")
                .build();

        // when
        Pais paisGuardado = paisRepository.save(pais1);

        // then
        assertThat(paisGuardado).isNotNull();
        assertThat(paisGuardado.getPais_id()).isGreaterThan(0);

    }

    @DisplayName("Test para listar paises")
    @Test
    void testListarPaises(){
        // given
        paisRepository.save(pais);
        // when
        List<Pais> listaPaises = paisRepository.findAll();

        // then
        assertThat(listaPaises).isNotNull();
        assertThat(listaPaises.size()).isEqualTo(1);
    }

    @DisplayName("Test para obtener un pais por Id")
    @Test
    void testObtenerPaisPorId(){
        // given
        paisRepository.save(pais);
        // when
        Pais paisBD = paisRepository.findById(pais.getPais_id()).get();
        // then
        assertThat(paisBD).isNotNull();
    }

    @DisplayName("Test para actualizar un pais")
    @Test
    void testActualizarPais(){
        // given
        paisRepository.save(pais);
        // when
        Pais paisGuardado = paisRepository.findById(pais.getPais_id()).get();
        paisGuardado.setNombre("Colombia");
        paisGuardado.setDominio("CO");
        // then
        Pais paisActualizado = paisRepository.save(paisGuardado);
        assertThat(paisActualizado.getNombre()).isEqualTo("Colombia");
        assertThat(paisActualizado.getDominio()).isEqualTo("CO");

    }

    @DisplayName("Test para eliminar un pais")
    @Test
    void testEliminarPais(){
        // given
        paisRepository.save(pais);
        // when
        paisRepository.deleteById(pais.getPais_id());
        Optional<Pais> paisOptional = paisRepository.findById(pais.getPais_id());
        // then
        assertThat(paisOptional).isEmpty();
    }

}
