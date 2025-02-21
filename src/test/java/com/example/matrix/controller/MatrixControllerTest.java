package com.example.matrix.controller;

import com.example.matrix.service.MatrixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MatrixControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MatrixService matrixService;

    @InjectMocks
    private MatrixController matrixController;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(matrixController).build();
        String csvData = "1,2,3\n4,5,6\n7,8,9";
        mockFile = new MockMultipartFile("file", "matrix.csv", MediaType.TEXT_PLAIN_VALUE, csvData.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void echoMatrix_ShouldReturnMatrix() throws Exception {
        when(matrixService.parseCsvFile(any())).thenReturn(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        when(matrixService.formatMatrix(any())).thenReturn("1,2,3\n4,5,6\n7,8,9");

        mockMvc.perform(multipart("/matrix/echo").file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().string("1,2,3\n4,5,6\n7,8,9"));
    }

    @Test
    void sumMatrix_ShouldReturnSum() throws Exception {
        when(matrixService.parseCsvFile(any())).thenReturn(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        when(matrixService.sumMatrix(any())).thenReturn(45);

        mockMvc.perform(multipart("/matrix/sum").file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().string("45"));
    }
}