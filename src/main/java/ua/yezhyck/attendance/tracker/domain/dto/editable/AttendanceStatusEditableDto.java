package ua.yezhyck.attendance.tracker.domain.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.domain.type.AttendanceStatusType;

@Getter
@Setter
@ToString
public class AttendanceStatusEditableDto {
    private String name;
    private AttendanceStatusType type;
}