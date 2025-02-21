package com.example.matrix.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatrixParserServiceTest {

    private final MatrixParserService service = new MatrixParserService();

    @Test
    void testParseCsvFile_ValidSquareMatrix() throws Exception {
        String csvContent = "1,2,3\n4,5,6\n7,8,9";
        MultipartFile file = createMockMultipartFile(csvContent);

        int[][] result = service.parseCsvFile(file);

        assertArrayEquals(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }, result);
    }

    @Test
    void testParseCsvFile_EmptyFile() {
        MultipartFile file = createMockMultipartFile("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.parseCsvFile(file));
        assertEquals("CSV file is empty", exception.getMessage());
    }

    @Test
    void testParseCsvFile_NonSquareMatrix() {
        String csvContent = "1,2,3\n4,5,6";
        MultipartFile file = createMockMultipartFile(csvContent);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.parseCsvFile(file));
        assertEquals("Matrix must be square (same number of rows and columns)", exception.getMessage());
    }

    @Test
    void testParseCsvFile_InconsistentRowLength() {
        String csvContent = "1,2,3\n4,5\n7,8,9";
        MultipartFile file = createMockMultipartFile(csvContent);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.parseCsvFile(file));
        assertEquals("Inconsistent row length at row 2", exception.getMessage());
    }

    @Test
    void testParseCsvFile_InvalidNumberFormat() {
        String csvContent = "1,2,3\n4,five,6\n7,8,9";
        MultipartFile file = createMockMultipartFile(csvContent);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.parseCsvFile(file));
        assertEquals("Invalid number format at row 2, column 2", exception.getMessage());
    }

    @Test
    void parseCsvFile_ValuesExceedingIntegerRange() {
        MockMultipartFile outOfRangeFile = new MockMultipartFile(
                "file", "invalid.csv", MediaType.TEXT_PLAIN_VALUE,
                "2147483648,1\n2,3".getBytes()); // 2147483648 exceeds Integer.MAX_VALUE

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.parseCsvFile(outOfRangeFile));
        assertEquals("Invalid number format at row 1, column 1", exception.getMessage());
    }

    @Test
    void parseCsvFile_ValuesBelowIntegerRange() {
        MockMultipartFile outOfRangeFile = new MockMultipartFile(
                "file", "invalid.csv", MediaType.TEXT_PLAIN_VALUE,
                "-2147483649,1\n2,3".getBytes()); // -2147483649 exceeds Integer.MIN_VALUE

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.parseCsvFile(outOfRangeFile));
        assertEquals("Invalid number format at row 1, column 1", exception.getMessage());
    }

    private MultipartFile createMockMultipartFile(String content) {
        MultipartFile file = mock(MultipartFile.class);
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        try {
            when(file.getInputStream()).thenReturn(inputStream);
        } catch (Exception e) {
            fail("Failed to create mock MultipartFile", e);
        }
        return file;
    }


}
