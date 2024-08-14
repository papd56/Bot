package com.ruoyi.project.system.bot.order.list.controller;

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
import com.ruoyi.project.system.bot.order.list.domain.BotOrderList;
import com.ruoyi.project.system.bot.order.list.service.IBotOrderListService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单列Controller
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@Controller
@RequestMapping("/baobeibot/bot/order/list")
public class BotOrderListController extends BaseController
{
    private String prefix = "baobeibot/bot/order/list";

    @Autowired
    private IBotOrderListService botOrderListService;

    @RequiresPermissions("baobeibot:bot:order:list:view")
    @GetMapping()
    public String botorderlist()
    {
        return prefix + "/bot/order/list";
    }

    /**
     * 查询订单列列表
     */
    @RequiresPermissions("baobeibot:bot/order/list:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BotOrderList botOrderList)
    {
        startPage();
        List<BotOrderList> list = botOrderListService.selectBotOrderListList(botOrderList);
        return getDataTable(list);
    }

    /**
     * 导出订单列列表
     */
    @RequiresPermissions("baobeibot:bot/order/list:export")
    @Log(title = "订单列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BotOrderList botOrderList)
    {
        List<BotOrderList> list = botOrderListService.selectBotOrderListList(botOrderList);
        ExcelUtil<BotOrderList> util = new ExcelUtil<BotOrderList>(BotOrderList.class);
        return util.exportExcel(list, "订单列数据");
    }

    /**
     * 新增订单列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单列
     */
    @RequiresPermissions("baobeibot:bot/order/list:add")
    @Log(title = "订单列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BotOrderList botOrderList)
    {
        return toAjax(botOrderListService.insertBotOrderList(botOrderList));
    }

    /**
     * 修改订单列
     */
    @RequiresPermissions("baobeibot:bot/order/list:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BotOrderList botOrderList = botOrderListService.selectBotOrderListById(id);
        mmap.put("botOrderList", botOrderList);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单列
     */
    @RequiresPermissions("baobeibot:bot/order/list:edit")
    @Log(title = "订单列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BotOrderList botOrderList)
    {
        return toAjax(botOrderListService.updateBotOrderList(botOrderList));
    }

    /**
     * 删除订单列
     */
    @RequiresPermissions("baobeibot:bot/order/list:remove")
    @Log(title = "订单列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(botOrderListService.deleteBotOrderListByIds(ids));
    }
}
