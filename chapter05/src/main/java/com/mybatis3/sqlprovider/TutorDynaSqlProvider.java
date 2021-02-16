package com.mybatis3.sqlprovider;

import com.mybatis3.domain.Tutor;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class TutorDynaSqlProvider {

    public String findAllTutorsSql() {
        // 这里使用了『匿名内部类』和普通初始化块简化代码的书写
        return new SQL() {
            {
                // 普通初始化块：它在构造方法被调用之前被隐式调用（多个普通初始化块按照它们在类中被定义的顺序执行）
                // 补充一下：静态初始化块和静态变量的初始化顺序：1. 都初始化为默认值；2. 按照它们在类中被定义的顺序执行初始化
                SELECT("tutor_id AS tutorId, name, email");
                FROM("tutor");
            }
        }.toString();
    }

    public String findTutorByIdSql(final int tutorId) {
        /*
        return new SQL() {{
            SELECT("tutor_id AS tutorId, name, email");
            FROM("tutor");
            WHERE("tutor_id = #{tutorId}");
        }}.toString();
        */

        return new SQL() {{
            SELECT("tutor_id AS tutorId, name, email");
            FROM("tutor");
            WHERE("tutor_id=" + tutorId);
        }}.toString();
    }


    public String findTutorByNameAndEmailSql(Map<String, Object> map) {
        /*
        String name = (String) map.get("name");
        String email = (String) map.get("email");
        System.out.println(name + ":" + email);
        */

        return new SQL() {{
            SELECT("tutor_id AS tutorId, name, email");
            FROM("tutor");
            WHERE("name = #{name} AND email = #{email}");
        }}.toString();
    }

    public String insertTutor(final Tutor tutor) {

        return new SQL() {{
            INSERT_INTO("tutor");

            if (tutor.getName() != null) {
                VALUES("name", "#{name}");
            }

            if (tutor.getEmail() != null) {
                VALUES("email", "#{email}");
            }

        }}.toString();

    }

    public String updateTutor(final Tutor tutor) {

        return new SQL() {{
            UPDATE("tutor");

            if (tutor.getName() != null) {
                SET("name = #{name}");
            }

            if (tutor.getEmail() != null) {
                SET("email = #{email}");
            }
            WHERE("tutor_id = #{tutorId}");
        }}.toString();
    }

    public String deleteTutor(int tutorId) {

        return new SQL() {{
            DELETE_FROM("tutor");
            WHERE("tutor_id = #{tutorId}");
        }}.toString();

    }

    public String selectTutorById() {
        return new SQL() {{
            SELECT("t.tutor_id, t.name AS tutor_name, email");
            SELECT("a.addr_id, street, city, state, zip, country");
            SELECT("course_id, c.name AS course_name, description, start_date, end_date");
            FROM("tutor t");
            LEFT_OUTER_JOIN("address a ON t.addr_id = a.addr_id");
            LEFT_OUTER_JOIN("course c ON t.tutor_id = c.tutor_id");
            WHERE("t.tutor_id = #{id}");
        }}.toString();
    }
}
