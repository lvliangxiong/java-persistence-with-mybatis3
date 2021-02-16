package com.mybatis3.service;

import com.mybatis3.domain.Course;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.mybatis3.util.DataInitialization.initDatabase;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseServiceTest {
    private static CourseService courseService;

    @BeforeAll
    public static void beforeAll() {
        initDatabase();
        courseService = new CourseService();
    }

    @AfterAll
    public static void afterAll() {
        courseService = null;
    }

    @Test
    public void searchCourses() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tutorId", 1);
        //map.put("courseName", "%java%");
        map.put("startDate", new Date());
        List<Course> courses = courseService.searchCourses(map);
        assertNotNull(courses);
        for (Course course : courses) {
            assertNotNull(course);
            //System.out.println(course);
        }
    }

    @Test
    public void searchCoursesByTutors() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> tutorIds = new ArrayList<Integer>();
        tutorIds.add(1);
        tutorIds.add(2);
        map.put("tutorIds", tutorIds);
        map.put("courseName", "%java%");
        map.put("startDate", new Date());
        List<Course> courses = courseService.searchCoursesByTutors(map);
        assertNotNull(courses);
        for (Course course : courses) {
            assertNotNull(course);
            //System.out.println(course);
        }
    }
}
