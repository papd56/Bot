package com.ruoyi.project.bot.group.mapper;

import java.util.List;
import com.ruoyi.project.bot.group.domain.BotGroup;

/**
 * 群组管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
public interface BotGroupMapper 
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
     * 分词查询群组管理列表
     *
     * @param botGroup 群组管理
     * @return 群组管理集合
     */
    public List<BotGroup> selectBotGroupListByGroupName(BotGroup botGroup);

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
     * 删除群组管理
     * 
     * @param id 群组管理主键
     * @return 结果
     */
    public int deleteBotGroupById(Long id);

    /**
     * 批量删除群组管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBotGroupByIds(String[] ids);
}
