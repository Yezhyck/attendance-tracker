package ua.yezhyck.attendance.tracker.model.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.model.type.AttendanceStatusType;

@Getter
@Setter
@ToString
public class AttendanceStatusEditableDto {
    private String name;
    private AttendanceStatusType type;
}