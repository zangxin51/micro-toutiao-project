package com.xxx.toutiao.test;

import com.xxx.toutiao.util.JwtHelper;
import org.junit.Test;

public class TestJwtHelper {

    @Test
    public void test01() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        System.out.println("token = " + token);

        Thread.sleep(6000);
        System.out.println(JwtHelper.isExpiration(token));

        Long userId = JwtHelper.getUserId(token);
        System.out.println("userId = " + userId);
    }
}
