package com.example.matrix.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class MatrixService {

    public int[][] parseCsvFile(MultipartFile file) throws Exception {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            List<CSVRecord> records = csvParser.getRecords();
            if (records.isEmpty()) {
                throw new IllegalArgumentException("CSV file is empty");
            }

            int rowCount = records.size();
            int colCount = records.getFirst().size();
            int[][] matrix = new int[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                CSVRecord record = records.get(i);
                if (record.size() != colCount) {
                    throw new IllegalArgumentException("Inconsistent row length at row " + (i + 1));
                }
                for (int j = 0; j < colCount; j++) {
                    try {
                        matrix[i][j] = Integer.parseInt(record.get(j).trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid number format at row " + (i + 1) + ", column " + (j + 1));
                    }
                }
            }

            if (rowCount != colCount) {
                throw new IllegalArgumentException("Matrix must be square (same number of rows and columns)");
            }

            return matrix;
        }
    }

    public String formatMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append(String.join(",", java.util.Arrays.stream(row)
                    .mapToObj(String::valueOf).toArray(String[]::new))).append("\n");
        }
        return sb.toString().trim();
    }

    public int[][] invertMatrix(int[][] matrix) {
        int size = matrix.length;
        int[][] inverted = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverted[j][i] = matrix[i][j];
            }
        }
        return inverted;
    }

    public String flattenMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value).append(",");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public int sumMatrix(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                sum += value;
            }
        }
        return sum;
    }

    public long multiplyMatrix(int[][] matrix) {
        long product = 1;
        for (int[] row : matrix) {
            for (int value : row) {
                product *= value;
            }
        }
        return product;
    }

}
