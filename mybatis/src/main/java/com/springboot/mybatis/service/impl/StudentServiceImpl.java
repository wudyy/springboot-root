package com.springboot.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.common.ResultMap;
import com.springboot.mybatis.entity.Student;
import com.springboot.mybatis.mapper.StudentMapper;
import com.springboot.mybatis.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wudyy
 * @date 2019/5/25 16:50
 * @Description: TODO
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResultMap query(int pageNum, int pageSize) {
        ResultMap resultMap = new ResultMap();
        //分页查询列表
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.getList();
        PageInfo<Student> result = new PageInfo<>(students);
        resultMap.addData(result);
        return resultMap;
    }


}
