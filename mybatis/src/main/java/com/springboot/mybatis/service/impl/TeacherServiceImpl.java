package com.springboot.mybatis.service.impl;

import com.springboot.common.ResultMap;
import com.springboot.mybatis.entity.Student;
import com.springboot.mybatis.entity.Teacher;
import com.springboot.mybatis.mapper.StudentMapper;
import com.springboot.mybatis.mapper.TeacherMapper;
import com.springboot.mybatis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wudyy
 * @date 2019/5/26 10:07
 * @Description: TODO
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultMap add(Teacher teacher) {
        ResultMap resultMap = new ResultMap();
        teacherMapper.insertOne(teacher);
        for (Student student : teacher.getStudents()) {
            student.setTeacherId(teacher.getId());
        }
        studentMapper.insertList(teacher.getStudents());
        return resultMap;
    }
}
