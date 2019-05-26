package com.springboot.mybatis.mapper.provider;

import com.springboot.mybatis.entity.Student;
import com.springboot.mybatis.mapper.StudentMapper;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;


/**
 * @author hushentao
 * @date 2019/4/1 16:31
 * @Description: TODO
 */
public class StudentMapperProvider {

    /**
     * API拼装sql
     *
     * @param student
     * @return
     */
    public String queryAll(Student student) {
        return new SQL() {
            {
                SELECT("*");
                FROM(StudentMapper.TABLE_NAME);
                if (student.getId() == null) {
                    WHERE("id = #{id}");
                } else {
                    if (StringUtils.isEmpty(student.getName())) {
                        AND();
                        WHERE("name = #{name}");
                    } else if (StringUtils.isEmpty(student.getPhoneNumber())) {
                        AND();
                        WHERE("phone_number = #{phoneNumber}");
                    }
                    ORDER_BY("create_time desc");
                }
            }
        }.toString();
    }

    /**
     * 手动拼装sql
     *
     * @param student
     * @return
     */
    public String queryAllByNativeSql(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");
        sb.append(StudentMapper.TABLE_NAME);
        if (student.getId() == null) {
            sb.append(" where id = #{id} ");
            return sb.toString();
        } else {
            sb.append(" where 1 = 1 ");
        }
        if (StringUtils.isEmpty(student.getName())) {
            sb.append("and name = #{name}");
        } else if (StringUtils.isEmpty(student.getPhoneNumber())) {
            sb.append("and phone_number = #{phoneNumber}");
        }
        sb.append("order by create_time desc");
        return sb.toString();
    }
}
