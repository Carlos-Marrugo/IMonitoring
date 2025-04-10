package com.backend.IMonitoring.controller;

import com.backend.IMonitoring.model.Classroom;
import com.backend.IMonitoring.dto.AvailabilityRequest;
import com.backend.IMonitoring.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    //classrooms available now
    @GetMapping("/available")
    public ResponseEntity<List<Classroom>> getAvailableNow() {
        return ResponseEntity.ok(classroomService.getAvailableNow());
    }

    //classrooms not available now
    @GetMapping("/unavailable")
    public ResponseEntity<List<Classroom>> getUnavailableNow() {
        return ResponseEntity.ok(classroomService.getUnavailableNow());
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkAvailability(@RequestBody AvailabilityRequest request) {
        return ResponseEntity.ok(
                classroomService.checkAvailability(
                        request.getClassroomId(),
                )
        );
    }

}