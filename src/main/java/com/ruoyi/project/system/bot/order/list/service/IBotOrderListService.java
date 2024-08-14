package com.ruoyi.project.system.bot.order.list.service;

import com.ruoyi.project.system.bot.order.list.domain.BotOrderList;

import java.util.List;

/**
 * 订单列Service接口
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public interface IBotOrderListService
{
    /**
     * 查询订单列
     *
     * @param id 订单列主键
     * @return 订单列
     */
    public BotOrderList selectBotOrderListById(Long id);

    /**
     * 查询订单列列表
     *
     * @param botOrderList 订单列
     * @return 订单列集合
     */
    public List<BotOrderList> selectBotOrderListList(BotOrderList botOrderList);

    /**
     * 新增订单列
     *
     * @param botOrderList 订单列
     * @return 结果
     */
    public int insertBotOrderList(BotOrderList botOrderList);

    /**
     * 修改订单列
     *
     * @param botOrderList 订单列
     * @return 结果
     */
    public int updateBotOrderList(BotOrderList botOrderList);

    /**
     * 批量删除订单列
     *
     * @param ids 需要删除的订单列主键集合
     * @return 结果
     */
    public int deleteBotOrderListByIds(String ids);

    /**
     * 删除订单列信息
     *
     * @param id 订单列主键
     * @return 结果
     */
    public int deleteBotOrderListById(Long id);
}
