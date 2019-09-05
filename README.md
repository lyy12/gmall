# gmall
电商项目
通用Mapper的使用
1、搭建module
 
依赖选Web和Mysql, Jdbc,MyBatis
 

 
注意Module位置要在Project路径下面
2、配置通用Mapper
 在pom.xml文件中，加入
<!-- 通用mapper -->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
<version>1.2.3</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
</dependency>



 GmallUserManageApplication.java 中增加注解
@SpringBootApplication
@MapperScan("com.liuyuan.gmall.mapper")
public class GmallOrderServiceApplication {

   public static void main(String[] args) {
      SpringApplication.run(GmallOrderServiceApplication.class, args);
   }
}
注意通用mapper是tk.mybatis.mapper
 
 
3、配置数据源
在application.properties中
spring.datasource.url=jdbc:mysql://localhost:3306/shop?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root

表结构
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `login_name` varchar(200) DEFAULT NULL COMMENT '用户名称',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '用户昵称',
  `passwd` varchar(200) DEFAULT NULL COMMENT '用户密码',
  `name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `phone_num` varchar(200) DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `head_img` varchar(200) DEFAULT NULL COMMENT '头像',
  `user_level` varchar(200) DEFAULT NULL COMMENT '用户级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户表'


4、代码开发
包	类	说明
controller	UserManageController	web
service	UserManageService	接口
service.impl	UserManageServiceImpl	实现类
Bean	UserInfo	实体bean
mapper	UserInfoMapper	mapper接口
		

4.1  bean

@Data
@NoArgsConstructor
public class UserInfo implements Serializable{
    @Id
    @Column
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String loginName;
    @Column
    private String nickName;
    @Column
    private String passwd;
    @Column
    private String name;
    @Column
    private String phoneNum;
    @Column
    private String email;
    @Column
    private String headImg;
    @Column
    private String userLevel;
}

注意：@Column 和@Id 都是javax.persistence包中的
技巧 idea快捷键：alt+insert可以快速插入getter 和setter




4.2  Mapper
public interface UserInfoMapper extends Mapper<UserInfo> {
}

注意： Mapper也是引用tk.mybatis.mapper.common.Mapper包中的


Idea 有的时候校验@Autowired不准 可以把校验关闭
settings -> Inspections -> spring->spring core -> code-> Autowiring for Bean class
 

4.4  接口声明UserService

public interface UserService {

    List<UserInfo> getUserInfoListAll();

    void addUser(UserInfo userInfo);

    void updateUser(UserInfo userInfo);

    void updateUserByName(String name,UserInfo userInfo);

    void delUser(UserInfo userInfo);

}


4.5  接口实现UserServiceImpl
@Service
public class UserServiceImpl implements UserService {

@Autowired
UserMapper userMapper;

@Override
public List<UserInfo> getUserInfoListAll() {
    List<UserInfo> userInfoList = userMapper.selectAll();
    return userInfoList;
}

public UserInfo getUserInfo(String id){
    UserInfo userInfo = userMapper.selectByPrimaryKey(id);
    return  userInfo;
}

@Override
public void addUser(UserInfo userInfo) {
    userMapper.insert(userInfo);
}

@Override
public void updateUser(UserInfo userInfo) {
    userMapper.updateByPrimaryKeySelective(userInfo);
}

@Override
public void updateUserByName(String name, UserInfo userInfo) {

    Example example = new Example(UserInfo.class);
    example.createCriteria().andEqualTo("name",name);
    userMapper.updateByExample(userInfo,example);

}

@Override
public void delUser( String id) {
    userMapper.deleteByPrimaryKey(id);
}
}



4.6  Controller

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public  String  addUser(  UserInfo userInfo){
        userService.addUser(userInfo);
        return "success";
    }

    @GetMapping("allUser")
    public List<UserInfo> getAllUser(){
        return  userService.getUserInfoListAll();
    }

    @GetMapping("userInfo")
    public UserInfo getUser(String id){
        return  userService.getUserInfo(id);
    }

    @PostMapping("delUser")
    public String deleteUser(String id){
        userService.delUser(id);
        return  "success";
    }

    @PostMapping("updateUser")
    public String updateUser(UserInfo userInfo){
      //  userService.updateUser(userInfo);

        userService.updateUserByName(userInfo.getName(),userInfo);
        return  "success";
    }
}

 


