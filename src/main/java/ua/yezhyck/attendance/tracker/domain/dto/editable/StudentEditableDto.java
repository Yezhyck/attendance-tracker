package ua.yezhyck.attendance.tracker.domain.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentEditableDto {
    private String firstName;
    private String lastName;
}