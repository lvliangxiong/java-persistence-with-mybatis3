package com.mybatis3.service;

import com.mybatis3.domain.Course;
import com.mybatis3.domain.Tutor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mybatis3.util.DataInitialization.initDatabase;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TutorServiceTest {
    private static TutorService tutorService;

    @BeforeAll
    public static void beforeAll() {
        initDatabase();
        tutorService = new TutorService();
    }

    @AfterAll
    public static void afterAll() {
        tutorService = null;
    }

    @Test
    public void testFindTutorById() {
        Tutor tutor = tutorService.findTutorById(1);
        assertNotNull(tutor);
        List<Course> courses = tutor.getCourses();
        assertNotNull(courses);
        for (Course course : courses) {
            assertNotNull(course);
            System.out.println(course);
        }
    }


}
