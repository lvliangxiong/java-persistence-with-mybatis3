package com.mybatis3.services;

import com.mybatis3.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mybatis3.util.JdbcUtil.closeAll;
import static com.mybatis3.util.JdbcUtil.getDatabaseConnection;


public class JdbcStudentService {

    public Student findStudentById(int studId) {
        Student student = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getDatabaseConnection();
            String sql = "select * from students where STUD_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStudId(rs.getInt("STUD_ID"));
                student.setName(rs.getString("NAME"));
                student.setEmail(rs.getString("EMAIL"));
                student.setDob(rs.getDate("DOB"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return student;
    }

    public void createStudent(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDatabaseConnection();
            String sql = "INSERT INTO students (STUD_ID, NAME, EMAIL, DOB) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student.getStudId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setDate(4, new java.sql.Date(student.getDob().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(conn, pstmt);
        }
    }

    public void updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDatabaseConnection();
            String sql = "UPDATE students SET NAME = ?, EMAIL = ?, DOB = ? WHERE STUD_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setDate(3, new java.sql.Date(student.getDob().getTime()));
            pstmt.setInt(4, student.getStudId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        } finally {
            closeAll(conn, pstmt);
        }
    }


}
