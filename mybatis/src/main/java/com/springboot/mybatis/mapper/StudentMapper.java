package com.springboot.mybatis.mapper;

import com.springboot.mybatis.entity.Student;
import com.springboot.mybatis.entity.StudentAgeInfo;
import com.springboot.mybatis.mapper.provider.StudentMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author wudyy
 * @date 2019/5/25 15:50
 * @Description: TODO
 */
@Mapper
public interface StudentMapper {

    public static final String TABLE_NAME = "t_student";

    //增
    @Insert("insert into " + TABLE_NAME + "(name,sex,age,phone_number,teacher_id,create_time) " +
            "values(#{name},#{sex},#{age},#{phoneNumber},#{teacherId},#{createTime})")
    int insertOne(Student student);

    @Insert({"<script> insert into " + TABLE_NAME + "(name,sex,age,phone_number,teacher_id,create_time) " +
            "values " +
            "<foreach collection='list' open='' item='item' separator=','> " +
            "(#{item.name},#{item.sex},#{item.age},#{item.phoneNumber},#{item.teacherId},#{item.createTime}) " +
            "</foreach> </script>"})
        //批量增加数据
    int insertList(List<Student> list);

    //删
    @Delete("delete from " + TABLE_NAME + " where id = #{id}")
    int delete(Long id);

    //改
    @Update("update  " + TABLE_NAME + "  set create_time = #{time} where id = #{id}")
    int updateTime(@Param("id") long id, @Param("time") Date time);

    //查
    @Select("select * from " + TABLE_NAME + " where id = #{id} ")
    Student getOneById(Long id);

    @Select("select * from " + TABLE_NAME + " where id = #{id} ")
    @Results({
            @Result(column = "teacher_id", property = "teacher",
                    one = @One(select = "com.springboot.mybatis.mapper.TeacherMapper.getOneById"))
    })
    Student getOneWithTeacherById(Long id);

    @Select("select * from " + TABLE_NAME + " order by create_time desc ")
    List<Student> getList();

    @Select("select * from " + TABLE_NAME + " where teacher_id = #{teacherId} order by create_time desc ")
    List<Student> getListByTeacherId(Long teacherId);

    //动态sql 查询
    @SelectProvider(type = StudentMapperProvider.class, method = "queryAll")
    List<Student> queryAll(Student student);

    //动态sql 查询
    @SelectProvider(type = StudentMapperProvider.class, method = "queryAllByNativeSql")
    List<Student> queryAllByNativeSql(Student student);


    @Select("select COUNT(*) AS age_total,SUM(IF(age<14 = 1, 1, 0)) AS age_a,SUM(IF(age>14 and age < 17, 1, 0)) AS age_b,SUM(IF(age >17, 1, 0)) AS age_c from t_student")
    @Results({
            @Result(column = "age_total", property = "ageTotal"),
            @Result(column = "age_a", property = "ageA"),
            @Result(column = "age_b", property = "ageB"),
            @Result(column = "age_c", property = "ageC"),
    })
    StudentAgeInfo getStudentAgeInfo();
}
