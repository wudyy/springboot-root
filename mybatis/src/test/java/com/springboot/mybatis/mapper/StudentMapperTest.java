package com.springboot.mybatis.mapper;

import com.springboot.mybatis.entity.Student;
import com.springboot.mybatis.entity.StudentAgeInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void insertOne() {
        log.error("---------->insertOne");
        Student student = new Student();
        student.setName(Student.getRandomName());
        student.setAge(Student.getRandomAge());
        student.setPhoneNumber(Student.getRandomphoneNumber());
        student.setSex(Student.getRandomSex());
        student.setTeacherId(Student.getRandomphoneTeacherId(-1L));
        student.setCreateTime(new Date());
        studentMapper.insertOne(student);
    }

    @Test
    public void insertList() {
        log.error("---------->insertList");
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setName(Student.getRandomName());
            student.setAge(Student.getRandomAge());
            student.setPhoneNumber(Student.getRandomphoneNumber());
            student.setSex(Student.getRandomSex());
            student.setTeacherId(Student.getRandomphoneTeacherId(-1L));
            student.setCreateTime(new Date());
            list.add(student);
        }
        studentMapper.insertList(list);
    }

    @Test
    public void delete() {
        log.error("---------->delete");
        studentMapper.delete(100L);
    }

    @Test
    public void updateTime() {
        log.error("---------->updateTime");
        studentMapper.updateTime(1L, new Date());
    }

    @Test
    public void getOneById() {
        log.error("---------->getOneById");
        Student student = studentMapper.getOneById(1L);
        log.error("student = {}", student);
    }

    @Test
    public void getOneWithTeacherById() {
        log.error("---------->getOneWithTeacherById");
        Student student = studentMapper.getOneWithTeacherById(1L);
        log.error("student = {}", student);
    }

    @Test
    public void getList() {
        log.error("---------->getList");
        List<Student> students = studentMapper.getList();
        log.error("students = {}", students);
    }

    @Test
    public void getListByTeacherId() {

        log.error("---------->getListByTeacherId");
        List<Student> students = studentMapper.getListByTeacherId(1L);
        log.error("students = {}", students);
    }

    @Test
    public void queryAll() {
        log.error("---------->queryAll");
        Student student = new Student();
        student.setName("张三");
        List<Student> students = studentMapper.queryAll(student);
        log.error("students = {}", students);
    }

    @Test
    public void queryAllByNativeSql() {
        log.error("---------->queryAllByNativeSql");
        Student student = new Student();
        student.setName("张三");
        List<Student> students = studentMapper.queryAllByNativeSql(student);
        log.error("students = {}", students);
    }

    @Test
    public void getStudentAgeInfo(){
        log.error("---------->getStudentAgeInfo");
        StudentAgeInfo studentAgeInfo = studentMapper.getStudentAgeInfo();
        log.error("studentAgeInfo = {}", studentAgeInfo);
    }
}