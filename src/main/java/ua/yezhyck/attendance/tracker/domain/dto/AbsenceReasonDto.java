package ua.yezhyck.attendance.tracker.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.domain.type.AbsenceReasonType;

@Getter
@Setter
@ToString
public class AbsenceReasonDto {
    private Long id;
    private String name;
    private AbsenceReasonType type;
}