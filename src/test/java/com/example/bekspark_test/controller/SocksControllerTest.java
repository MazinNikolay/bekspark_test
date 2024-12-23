package com.example.bekspark_test.controller;

import com.example.bekspark_test.dto.SocksDto;
import com.example.bekspark_test.entity.Socks;
import com.example.bekspark_test.repository.SocksRepository;
import com.example.bekspark_test.service.impl.SocksServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class SocksControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    SocksRepository repository;

    @MockitoSpyBean
    SocksServiceImpl service;

    @InjectMocks
    SocksController controller;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void incomeSocksTestCorrect() throws Exception {
        //given
        Socks socks = Socks.builder()
                .socksColor("red")
                .socksCottonPart(80)
                .socksQuantity(2)
                .build();
        SocksDto dto = SocksDto.builder()
                .socksColor("ReD")
                .socksCottonPart(80)
                .socksQuantity(4)
                .build();
        //when
        when(repository.findByData(any(String.class), anyInt()))
                .thenReturn(Optional.of(socks));
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void incomeSocksTestWithIncorrectArgs() throws Exception {
        //given
        SocksDto dto = SocksDto.builder()
                .socksColor(null)
                .socksCottonPart(0)
                .socksQuantity(-1)
                .build();
        //when
        when(repository.findByData(any(String.class), anyInt()))
                .thenThrow(new IllegalArgumentException());
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void outcomeSocksTestCorrect() throws Exception {
        //given
        Socks socks = Socks.builder()
                .socksColor("red")
                .socksCottonPart(80)
                .socksQuantity(2)
                .build();
        SocksDto dto = SocksDto.builder()
                .socksColor("ReD")
                .socksCottonPart(80)
                .socksQuantity(1)
                .build();
        //when
        when(repository.findByData(any(String.class), anyInt()))
                .thenReturn(Optional.of(socks));
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void outcomeSocksTestWithIncorrectArgs() throws Exception {
        //given
        Socks socks = Socks.builder()
                .socksColor("red")
                .socksCottonPart(80)
                .socksQuantity(2)
                .build();
        SocksDto dto = SocksDto.builder()
                .socksColor("ReD")
                .socksCottonPart(80)
                .socksQuantity(3)
                .build();
        //when
        when(repository.findByData(any(String.class), anyInt()))
                .thenReturn(Optional.of(socks));
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getQuantitySocksTestCorrect() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/socks?socksColor=red&" +
                        "compareCommand=moreThan&cottonPart=70"))
                .andExpect(status().isOk());
    }

    @Test
    void getQuantitySocksTestWithIncorrectArg() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/socks?socksColor=red&" +
                        "compareCommand=moreTha&cottonPart=70"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateSocksTestCorrect() throws Exception {
        //given
        Long id = 1L;
        Socks socks = Socks.builder()
                .socksColor("red")
                .socksCottonPart(80)
                .socksQuantity(2)
                .build();
        SocksDto dto = SocksDto.builder()
                .socksColor("ReD")
                .socksCottonPart(80)
                .socksQuantity(1)
                .build();
        //when
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(socks));
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/socks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateSocksTestWithIncorrectArg() throws Exception {
        //given
        Long id = 1L;
        Socks socks = Socks.builder()
                .socksColor("red")
                .socksCottonPart(80)
                .socksQuantity(2)
                .build();
        SocksDto dto = SocksDto.builder()
                .socksColor("ReD")
                .socksCottonPart(-1)
                .socksQuantity(1)
                .build();
        //when
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(socks));
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/socks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}