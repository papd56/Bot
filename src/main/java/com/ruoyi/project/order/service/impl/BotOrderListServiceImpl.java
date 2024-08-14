package com.ruoyi.project.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.order.domain.BotOrderList;
import com.ruoyi.project.order.mapper.BotOrderListMapper;
import com.ruoyi.project.order.service.IBotOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 订单列Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@Service
public class BotOrderListServiceImpl implements IBotOrderListService
{
    @Autowired
    private BotOrderListMapper botOrderListMapper;

    /**
     * 查询订单列
     *
     * @param id 订单列主键
     * @return 订单列
     */
    @Override
    public BotOrderList selectBotOrderListById(Long id)
    {
        return botOrderListMapper.selectBotOrderListById(id);
    }

    /**
     * 查询订单列列表
     *
     * @param botOrderList 订单列
     * @return 订单列
     */
    @Override
    public List<BotOrderList> selectBotOrderListList(BotOrderList botOrderList)
    {
        return botOrderListMapper.selectBotOrderListList(botOrderList);
    }

    /**
     * 新增订单列
     *
     * @param botOrderList 订单列
     * @return 结果
     */
    @Override
    public int insertBotOrderList(BotOrderList botOrderList)
    {
        botOrderList.setCreateTime(DateUtils.getNowDate());
        return botOrderListMapper.insertBotOrderList(botOrderList);
    }

    /**
     * 修改订单列
     *
     * @param botOrderList 订单列
     * @return 结果
     */
    @Override
    public int updateBotOrderList(BotOrderList botOrderList)
    {
        botOrderList.setUpdateTime(DateUtils.getNowDate());
        return botOrderListMapper.updateBotOrderList(botOrderList);
    }

    /**
     * 批量删除订单列
     *
     * @param ids 需要删除的订单列主键
     * @return 结果
     */
    @Override
    public int deleteBotOrderListByIds(String ids)
    {
        return botOrderListMapper.deleteBotOrderListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单列信息
     *
     * @param id 订单列主键
     * @return 结果
     */
    @Override
    public int deleteBotOrderListById(Long id)
    {
        return botOrderListMapper.deleteBotOrderListById(id);
    }
}
