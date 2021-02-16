package com.mybatis3.mapper;

import com.mybatis3.domain.Course;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {

    @Select("SELECT * FROM course WHERE tutor_id = #{tutorId}")
    @ResultMap("com.mybatis3.mapper.CourseMapper.CourseResultMap")
    List<Course> selectCoursesByTutorId(int tutorId);
}
