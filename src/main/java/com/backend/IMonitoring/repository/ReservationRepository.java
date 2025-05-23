package com.backend.IMonitoring.repository;

import com.backend.IMonitoring.model.Reservation;
import com.backend.IMonitoring.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByClassroomId(String classroomId);
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByStartTimeAfter(LocalDateTime dateTime);

    @Query("SELECT r FROM Reservation r WHERE " +
            "r.status = 'CONFIRMADA' AND " +
            "r.startTime <= :now AND r.endTime >= :now")
    List<Reservation> findCurrentReservations(@Param("now") LocalDateTime now);
}