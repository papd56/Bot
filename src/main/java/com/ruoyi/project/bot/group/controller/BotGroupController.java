package com.ruoyi.project.bot.group.controller;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import com.ruoyi.project.bot.RedisCacheService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
 * @date 2024-09-01
 */
@Controller
@RequestMapping("/bot/group")
public class BotGroupController extends BaseController
{
    private String prefix = "bot/group";

    @Autowired
    private IBotGroupService botGroupService;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
     * 查询群组管理列表
     */
    @Anonymous
    @PostMapping("/groupList")
    @ResponseBody
    public TableDataInfo groupList(@RequestBody BotGroup botGroup)
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
     * 新增保存群组管理
     */
    @Anonymous
    @PostMapping("/addGroup")
    @ResponseBody
    public int addGroup(@RequestBody BotGroup botGroup)
    {
        if (Boolean.FALSE.equals(redisTemplate.hasKey("group:"+botGroup.getGroupId()))) {
            redisCacheService.botGroup(botGroup);
            return botGroupService.insertBotGroup(botGroup);
        }
        return 0;
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
     * 修改群组
     */
    @Anonymous
    @PostMapping("/editGroup")
    @ResponseBody
    public int editGroup(@RequestBody BotGroup botGroup)
    {
        redisCacheService.botGroup(botGroup);
        return botGroupService.updateBotGroup(botGroup);
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
