package com.mybatis3.service;

import com.mybatis3.domain.Tutor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.mybatis3.util.DataInitialization.initDatabase;
import static com.mybatis3.util.SpringUtil.showInfo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring/spring-mybatis-SqlSessionTemplate.xml")
public class TutorServiceTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TutorService tutorService;

    @BeforeAll
    public static void beforeAll() {
        initDatabase();
    }

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread());
        System.out.println(this);
        System.out.println(tutorService);
    }

    @Test
    void showApplicationContext() {
        showInfo(applicationContext);
    }

    @Test
    public void testFindAllTutors() {
        List<Tutor> tutors = tutorService.findAllTutors();
        assertNotNull(tutors);
        for (Tutor tutor : tutors) {
            System.out.println(tutor);
        }
    }

    @Test
    public void testFindTutorById() {
        Tutor tutor = tutorService.findTutorById(1);
        assertNotNull(tutor);
        //System.out.println(tutor);
    }

    @Test
    public void testFindTutorByNameAndEmail() {
        Tutor tutor = tutorService.findTutorByNameAndEmail("Paul", "paul@gmail.com");
        assertNotNull(tutor);
        //System.out.println(tutor);
    }

    @Test
    public void testCreateTutor() {
        System.out.println(createTutor());
    }

    private int createTutor() {
        Tutor tutor = new Tutor();
        long ts = System.currentTimeMillis();
        tutor.setName("siva_" + ts);
        tutor.setEmail(tutor.getName() + "@gmail.com");
        tutor = tutorService.createTutor(tutor);
        assertNotNull(tutor);
        return tutor.getTutorId();
    }

    @Test
    public void testUpdateTutor() {
        Tutor tutor = new Tutor();
        tutor.setTutorId(1);
        tutor.setName("sivaprasad");
        tutor.setEmail("sivaprasad@gmail.com");
        tutor = tutorService.updateTutor(tutor);
        Tutor updTutor = tutorService.findTutorById(1);
        assertNotNull(updTutor);
        System.out.println(updTutor);
    }

    @Test
    public void testDeleteTutor() {
        boolean deleted = tutorService.deleteTutor(createTutor());
        assertTrue(deleted);
    }

    @Test
    public void testSelectTutorById() {
        Tutor tutor = tutorService.selectTutorById(1);
        assertNotNull(tutor);
        System.out.println(tutor);
    }

    @Test
    public void testSelectTutorWithCoursesById() {
        Tutor tutor = tutorService.selectTutorWithCoursesById(1);
        assertNotNull(tutor);
        System.out.println(tutor);
    }

}
