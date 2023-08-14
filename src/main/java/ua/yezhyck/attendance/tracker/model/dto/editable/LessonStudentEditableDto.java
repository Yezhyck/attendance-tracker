package ua.yezhyck.attendance.tracker.model.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LessonStudentEditableDto {
    private Long studentId;
    private Long lessonId;
    private Long attendanceStatusId;
    private Long absenceReasonId;
}