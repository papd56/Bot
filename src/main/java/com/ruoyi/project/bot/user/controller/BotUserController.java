package com.ruoyi.project.bot.user.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.bot.user.domain.BotUser;
import com.ruoyi.project.bot.user.service.IBotUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
        return toAjax(botUserService.insertBotUser(botUser));
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
