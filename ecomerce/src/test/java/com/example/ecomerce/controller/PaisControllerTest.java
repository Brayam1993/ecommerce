package com.example.ecomerce.controller;

import com.example.ecomerce.entity.Pais;
import com.example.ecomerce.service.PaisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(PaisController.class)
public class PaisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaisService paisService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Test para guardar un país")
    @Test
    void testGuardarPais() throws Exception {
        // given
        Pais pais = Pais.builder()
                .pais_id(1L)
                .nombre("Venezuela")
                .dominio("VE")
                .build();
        given(paisService.crearPais(any(Pais.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        // when
        ResultActions response = mockMvc.perform(post("/paises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pais)));

        // then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(pais.getNombre())))
                .andExpect(jsonPath("$.dominio", is(pais.getDominio())));
    }

    @DisplayName("Test para listar paises")
    @Test
    void testListarPaises() throws Exception {
        // given
        List<Pais> listaPaises = new ArrayList<>();
        listaPaises.add(Pais.builder().nombre("Alemania").dominio("AL").build());
        listaPaises.add(Pais.builder().nombre("Rusia").dominio("RU").build());
        given(paisService.obtenerTodosLosPaises()).willReturn(listaPaises);

        // when
        ResultActions response = mockMvc.perform(get("/paises"));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listaPaises.size())));
    }

    @DisplayName("Test para obtener pais por id")
    @Test
    void testObtenerPaisPorId() throws Exception {
        // given
        long paisId = 1L;
        Pais pais = Pais.builder()
                .nombre("Mexico")
                .dominio("MX")
                .build();
        given(paisService.obtenerPaisPorId(paisId)).willReturn(Optional.of(pais));

        // when
        ResultActions response = mockMvc.perform(get("/paises/{id}",paisId));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(pais.getNombre())))
                .andExpect(jsonPath("$.dominio",is(pais.getDominio())));

    }

    @DisplayName("Test para obtener pais no encontrado")
    @Test
    void testObtenerPaisNoEncontrado() throws Exception {
        // given
        long paisId = 1L;
        Pais pais = Pais.builder()
                .nombre("Mexico")
                .dominio("MX")
                .build();
        given(paisService.obtenerPaisPorId(paisId)).willReturn(Optional.empty());

        // when
        ResultActions response = mockMvc.perform(get("/paises/{id}",paisId));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }



}