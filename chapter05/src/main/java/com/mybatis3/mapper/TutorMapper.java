package com.mybatis3.mapper;

import com.mybatis3.domain.Tutor;
import com.mybatis3.sqlprovider.TutorDynaSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TutorMapper {

    @Select("SELECT tutor_id, name AS tutor_name, email, addr_id FROM tutor WHERE tutor_id = #{tutorId}")
    @Results({
            @Result(id = true, column = "tutor_id", property = "tutorId"),
            @Result(column = "tutor_name", property = "name"),
            @Result(column = "email", property = "email"),
            @Result(column = "addr_id", property = "address",
                    // one-to-one mapping nested select
                    one = @One(select = "com.mybatis3.mapper.AddressMapper.selectAddressById")),
            @Result(column = "tutor_id", property = "courses",
                    // one-to-many mapping nested select
                    many = @Many(select = "com.mybatis3.mapper.CourseMapper.selectCoursesByTutorId"))
    })
    Tutor selectTutorWithCoursesById(int tutorId);

    // @SelectProvider 用于组建 SELECT 类型的动态 SQL 语句，该 SQL 会由 type.method 指定的方法的返回值确定
    @SelectProvider(type = TutorDynaSqlProvider.class, method = "findAllTutorsSql")
    List<Tutor> findAllTutors();

    /*
     * SQL provider method 的方法参数类型：
     * 1. 无参数
     * 2. 和对应的 Mapper 接口方法参数类型和数量（只能有一个）相同
     * 3. java.util.Map (和 #{} 取参数的规则类似)
     * */
    @SelectProvider(type = TutorDynaSqlProvider.class, method = "findTutorByIdSql")
    Tutor findTutorById(int tutorId);

    @SelectProvider(type = TutorDynaSqlProvider.class, method = "findTutorByNameAndEmailSql")
    Tutor findTutorByNameAndEmail(@Param("name") String name, @Param("email") String email);

    @InsertProvider(type = TutorDynaSqlProvider.class, method = "insertTutor")
    @Options(useGeneratedKeys = true, keyProperty = "tutorId")
    int insertTutor(Tutor tutor);

    @UpdateProvider(type = TutorDynaSqlProvider.class, method = "updateTutor")
    int updateTutor(Tutor tutor);

    @DeleteProvider(type = TutorDynaSqlProvider.class, method = "deleteTutor")
    int deleteTutor(int tutorId);

    @SelectProvider(type = TutorDynaSqlProvider.class, method = "selectTutorById")
    @ResultMap("com.mybatis3.mapper.TutorMapper.TutorResultMap")
    Tutor selectTutorById(int tutorId);

}
