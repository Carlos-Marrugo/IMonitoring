package com.backend.IMonitoring.service;

import com.backend.IMonitoring.dto.AvailabilityRequest;
import com.backend.IMonitoring.model.Classroom;
import com.backend.IMonitoring.model.ClassroomType;
import com.backend.IMonitoring.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public boolean checkAvailability(AvailabilityRequest request) {
        // Validar formato de horas y fecha
        LocalDateTime start = parseDateTime(request.getDate(), request.getStartTime());
        LocalDateTime end = parseDateTime(request.getDate(), request.getEndTime());

        // Validar que la hora de inicio sea antes que la de fin
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("La hora de inicio debe ser antes que la hora de fin.");
        }

        // 2 horas en la sala
        if (Duration.between(start, end).toHours() != 2) {
            throw new IllegalArgumentException("El bloque de tiempo debe ser de 2 horas exactas.");
        }

        return classroomRepository.isAvailable(request.getClassroomId(), start, end);
    }

    private LocalDateTime parseDateTime(String date, String time) {
        try {
            String dateTimeString = date + "T" + time + ":00";
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha u hora inválido.");
        }
    }
}