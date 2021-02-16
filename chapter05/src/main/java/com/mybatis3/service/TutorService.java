package com.mybatis3.service;

import com.mybatis3.domain.Tutor;
import com.mybatis3.mapper.TutorMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TutorService {
    /**
     * SqlSessionTemplate 会自动提交。
     */
    @Autowired
    private SqlSession sqlSession;

    private TutorMapper getTutorMapper() {
        TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
        System.out.println(mapper);
        return mapper;
    }

    public List<Tutor> findAllTutors() {
        return getTutorMapper().findAllTutors();
    }

    public Tutor findTutorById(int tutorId) {
        return getTutorMapper().findTutorById(tutorId);
    }

    public Tutor findTutorByNameAndEmail(String name, String email) {
        return getTutorMapper().findTutorByNameAndEmail(name, email);
    }

    public Tutor createTutor(Tutor tutor) {
        getTutorMapper().insertTutor(tutor);
        return tutor;
    }

    public Tutor updateTutor(Tutor tutor) {
        getTutorMapper().updateTutor(tutor);
        return tutor;
    }

    public boolean deleteTutor(int tutorId) {
        boolean deleted;
        int nor = getTutorMapper().deleteTutor(tutorId);
        deleted = (nor == 1);
        return deleted;
    }

    public Tutor selectTutorById(int tutorId) {
        return getTutorMapper().selectTutorById(tutorId);
    }

    public Tutor selectTutorWithCoursesById(int tutorId) {
        return getTutorMapper().selectTutorWithCoursesById(tutorId);
    }
}
