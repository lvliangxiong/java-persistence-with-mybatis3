package com.mybatis3.mapper;

import com.mybatis3.domain.Tutor;


/**
 * @author Siva
 */
public interface TutorMapper {

    Tutor selectTutorWithCourses(int tutorId);

    Tutor selectTutorById(int tutorId);

}
