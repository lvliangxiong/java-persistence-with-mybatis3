package com.mybatis3.mapper;

import com.mybatis3.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


/**
 * @author Siva
 */
public interface StudentMapper {

    // @Select 相当于 Mapper XML 文件中的 <select> 标签
    @Select({"SELECT * FROM student"})
    // @Results 相当于一个 <resultMap> 标签
    @Results({
            // @Result 相当于 <resultMap> 下的一个 <result> 子标签
            @Result(id = true, column = "stud_id", property = "studId"),
            @Result(column = "name", property = "name"),
            @Result(column = "email", property = "email"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "addr_id", property = "address.addrId")
    })
    List<Student> findAllStudents();

    @Select("SELECT stud_id AS studId, name, email, addr_id AS 'address.addrId', phone FROM student")
    List<Map<String, Object>> findAllStudentsMap();

    @Select("SELECT stud_id AS studId, name, email, addr_id AS 'address.addrId', phone FROM student WHERE stud_id = #{id}")
    Student findStudentById(Integer id);

    @Select("SELECT stud_id AS studId, name, email, addr_id AS 'address.addrId', phone FROM student WHERE stud_id = #{id}")
    Map<String, Object> findStudentMapById(Integer id);

    @Select("SELECT stud_id, name, email, a.addr_id, street, city, state, zip, country " +
            "FROM student s LEFT OUTER JOIN address a " +
            "ON s.addr_id = a.addr_id " +
            "WHERE stud_id = #{studId}")
    // @ResultMap 注解用来引用定义在 Mapper XML 文件中的 ResultMap
    @ResultMap("com.mybatis3.mapper.StudentMapper.StudentWithAddressResultMap")
    Student selectStudentWithAddress(int studId);

    @Insert("INSERT INTO student (name, email, addr_id, phone) " +
            "VALUES (#{name}, #{email}, #{address.addrId}, #{phone})")
    // 这里的 @Options 注解的作用，相当于 Mapper XML 文件中 <insert> 标签的 useGeneratedKeys 和 keyProperty 属性
    @Options(useGeneratedKeys = true, keyProperty = "studId")
    void insertStudent(Student student);

    @Insert("INSERT INTO student (name, email, addr_id, phone) " +
            "VALUES (#{name}, #{email}, #{address.addrId}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "studId")
    void insertStudentWithMap(Map<String, Object> map);

    @Update("UPDATE student " +
            "SET name = #{name}, email = #{email}, phone = #{phone} " +
            "WHERE stud_id = #{studId}")
    void updateStudent(Student student);

    @Delete("DELETE FROM student WHERE stud_id = #{studId}")
    int deleteStudent(int studId);

}
