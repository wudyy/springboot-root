package com.springboot.mybatis.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wudyy
 * @date 2019/5/26 14:57
 * @Description: TODO
 */
@Data
@ToString
public class StudentAgeInfo implements Serializable {
    private Long ageTotal;
    private Long ageA;
    private Long ageB;
    private Long ageC;
}
