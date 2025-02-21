package com.example.matrix.operations;

import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component("sum")
public class SumMatrix implements MatrixOperation {
    @Override
    public Long perform(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .asLongStream()
                .sum();
    }
}
