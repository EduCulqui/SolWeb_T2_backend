package com.health.service.implementation;

import com.health.model.Specialty;
import com.health.repository.IGenericRepository;
import com.health.repository.ISpecialtyRepository;
import com.health.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyService extends GenericService<Specialty, Integer> implements ISpecialtyService {

    //@Autowired
    private final ISpecialtyRepository repo;


    @Override
    protected IGenericRepository<Specialty, Integer> getRepo() {
        return repo;
    }
}
