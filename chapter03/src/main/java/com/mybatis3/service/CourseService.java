package com.mybatis3.service;

import com.mybatis3.domain.Course;
import com.mybatis3.mapper.CourseMapper;
import com.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class CourseService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Course> searchCourses(Map<String, Object> map) {
        logger.debug("searchCourses By :" + map);
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.searchCourses(map);
        } finally {
            sqlSession.close();
        }
    }

    public List<Course> searchCoursesByTutors(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.searchCoursesByTutors(map);
        } finally {
            sqlSession.close();
        }
    }
}
