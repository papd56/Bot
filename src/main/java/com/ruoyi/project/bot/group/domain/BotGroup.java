package com.ruoyi.project.bot.group.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 群组管理对象 bot_group
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
public class BotGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 机器人id */
    @Excel(name = "机器人id")
    private String botId;

    /** 群组id */
    @Excel(name = "群组id")
    private String groupId;

    /** 公群名称 */
    @Excel(name = "公群名称")
    private String groupName;

    /** 公群老板 */
    @Excel(name = "公群老板")
    private String groupBoss;

    /** 公群业务员 */
    @Excel(name = "公群业务员")
    private String groupSalesman;

    /** 开启担保时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开启担保时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date guaranteeOpenTime;

    /** 关闭担保时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "关闭担保时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date guaranteeCloseTime;

    /** 群欢迎语 */
    @Excel(name = "群欢迎语")
    private String groupWelcome;

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

    public void setGroupBoss(String groupBoss) 
    {
        this.groupBoss = groupBoss;
    }

    public String getGroupBoss() 
    {
        return groupBoss;
    }

    public void setGroupSalesman(String groupSalesman) 
    {
        this.groupSalesman = groupSalesman;
    }

    public String getGroupSalesman() 
    {
        return groupSalesman;
    }

    public void setGuaranteeOpenTime(Date guaranteeOpenTime) 
    {
        this.guaranteeOpenTime = guaranteeOpenTime;
    }

    public Date getGuaranteeOpenTime() 
    {
        return guaranteeOpenTime;
    }

    public void setGuaranteeCloseTime(Date guaranteeCloseTime) 
    {
        this.guaranteeCloseTime = guaranteeCloseTime;
    }

    public Date getGuaranteeCloseTime() 
    {
        return guaranteeCloseTime;
    }

    public void setGroupWelcome(String groupWelcome) 
    {
        this.groupWelcome = groupWelcome;
    }

    public String getGroupWelcome() 
    {
        return groupWelcome;
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
