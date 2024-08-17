package com.ruoyi.project.bot.user.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.bot.RedisCacheService;
import com.ruoyi.project.bot.user.domain.BotUser;
import com.ruoyi.project.bot.user.service.IBotUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
@Controller
@RequestMapping("/bot/user")
public class BotUserController extends BaseController
{

    private String prefix = "bot/user";

    @Autowired
    private IBotUserService botUserService;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequiresPermissions("bot:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户管理列表
     */
    @RequiresPermissions("bot:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BotUser botUser)
    {
        startPage();
        List<BotUser> list = botUserService.selectBotUserList(botUser);
        return getDataTable(list);
    }

    /**
     * 查询用户列表并缓存
     */
    @Anonymous
    @PostMapping("/userList")
    @ResponseBody
    public void userList()
    {
        botUserService.selectBotUserList(new BotUser()).forEach(botUser -> {
            redisCacheService.botUser(botUser);
        });
    }

    /**
     * 导出用户管理列表
     */
    @RequiresPermissions("bot:user:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BotUser botUser)
    {
        List<BotUser> list = botUserService.selectBotUserList(botUser);
        ExcelUtil<BotUser> util = new ExcelUtil<BotUser>(BotUser.class);
        return util.exportExcel(list, "用户管理数据");
    }

    /**
     * 新增用户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户管理
     */
    @RequiresPermissions("bot:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BotUser botUser)
    {
        redisCacheService.botUser(botUser);
        return toAjax(botUserService.insertBotUser(botUser));
    }

    /**
     * 新增用户
     */
    @Anonymous
    @PostMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestBody BotUser botUser)
    {
        redisCacheService.botUser(botUser);
        return botUserService.insertBotUser(botUser);
    }

    /**
     * 新增用户
     */
    @Anonymous
    @PostMapping("/addUsers")
    @ResponseBody
    public void addUser(@RequestBody List<BotUser> botUsers)
    {
        botUsers.forEach(botUser -> {
            if (Boolean.FALSE.equals(redisTemplate.hasKey(botUser.getUserName()))) {
                redisCacheService.botUser(botUser);
                botUserService.insertBotUser(botUser);
            }
        });
    }

    /**
     * 修改用户管理
     */
    @RequiresPermissions("bot:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BotUser botUser = botUserService.selectBotUserById(id);
        mmap.put("botUser", botUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户管理
     */
    @RequiresPermissions("bot:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BotUser botUser)
    {
        redisCacheService.botUser(botUser);
        return toAjax(botUserService.updateBotUser(botUser));
    }

    /**
     * 删除用户管理
     */
    @RequiresPermissions("bot:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(botUserService.deleteBotUserByIds(ids));
    }
}
