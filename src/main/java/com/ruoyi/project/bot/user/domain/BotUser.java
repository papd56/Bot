package com.ruoyi.project.bot.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户管理对象 bot_user
 * 
 * @author ruoyi
 * @date 2024-08-14
 */
public class BotUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 机器人id */
    @Excel(name = "机器人id")
    private String botId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userNickname;

    /** 权限状态 */
    @Excel(name = "权限状态")
    private String status;

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

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserNickname(String userNickname)
    {
        this.userNickname = userNickname;
    }

    public String getUserNickname()
    {
        return userNickname;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将当前对象转换为JSON字符串
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString(); // 如果JSON转换失败，返回父类的toString()
        }
    }
}
