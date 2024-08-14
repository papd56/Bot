package com.ruoyi.project.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 机器人用户列对象 bot_user_list
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public class BotUserList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** tg唯一标识 */
    @Excel(name = "tg唯一标识")
    private Long tgUnqiueId;

    /** 群组名称 */
    @Excel(name = "群组名称")
    private String groupName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTgUnqiueId(Long tgUnqiueId)
    {
        this.tgUnqiueId = tgUnqiueId;
    }

    public Long getTgUnqiueId()
    {
        return tgUnqiueId;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tgUnqiueId", getTgUnqiueId())
            .append("groupName", getGroupName())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
