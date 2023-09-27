package ua.yezhyck.attendance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yezhyck.attendance.tracker.domain.entity.StudyClass;

@Repository
public interface StudyClassRepository extends JpaRepository<StudyClass, Long> {
}