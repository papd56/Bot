package com.ruoyi.project.system.bot.group.list.mapper;

import java.util.List;
import com.ruoyi.project.system.bot.group.list.domain.BotGroupList;

/**
 * 群组列Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public interface BotGroupListMapper
{
    /**
     * 查询群组列
     *
     * @param id 群组列主键
     * @return 群组列
     */
    public BotGroupList selectBotGroupListById(Long id);

    /**
     * 查询群组列列表
     *
     * @param botGroupList 群组列
     * @return 群组列集合
     */
    public List<BotGroupList> selectBotGroupListList(BotGroupList botGroupList);

    /**
     * 新增群组列
     *
     * @param botGroupList 群组列
     * @return 结果
     */
    public int insertBotGroupList(BotGroupList botGroupList);

    /**
     * 修改群组列
     *
     * @param botGroupList 群组列
     * @return 结果
     */
    public int updateBotGroupList(BotGroupList botGroupList);

    /**
     * 删除群组列
     *
     * @param id 群组列主键
     * @return 结果
     */
    public int deleteBotGroupListById(Long id);

    /**
     * 批量删除群组列
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBotGroupListByIds(String[] ids);
}
