package com.healthcare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Test64ApplicationTests {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
//
//        //对密码进行加密
//        String encode1 = passwordEncoder.encode("123");
//        String encode2 = passwordEncoder.encode("123");
//
//        //加密之后的密码
//        System.out.println(encode1);
//        System.out.println(encode2);
//
//        //可以通过这段代码随机生成盐值
//        System.out.println(BCrypt.gensalt());
//
//        //能否匹配原始密码
//        boolean matches1 = passwordEncoder.matches("123", encode1);
//        boolean matches2 = passwordEncoder.matches("123", encode2);
//
//        //打印匹配的结果
//        System.out.println(matches1);
//        System.out.println(matches2);

    }

}
