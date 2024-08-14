package com.ruoyi.project.group.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.group.domain.BotGroupList;
import com.ruoyi.project.group.mapper.BotGroupListMapper;
import com.ruoyi.project.group.service.IBotGroupListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 群组列Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@Service
public class BotGroupListServiceImpl implements IBotGroupListService
{
    @Autowired
    private BotGroupListMapper botGroupListMapper;

    /**
     * 查询群组列
     *
     * @param id 群组列主键
     * @return 群组列
     */
    @Override
    public BotGroupList selectBotGroupListById(Long id)
    {
        return botGroupListMapper.selectBotGroupListById(id);
    }

    /**
     * 查询群组列列表
     *
     * @param botGroupList 群组列
     * @return 群组列
     */
    @Override
    public List<BotGroupList> selectBotGroupListList(BotGroupList botGroupList)
    {
        return botGroupListMapper.selectBotGroupListList(botGroupList);
    }

    /**
     * 新增群组列
     *
     * @param botGroupList 群组列
     * @return 结果
     */
    @Override
    public int insertBotGroupList(BotGroupList botGroupList)
    {
        botGroupList.setCreateTime(DateUtils.getNowDate());
        return botGroupListMapper.insertBotGroupList(botGroupList);
    }

    /**
     * 修改群组列
     *
     * @param botGroupList 群组列
     * @return 结果
     */
    @Override
    public int updateBotGroupList(BotGroupList botGroupList)
    {
        return botGroupListMapper.updateBotGroupList(botGroupList);
    }

    /**
     * 批量删除群组列
     *
     * @param ids 需要删除的群组列主键
     * @return 结果
     */
    @Override
    public int deleteBotGroupListByIds(String ids)
    {
        return botGroupListMapper.deleteBotGroupListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除群组列信息
     *
     * @param id 群组列主键
     * @return 结果
     */
    @Override
    public int deleteBotGroupListById(Long id)
    {
        return botGroupListMapper.deleteBotGroupListById(id);
    }
}
