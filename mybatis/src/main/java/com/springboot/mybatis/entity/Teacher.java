package com.springboot.mybatis.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author wudyy
 * @date 2019/5/25 15:44
 * @Description: 老师实体类
 */
@Data
@ToString
public class Teacher implements Serializable {
    public final static String SEX_BOY = "1";
    public final static String SEX_GIRL = "2";
    private Long id;
    private String name;
    private String sex;
    private String phoneNumber;
    private Date createTime;
    private List<Student> students;

    private static Random random = new Random();
    private static List<String> teacherNameList = new ArrayList<>();

    static {
        teacherNameList.add("李");
        teacherNameList.add("胡");
        teacherNameList.add("张");
        teacherNameList.add("程");

    }

    public static String getRandomName() {
        return teacherNameList.get(getRandomIndex(teacherNameList.size())) + getRandomIndex(100);
    }

    public static String getRandomSex() {
        return getRandomIndex(5) % 2 == 0 ? SEX_BOY : SEX_GIRL;
    }


    public static String getRandomphoneNumber() {
        return "1392349" + (1000 + getRandomIndex(100));
    }


    public static int getRandomIndex(int length) {
        return random.nextInt(length);
    }
}
