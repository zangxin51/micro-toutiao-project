package com.xxx.toutiao.test;

import com.xxx.toutiao.util.MD5Util;
import org.junit.Test;

public class TestMd5Util {

    @Test
    public void test01() {
        String encrypt = MD5Util.encrypt("123456");
        System.out.println("encrypt = " + encrypt);
    }
}
