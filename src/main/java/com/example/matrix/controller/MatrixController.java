package com.example.matrix.controller;

import com.example.matrix.service.MatrixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @PostMapping("/echo")
    public String echoMatrix(@RequestParam("file") MultipartFile file) throws Exception {
        int[][] matrix = matrixService.parseCsvFile(file);
        return matrixService.formatMatrix(matrix);
    }

    @PostMapping("/invert")
    public String invertMatrix(@RequestParam("file") MultipartFile file) throws Exception {
        int[][] matrix = matrixService.parseCsvFile(file);
        return matrixService.formatMatrix(matrixService.invertMatrix(matrix));
    }

    @PostMapping("/flatten")
    public String flattenMatrix(@RequestParam("file") MultipartFile file) throws Exception {
        int[][] matrix = matrixService.parseCsvFile(file);
        return matrixService.flattenMatrix(matrix);
    }

    @PostMapping("/sum")
    public int sumMatrix(@RequestParam("file") MultipartFile file) throws Exception {
        int[][] matrix = matrixService.parseCsvFile(file);
        return matrixService.sumMatrix(matrix);
    }

    @PostMapping("/multiply")
    public long multiplyMatrix(@RequestParam("file") MultipartFile file) throws Exception {
        int[][] matrix = matrixService.parseCsvFile(file);
        return matrixService.multiplyMatrix(matrix);
    }
}
