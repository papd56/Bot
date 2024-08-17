package com.ruoyi.project.bot.user.mapper;

import com.ruoyi.project.bot.user.domain.BotUser;

import java.util.List;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public interface BotUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public BotUser selectBotUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param botUser 用户管理
     * @return 用户管理集合
     */
    public List<BotUser> selectBotUserList(BotUser botUser);

    /**
     * 新增用户管理
     * 
     * @param botUser 用户管理
     * @return 结果
     */
    public int insertBotUser(BotUser botUser);

    /**
     * 新增批量用户
     *
     * @param botUsers 批量用户
     * @return 结果
     */
    public int insertBotUsers(List<BotUser> botUsers);

    /**
     * 修改用户管理
     * 
     * @param botUser 用户管理
     * @return 结果
     */
    public int updateBotUser(BotUser botUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteBotUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBotUserByIds(String[] ids);
}
