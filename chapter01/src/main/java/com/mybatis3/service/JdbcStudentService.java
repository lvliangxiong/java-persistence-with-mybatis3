package com.mybatis3.service;

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
            String sql = "SELECT * FROM student where stud_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStudId(rs.getInt("stud_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDob(rs.getDate("dob"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return student;
    }

    /**
     * JDBC 操作默认会自动提交，如果需要修改，可以使用 {@link Connection#setAutoCommit(boolean)} 方法进行修改。
     *
     * @param student 待插入的 Student 对象
     */
    public void createStudent(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDatabaseConnection();
            String sql = "INSERT INTO student (stud_id, name, email, dob) VALUES (?, ?, ?, ?)";
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
            String sql = "UPDATE student SET name = ?, email = ?, dob = ? WHERE stud_id = ?";
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
