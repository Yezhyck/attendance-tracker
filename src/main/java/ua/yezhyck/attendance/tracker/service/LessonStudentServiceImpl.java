package ua.yezhyck.attendance.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.dto.LessonStudentEditableDto;
import ua.yezhyck.attendance.tracker.entity.LessonStudent;
import ua.yezhyck.attendance.tracker.mapper.AbsenceReasonMapper;
import ua.yezhyck.attendance.tracker.mapper.AttendanceStatusMapper;
import ua.yezhyck.attendance.tracker.mapper.LessonStudentMapper;
import ua.yezhyck.attendance.tracker.mapper.StudentMapper;
import ua.yezhyck.attendance.tracker.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class LessonStudentServiceImpl implements LessonStudentService {
    private final LessonStudentMapper lessonStudentMapper;
    private final StudentMapper studentMapper;
    private final AttendanceStatusMapper attendanceStatusMapper;
    private final AbsenceReasonMapper absenceReasonMapper;
    private final LessonStudentRepository lessonStudentRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final AttendanceStatusRepository attendanceStatusRepository;
    private final AbsenceReasonRepository absenceReasonRepository;

    @Autowired
    public LessonStudentServiceImpl(LessonStudentMapper lessonStudentMapper, StudentMapper studentMapper, AttendanceStatusMapper attendanceStatusMapper, AbsenceReasonMapper absenceReasonMapper, LessonStudentRepository lessonStudentRepository,
                                    StudentRepository studentRepository,
                                    LessonRepository lessonRepository,
                                    AttendanceStatusRepository attendanceStatusRepository,
                                    AbsenceReasonRepository absenceReasonRepository) {
        this.lessonStudentMapper = lessonStudentMapper;
        this.studentMapper = studentMapper;
        this.attendanceStatusMapper = attendanceStatusMapper;
        this.absenceReasonMapper = absenceReasonMapper;
        this.lessonStudentRepository = lessonStudentRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.attendanceStatusRepository = attendanceStatusRepository;
        this.absenceReasonRepository = absenceReasonRepository;
    }

    @Override
    public LessonStudentDto addLessonStudent(LessonStudentEditableDto lessonStudentEditableDto) {
        LessonStudent lessonStudent = lessonStudentMapper.mapToLessonStudent(lessonStudentEditableDto);

        lessonRepository.findById(lessonStudentEditableDto.getLessonId())
                .ifPresent(lessonStudent::setLesson);
        studentRepository.findById(lessonStudentEditableDto.getStudentId())
                .ifPresent(lessonStudent::setStudent);
        attendanceStatusRepository.findById(lessonStudentEditableDto.getAttendanceStatusId())
                .ifPresent(lessonStudent::setAttendanceStatus);
        absenceReasonRepository.findById(lessonStudentEditableDto.getAbsenceReasonId())
                .ifPresent(lessonStudent::setAbsenceReason);

        return lessonStudentMapper.mapToLessonStudentDto(lessonStudentRepository.save(lessonStudent));
    }

    @Override
    public Optional<LessonStudentDto> getLessonStudentById(Long id) {
        return lessonStudentRepository.findById(id).map(lessonStudentMapper::mapToLessonStudentDto);
    }

    @Override
    public List<LessonStudentDto> getAllLessonStudents() {
        return lessonStudentMapper.mapAllToLessonStudentDtoList(lessonStudentRepository.findAll());
    }

    @Override
    public Optional<LessonStudentDto> modifyLessonStudentById(Long id, LessonStudentEditableDto lessonStudentEditableDto) {
        return lessonStudentRepository.findById(id)
                .map(lessonStudent -> {
                    lessonRepository.findById(lessonStudentEditableDto.getLessonId())
                            .ifPresent(lessonStudent::setLesson);
                    studentRepository.findById(lessonStudentEditableDto.getStudentId())
                            .ifPresent(lessonStudent::setStudent);
                    attendanceStatusRepository.findById(lessonStudentEditableDto.getAttendanceStatusId())
                            .ifPresent(lessonStudent::setAttendanceStatus);
                    absenceReasonRepository.findById(lessonStudentEditableDto.getAbsenceReasonId())
                            .ifPresent(lessonStudent::setAbsenceReason);

                    return Optional.of(lessonStudentMapper.mapToLessonStudentDto(lessonStudentRepository.save(lessonStudent)));
                })
                .orElse(Optional.empty());
    }

    @Override
    public void removeLessonStudentById(Long id) {
        lessonStudentRepository.deleteById(id);
    }

    @Override
    public boolean checkIfLessonStudentExistsById(Long id) {
        return lessonStudentRepository.existsById(id);
    }
}