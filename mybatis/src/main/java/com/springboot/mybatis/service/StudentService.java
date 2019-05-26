package com.springboot.mybatis.service;

import com.springboot.common.ResultMap;

/**
 * @author wudyy
 * @date 2019/5/25 16:39
 * @Description: TODO
 */
public interface StudentService {
    ResultMap query(int pageNum,int pageSize);
}
