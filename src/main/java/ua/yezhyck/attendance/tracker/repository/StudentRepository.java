package ua.yezhyck.attendance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.yezhyck.attendance.tracker.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s1.id, first_name, last_name " +
            "FROM students s1 " +
            "         JOIN study_classes_students scs1 ON s1.id = scs1.id_student " +
            "         JOIN study_classes sc1 ON sc1.id = scs1.id_study_class " +
            "WHERE s1 IN (SELECT s2 " +
            "             FROM study_classes sc2 " +
            "                      JOIN study_classes_students scs2 on sc2.id = scs2.id_study_class " +
            "                      JOIN students s2 on s2.id = scs2.id_student " +
            "             WHERE sc2.id = :studyClassId) " +
            "GROUP BY s1.id " +
            "HAVING COUNT(sc1) = 1", nativeQuery = true)
    List<Student> findWithOneStudyClassByStudyClassId(@Param("studyClassId") Long studyClassId);
}