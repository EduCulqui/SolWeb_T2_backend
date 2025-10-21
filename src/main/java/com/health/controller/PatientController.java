package com.health.controller;

import com.health.dto.PatientDTO;
import com.health.model.Patient;
import com.health.service.IPatientService;
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
@RequestMapping("/patients")
//@AllArgsConstructor
@RequiredArgsConstructor
// @CrossOrigin(origins = "*")
public class PatientController {
    //@Autowired
    private final IPatientService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>>  findAll() throws Exception{
        //List<Patient> list = service.findAll();
        /*List<PatientDTO> list = service.findAll().stream().map(e -> new PatientDTO(
                e.getIdPatient(),e.getFirstName(), e.getLastName(), e.getDni(), e.getAddress(),
                e.getPhone(), e.getEmail()
        )).toList();*/
        //ModelMapper modelMapper = new ModelMapper();
        // List<PatientDTO> list = service.findAll().stream().map(e->modelMapper.map(e, PatientDTO.class)).toList();
        List<PatientDTO> list = service.findAll().stream().map(this::convertToDto).toList(); // e -> convertToDto(e)
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO>  findById(@PathVariable("id") Integer id) throws Exception{
        // Patient obj =  service.findById(id);
        //PatientDTO obj = modelMapper.map(service.findById(id), PatientDTO.class) ;
        PatientDTO obj = convertToDto(service.findById(id)) ;
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> save(@Valid @RequestBody PatientDTO dto) throws Exception{
        // Patient obj = service.save(patient);
        //Patient obj = service.save(modelMapper.map(dto, Patient.class));
        Patient obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        // return ResponseEntity.ok(obj);
        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PatientDTO dto) throws Exception{
        //Patient obj =  service.update(patient, id);
        //return ResponseEntity.ok(obj);

        //Patient obj =  service.update(modelMapper.map(dto, Patient.class), id);
        //return ResponseEntity.ok(modelMapper.map(obj, PatientDTO.class));

        Patient obj =  service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Convertir de un Modelo a un DTO
    private PatientDTO convertToDto(Patient obj){
        return modelMapper.map(obj, PatientDTO.class);
    }

    // Convertir de un DTO a un Modelo (Entity)
    private Patient convertToEntity(PatientDTO dto){
        return modelMapper.map(dto, Patient.class);
    }

    /*public PatientController(IPatientService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Patient savePatient(){
        // Pedirle al servicio que me envie informacion
        //service = new PatientService();
        return service.validAndSave(new Patient());
        //return new Patient(1,"Carlos","Valdivia");
    }
     */
}
