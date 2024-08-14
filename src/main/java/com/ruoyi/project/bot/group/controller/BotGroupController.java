package com.ruoyi.project.bot.group.controller;

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
import com.ruoyi.project.bot.group.domain.BotGroup;
import com.ruoyi.project.bot.group.service.IBotGroupService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 群组管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
@Controller
@RequestMapping("/bot/group")
public class BotGroupController extends BaseController
{
    private String prefix = "bot/group";

    @Autowired
    private IBotGroupService botGroupService;

    @RequiresPermissions("bot:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询群组管理列表
     */
    @RequiresPermissions("bot:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BotGroup botGroup)
    {
        startPage();
        List<BotGroup> list = botGroupService.selectBotGroupList(botGroup);
        return getDataTable(list);
    }

    /**
     * 导出群组管理列表
     */
    @RequiresPermissions("bot:group:export")
    @Log(title = "群组管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BotGroup botGroup)
    {
        List<BotGroup> list = botGroupService.selectBotGroupList(botGroup);
        ExcelUtil<BotGroup> util = new ExcelUtil<BotGroup>(BotGroup.class);
        return util.exportExcel(list, "群组管理数据");
    }

    /**
     * 新增群组管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存群组管理
     */
    @RequiresPermissions("bot:group:add")
    @Log(title = "群组管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BotGroup botGroup)
    {
        return toAjax(botGroupService.insertBotGroup(botGroup));
    }

    /**
     * 修改群组管理
     */
    @RequiresPermissions("bot:group:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BotGroup botGroup = botGroupService.selectBotGroupById(id);
        mmap.put("botGroup", botGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存群组管理
     */
    @RequiresPermissions("bot:group:edit")
    @Log(title = "群组管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BotGroup botGroup)
    {
        return toAjax(botGroupService.updateBotGroup(botGroup));
    }

    /**
     * 删除群组管理
     */
    @RequiresPermissions("bot:group:remove")
    @Log(title = "群组管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(botGroupService.deleteBotGroupByIds(ids));
    }
}
