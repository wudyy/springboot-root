## 1.前言
Springboot 的使用已经越来越广泛了，目前使用的主流持久层框架有JPA+Hibernate,Mybatis等，JPA不用说api封装的很完善，一切面向对象，基本上不用写啥sql就可以完成大部分增删改查功能，据统计目前国内使用mybaits的表较多，JPA国外使用的比较多。话说写的一手好sql才能成为一个合格的后台，所以。。。 我还是比较喜欢mybatis,毕竟自己写出来的sql才能有灵魂，Springboot+mybatis 有两种写法，mapper.xml文件或者注解，这里主要讲mybatis注解的用法，持久化层无外乎增删改查，从这篇文章你可以学到90%的注解使用方法，举一反三可以完成各种需求的业务。

## 1.准备

 1. 友情链接：[mybatis-3 github](https://github.com/mybatis/mybatis-3)
 2. 项目地址：[github](git@github.com:wudyy/springboot-root.git)
 3. 项目环境：springboot+mybatis+mysql
 4. 配置文件如下


###### application.properties：

```
server.port=8081
server.servlet.context-path=/mybatis
spring.main.banner-mode=off
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/root?characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=123456
##时间格式化
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8
##mybatis
##开启驼峰发命名
mybatis.configuration.mapUnderscoreToCamelCase=true
## 分页插件
pagehelper.helperDialect=mysql
pagehelper.reasonable=false
##去掉这个配置 否则出现分页异常
#pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql
pagehelper.returnPageInfo=check
##redis 地址

```
 1. 实体类一对多关系
 
```
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
	 }
 
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
	}
```
 6. 创建Mapper接口类
方式一：在Mapper类增加注解 @Mapper 需要每个文件都加
方式二：在MybatisApplication启动类增加注解：@MapperScan("com.springboot.mybatis.mapper") 
这样spring 就可以扫描Mapper类
 
```
@Mapper
public interface StudentMapper {
	public static final String TABLE_NAME = "t_student";
}
```

## 2.Mapper下的增删改查
### 2.1 增
 1. 增加一条数据

```
   @Insert("insert into " + TABLE_NAME + "(name,sex,age,phone_number,teacher_id,create_time) " +
            "values(#{name},#{sex},#{age},#{phoneNumber},#{teacherId},#{createTime})")
    int insertOne(Student student);
```
 2. 批量增加数据

```
    @Insert({"<script> insert into "+TABLE_NAME+"(name,sex,age,phone_number,teacher_id,create_time) " +
            "values " +
            "<foreach collection='list' open='' item='item' separator=','> " +
            "(#{item.name},#{item.sex},#{item.age},#{item.phoneNumber},#{item.teacherId},#{item.createTime}) " +
            "</foreach> </script>"})
        //批量增加数据
    int insertList(List<Student> list);
```
### 2.2 删
	1.删除数据
	

```
 @Delete("delete from " + TABLE_NAME + " where id = #{id}")
    int delete(Long id);

```
### 2.3 改

```
   //改
    @Update("update  " + TABLE_NAME + "  set create_time = #{time} where id = #{id}")
    int updateTime(@Param("id") long id, @Param("time") Date time);
```
### 2.4 查

 1. 查找一条数据

```
 @Select("select * from " + TABLE_NAME + " where id = #{id} ")
    Student getOneById(Long id);
```
 2. 查找一条数据（一对一）
 

```
@Select("select * from " + TABLE_NAME + " where id = #{id} ")
    @Results({
            @Result(column = "teacher_id", property = "teacher",
                    one = @One(select = "com.springboot.mybatis.mapper.TeacherMapper.getOneById"))
    })
    Student getOneWithTeacherById(Long id);
```
 3. 查找一条数据（一对多）
 

```
  @Select("select * from " + TABLE_NAME + " where id = #{id} ")
    @Results({
            @Result(column = "id", property = "students",
                    many = @Many(select = "com.springboot.mybatis.mapper.StudentMapper.getListByTeacherId"))
    })
    Teacher getOneWithStudentsById(long id);
```
  4. 查找列表数据
 

```
  @Select("select * from " + TABLE_NAME + " order by create_time desc ")
    List<Teacher> getList();

  @Select("select * from " + TABLE_NAME + "  order by create_time desc ")
    @Results({
            @Result(column = "id", property = "students",
                    many = @Many(select = "com.springboot.mybatis.mapper.StudentMapper.getListByTeacherId"))
    })
    List<Teacher> getTeacherWithStudentsList();
```
  5. 灵活配置返回的参数
  
  

```
@Select("select COUNT(*) AS age_total,SUM(IF(age<14 = 1, 1, 0)) AS age_a,SUM(IF(age>14 and age < 17, 1, 0)) AS age_b,SUM(IF(age >17, 1, 0)) AS age_c from t_student")
    @Results({
            @Result(column = "age_total", property = "ageTotal"),
            @Result(column = "age_a", property = "ageA"),
            @Result(column = "age_b", property = "ageB"),
            @Result(column = "age_c", property = "ageC"),
    })
    StudentAgeInfo getStudentAgeInfo();
```

   6. 动态sql (如果mapper sql 无法满足你的需求动态sql才是考验你技术的时候)
   创建 StudentMapperProvider 在这里你可以写出你想要的各种sql 
	

```
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
     * 手动拼装sql(个人比较喜欢这种，感觉一切都在掌握中)
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
```
使用：

```
  //动态sql 查询
    @SelectProvider(type = StudentMapperProvider.class, method = "queryAll")
    List<Student> queryAll(Student student);

    //动态sql 查询
    @SelectProvider(type = StudentMapperProvider.class, method = "queryAllByNativeSql")
    List<Student> queryAllByNativeSql(Student student);
```

当然增删改也可以使用动态sql，  mybatis就是那么灵活，你想这么玩就可以怎么玩，前提是你能写出一手good sql.
