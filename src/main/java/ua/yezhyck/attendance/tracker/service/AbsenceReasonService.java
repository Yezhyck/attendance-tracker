package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.model.dto.AbsenceReasonDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchAbsenceReasonException;

import java.util.List;
import java.util.Optional;

public interface AbsenceReasonService {

    AbsenceReasonDto addAbsenceReason(AbsenceReasonDto absenceReasonDto);

    Optional<AbsenceReasonDto> getAbsenceReasonById(Long id);

    List<AbsenceReasonDto> getAllAbsenceReasons();

    AbsenceReasonDto modifyAbsenceReasonById(Long id, AbsenceReasonDto absenceReasonDto) throws NoSuchAbsenceReasonException;

    void removeAbsenceReasonById(Long id) throws NoSuchAbsenceReasonException;
}