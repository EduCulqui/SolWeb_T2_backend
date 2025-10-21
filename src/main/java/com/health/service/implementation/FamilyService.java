package com.health.service.implementation;

import com.health.model.Family;
import com.health.repository.IFamilyRepository;
import com.health.repository.IGenericRepository;
import com.health.service.IFamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyService extends GenericService<Family, Integer> implements IFamilyService {

    private final IFamilyRepository repo;

    @Override
    protected IGenericRepository<Family, Integer> getRepo() {
        return repo;
    }
}

