package ua.yezhyck.attendance.tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
}