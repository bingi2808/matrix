package com.example.matrix.operations;

import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.util.Arrays;

@Component("multiply")
public class MultiplyMatrix implements MatrixOperation {
    @Override
    public Number perform(int[][] matrix) {
        long product = 1;
        BigInteger bigProduct = null;

        for (int[] row : matrix) {
            for (int value : row) {
                if (bigProduct == null) { // Using long
                    if (value != 0 && Long.MAX_VALUE / Math.abs(value) < Math.abs(product)) {
                        // Overflow detected, switch to BigInteger
                        bigProduct = BigInteger.valueOf(product).multiply(BigInteger.valueOf(value));
                    } else {
                        product *= value;
                    }
                } else {
                    // Already using BigInteger
                    bigProduct = bigProduct.multiply(BigInteger.valueOf(value));
                }
            }
        }
        return (bigProduct != null) ? bigProduct : product;
    }
}
