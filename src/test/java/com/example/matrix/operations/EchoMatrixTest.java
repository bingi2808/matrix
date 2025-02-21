package com.example.matrix.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchoMatrixTest {

    private EchoMatrix echoMatrix = new EchoMatrix();

    @Test
    void echoMatrix_shouldReturnMatrixString() {
        // Test with a normal matrix
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        String result = echoMatrix.perform(matrix);
        assertEquals("1,2,3\n4,5,6\n7,8,9", result);
    }

    @Test
    void echoMatrix_shouldHandleSingleRowMatrxi() {
        // Test with a single row matrix
        int[][] singleRowMatrix = {
                {1, 2, 3}
        };
        String result = echoMatrix.perform(singleRowMatrix);
        assertEquals("1,2,3", result);
    }

    @Test
    void echoMatrix_shouldHandleSingleColumnMatrxi() {
        // Test with a single column matrix
        int[][] singleColumnMatrix = {
                {1},
                {2},
                {3}
        };
        String result = echoMatrix.perform(singleColumnMatrix);
        assertEquals("1\n2\n3", result);
    }

    @Test
    void echoMatrix_shouldHandleEmptyMatrxi() {
        // Test with an empty matrix
        int[][] emptyMatrix = {};
        String result = echoMatrix.perform(emptyMatrix);
        assertEquals("", result);
    }

}