package com.ruoyi.project.bot;

import com.ruoyi.project.bot.promote.domain.BotPromote;
import com.ruoyi.project.bot.user.domain.BotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void botUser(BotUser botUser) {
        redisTemplate.opsForValue().set("user:"+botUser.getUserName(), botUser);
        if ("1".equals(botUser.getStatus())) {
            redisTemplate.opsForValue().set("admin:"+botUser.getUserId(), botUser);
        }
    }

    public void botPromote(BotPromote botPromote) {
        if ("1".equals(botPromote.getStatus())) {
            redisTemplate.opsForValue().set("promote:"+botPromote.getCommand(), botPromote);
        }
    }
}
