package com.springboot.mybatis.mapper;

import com.springboot.mybatis.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void insertOne() {
        log.error("-------> insertOne");
        Teacher teacher = new Teacher();
        teacher.setName(Teacher.getRandomName());
        teacher.setPhoneNumber(Teacher.getRandomphoneNumber());
        teacher.setSex(Teacher.getRandomSex());
        teacher.setCreateTime(new Date());
        teacherMapper.insertOne(teacher);
    }

    @Test
    public void delete() {
        log.error("-------> delete");
        teacherMapper.delete(3L);
    }

    @Test
    public void updateTime() {
        log.error("-------> updateTime");
        teacherMapper.updateTime(1L,new Date());
    }

    @Test
    public void getOneById() {
        log.error("-------> getOneById");
        Teacher teacher = teacherMapper.getOneById(1L);
        log.error("teacher ={}",teacher);
    }

    @Test
    public void getList() {
        log.error("-------> getList");
        List<Teacher> teachers = teacherMapper.getList();
        log.error("teachers ={}",teachers);
    }

    @Test
    public void getTeacherWithStudentsList() {
        log.error("-------> getTeacherWithStudentsList");
        List<Teacher> teachers = teacherMapper.getTeacherWithStudentsList();
        log.error("teachers ={}",teachers);
    }
}