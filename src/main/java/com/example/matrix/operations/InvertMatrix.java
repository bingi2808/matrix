package com.example.matrix.operations;

import org.springframework.stereotype.Component;

@Component("invert")
public class InvertMatrix implements MatrixOperation {

    private final EchoMatrix echoMatrix;

    public InvertMatrix(EchoMatrix echoMatrix) {
        this.echoMatrix = echoMatrix;
    }

    @Override
    public String perform(int[][] matrix) {
        int size = matrix.length;
        int[][] inverted = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverted[j][i] = matrix[i][j];
            }
        }
        return echoMatrix.perform(inverted);
    }

}
