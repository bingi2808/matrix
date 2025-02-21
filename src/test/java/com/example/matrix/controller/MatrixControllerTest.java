package com.example.matrix.controller;

import com.example.matrix.operations.MatrixOperation;
import com.example.matrix.service.MatrixParserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatrixControllerTest {

    @Mock
    private MatrixParserService parserService;

    @Mock
    private MatrixOperation matrixOperation;

    @InjectMocks
    private MatrixController matrixController;

    private Map<String, MatrixOperation> operations;

    @BeforeEach
    void setUp() {
        operations = new HashMap<>();
        operations.put("testOperation", matrixOperation);
        matrixController = new MatrixController(parserService, operations);
    }

    @Test
    void testProcessMatrix_ValidOperation() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", "1,2,3\n4,5,6\n7,8,9".getBytes());
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Object expectedResult = new int[][]{{2, 4, 6}, {8, 10, 12}, {14, 16, 18}};

        when(parserService.parseCsvFile(file)).thenReturn(matrix);
        when(matrixOperation.perform(matrix)).thenReturn(expectedResult);

        Object result = matrixController.processMatrix("testOperation", file);

        assertEquals(expectedResult, result);
        verify(parserService).parseCsvFile(file);
        verify(matrixOperation).perform(matrix);
    }

    @Test
    void testProcessMatrix_InvalidOperation() {
        MultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", "1,2,3\n4,5,6\n7,8,9".getBytes());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            matrixController.processMatrix("invalidOperation", file);
        });

        assertEquals("Invalid operation: invalidOperation", exception.getMessage());
    }
}
