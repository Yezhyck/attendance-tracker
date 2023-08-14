package ua.yezhyck.attendance.tracker.model.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudyClassEditableDto {
    private Long userId;
    private String name;
}