package ua.yezhyck.attendance.tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.type.AbsenceReasonType;

@Getter
@Setter
@ToString
public class AbsenceReasonDto {
    private Long id;
    private String name;
    private AbsenceReasonType type;
}