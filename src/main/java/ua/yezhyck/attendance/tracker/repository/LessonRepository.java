package ua.yezhyck.attendance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yezhyck.attendance.tracker.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}