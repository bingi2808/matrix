package com.example.matrix.operations;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("flatten")
public class FlattenMatrix implements MatrixOperation {

    @Override
    public String perform(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse("");
    }
}
