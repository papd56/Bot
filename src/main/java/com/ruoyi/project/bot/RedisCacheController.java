package com.ruoyi.project.bot;

import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import com.ruoyi.project.bot.group.domain.BotGroup;
import com.ruoyi.project.bot.group.service.IBotGroupService;
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
    private IBotGroupService botGroupService;

    @Autowired
    private IBotPromoteService botPromoteService;

    @Autowired
    private IBotUserService botUserService;

    /**
     * 查询用户列表和推广指令列表并缓存
     */
    @Anonymous
    @PostMapping("/list")
    @ResponseBody
    public void list()
    {
        botGroupService.selectBotGroupList(new BotGroup()).forEach(botGroup -> {
            redisCacheService.botGroup(botGroup);
        });
        botPromoteService.selectBotPromoteList(new BotPromote()).forEach(botPromote -> {
            redisCacheService.botPromote(botPromote);
        });
        botUserService.selectBotUserList(new BotUser()).forEach(botUser -> {
            redisCacheService.botUser(botUser);
        });
    }
}
