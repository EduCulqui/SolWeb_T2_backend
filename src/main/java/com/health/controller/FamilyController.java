package com.health.controller;

import com.health.dto.FamilyDTO;
import com.health.model.Family;
import com.health.service.IFamilyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/families") // Ruta principal
@RequiredArgsConstructor
public class FamilyController {

    private final IFamilyService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FamilyDTO>> findAll() throws Exception {
        List<FamilyDTO> list = service.findAll()
                .stream()
                .map(f -> modelMapper.map(f, FamilyDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Family obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, FamilyDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody FamilyDTO dto) throws Exception {
        Family fam = modelMapper.map(dto, Family.class);
        Family obj = service.save(fam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdFamily())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyDTO> update(@Valid @RequestBody FamilyDTO dto, @PathVariable("id") Integer id) throws Exception {
        Family fam = modelMapper.map(dto, Family.class);
        Family obj = service.update(fam, id);
        return ResponseEntity.ok(modelMapper.map(obj, FamilyDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

