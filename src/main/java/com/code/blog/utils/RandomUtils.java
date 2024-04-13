package com.code.blog.utils;

import cn.hutool.json.JSONUtil;
import com.code.blog.constants.RedisConstants;
import com.code.blog.entity.WebInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 随机工具类
 *
 * @author HeXin
 * @date 2024/03/11
 */
@Component
public class RandomUtils {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取随机名称
     *
     * @param key 键
     * @return {@link String}
     */
    public String getRandomName(String key) {
        WebInfo webInfo = redisUtil.get(RedisConstants.WEB_INFO.getKey(), WebInfo.class);
        if (webInfo != null) {
            String randomName = webInfo.getRandomName();
            List<String> randomNames = JSONUtil.toList(randomName, String.class);
            if (!CollectionUtils.isEmpty(randomNames)) {
                if (StringUtils.hasText(key)) {
                    return randomNames.get(hashLocation(key, randomNames.size()));
                }
                return randomNames.get(randomNames.size() - 1);
            }
        }
        return null;
    }

    /**
     * 获得随机封面
     *
     * @param key 钥匙
     * @return {@link String}
     */
    public String getRandomCover(String key) {
        WebInfo webInfo = redisUtil.get(RedisConstants.WEB_INFO.getKey(), WebInfo.class);
        if (webInfo != null) {
            String randomCover = webInfo.getRandomCover();
            List<String> randomCovers = JSONUtil.toList(randomCover, String.class);
            if (!CollectionUtils.isEmpty(randomCovers)) {
                if (StringUtils.hasText(key)) {
                    return randomCovers.get(hashLocation(key, randomCovers.size()));
                }
                return randomCovers.get(randomCovers.size() - 1);
            }
        }
        return null;
    }

    /**
     * 获取随机头像
     *
     * @param key 钥匙
     * @return {@link String}
     */
    public String getRandomAvatar(String key) {
        WebInfo webInfo = redisUtil.get(RedisConstants.WEB_INFO.getKey(), WebInfo.class);
        if (webInfo != null) {
            String randomAvatar = webInfo.getRandomAvatar();
            List<String> randomAvatars = JSONUtil.toList(randomAvatar, String.class);
            if (!CollectionUtils.isEmpty(randomAvatars)) {
                if (StringUtils.hasText(key)) {
                    return randomAvatars.get(hashLocation(key, randomAvatars.size()));
                }
                return randomAvatars.get(randomAvatars.size() - 1);
            }
        }
        return null;
    }

    /**
     * 哈希位置
     *
     * @param key    钥匙
     * @param length 长度
     * @return int
     */
    private int hashLocation(String key, int length) {
        int hashCode = key.hashCode();
        return (hashCode ^ (hashCode >>> 16)) & (length - 1);
    }
}
