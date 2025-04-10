package com.backend.IMonitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {
    @Id
    private String id; // Ej: "A101"

    private String name;
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private ClassroomType type; // AULA, LABORATORIO, AUDITORIO

    private String resources; // "PROYECTOR,PC,AIRE"

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
