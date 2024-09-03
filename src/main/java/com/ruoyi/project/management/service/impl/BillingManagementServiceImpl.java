package com.ruoyi.project.management.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.management.domain.BillingManagement;
import com.ruoyi.project.management.mapper.BillingManagementMapper;
import com.ruoyi.project.management.service.IBillingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 账单管理记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@Service
public class BillingManagementServiceImpl implements IBillingManagementService
{
    @Autowired
    private BillingManagementMapper billingManagementMapper;

    /**
     * 查询账单管理记录
     *
     * @param id 账单管理记录主键
     * @return 账单管理记录
     */
    @Override
    public BillingManagement selectBillingManagementById(Long id)
    {
        return billingManagementMapper.selectBillingManagementById(id);
    }

    /**
     * 查询账单管理记录列表
     *
     * @param billingManagement 账单管理记录
     * @return 账单管理记录
     */
    @Override
    public List<BillingManagement> selectBillingManagementList(BillingManagement billingManagement)
    {
        return billingManagementMapper.selectBillingManagementList(billingManagement);
    }

    /**
     * 新增账单管理记录
     *
     * @param billingManagement 账单管理记录
     * @return 结果
     */
    @Override
    public int insertBillingManagement(BillingManagement billingManagement)
    {
        billingManagement.setCreateTime(DateUtils.getNowDate());
        return billingManagementMapper.insertBillingManagement(billingManagement);
    }

    /**
     * 修改账单管理记录
     *
     * @param billingManagement 账单管理记录
     * @return 结果
     */
    @Override
    public int updateBillingManagement(BillingManagement billingManagement)
    {
        return billingManagementMapper.updateBillingManagement(billingManagement);
    }

    /**
     * 批量删除账单管理记录
     *
     * @param ids 需要删除的账单管理记录主键
     * @return 结果
     */
    @Override
    public int deleteBillingManagementByIds(String ids)
    {
        return billingManagementMapper.deleteBillingManagementByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账单管理记录信息
     *
     * @param id 账单管理记录主键
     * @return 结果
     */
    @Override
    public int deleteBillingManagementById(Long id)
    {
        return billingManagementMapper.deleteBillingManagementById(id);
    }
}
