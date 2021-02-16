package com.mybatis3.service;

import com.mybatis3.domain.Student;
import com.mybatis3.mapper.StudentMapper;
import com.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class StudentService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Student> findAllStudents() {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper StudentMapper = sqlSession.getMapper(StudentMapper.class);
            return StudentMapper.findAllStudents();
        } finally {
            sqlSession.close();
        }
    }

    public Student findStudentById(Integer id) {
        logger.debug("findStudentById :" + id);
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findStudentById(id);
        } finally {
            sqlSession.close();
        }
    }

    public Student findStudentWithAddressById(int id) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.selectStudentWithAddress(id);
        } finally {
            sqlSession.close();
        }
    }

    public Student createStudent(Student student) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.insertStudent(student);
            sqlSession.commit();
            return student;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

    public void createStudentWithMap(Map<String, Object> studentDataMap) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.insertStudentWithMap(studentDataMap);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

    public Student updateStudent(Student student) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            mapper.updateStudent(student);
            sqlSession.commit();
            return student;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

    public boolean deleteStudent(int id) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            int count = mapper.deleteStudent(id);
            sqlSession.commit();
            return count > 0;
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

    public Map<String, Object> findStudentMapById(int id) {

        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.findStudentMapById(id);
        } finally {
            sqlSession.close();
        }

    }

    public List<Map<String, Object>> findAllStudentsMap() {

        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            StudentMapper StudentMapper = sqlSession.getMapper(StudentMapper.class);
            return StudentMapper.findAllStudentsMap();
        } finally {
            sqlSession.close();
        }

    }
}
