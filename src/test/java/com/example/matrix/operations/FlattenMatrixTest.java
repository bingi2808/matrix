package com.example.matrix.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlattenMatrixTest {

    private FlattenMatrix flattenMatrix = new FlattenMatrix();

    @Test
    void flattenMatrix_ShouldReturnSingleArray() {
        // Test with a normal matrix
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        String result = flattenMatrix.perform(matrix);
        assertEquals("1,2,3,4,5,6,7,8,9", result);
    }

    @Test
    void flattenMatrix_ShouldHandleSingleRowMatrix() {
        FlattenMatrix flattenMatrix = new FlattenMatrix();
        // Test with a single row matrix
        int[][] singleRowMatrix = {
                {1, 2, 3}
        };
        String result = flattenMatrix.perform(singleRowMatrix);
        assertEquals("1,2,3", result);
    }

    @Test
    void flattenMatrix_ShouldHandleSingleColumnMatrix() {
        // Test with a single column matrix
        int[][] singleColumnMatrix = {
                {1},
                {2},
                {3}
        };
        String result = flattenMatrix.perform(singleColumnMatrix);
        assertEquals("1,2,3", result);
    }

    @Test
    void flattenMatrix_ShouldHandleEmptyMatrix() {
        // Test with an empty matrix
        int[][] emptyMatrix = {};
        String result = flattenMatrix.perform(emptyMatrix);
        assertEquals("", result);
    }
}