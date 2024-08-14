package com.ruoyi.project.bot.promote.service;

import java.util.List;
import com.ruoyi.project.bot.promote.domain.BotPromote;

/**
 * 推广管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public interface IBotPromoteService 
{
    /**
     * 查询推广管理
     * 
     * @param id 推广管理主键
     * @return 推广管理
     */
    public BotPromote selectBotPromoteById(Long id);

    /**
     * 查询推广管理列表
     * 
     * @param botPromote 推广管理
     * @return 推广管理集合
     */
    public List<BotPromote> selectBotPromoteList(BotPromote botPromote);

    /**
     * 新增推广管理
     * 
     * @param botPromote 推广管理
     * @return 结果
     */
    public int insertBotPromote(BotPromote botPromote);

    /**
     * 修改推广管理
     * 
     * @param botPromote 推广管理
     * @return 结果
     */
    public int updateBotPromote(BotPromote botPromote);

    /**
     * 批量删除推广管理
     * 
     * @param ids 需要删除的推广管理主键集合
     * @return 结果
     */
    public int deleteBotPromoteByIds(String ids);

    /**
     * 删除推广管理信息
     * 
     * @param id 推广管理主键
     * @return 结果
     */
    public int deleteBotPromoteById(Long id);
}
