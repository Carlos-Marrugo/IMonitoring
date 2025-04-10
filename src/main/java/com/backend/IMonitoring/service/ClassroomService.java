package com.backend.IMonitoring.service;

import com.backend.IMonitoring.model.Classroom;
import com.backend.IMonitoring.model.ClassroomType;
import com.backend.IMonitoring.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public List<Classroom> getAvailableClassrooms(
            LocalDateTime start,
            LocalDateTime end,
            String building,
            ClassroomType type
    ) {
        if (Duration.between(start, end).toHours() != 2) {
            throw new IllegalArgumentException("Time block must be exactly 2 hours.");
        }
        return classroomRepository.findAvailableClassrooms(start, end, building, type);
    }
}