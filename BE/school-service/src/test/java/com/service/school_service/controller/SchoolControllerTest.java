package com.example.controller;

import com.example.service.SchoolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SchoolController.class)
class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateSchool() throws Exception {
        CreateSchoolDto createDto = new CreateSchoolDto("High School");
        SchoolDto responseDto = new SchoolDto(1L, "High School");

        Mockito.when(schoolService.createSchool(any(CreateSchoolDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/schools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("High School"));
    }

    @Test
    void testGetSchoolById() throws Exception {
        SchoolDto dto = new SchoolDto(1L, "Science Academy");

        Mockito.when(schoolService.getSchoolById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/schools/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Science Academy"));
    }

    @Test
    void testUpdateSchool() throws Exception {
        UpdateSchoolDto updateDto = new UpdateSchoolDto("New Name");
        SchoolDto updatedDto = new SchoolDto(1L, "New Name");

        Mockito.when(schoolService.updateSchool(eq(1L), any(UpdateSchoolDto.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/schools/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    void testGetAllSchools_returnsOk() throws Exception {
        List<SchoolDto> schools = List.of(new SchoolDto(1L, "A"), new SchoolDto(2L, "B"));

        Mockito.when(schoolService.getAllSchools()).thenReturn(schools);

        mockMvc.perform(get("/api/schools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetAllSchools_returnsNoContent() throws Exception {
        Mockito.when(schoolService.getAllSchools()).thenReturn(List.of());

        mockMvc.perform(get("/api/schools"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteSchool() throws Exception {
        mockMvc.perform(delete("/api/schools/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(schoolService).deleteSchoolById(1L);
    }

    @Test
    void testAddClassToSchool() throws Exception {
        CreateSchoolClassDto createClassDto = new CreateSchoolClassDto("10A");
        SchoolClassDto responseDto = new SchoolClassDto(1L, "10A");

        Mockito.when(schoolService.addClassToSchool(eq(1L), any(CreateSchoolClassDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/schools/1/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createClassDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("10A"));
    }

    @Test
    void testRemoveClassFromSchool() throws Exception {
        mockMvc.perform(delete("/api/schools/1/classes/2"))
                .andExpect(status().isNoContent());

        Mockito.verify(schoolService).removeClassFromSchool(1L, 2L);
    }
}