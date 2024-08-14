package com.ruoyi.project.bot.group.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 群组管理对象 bot_group
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public class BotGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 机器人id */
    @Excel(name = "机器人id")
    private String botId;

    /** 群组id */
    @Excel(name = "群组id")
    private String groupId;

    /** 群名称 */
    @Excel(name = "群名称")
    private String groupName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setBotId(String botId) 
    {
        this.botId = botId;
    }

    public String getBotId() 
    {
        return botId;
    }

    public void setGroupId(String groupId) 
    {
        this.groupId = groupId;
    }

    public String getGroupId() 
    {
        return groupId;
    }

    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("botId", getBotId())
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .toString();
    }
}
