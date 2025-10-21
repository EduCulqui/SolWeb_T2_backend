package com.health.controller;

import com.health.dto.LaboratoryDTO;
import com.health.model.Laboratory;
import com.health.service.ILaboratoryService;
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
@RequestMapping("/laboratories") // Ruta principal
@RequiredArgsConstructor
public class LaboratoryController {

    private final ILaboratoryService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> findAll() throws Exception {
        List<LaboratoryDTO> list = service.findAll()
                .stream()
                .map(l -> modelMapper.map(l, LaboratoryDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Laboratory obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, LaboratoryDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody LaboratoryDTO dto) throws Exception {
        Laboratory lab = modelMapper.map(dto, Laboratory.class);
        Laboratory obj = service.save(lab);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdLaboratory())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> update(@Valid @RequestBody LaboratoryDTO dto, @PathVariable("id") Integer id) throws Exception {
        Laboratory lab = modelMapper.map(dto, Laboratory.class);
        Laboratory obj = service.update(lab, id);
        return ResponseEntity.ok(modelMapper.map(obj, LaboratoryDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
