package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.AbsenceReasonDto;

import java.util.List;
import java.util.Optional;

public interface AbsenceReasonService {

    AbsenceReasonDto addAbsenceReason(AbsenceReasonDto absenceReasonDto);

    Optional<AbsenceReasonDto> getAbsenceReasonById(Long id);

    List<AbsenceReasonDto> getAllAbsenceReasons();

    Optional<AbsenceReasonDto> modifyAbsenceReasonById(Long id, AbsenceReasonDto absenceReasonDto);

    void removeAbsenceReasonById(Long id);

    boolean checkIfAbsenceReasonExistsById(Long id);
}