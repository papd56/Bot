package com.ruoyi.project.system.bot.user.list.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.bot.user.list.domain.BotUserList;
import com.ruoyi.project.system.bot.user.list.mapper.BotUserListMapper;
import com.ruoyi.project.system.bot.user.list.service.IBotUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.text.Convert;

/**
 * 机器人用户列Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@Service
public class BotUserListServiceImpl implements IBotUserListService
{
    @Autowired
    private BotUserListMapper botUserListMapper;

    /**
     * 查询机器人用户列
     *
     * @param id 机器人用户列主键
     * @return 机器人用户列
     */
    @Override
    public BotUserList selectBotUserListById(Long id)
    {
        return botUserListMapper.selectBotUserListById(id);
    }

    /**
     * 查询机器人用户列列表
     *
     * @param botUserList 机器人用户列
     * @return 机器人用户列
     */
    @Override
    public List<BotUserList> selectBotUserListList(BotUserList botUserList)
    {
        return botUserListMapper.selectBotUserListList(botUserList);
    }

    /**
     * 新增机器人用户列
     *
     * @param botUserList 机器人用户列
     * @return 结果
     */
    @Override
    public int insertBotUserList(BotUserList botUserList)
    {
        botUserList.setCreateTime(DateUtils.getNowDate());
        return botUserListMapper.insertBotUserList(botUserList);
    }

    /**
     * 修改机器人用户列
     *
     * @param botUserList 机器人用户列
     * @return 结果
     */
    @Override
    public int updateBotUserList(BotUserList botUserList)
    {
        return botUserListMapper.updateBotUserList(botUserList);
    }

    /**
     * 批量删除机器人用户列
     *
     * @param ids 需要删除的机器人用户列主键
     * @return 结果
     */
    @Override
    public int deleteBotUserListByIds(String ids)
    {
        return botUserListMapper.deleteBotUserListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除机器人用户列信息
     *
     * @param id 机器人用户列主键
     * @return 结果
     */
    @Override
    public int deleteBotUserListById(Long id)
    {
        return botUserListMapper.deleteBotUserListById(id);
    }
}
