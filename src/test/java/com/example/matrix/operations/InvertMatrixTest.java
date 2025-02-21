package com.example.matrix.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InvertMatrixTest {

    @Mock
    private EchoMatrix echoMatrix;

    @InjectMocks
    private InvertMatrix invertMatrix;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void invertMatrix_ShouldTransposeCorrectly() {
        int[][] input = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expectedInverted = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };

        when(echoMatrix.perform(eq(expectedInverted))).thenReturn("Inverted Matrix");

        String result = invertMatrix.perform(input);

        assertEquals("Inverted Matrix", result);
        verify(echoMatrix).perform(eq(expectedInverted));
    }

    @Test
    void invertMatrix_ShouldHandleEmptyMatrix() {
        int[][] input = {};
        int[][] expectedInverted = {};

        when(echoMatrix.perform(eq(expectedInverted))).thenReturn("Empty Matrix");

        String result = invertMatrix.perform(input);

        assertEquals("Empty Matrix", result);
        verify(echoMatrix).perform(eq(expectedInverted));
    }

    @Test
    void invertMatrix_ShouldHandleSingleElementMatrix() {
        int[][] input = {{42}};
        int[][] expectedInverted = {{42}};

        when(echoMatrix.perform(eq(expectedInverted))).thenReturn("Single Element Matrix");

        String result = invertMatrix.perform(input);

        assertEquals("Single Element Matrix", result);
        verify(echoMatrix).perform(eq(expectedInverted));
    }
}
