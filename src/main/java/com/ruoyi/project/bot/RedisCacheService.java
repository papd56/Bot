package com.ruoyi.project.bot;

import com.ruoyi.project.bot.group.domain.BotGroup;
import com.ruoyi.project.bot.promote.domain.BotPromote;
import com.ruoyi.project.bot.user.domain.BotUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {
    private static final Logger log = LoggerFactory.getLogger(RedisCacheService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String getWithRetry(String key) {
        int retryCount = 0;
        while (retryCount < 3) {
            try {
                return (String) redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                log.error("setWithRetry Exception:",e);
                retryCount++;
                try {
                    Thread.sleep(1000); // 每次重试间隔1秒
                } catch (InterruptedException ex) {
                    log.error("setWithRetry InterruptedException:",ex);
                }
            }
        }
        return null;
    }

    public void setWithRetry(String key, Object value) {
        int retryCount = 0;
        while (retryCount < 3) {
            try {
                redisTemplate.opsForValue().set(key, value.toString());
                return;
            } catch (Exception e) {
                log.error("setWithRetry Exception:",e);
                retryCount++;
                try {
                    Thread.sleep(1000); // 每次重试间隔1秒
                } catch (InterruptedException ex) {
                    log.error("setWithRetry InterruptedException:",ex);
                }
            }
        }
    }

    public void setHashWithRetry(String key, String field, Object value) {
        int retryCount = 0;
        while (retryCount < 3) {
            try {
                redisTemplate.opsForHash().put(key, field, value.toString());
                return;
            } catch (Exception e) {
                log.error("setWithRetry Exception:",e);
                retryCount++;
                try {
                    Thread.sleep(1000); // 每次重试间隔1秒
                } catch (InterruptedException ex) {
                    log.error("setWithRetry InterruptedException:",ex);
                }
            }
        }
    }

    public void botGroup(BotGroup botGroup) {
        setHashWithRetry("group", botGroup.getGroupId(), botGroup);
    }

    public void botPromote(BotPromote botPromote) {
        if ("1".equals(botPromote.getStatus())) {
            setWithRetry("promote:"+botPromote.getCommand(), botPromote);
        }
    }

    public void botUser(BotUser botUser) {
        setWithRetry("user:"+botUser.getUserName(), botUser);
        if ("1".equals(botUser.getStatus())) {
            setWithRetry("admin:"+botUser.getUserId(), botUser);
        }
    }
}
