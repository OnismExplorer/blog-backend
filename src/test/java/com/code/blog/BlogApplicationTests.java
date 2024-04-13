package com.code.blog;

import cn.hutool.json.JSONUtil;
import com.code.blog.constants.RedisConstants;
import com.code.blog.entity.WebInfo;
import com.code.blog.utils.RedisUtil;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RedisUtil redisUtil;
    @Test
    void contextLoads() {
    }


    @Test
    public void testJsoup(){
        String[] names = new String[]{"张三","李四","王五","赵六","钱七","孙八","周九","吴十","郑十一","王十二","李十三","王十四","张十五","李十六","王十七","张十八","李十九","王二十"};
        WebInfo webInfo = new WebInfo().setId(1L).setRandomName(Arrays.toString(names));
        redisUtil.set(RedisConstants.WEB_INFO,webInfo.getId().toString(),webInfo);
        WebInfo info = redisUtil.get(RedisConstants.WEB_INFO.getKey() + webInfo.getId(),WebInfo.class);
        List<String> strings = JSONUtil.toList(info.getRandomName(), String.class);
        System.out.println(strings.toString());
    }

    @Test
    public void testCode(){
        redisUtil.set(RedisConstants.COMMENT_COUNT,"1:1","123456");
        System.out.println(redisUtil.get(RedisConstants.COMMENT_COUNT.getKey() + 1 + ":" + 1, Integer.class));
    }

    @Test
    public void testSensitiveWord(){
        Map<Integer,Integer> map = new HashMap<>();
    }
}
