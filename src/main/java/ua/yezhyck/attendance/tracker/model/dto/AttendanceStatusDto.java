package ua.yezhyck.attendance.tracker.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.model.type.AttendanceStatusType;

@Getter
@Setter
@ToString
public class AttendanceStatusDto {
    private Long id;
    private String name;
    private AttendanceStatusType type;
}