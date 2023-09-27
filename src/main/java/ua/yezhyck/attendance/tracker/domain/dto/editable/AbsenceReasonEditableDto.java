package ua.yezhyck.attendance.tracker.domain.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.domain.type.AbsenceReasonType;

@Getter
@Setter
@ToString
public class AbsenceReasonEditableDto {
    private String name;
    private AbsenceReasonType type;
}