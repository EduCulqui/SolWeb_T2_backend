package com.health.controller;

import com.health.dto.ProductDTO;
import com.health.model.Category;
import com.health.model.Family;
import com.health.model.Laboratory;
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
    // GET - Listar todos
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
    // GET - Buscar por ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Product obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    // =============================
    // POST - Registrar nuevo producto
    // =============================
    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = convertToEntity(dto);

        // Vincular relaciones usando IDs
        obj.setCategory(new Category(dto.getIdCategory(), null, null));
        obj.setFamily(new Family(dto.getIdFamily(), null, null));
        obj.setLaboratory(new Laboratory(dto.getIdLaboratory(), null, null, null, null));

        Product saved = service.save(obj);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getIdProduct())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(saved));
    }

    // =============================
    // PUT - Actualizar producto
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Integer id,
                                             @Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = convertToEntity(dto);

        // Vincular relaciones usando IDs
        obj.setCategory(new Category(dto.getIdCategory(), null, null));
        obj.setFamily(new Family(dto.getIdFamily(), null, null));
        obj.setLaboratory(new Laboratory(dto.getIdLaboratory(), null, null, null, null));

        Product updated = service.update(obj, id);
        return ResponseEntity.ok(convertToDto(updated));
    }

    // =============================
    // DELETE - Eliminar producto
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =============================
    // Métodos de conversión
    // =============================
    private ProductDTO convertToDto(Product obj) {
        ProductDTO dto = modelMapper.map(obj, ProductDTO.class);

        // Mapear IDs de relaciones
        dto.setIdCategory(obj.getCategory().getIdCategory());
        dto.setIdFamily(obj.getFamily().getIdFamily());
        dto.setIdLaboratory(obj.getLaboratory().getIdLaboratory());

        dto.setCategoryName(obj.getCategory().getName());
        dto.setFamilyName(obj.getFamily().getName());
        dto.setLaboratoryName(obj.getLaboratory().getName());

        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }
}


