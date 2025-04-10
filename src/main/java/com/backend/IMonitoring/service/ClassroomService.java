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

    public List<Classroom> getAvailableNow() {
        return classroomRepository.findAvailableNow();
    }

    public List<Classroom> getUnavailableNow() {
        return classroomRepository.findUnavailableNow();
    }

    public boolean checkAvailability(String classroomId, LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("La hora de inicio debe ser antes que la hora de fin.");
        }
        return classroomRepository.isAvailable(classroomId, startTime, endTime);
    }
}