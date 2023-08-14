package ua.yezhyck.attendance.tracker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.model.type.AbsenceReasonType;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "absence_reasons")
public class AbsenceReason {

    @Id
    @SequenceGenerator(name = "absence_reasons_id_sequence", sequenceName = "absence_reasons_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absence_reasons_id_sequence")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type", unique = true, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AbsenceReasonType type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "absenceReason", orphanRemoval = true)
    @ToString.Exclude
    private Set<LessonStudent> lessonStudents = new LinkedHashSet<>();
}