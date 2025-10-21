package com.health.service.implementation;

import com.health.model.Category;
import com.health.repository.ICategoryRepository;
import com.health.repository.IGenericRepository;
import com.health.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends GenericService<Category, Integer> implements ICategoryService {

    private final ICategoryRepository repo;

    @Override
    protected IGenericRepository<Category, Integer> getRepo() {
        return repo;
    }
}

