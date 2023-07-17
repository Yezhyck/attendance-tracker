package ua.yezhyck.attendance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yezhyck.attendance.tracker.entity.LessonStudent;

@Repository
public interface LessonStudentRepository extends JpaRepository<LessonStudent, Long> {
}