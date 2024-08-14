package com.ruoyi.project.bot.promote.controller;

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
import com.ruoyi.project.bot.promote.domain.BotPromote;
import com.ruoyi.project.bot.promote.service.IBotPromoteService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
        return toAjax(botPromoteService.insertBotPromote(botPromote));
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
