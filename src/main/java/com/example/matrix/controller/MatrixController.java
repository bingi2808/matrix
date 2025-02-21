package com.example.matrix.controller;

import com.example.matrix.operations.MatrixOperation;
import com.example.matrix.service.MatrixParserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    private final MatrixParserService parserService;
    private final Map<String, MatrixOperation> operations;

    public MatrixController(MatrixParserService parserService, Map<String, MatrixOperation> operations) {
        this.parserService = parserService;
        this.operations = operations;
    }

    @PostMapping("/{operation}")
    public Object processMatrix(@PathVariable String operation, @RequestParam("file") MultipartFile file) throws Exception {
        int[][] matrix = parserService.parseCsvFile(file);

        MatrixOperation matrixOperation = operations.get(operation);
        if (matrixOperation == null) {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        return matrixOperation.perform(matrix);
    }
}
