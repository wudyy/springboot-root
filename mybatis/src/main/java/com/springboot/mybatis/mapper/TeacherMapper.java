package com.springboot.mybatis.mapper;

import com.springboot.mybatis.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author wudyy
 * @date 2019/5/25 15:50
 * @Description: TODO
 */
public interface TeacherMapper {
    public static final String TABLE_NAME = "t_teacher";

    //增
    @Insert("insert into " + TABLE_NAME + "(name,sex,phone_number,create_time) " +
            "values(#{name},#{sex},#{phoneNumber},#{createTime})")
    int insertOne(Teacher teacher);

    //删
    @Delete("delete from " + TABLE_NAME + " where id = #{id}")
    int delete(long id);

    //改
    @Update("update  " + TABLE_NAME + "  set create_time = #{time} where id = #{id}")
    int updateTime(@Param("id") long id, @Param("time") Date time);

    //查
    @Select("select * from " + TABLE_NAME + " where id = #{id} ")
    Teacher getOneById(long id);

    @Select("select * from " + TABLE_NAME + " where id = #{id} ")
    @Results({
            @Result(column = "id", property = "students",
                    many = @Many(select = "com.springboot.mybatis.mapper.StudentMapper.getListByTeacherId"))
    })
    Teacher getOneWithStudentsById(long id);

    @Select("select * from " + TABLE_NAME + " order by create_time desc ")
    List<Teacher> getList();

    @Select("select * from " + TABLE_NAME + "  order by create_time desc ")
    @Results({
            @Result(column = "id", property = "students",
                    many = @Many(select = "com.springboot.mybatis.mapper.StudentMapper.getListByTeacherId"))
    })
    List<Teacher> getTeacherWithStudentsList();


}
