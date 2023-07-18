package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.AbsenceReasonDto;
import ua.yezhyck.attendance.tracker.mapper.AbsenceReasonMapper;
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
    public Optional<AbsenceReasonDto> modifyAbsenceReasonById(Long id, AbsenceReasonDto absenceReasonDto) {
        return absenceReasonRepository.findById(id)
                .map(absenceReason -> {
                    absenceReason.setName(absenceReasonDto.getName());
                    absenceReason.setType(absenceReasonDto.getType());

                    return absenceReasonMapper.mapToAbsenceReasonDto(absenceReasonRepository.save(absenceReason));
                });
    }

    @Override
    public void removeAbsenceReasonById(Long id) {
        absenceReasonRepository.deleteById(id);
    }

    @Override
    public boolean checkIfAbsenceReasonExistsById(Long id) {
        return absenceReasonRepository.existsById(id);
    }
}