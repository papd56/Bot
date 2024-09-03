package com.ruoyi.project.management.mapper;

import com.ruoyi.project.management.domain.BillingManagement;

import java.util.List;

/**
 * 账单管理记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-02
 */
public interface BillingManagementMapper
{
    /**
     * 查询账单管理记录
     *
     * @param id 账单管理记录主键
     * @return 账单管理记录
     */
    public BillingManagement selectBillingManagementById(Long id);

    /**
     * 查询账单管理记录列表
     *
     * @param billingManagement 账单管理记录
     * @return 账单管理记录集合
     */
    public List<BillingManagement> selectBillingManagementList(BillingManagement billingManagement);

    /**
     * 新增账单管理记录
     *
     * @param billingManagement 账单管理记录
     * @return 结果
     */
    public int insertBillingManagement(BillingManagement billingManagement);

    /**
     * 修改账单管理记录
     *
     * @param billingManagement 账单管理记录
     * @return 结果
     */
    public int updateBillingManagement(BillingManagement billingManagement);

    /**
     * 删除账单管理记录
     *
     * @param id 账单管理记录主键
     * @return 结果
     */
    public int deleteBillingManagementById(Long id);

    /**
     * 批量删除账单管理记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBillingManagementByIds(String[] ids);
}
