package com.ruoyi.project.bot.user.service;

import java.util.List;
import com.ruoyi.project.bot.user.domain.BotUser;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public interface IBotUserService 
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
     * 修改用户管理
     * 
     * @param botUser 用户管理
     * @return 结果
     */
    public int updateBotUser(BotUser botUser);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteBotUserByIds(String ids);

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteBotUserById(Long id);
}
