package com.hero;

import com.hero.pojo.User;
import com.hero.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

//测试读写分离
@SpringBootTest
class MasterSlaveTests {

    @Autowired
    private UserService userService;

    @Test
    void addUser() throws Exception {
        User user = new User();
        user.setName("永琪");
        user.setAge(28);
        user.setAddress("故宫");
        userService.addUser(user);
    }
    @Test
    void getUserList() throws Exception {
        for(int i=1;i<=100;i++){
            System.out.print(i+": ");
            List<User> userList = userService.selectLikeName("永琪");
            if(userList.isEmpty()){
                System.out.println("null");
            }else{
                userList.forEach(System.out::println);
            }
        }

    }
}
