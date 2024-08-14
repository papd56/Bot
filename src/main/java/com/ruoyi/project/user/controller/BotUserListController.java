package com.ruoyi.project.user.controller;

import java.util.List;

import com.ruoyi.project.user.domain.BotUserList;
import com.ruoyi.project.user.service.IBotUserListService;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 机器人用户列Controller
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@Controller
@RequestMapping("/baobeibot/user")
public class BotUserListController extends BaseController
{
    private String prefix = "baobeibot/user";

    @Autowired
    private IBotUserListService botUserListService;

    @RequiresPermissions("baobeibot:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询机器人用户列列表
     */
    @RequiresPermissions("baobeibot:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BotUserList botUserList)
    {
        startPage();
        List<BotUserList> list = botUserListService.selectBotUserListList(botUserList);
        return getDataTable(list);
    }

    /**
     * 导出机器人用户列列表
     */
    @RequiresPermissions("baobeibot:user:export")
    @Log(title = "机器人用户列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BotUserList botUserList)
    {
        List<BotUserList> list = botUserListService.selectBotUserListList(botUserList);
        ExcelUtil<BotUserList> util = new ExcelUtil<BotUserList>(BotUserList.class);
        return util.exportExcel(list, "机器人用户列数据");
    }

    /**
     * 新增机器人用户列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机器人用户列
     */
    @RequiresPermissions("baobeibot:user:add")
    @Log(title = "机器人用户列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BotUserList botUserList)
    {
        return toAjax(botUserListService.insertBotUserList(botUserList));
    }

    /**
     * 修改机器人用户列
     */
    @RequiresPermissions("baobeibot:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BotUserList botUserList = botUserListService.selectBotUserListById(id);
        mmap.put("botUserList", botUserList);
        return prefix + "/edit";
    }

    /**
     * 修改保存机器人用户列
     */
    @RequiresPermissions("baobeibot:user:edit")
    @Log(title = "机器人用户列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BotUserList botUserList)
    {
        return toAjax(botUserListService.updateBotUserList(botUserList));
    }

    /**
     * 删除机器人用户列
     */
    @RequiresPermissions("baobeibot:user:remove")
    @Log(title = "机器人用户列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(botUserListService.deleteBotUserListByIds(ids));
    }
}
