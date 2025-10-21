package com.health.controller;

import com.health.dto.ProductDTO;
import com.health.model.Product;
import com.health.service.IProductService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Data
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;


    // =============================
    // 🔹 GET - Listar todos
    // =============================
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() throws Exception {
        List<ProductDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    // =============================
    // 🔹 GET - Buscar por ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Product obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    // =============================
    // 🔹 POST - Registrar nuevo producto
    // =============================
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdProduct())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // =============================
    // 🔹 PUT - Actualizar producto
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable("id") Integer id,
                                             @RequestBody ProductDTO dto) throws Exception {
        Product obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    // =============================
    // 🔹 DELETE - Eliminar producto
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =============================
    // 🔁 Métodos de conversión
    // =============================

    private ProductDTO convertToDto(Product obj) {
        return modelMapper.map(obj, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }
}

