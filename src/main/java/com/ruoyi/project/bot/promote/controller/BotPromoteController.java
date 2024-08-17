package com.ruoyi.project.bot.promote.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.bot.RedisCacheService;
import com.ruoyi.project.bot.promote.domain.BotPromote;
import com.ruoyi.project.bot.promote.service.IBotPromoteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推广管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
@Controller
@RequestMapping("/bot/promote")
public class BotPromoteController extends BaseController
{

    private String prefix = "bot/promote";

    @Autowired
    private IBotPromoteService botPromoteService;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequiresPermissions("bot:promote:view")
    @GetMapping()
    public String promote()
    {
        return prefix + "/promote";
    }

    /**
     * 查询推广管理列表
     */
    @RequiresPermissions("bot:promote:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BotPromote botPromote)
    {
        startPage();
        List<BotPromote> list = botPromoteService.selectBotPromoteList(botPromote);
        return getDataTable(list);
    }

    /**
     * 查询推广指令列表并缓存
     */
    @Anonymous
    @PostMapping("/promoteList")
    @ResponseBody
    public void promoteList()
    {
        botPromoteService.selectBotPromoteList(new BotPromote()).forEach(botPromote -> {
            redisCacheService.botPromote(botPromote);
        });
    }

    /**
     * 导出推广管理列表
     */
    @RequiresPermissions("bot:promote:export")
    @Log(title = "推广管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BotPromote botPromote)
    {
        List<BotPromote> list = botPromoteService.selectBotPromoteList(botPromote);
        ExcelUtil<BotPromote> util = new ExcelUtil<BotPromote>(BotPromote.class);
        return util.exportExcel(list, "推广管理数据");
    }

    /**
     * 新增推广管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存推广管理
     */
    @RequiresPermissions("bot:promote:add")
    @Log(title = "推广管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BotPromote botPromote)
    {
        if (Boolean.FALSE.equals(redisTemplate.hasKey("promote:"+botPromote.getCommand()))) {
            redisCacheService.botPromote(botPromote);
            return toAjax(botPromoteService.insertBotPromote(botPromote));
        }
        return toAjax(0);
    }

    /**
     * 修改推广管理
     */
    @RequiresPermissions("bot:promote:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BotPromote botPromote = botPromoteService.selectBotPromoteById(id);
        mmap.put("botPromote", botPromote);
        return prefix + "/edit";
    }

    /**
     * 修改保存推广管理
     */
    @RequiresPermissions("bot:promote:edit")
    @Log(title = "推广管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BotPromote botPromote)
    {
        redisCacheService.botPromote(botPromote);
        return toAjax(botPromoteService.updateBotPromote(botPromote));
    }

    /**
     * 删除推广管理
     */
    @RequiresPermissions("bot:promote:remove")
    @Log(title = "推广管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(botPromoteService.deleteBotPromoteByIds(ids));
    }
}
