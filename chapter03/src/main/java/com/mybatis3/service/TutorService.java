package com.mybatis3.service;

import com.mybatis3.domain.Tutor;
import com.mybatis3.mapper.TutorMapper;
import com.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TutorService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Tutor findTutorById(int tutorId) {
        logger.debug("findTutorById :" + tutorId);
        SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        try {
            TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
            return mapper.selectTutorById(tutorId);
        } finally {
            sqlSession.close();
        }
    }

}
