package com.health.service.implementation;

import com.health.model.Exam;
import com.health.repository.IExamRepository;
import com.health.repository.IGenericRepository;
import com.health.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService extends GenericService<Exam, Integer> implements IExamService {
    private final IExamRepository repo;


    @Override
    protected IGenericRepository<Exam, Integer> getRepo() {
        return repo;
    }
}
