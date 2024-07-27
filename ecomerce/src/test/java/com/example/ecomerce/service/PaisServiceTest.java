package com.example.ecomerce.service;

import com.example.ecomerce.entity.Pais;
import com.example.ecomerce.repository.PaisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PaisServiceTest {

    @Mock
    private PaisRepository paisRepository;

    @InjectMocks
    private PaisService paisService;

    private Pais pais;

    @BeforeEach
    void setup(){
        pais = Pais.builder()
                .pais_id(1L)
                .nombre("España")
                .dominio("ES")
                .build();
    }

    @DisplayName("Test para guardar un pais")
    @Test
    void testGuardarPais(){
        // given
        given(paisRepository.save(pais)).willReturn(pais);

        // when
        Pais paisGuardado = paisService.crearPais(pais);

        // then
        assertThat(paisGuardado).isNotNull();
        assertThat(paisGuardado.getNombre()).isEqualTo("España");
        assertThat(paisGuardado.getDominio()).isEqualTo("ES");

        verify(paisRepository).save(pais);
    }

    @DisplayName("Test para listar a los paises")
    @Test
    void testListarPaises(){
        // given
        Pais pais1 = Pais.builder()
                .pais_id(1L)
                .nombre("Venezuela")
                .dominio("VE")
                .build();
        given(paisRepository.findAll()).willReturn(List.of(pais,pais1));

        // when
        List<Pais> paises = paisService.obtenerTodosLosPaises();

        // then
        assertThat(paises).isNotNull();
        assertThat(paises.size()).isEqualTo(2);

    }

    @DisplayName("Test para retornar una lista de paises vacia")
    @Test
    void testListarColeccionPaisesVacia(){
        // given
        Pais pais1 = Pais.builder()
                .pais_id(1L)
                .nombre("Venezuela")
                .dominio("VE")
                .build();
        given(paisRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<Pais> listaPaises = paisService.obtenerTodosLosPaises();

        // then
        assertThat(listaPaises).isEmpty();
        assertThat(listaPaises.size()).isEqualTo(0);

    }

    @DisplayName("Test para obtener un pais por id")
    @Test
    void testObtenerPaisPorId(){
        // given
        given(paisRepository.findById(1L)).willReturn(Optional.of(pais));
        // when
        Pais paisGuardado = paisService.obtenerPaisPorId(pais.getPais_id()).get();

        // then
        assertThat(paisGuardado).isNotNull();

    }

    @DisplayName("Test para actualizar un pais")
    @Test
    void testActualizarPais(){
        // given
        given(paisRepository.save(pais)).willReturn(pais);
        pais.setNombre("Nicaragua");
        pais.setDominio("NI");

        // when
        Pais paisActualizado = paisService.actualizarPais(1L,pais);

        // then
        assertThat(paisActualizado.getNombre()).isEqualTo("Nicaragua");
        assertThat(paisActualizado.getDominio()).isEqualTo("NI");

    }

    @DisplayName("Test para eliminar un pais")
    @Test
    void testEliminarPais(){
        // given
        long paisId = 1L;
        willDoNothing().given(paisRepository).deleteById(paisId);

        // when
        paisService.eliminarPais(paisId);

        // then
        verify(paisRepository,times(1)).deleteById(paisId);

    }


}
