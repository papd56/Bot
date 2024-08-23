package com.ruoyi.project.bot.group.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.bot.group.mapper.BotGroupMapper;
import com.ruoyi.project.bot.group.domain.BotGroup;
import com.ruoyi.project.bot.group.service.IBotGroupService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 群组管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@Service
public class BotGroupServiceImpl implements IBotGroupService 
{
    @Autowired
    private BotGroupMapper botGroupMapper;

    /**
     * 查询群组管理
     * 
     * @param id 群组管理主键
     * @return 群组管理
     */
    @Override
    public BotGroup selectBotGroupById(Long id)
    {
        return botGroupMapper.selectBotGroupById(id);
    }

    /**
     * 查询群组管理列表
     * 
     * @param botGroup 群组管理
     * @return 群组管理
     */
    @Override
    public List<BotGroup> selectBotGroupList(BotGroup botGroup)
    {
        return botGroupMapper.selectBotGroupList(botGroup);
    }

    /**
     * 新增群组管理
     * 
     * @param botGroup 群组管理
     * @return 结果
     */
    @Override
    public int insertBotGroup(BotGroup botGroup)
    {
        return botGroupMapper.insertBotGroup(botGroup);
    }

    /**
     * 修改群组管理
     * 
     * @param botGroup 群组管理
     * @return 结果
     */
    @Override
    public int updateBotGroup(BotGroup botGroup)
    {
        return botGroupMapper.updateBotGroup(botGroup);
    }

    /**
     * 批量删除群组管理
     * 
     * @param ids 需要删除的群组管理主键
     * @return 结果
     */
    @Override
    public int deleteBotGroupByIds(String ids)
    {
        return botGroupMapper.deleteBotGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除群组管理信息
     * 
     * @param id 群组管理主键
     * @return 结果
     */
    @Override
    public int deleteBotGroupById(Long id)
    {
        return botGroupMapper.deleteBotGroupById(id);
    }
}
