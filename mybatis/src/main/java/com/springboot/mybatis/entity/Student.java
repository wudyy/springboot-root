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
 * @Description: TODO
 */
@Data
@ToString
public class Student implements Serializable {
    public final static String SEX_BOY = "1";
    public final static String SEX_GIRL = "2";
    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String phoneNumber;
    private Long teacherId;
    private Date createTime;
    private Teacher teacher;


    private static Random random = new Random();
    private static List<String> studentNamelist = new ArrayList();
    private static List<Long> list = new ArrayList();

    static {
        studentNamelist.add("赵");
        studentNamelist.add("钱");
        studentNamelist.add("欧阳");
        studentNamelist.add("爱新觉罗");
        list.add(1L);
        list.add(2L);
    }

    public static String getRandomName() {
        return studentNamelist.get(getRandomIndex(studentNamelist.size())) + getRandomIndex(100);
    }

    public static String getRandomSex() {
        return getRandomIndex(5) % 2 == 0 ? SEX_BOY : SEX_GIRL;
    }

    public static int getRandomAge() {
        return 10 + getRandomIndex(10);
    }

    public static String getRandomphoneNumber() {
        return "1302349" + (1000 + getRandomIndex(100));
    }

    public static long getRandomphoneTeacherId(long teacherId) {
        if (teacherId>0){
            return teacherId;
        }
        return list.get(getRandomIndex(list.size()));
    }

    public static int getRandomIndex(int length) {
        return random.nextInt(length);
    }
}
