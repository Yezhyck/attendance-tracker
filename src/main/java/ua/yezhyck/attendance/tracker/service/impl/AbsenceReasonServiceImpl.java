package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.domain.dto.AbsenceReasonDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchAbsenceReasonException;
import ua.yezhyck.attendance.tracker.domain.mapper.AbsenceReasonMapper;
import ua.yezhyck.attendance.tracker.repository.AbsenceReasonRepository;
import ua.yezhyck.attendance.tracker.service.AbsenceReasonService;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceReasonServiceImpl implements AbsenceReasonService {
    private final AbsenceReasonMapper absenceReasonMapper;
    private final AbsenceReasonRepository absenceReasonRepository;

    @Autowired
    public AbsenceReasonServiceImpl(AbsenceReasonMapper absenceReasonMapper, AbsenceReasonRepository absenceReasonRepository) {
        this.absenceReasonMapper = absenceReasonMapper;
        this.absenceReasonRepository = absenceReasonRepository;
    }

    @Override
    public AbsenceReasonDto addAbsenceReason(AbsenceReasonDto absenceReasonDto) {
        return absenceReasonMapper.mapToAbsenceReasonDto(absenceReasonRepository.save(absenceReasonMapper.mapToAbsenceReason(absenceReasonDto)));
    }

    @Override
    public Optional<AbsenceReasonDto> getAbsenceReasonById(Long id) {
        return absenceReasonRepository.findById(id).map(absenceReasonMapper::mapToAbsenceReasonDto);
    }

    @Override
    public List<AbsenceReasonDto> getAllAbsenceReasons() {
        return absenceReasonMapper.mapAllToAbsenceReasonDtoList(absenceReasonRepository.findAll());
    }

    @Override
    public AbsenceReasonDto modifyAbsenceReasonById(Long id, AbsenceReasonDto absenceReasonDto) throws NoSuchAbsenceReasonException {
        return absenceReasonRepository.findById(id)
                .map(absenceReason -> {
                    absenceReason.setName(absenceReasonDto.getName());
                    absenceReason.setType(absenceReasonDto.getType());

                    return absenceReasonMapper.mapToAbsenceReasonDto(absenceReasonRepository.save(absenceReason));
                })
                .orElseThrow(() -> new NoSuchAbsenceReasonException(String.format("Absence reason does not exist with id=%d", id)));
    }

    @Override
    public void removeAbsenceReasonById(Long id) throws NoSuchAbsenceReasonException {
        if (!absenceReasonRepository.existsById(id)) {
            throw new NoSuchAbsenceReasonException(String.format("Absence reason does not exist with id=%d", id));
        }

        absenceReasonRepository.deleteById(id);
    }
}