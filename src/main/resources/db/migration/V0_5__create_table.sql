create table absence_reasons
(
    id   bigint default nextval('absence_reasons_seq') not null,
    name varchar(255)                                  not null unique,
    type varchar(255)                                  not null unique check (type in ('HEALTH', 'FAMILY', 'WORK', 'NONE')),
    primary key (id)
);

create table attendance_statuses
(
    id   bigint default nextval('attendance_statuses_seq') not null,
    name varchar(255)                                      not null unique,
    type varchar(255)                                      not null unique check (type in ('PRESENT', 'ABSENT', 'LATE')),
    primary key (id)
);

create table lessons
(
    id             bigint default nextval('lessons_seq') not null,
    date           date                                  not null,
    id_study_class bigint,
    primary key (id)
);

create table lessons_students
(
    id                   bigint default nextval('lessons_students_seq') not null,
    id_absence_reason    bigint,
    id_attendance_status bigint,
    id_lesson            bigint,
    id_student           bigint,
    primary key (id)
);

create table students
(
    id         bigint default nextval('students_seq') not null,
    first_name varchar(255)                           not null,
    last_name  varchar(255)                           not null,
    primary key (id)
);

create table study_classes
(
    id   bigint default nextval('study_classes_seq') not null,
    name varchar(255)                                not null,
    primary key (id)
);

create table study_classes_students
(
    id_student     bigint not null,
    id_study_class bigint not null,
    primary key (id_student, id_study_class)
);
