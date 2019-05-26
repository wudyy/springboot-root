package com.springboot.mybatis.service;

import com.springboot.common.ResultMap;
import com.springboot.mybatis.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void add() {
        Teacher teacher = new Teacher();
        teacher.setName(Teacher.getRandomName());
        teacher.setPhoneNumber(Teacher.getRandomphoneNumber());
        teacher.setSex(Teacher.getRandomSex());
        teacher.setCreateTime(new Date());
        ResultMap resultMap = teacherService.add(teacher);
        log.error("resultMap = {}", resultMap);
    }
}