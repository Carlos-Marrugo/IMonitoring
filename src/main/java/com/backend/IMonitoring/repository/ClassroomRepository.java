package com.backend.IMonitoring.repository;


import com.backend.IMonitoring.model.Classroom;
import com.backend.IMonitoring.model.ClassroomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {
    @Query("SELECT c FROM Classroom c WHERE " +
            "c.building.name = :building AND c.type = :type AND " +
            "c NOT IN (SELECT r.classroom FROM Reservation r WHERE " +
            "r.status = 'CONFIRMADA' AND " +
            "(r.startTime < :end AND r.endTime > :start))")
    List<Classroom> findAvailableClassrooms(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("building") String building,
            @Param("type") ClassroomType type
    );
}