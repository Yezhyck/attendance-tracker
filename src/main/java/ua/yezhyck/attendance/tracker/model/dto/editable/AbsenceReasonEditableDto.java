package ua.yezhyck.attendance.tracker.model.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.yezhyck.attendance.tracker.model.type.AbsenceReasonType;

@Getter
@Setter
@ToString
public class AbsenceReasonEditableDto {
    private String name;
    private AbsenceReasonType type;
}