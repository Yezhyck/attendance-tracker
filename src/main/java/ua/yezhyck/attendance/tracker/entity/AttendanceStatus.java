package ua.yezhyck.attendance.tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.type.AttendanceStatusType;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "attendance_statuses")
public class AttendanceStatus {

    @Id
    @SequenceGenerator(name = "attendance_statuses_id_sequence", sequenceName = "attendance_statuses_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_statuses_id_sequence")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type", unique = true, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AttendanceStatusType type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attendanceStatus", orphanRemoval = true)
    @ToString.Exclude
    private Set<LessonStudent> lessonStudents = new LinkedHashSet<>();
}