package ua.yezhyck.attendance.tracker.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {
    private Long id;
    private Long telegramId;

    @JsonProperty("studyClasses")
    private List<StudyClassDto> studyClassDtoList;
}
