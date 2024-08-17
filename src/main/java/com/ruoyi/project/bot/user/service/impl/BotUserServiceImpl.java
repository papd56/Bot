package com.ruoyi.project.bot.user.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.bot.user.domain.BotUser;
import com.ruoyi.project.bot.user.mapper.BotUserMapper;
import com.ruoyi.project.bot.user.service.IBotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
@Service
public class BotUserServiceImpl implements IBotUserService 
{
    @Autowired
    private BotUserMapper botUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public BotUser selectBotUserById(Long id)
    {
        return botUserMapper.selectBotUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param botUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<BotUser> selectBotUserList(BotUser botUser)
    {
        return botUserMapper.selectBotUserList(botUser);
    }

    /**
     * 新增用户管理
     * 
     * @param botUser 用户管理
     * @return 结果
     */
    @Override
    public int insertBotUser(BotUser botUser)
    {
        return botUserMapper.insertBotUser(botUser);
    }

    /**
     * 新增批量用户
     *
     * @param botUsers 批量用户
     * @return 结果
     */
    @Override
    public int insertBotUsers(List<BotUser> botUsers)
    {
        return botUserMapper.insertBotUsers(botUsers);
    }

    /**
     * 修改用户管理
     * 
     * @param botUser 用户管理
     * @return 结果
     */
    @Override
    public int updateBotUser(BotUser botUser)
    {
        return botUserMapper.updateBotUser(botUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteBotUserByIds(String ids)
    {
        return botUserMapper.deleteBotUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteBotUserById(Long id)
    {
        return botUserMapper.deleteBotUserById(id);
    }
}
