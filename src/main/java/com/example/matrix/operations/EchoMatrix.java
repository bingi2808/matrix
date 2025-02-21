package com.example.matrix.operations;

import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component("echo")
public class EchoMatrix implements MatrixOperation {
    @Override
    public String perform(int[][] matrix) {
        return Arrays.stream(matrix)
                .map(row -> Arrays.toString(row).replaceAll("[\\[\\] ]", ""))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");
    }
}
