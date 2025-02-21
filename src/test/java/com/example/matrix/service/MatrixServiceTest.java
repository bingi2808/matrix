package com.example.matrix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatrixServiceTest {

    @InjectMocks
    private MatrixService matrixService;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        String csvData = "1,2,3\n4,5,6\n7,8,9";
        mockFile = new MockMultipartFile(
                "file", "matrix.csv", MediaType.TEXT_PLAIN_VALUE, csvData.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void parseCsvFile_ShouldParseCorrectly() throws Exception {
        int[][] matrix = matrixService.parseCsvFile(mockFile);
        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertArrayEquals(new int[]{1, 2, 3}, matrix[0]);
        assertArrayEquals(new int[]{4, 5, 6}, matrix[1]);
        assertArrayEquals(new int[]{7, 8, 9}, matrix[2]);
    }

    @Test
    void parseCsvFile_ShouldThrowExceptionForEmptyFile() {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.csv", MediaType.TEXT_PLAIN_VALUE, "".getBytes());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> matrixService.parseCsvFile(emptyFile));
        assertTrue(exception.getMessage().contains("CSV file is empty"));
    }

    @Test
    void parseCsvFile_ShouldThrowExceptionForInconsistentRowLengths() {
        MockMultipartFile inconsistentFile = new MockMultipartFile(
                "file", "invalid.csv", MediaType.TEXT_PLAIN_VALUE,
                "1,2,3\n4,5\n7,8,9".getBytes(StandardCharsets.UTF_8));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> matrixService.parseCsvFile(inconsistentFile));
        assertTrue(exception.getMessage().contains("Inconsistent row length"));
    }

    @Test
    void parseCsvFile_ShouldThrowExceptionForNonSquareMatrix() {
        MockMultipartFile nonSquareFile = new MockMultipartFile(
                "file", "invalid.csv", MediaType.TEXT_PLAIN_VALUE,
                "1,2\n3,4\n5,6".getBytes(StandardCharsets.UTF_8));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> matrixService.parseCsvFile(nonSquareFile));
        assertTrue(exception.getMessage().contains("Matrix must be square"));
    }

    @Test
    void parseCsvFile_ShouldThrowExceptionForInvalidNumberFormat() {
        MockMultipartFile invalidFile = new MockMultipartFile(
                "file", "invalid.csv", MediaType.TEXT_PLAIN_VALUE,
                "1,2,3\n4,a,6\n7,8,9".getBytes(StandardCharsets.UTF_8));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> matrixService.parseCsvFile(invalidFile));
        assertTrue(exception.getMessage().contains("Invalid number format"));
    }

    @Test
    void invertMatrix_ShouldTransposeCorrectly() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] result = matrixService.invertMatrix(matrix);
        assertArrayEquals(new int[]{1, 4, 7}, result[0]);
        assertArrayEquals(new int[]{2, 5, 8}, result[1]);
        assertArrayEquals(new int[]{3, 6, 9}, result[2]);
    }

    @Test
    void sumMatrix_ShouldReturnCorrectSum() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertEquals(45, matrixService.sumMatrix(matrix));
    }

    @Test
    void multiplyMatrix_ShouldReturnLongForSmallNumbers() {
        int[][] matrix = {{2, 3}, {4, 5}};
        Number result = matrixService.multiplyMatrix(matrix);
        assertInstanceOf(Long.class, result);
        assertEquals(120L, result);
    }

    @Test
    void multiplyMatrix_ShouldReturnBigIntegerForLargeNumbers() {
        int[][] matrix = {{1000000, 2000000}, {3000000, 4000000}};
        Number result = matrixService.multiplyMatrix(matrix);
        assertInstanceOf(BigInteger.class, result);
        assertEquals(new BigInteger("24000000000000000000000000"), result);
    }

    @Test
    void multiplyMatrix_ShouldHandleZeroInMatrix() {
        int[][] matrix = {{1000, 2000}, {0, 4000}};
        Number result = matrixService.multiplyMatrix(matrix);
        assertInstanceOf(Long.class, result);
        assertEquals(0L, result);
    }

    @Test
    void multiplyMatrix_ShouldHandleSingleElementMatrix() {
        int[][] matrix = {{5}};
        Number result = matrixService.multiplyMatrix(matrix);
        assertInstanceOf(Long.class, result);
        assertEquals(5L, result);
    }
}