package com.backend.IMonitoring.controller;

import com.backend.IMonitoring.model.Classroom;
import com.backend.IMonitoring.dto.AvailabilityRequest;
import com.backend.IMonitoring.service.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    @PostMapping("/check")
    public ResponseEntity<?> checkAvailability(@Valid @RequestBody AvailabilityRequest request) {
        try {
            boolean isAvailable = classroomService.checkAvailability(request);
            return ResponseEntity.ok(Map.of(
                    "available", isAvailable,
                    "message", isAvailable ? "La sala está disponible." : "La sala no está disponible."
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Datos inválidos",
                    "message", e.getMessage()
            ));
        }
    }
}

