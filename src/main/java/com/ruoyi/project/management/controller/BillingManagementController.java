package com.ruoyi.project.management.controller;

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
import com.ruoyi.project.management.domain.BillingManagement;
import com.ruoyi.project.management.service.IBillingManagementService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 账单管理记录Controller
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@Controller
@RequestMapping("/billing/management")
public class BillingManagementController extends BaseController
{
    private String prefix = "billing/management";

    @Autowired
    private IBillingManagementService billingManagementService;

    @RequiresPermissions("billing:management:view")
    @GetMapping()
    public String management()
    {
        return prefix + "/management";
    }

    /**
     * 查询账单管理记录列表
     */
    @RequiresPermissions("billing:management:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BillingManagement billingManagement)
    {
        startPage();
        List<BillingManagement> list = billingManagementService.selectBillingManagementList(billingManagement);
        return getDataTable(list);
    }

    /**
     * 导出账单管理记录列表
     */
    @RequiresPermissions("billing:management:export")
    @Log(title = "账单管理记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BillingManagement billingManagement)
    {
        List<BillingManagement> list = billingManagementService.selectBillingManagementList(billingManagement);
        ExcelUtil<BillingManagement> util = new ExcelUtil<BillingManagement>(BillingManagement.class);
        return util.exportExcel(list, "账单管理记录数据");
    }

    /**
     * 新增账单管理记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存账单管理记录
     */
    @Log(title = "账单管理记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BillingManagement billingManagement)
    {
        return toAjax(billingManagementService.insertBillingManagement(billingManagement));
    }

    /**
     * 修改账单管理记录
     */
    @RequiresPermissions("billing:management:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BillingManagement billingManagement = billingManagementService.selectBillingManagementById(id);
        mmap.put("billingManagement", billingManagement);
        return prefix + "/edit";
    }

    /**
     * 修改保存账单管理记录
     */
    @RequiresPermissions("billing:management:edit")
    @Log(title = "账单管理记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BillingManagement billingManagement)
    {
        return toAjax(billingManagementService.updateBillingManagement(billingManagement));
    }

    /**
     * 删除账单管理记录
     */
    @RequiresPermissions("billing:management:remove")
    @Log(title = "账单管理记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(billingManagementService.deleteBillingManagementByIds(ids));
    }
}
