package com.mybatis3.service;

import com.mybatis3.domain.Tutor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mybatis3.util.DataInitialization.initDatabase;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TutorServiceTest {

    private static TutorService tutorService;

    @BeforeAll
    public static void beforeAll() {
        tutorService = new TutorService();
        initDatabase();
    }

    @AfterAll
    public static void teardown() {
        tutorService = null;
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
        System.out.println(tutor);
    }

    @Test
    public void testFindTutorByNameAndEmail() {
        Tutor tutor = tutorService.findTutorByNameAndEmail("Paul", "paul@gmail.com");
        assertNotNull(tutor);
        System.out.println(tutor);
    }

    @Test
    public void testCreateTutor() {
        System.out.println(createTutor());
    }

    private int createTutor() {
        Tutor tutor = new Tutor();
        long ctm = System.currentTimeMillis();
        tutor.setName("siva" + ctm);
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
        int id = createTutor();
        boolean deleted = tutorService.deleteTutor(id);
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
