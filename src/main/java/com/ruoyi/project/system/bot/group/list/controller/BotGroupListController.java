package com.ruoyi.project.system.bot.group.list.controller;

import java.util.List;

import com.ruoyi.project.system.bot.group.list.domain.BotGroupList;
import com.ruoyi.project.system.bot.group.list.service.IBotGroupListService;
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
 * 群组列Controller
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@Controller
@RequestMapping("/baobeibot/bot/group/list")
public class BotGroupListController extends BaseController
{
    private String prefix = "baobeibot/bot/group/list";

    @Autowired
    private IBotGroupListService botGroupListService;

    @RequiresPermissions("baobeibot:bot/group/list:view")
    @GetMapping()
    public String botgrouplist()
    {
        return prefix + "/bot/group/list";
    }

    /**
     * 查询群组列列表
     */
    @RequiresPermissions("baobeibot:bot/group/list:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BotGroupList botGroupList)
    {
        startPage();
        List<BotGroupList> list = botGroupListService.selectBotGroupListList(botGroupList);
        return getDataTable(list);
    }

    /**
     * 导出群组列列表
     */
    @RequiresPermissions("baobeibot:bot/group/list:export")
    @Log(title = "群组列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BotGroupList botGroupList)
    {
        List<BotGroupList> list = botGroupListService.selectBotGroupListList(botGroupList);
        ExcelUtil<BotGroupList> util = new ExcelUtil<BotGroupList>(BotGroupList.class);
        return util.exportExcel(list, "群组列数据");
    }

    /**
     * 新增群组列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存群组列
     */
    @RequiresPermissions("baobeibot:bot/group/list:add")
    @Log(title = "群组列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BotGroupList botGroupList)
    {
        return toAjax(botGroupListService.insertBotGroupList(botGroupList));
    }

    /**
     * 修改群组列
     */
    @RequiresPermissions("baobeibot:bot/group/list:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BotGroupList botGroupList = botGroupListService.selectBotGroupListById(id);
        mmap.put("botGroupList", botGroupList);
        return prefix + "/edit";
    }

    /**
     * 修改保存群组列
     */
    @RequiresPermissions("baobeibot:bot/group/list:edit")
    @Log(title = "群组列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BotGroupList botGroupList)
    {
        return toAjax(botGroupListService.updateBotGroupList(botGroupList));
    }

    /**
     * 删除群组列
     */
    @RequiresPermissions("baobeibot:bot/group/list:remove")
    @Log(title = "群组列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(botGroupListService.deleteBotGroupListByIds(ids));
    }
}
