package ua.yezhyck.attendance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.yezhyck.attendance.tracker.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s IN (SELECT s2 FROM Student s2 JOIN s2.studyClasses scs GROUP BY s2 HAVING COUNT(scs) = 1) AND s IN :students")
    List<Student> findWithOneStudyClass(@Param("students") List<Student> students);
}