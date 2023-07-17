package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.yezhyck.attendance.tracker.dto.AbsenceReasonDto;
import ua.yezhyck.attendance.tracker.entity.AbsenceReason;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbsenceReasonMapper {
    AbsenceReasonMapper INSTANCE = Mappers.getMapper(AbsenceReasonMapper.class);

    AbsenceReasonDto mapToAbsenceReasonDto(AbsenceReason absenceReason);

    AbsenceReason mapToAbsenceReason(AbsenceReasonDto absenceReasonDto);

    List<AbsenceReasonDto> mapAllToAbsenceReasonDtoList(List<AbsenceReason> absenceReasons);

    List<AbsenceReason> mapAllToAbsenceReasons(List<AbsenceReasonDto> absenceReasonDtoList);
}