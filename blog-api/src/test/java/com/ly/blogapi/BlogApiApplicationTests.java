package com.ly.blogapi;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApiApplicationTests {

    @Test
    void getPassword() {
        String slat = "mszlu!@#";   // 加密盐
        String password = "admin";  // 密码
        password = DigestUtil.md5Hex(password + slat);
        System.out.println("password = " + password);
    }

}
