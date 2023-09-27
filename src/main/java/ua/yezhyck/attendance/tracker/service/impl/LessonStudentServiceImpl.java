package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.domain.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.domain.dto.editable.LessonStudentEditableDto;
import ua.yezhyck.attendance.tracker.exception.*;
import ua.yezhyck.attendance.tracker.domain.mapper.LessonStudentMapper;
import ua.yezhyck.attendance.tracker.domain.entity.*;
import ua.yezhyck.attendance.tracker.repository.*;
import ua.yezhyck.attendance.tracker.service.LessonStudentService;

import java.util.List;
import java.util.Optional;

@Service
public class LessonStudentServiceImpl implements LessonStudentService {
    private final LessonStudentMapper lessonStudentMapper;
    private final LessonStudentRepository lessonStudentRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final AttendanceStatusRepository attendanceStatusRepository;
    private final AbsenceReasonRepository absenceReasonRepository;

    @Autowired
    public LessonStudentServiceImpl(LessonStudentMapper lessonStudentMapper, LessonStudentRepository lessonStudentRepository, StudentRepository studentRepository, LessonRepository lessonRepository, AttendanceStatusRepository attendanceStatusRepository, AbsenceReasonRepository absenceReasonRepository) {
        this.lessonStudentMapper = lessonStudentMapper;
        this.lessonStudentRepository = lessonStudentRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.attendanceStatusRepository = attendanceStatusRepository;
        this.absenceReasonRepository = absenceReasonRepository;
    }

    @Override
    public LessonStudentDto addLessonStudent(LessonStudentEditableDto lessonStudentEditableDto) throws NoSuchStudentException, NoSuchLessonException, NoSuchAbsenceReasonException, NoSuchAttendanceStatusException, NotAddedToStudyClassStudentException {
        return lessonStudentMapper.mapToLessonStudentDto(lessonStudentRepository.save(configureLessonStudent(lessonStudentEditableDto, new LessonStudent())));
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
    public LessonStudentDto modifyLessonStudentById(Long id, LessonStudentEditableDto lessonStudentEditableDto) throws NoSuchLessonStudentException, NoSuchStudentException, NoSuchLessonException, NoSuchAbsenceReasonException, NoSuchAttendanceStatusException, NotAddedToStudyClassStudentException {
        LessonStudent lessonStudent = lessonStudentRepository.findById(id)
                .orElseThrow(() -> new NoSuchLessonStudentException(String.format("LessonStudent does not exist with id=%d", id)));

        return lessonStudentMapper.mapToLessonStudentDto(lessonStudentRepository.save(configureLessonStudent(lessonStudentEditableDto, lessonStudent)));
    }

    @Override
    public void removeLessonStudentById(Long id) throws NoSuchLessonStudentException {
        if (!lessonStudentRepository.existsById(id)) {
            throw new NoSuchLessonStudentException(String.format("LessonStudent does not exist with id=%d", id));
        }

        lessonStudentRepository.deleteById(id);
    }

    private LessonStudent configureLessonStudent(LessonStudentEditableDto lessonStudentEditableDto, LessonStudent lessonStudent) throws NoSuchLessonException, NoSuchStudentException, NoSuchAttendanceStatusException, NoSuchAbsenceReasonException, NotAddedToStudyClassStudentException {
        Lesson lesson = lessonRepository.findById(lessonStudentEditableDto.getLessonId())
                .orElseThrow(() -> new NoSuchLessonException(String.format("Lesson does not exist with id=%d", lessonStudentEditableDto.getLessonId())));
        Student student = studentRepository.findById(lessonStudentEditableDto.getStudentId())
                .orElseThrow(() -> new NoSuchStudentException(String.format("Student does not exist with id=%d", lessonStudentEditableDto.getStudentId())));

        if (!lesson.getStudyClass().getStudents().contains(student)) {
            throw new NotAddedToStudyClassStudentException(String.format("Student with id=%d is not added to StudyClass with id=%d", lessonStudentEditableDto.getStudentId(), lesson.getStudyClass().getId()));
        }

        AttendanceStatus attendanceStatus = attendanceStatusRepository.findById(lessonStudentEditableDto.getAttendanceStatusId())
                .orElseThrow(() -> new NoSuchAttendanceStatusException(String.format("Attendance status does not exist with id=%d", lessonStudentEditableDto.getAttendanceStatusId())));
        AbsenceReason absenceReason = absenceReasonRepository.findById(lessonStudentEditableDto.getAbsenceReasonId())
                .orElseThrow(() -> new NoSuchAbsenceReasonException(String.format("Absence reason does not exist with id=%d", lessonStudentEditableDto.getAbsenceReasonId())));

        lessonStudent.setLesson(lesson);
        lessonStudent.setStudent(student);
        lessonStudent.setAttendanceStatus(attendanceStatus);
        lessonStudent.setAbsenceReason(absenceReason);

        return lessonStudent;
    }
}