package com.springboot.mybatis.service;

import com.springboot.common.ResultMap;
import com.springboot.mybatis.entity.Teacher;

/**
 * @author wudyy
 * @date 2019/5/26 10:06
 * @Description: TODO
 */
public interface TeacherService {

    ResultMap add(Teacher teacher);
}
