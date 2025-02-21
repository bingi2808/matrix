package com.example.matrix.operations;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyMatrixTest {

    private final MultiplyMatrix multiplyMatrix = new MultiplyMatrix();

    @Test
    void multiplyMatrix_ShouldReturnLongForSmallNumbers() {
        int[][] matrix = {{2, 3}, {4, 5}};
        Number result = multiplyMatrix.perform(matrix);
        assertInstanceOf(Long.class, result);
        assertEquals(120L, result);
    }

    @Test
    void multiplyMatrix_ShouldReturnBigIntegerForLargeNumbers() {
        int[][] matrix = {{1000000, 2000000}, {3000000, 4000000}};
        Number result = multiplyMatrix.perform(matrix);
        assertInstanceOf(BigInteger.class, result);
        assertEquals(new BigInteger("24000000000000000000000000"), result);
    }

    @Test
    void multiplyMatrix_ShouldHandleZeroInMatrix() {
        int[][] matrix = {{1000, 2000}, {0, 4000}};
        Number result = multiplyMatrix.perform(matrix);
        assertInstanceOf(Long.class, result);
        assertEquals(0L, result);
    }

    @Test
    void multiplyMatrix_ShouldHandleSingleElementMatrix() {
        int[][] matrix = {{5}};
        Number result = multiplyMatrix.perform(matrix);
        assertInstanceOf(Long.class, result);
        assertEquals(5L, result);
    }
}