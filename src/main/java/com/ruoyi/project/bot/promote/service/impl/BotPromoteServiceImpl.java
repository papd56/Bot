package com.ruoyi.project.bot.promote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.bot.promote.mapper.BotPromoteMapper;
import com.ruoyi.project.bot.promote.domain.BotPromote;
import com.ruoyi.project.bot.promote.service.IBotPromoteService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 推广管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
@Service
public class BotPromoteServiceImpl implements IBotPromoteService 
{
    @Autowired
    private BotPromoteMapper botPromoteMapper;

    /**
     * 查询推广管理
     * 
     * @param id 推广管理主键
     * @return 推广管理
     */
    @Override
    public BotPromote selectBotPromoteById(Long id)
    {
        return botPromoteMapper.selectBotPromoteById(id);
    }

    /**
     * 查询推广管理列表
     * 
     * @param botPromote 推广管理
     * @return 推广管理
     */
    @Override
    public List<BotPromote> selectBotPromoteList(BotPromote botPromote)
    {
        return botPromoteMapper.selectBotPromoteList(botPromote);
    }

    /**
     * 新增推广管理
     * 
     * @param botPromote 推广管理
     * @return 结果
     */
    @Override
    public int insertBotPromote(BotPromote botPromote)
    {
        return botPromoteMapper.insertBotPromote(botPromote);
    }

    /**
     * 修改推广管理
     * 
     * @param botPromote 推广管理
     * @return 结果
     */
    @Override
    public int updateBotPromote(BotPromote botPromote)
    {
        return botPromoteMapper.updateBotPromote(botPromote);
    }

    /**
     * 批量删除推广管理
     * 
     * @param ids 需要删除的推广管理主键
     * @return 结果
     */
    @Override
    public int deleteBotPromoteByIds(String ids)
    {
        return botPromoteMapper.deleteBotPromoteByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除推广管理信息
     * 
     * @param id 推广管理主键
     * @return 结果
     */
    @Override
    public int deleteBotPromoteById(Long id)
    {
        return botPromoteMapper.deleteBotPromoteById(id);
    }
}
