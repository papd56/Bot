package com.ruoyi.project.user.mapper;

import com.ruoyi.project.group.domain.BotGroupList;
import com.ruoyi.project.user.domain.BotUserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机器人用户列Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public interface BotUserListMapper
{
    /**
     * 查询机器人用户列
     *
     * @param id 机器人用户列主键
     * @return 机器人用户列
     */
    public BotUserList selectBotUserListById(Long id);

    BotUserList selectByName(@Param("userName") String userName);

    BotUserList selectBotGroupByIdAndUserName(Long userId, String userName);

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
     * 删除机器人用户列
     *
     * @param id 机器人用户列主键
     * @return 结果
     */
    public int deleteBotUserListById(Long id);

    /**
     * 批量删除机器人用户列
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBotUserListByIds(String[] ids);
}
