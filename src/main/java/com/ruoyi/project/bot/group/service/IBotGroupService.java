package com.ruoyi.project.bot.group.service;

import java.util.List;
import com.ruoyi.project.bot.group.domain.BotGroup;

/**
 * 群组管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public interface IBotGroupService 
{
    /**
     * 查询群组管理
     * 
     * @param id 群组管理主键
     * @return 群组管理
     */
    public BotGroup selectBotGroupById(Long id);

    /**
     * 查询群组管理列表
     * 
     * @param botGroup 群组管理
     * @return 群组管理集合
     */
    public List<BotGroup> selectBotGroupList(BotGroup botGroup);

    /**
     * 新增群组管理
     * 
     * @param botGroup 群组管理
     * @return 结果
     */
    public int insertBotGroup(BotGroup botGroup);

    /**
     * 修改群组管理
     * 
     * @param botGroup 群组管理
     * @return 结果
     */
    public int updateBotGroup(BotGroup botGroup);

    /**
     * 批量删除群组管理
     * 
     * @param ids 需要删除的群组管理主键集合
     * @return 结果
     */
    public int deleteBotGroupByIds(String ids);

    /**
     * 删除群组管理信息
     * 
     * @param id 群组管理主键
     * @return 结果
     */
    public int deleteBotGroupById(Long id);
}
