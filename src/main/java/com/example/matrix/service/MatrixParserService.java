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
public class MatrixParserService {

    public int[][] parseCsvFile(MultipartFile file) throws Exception {
        List<CSVRecord> records = readCsvRecords(file);
        validateMatrix(records);
        return convertToMatrix(records);
    }

    private List<CSVRecord> readCsvRecords(MultipartFile file) throws Exception {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            List<CSVRecord> records = csvParser.getRecords();
            if (records.isEmpty()) {
                throw new IllegalArgumentException("CSV file is empty");
            }
            return records;
        }
    }

    private void validateMatrix(List<CSVRecord> records) {
        int rowCount = records.size();
        int colCount = records.get(0).size();

        if (rowCount != colCount) {
            throw new IllegalArgumentException("Matrix must be square (same number of rows and columns)");
        }

        for (int i = 0; i < rowCount; i++) {
            if (records.get(i).size() != colCount) {
                throw new IllegalArgumentException("Inconsistent row length at row " + (i + 1));
            }
        }
    }

    private int[][] convertToMatrix(List<CSVRecord> records) {
        int rowCount = records.size();
        int colCount = records.get(0).size();
        int[][] matrix = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            CSVRecord record = records.get(i);
            for (int j = 0; j < colCount; j++) {
                try {
                    matrix[i][j] = Integer.parseInt(record.get(j).trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format at row " + (i + 1) + ", column " + (j + 1));
                }
            }
        }
        return matrix;
    }

}