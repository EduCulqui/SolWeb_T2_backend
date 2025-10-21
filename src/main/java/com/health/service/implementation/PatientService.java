package com.health.service.implementation;

import com.health.model.Patient;
import com.health.repository.IGenericRepository;
import com.health.repository.IPatientRepository;
import com.health.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService extends GenericService<Patient, Integer> implements IPatientService {
    //@Autowired
    private final IPatientRepository repo;

    @Override
    protected IGenericRepository<Patient, Integer> getRepo() {
        return repo;
    }

    /*
    @Override
    public Patient save(Patient patient) throws Exception {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient, Integer id) throws Exception {
        // EVALUACION CON EL ID
        // API REFLEXION
        patient.setIdPatient(id);
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Patient());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
     */

    /*public PatientService(IPatientRepository repo) {
        this.repo = repo;
    }*/

    /*@Override
    public Patient validAndSave(Patient patient) {
        if(patient.getIdPatient() > 0){
            return repo.save(patient);
        }else{
            return new Patient();
        }
    }
     */
}
