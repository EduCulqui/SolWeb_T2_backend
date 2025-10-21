package com.health.controller;

import com.health.dto.CategoryDTO;
import com.health.model.Category;
import com.health.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class CategoryController {

    private final ICategoryService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    // =============================
    // 🔹 GET - Listar todas las categorías
    // =============================
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() throws Exception {
        List<CategoryDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    // =============================
    // 🔹 GET - Buscar categoría por ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Category obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    // =============================
    // 🔹 POST - Registrar nueva categoría
    // =============================
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO dto) throws Exception {
        Category obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdCategory())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // =============================
    // 🔹 PUT - Actualizar categoría existente
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @PathVariable("id") Integer id,
                                              @RequestBody CategoryDTO dto) throws Exception {
        Category obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    // =============================
    // 🔹 DELETE - Eliminar categoría
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =============================
    // 🔁 Métodos auxiliares de conversión
    // =============================
    private CategoryDTO convertToDto(Category obj) {
        return modelMapper.map(obj, CategoryDTO.class);
    }

    private Category convertToEntity(CategoryDTO dto) {
        return modelMapper.map(dto, Category.class);
    }
}

