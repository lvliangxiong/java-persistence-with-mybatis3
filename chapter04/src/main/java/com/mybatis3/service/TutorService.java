package com.mybatis3.service;

import com.mybatis3.domain.Tutor;
import com.mybatis3.mapper.TutorMapper;
import com.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class TutorService {
    public List<Tutor> findAllTutors() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            return mapper.findAllTutors();
        } finally {
            sqlSession.close();
        }
    }

    public Tutor findTutorById(int tutorId) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            return mapper.findTutorById(tutorId);
        } finally {
            sqlSession.close();
        }
    }

    public Tutor findTutorByNameAndEmail(String name, String email) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            return mapper.findTutorByNameAndEmail(name, email);
        } finally {
            sqlSession.close();
        }
    }

    public Tutor createTutor(Tutor tutor) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            mapper.insertTutor(tutor);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return tutor;
    }

    public Tutor updateTutor(Tutor tutor) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            mapper.updateTutor(tutor);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return tutor;
    }

    public boolean deleteTutor(int tutorId) {
        boolean deleted = false;
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            int nor = mapper.deleteTutor(tutorId);
            deleted = (nor == 1);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return deleted;
    }

    public Tutor selectTutorById(int tutorId) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            return mapper.selectTutorById(tutorId);
        } finally {
            sqlSession.close();
        }
    }

    public Tutor selectTutorWithCoursesById(int tutorId) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            return mapper.selectTutorWithCoursesById(tutorId);
        } finally {
            sqlSession.close();
        }
    }
}
