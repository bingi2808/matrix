package com.example.matrix.operations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SumMatrixTest {

    private final SumMatrix sumMatrix = new SumMatrix();

    @Test
    void sumMatrix_ShouldReturnCorrectSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertEquals(45, sumMatrix.perform(matrix));
    }

    @Test
    void sumMatrix_ShouldReturnZero_ForEmptyMatrix() {
        int[][] matrix = new int[0][0];
        assertEquals(0, sumMatrix.perform(matrix));
    }

    @Test
    void sumMatrix_ShouldHandleNegativeNumbers() {
        int[][] matrix = {
                {-1, -2, -3},
                {-4, -5, -6},
                {-7, -8, -9}
        };
        assertEquals(-45, sumMatrix.perform(matrix));
    }

    @Test
    void sumMatrix_ShouldHandleMixedNumbers() {
        int[][] matrix = {
                {10, -5, 15},
                {-10, 20, -30},
                {25, -35, 40}
        };
        assertEquals(30, sumMatrix.perform(matrix));
    }

    @Test
    void sumMatrix_ShouldHandleLargeNumbers() {
        int[][] matrix = {
                {2_000_000_000, 1_000_000_000},
                {1_500_000_000, 1_500_000_000}
        };
        assertEquals(6_000_000_000L, sumMatrix.perform(matrix));
    }
}
