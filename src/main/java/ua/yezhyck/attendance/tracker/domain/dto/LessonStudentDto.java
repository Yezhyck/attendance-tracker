package ua.yezhyck.attendance.tracker.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LessonStudentDto {
    private Long id;

    @JsonProperty("student")
    private StudentDto studentDto;

    @JsonProperty("attendanceStatus")
    private AttendanceStatusDto attendanceStatusDto;

    @JsonProperty("absenceReason")
    private AbsenceReasonDto absenceReasonDto;
}