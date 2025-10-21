package com.health.service.implementation;

import com.health.model.Medic;
import com.health.repository.IGenericRepository;
import com.health.repository.IMedicRepository;
import com.health.repository.IPatientRepository;
import com.health.service.IMedicService;
import com.health.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicService extends GenericService<Medic, Integer> implements IMedicService {
    //@Autowired
    private final IMedicRepository repo;


    @Override
    protected IGenericRepository<Medic, Integer> getRepo() {
        return repo;
    }
}
