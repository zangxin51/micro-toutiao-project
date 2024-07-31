package com.xxx.toutiao.test;

import com.xxx.toutiao.pojo.vo.HeadlineDetailVo;
import org.junit.Test;

public class TestEscaping {
    @Test
    public void processEscaping() {
        HeadlineDetailVo vo = new HeadlineDetailVo();
        vo.setTitle("唯有那份眩目　'未曾忘却。（眩しさだけは、忘れなかった）\t” “\t无论何时，我都会记得夏天的蓝……。");

        vo.setTitle(vo.getTitle().replaceAll("\'","\\\'"));
        System.out.println("vo = " + vo);

    }
}
