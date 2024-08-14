package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.BotUserList;

import java.util.List;

/**
 * 机器人用户列Service接口
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public interface IBotUserListService {
    /**
     * 查询机器人用户列
     *
     * @param id 机器人用户列主键
     * @return 机器人用户列
     */
    public BotUserList selectBotUserListById(Long id);

    /**
     * 查询机器人用户列列表
     *
     * @param botUserList 机器人用户列
     * @return 机器人用户列集合
     */
    public List<BotUserList> selectBotUserListList(BotUserList botUserList);

    /**
     * 新增机器人用户列
     *
     * @param botUserList 机器人用户列
     * @return 结果
     */
    public int insertBotUserList(BotUserList botUserList);

    /**
     * 修改机器人用户列
     *
     * @param botUserList 机器人用户列
     * @return 结果
     */
    public int updateBotUserList(BotUserList botUserList);

    /**
     * 批量删除机器人用户列
     *
     * @param ids 需要删除的机器人用户列主键集合
     * @return 结果
     */
    public int deleteBotUserListByIds(String ids);

    /**
     * 删除机器人用户列信息
     *
     * @param id 机器人用户列主键
     * @return 结果
     */
    public int deleteBotUserListById(Long id);
}
