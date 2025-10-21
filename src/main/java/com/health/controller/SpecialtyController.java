package com.health.controller;

import com.health.dto.SpecialtyDTO;
import com.health.model.Specialty;
import com.health.service.ISpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    //@Autowired
    private final ISpecialtyService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAll() throws Exception {
        List<SpecialtyDTO> list = service.findAll().stream().map(this::convertToDto).toList();

         return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable("id")  Integer id) throws Exception {
        SpecialtyDTO obj = convertToDto(service.findById(id));

        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SpecialtyDTO dto) throws Exception {
        Specialty obj = service.save(convertToEntity(dto));

        //localhost:8080/specialtys/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();

        return ResponseEntity.created(location).build();
        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SpecialtyDTO dto) throws Exception {
        //dto.setIdSpecialty(id);
        Specialty obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<SpecialtyDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Specialty obj = service.findById(id);
        EntityModel<SpecialtyDTO> resource = EntityModel.of(convertToDto(obj));

        //generar link informativo
        //localhost:8080/specialtys/4
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("specialty-self-info"));
        resource.add(link2.withRel("specialty-all-info"));

        return resource;
    }

    private SpecialtyDTO convertToDto(Specialty obj) {
        return modelMapper.map(obj, SpecialtyDTO.class);
    }

    private Specialty convertToEntity(SpecialtyDTO dto) {
        return modelMapper.map(dto, Specialty.class);
    }

}
