package com.ruoyi.project.bot;

import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import com.ruoyi.project.bot.promote.domain.BotPromote;
import com.ruoyi.project.bot.promote.service.IBotPromoteService;
import com.ruoyi.project.bot.user.domain.BotUser;
import com.ruoyi.project.bot.user.service.IBotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 缓存Controller
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@Controller
@RequestMapping("/redisCache")
public class RedisCacheController
{
    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private IBotUserService botUserService;

    @Autowired
    private IBotPromoteService botPromoteService;

    /**
     * 查询用户列表和推广指令列表并缓存
     */
    @Anonymous
    @PostMapping("/list")
    @ResponseBody
    public void list()
    {
        botUserService.selectBotUserList(new BotUser()).forEach(botUser -> {
            redisCacheService.botUser(botUser);
        });
        botPromoteService.selectBotPromoteList(new BotPromote()).forEach(botPromote -> {
            redisCacheService.botPromote(botPromote);
        });
    }
}
