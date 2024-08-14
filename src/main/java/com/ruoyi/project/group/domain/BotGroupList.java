package com.ruoyi.project.group.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 群组列对象 bot_group_list
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public class BotGroupList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 群组id */
    @Excel(name = "群组id")
    private Long groupId;

    /** 群组名称 */
    @Excel(name = "群组名称")
    private String groupName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 是否是管理员：true 或者 false */
    @Excel(name = "是否是管理员：true 或者 false")
    private Long isNotManage;

    /** 押群金额 */
    @Excel(name = "押群金额")
    private BigDecimal groupAmount;

    /** 交易中金额 */
    @Excel(name = "交易中金额")
    private BigDecimal trandPendingAmount;

    /** 剩余交易金额 */
    @Excel(name = "剩余交易金额")
    private BigDecimal balance;

    /** 完成金额 */
    @Excel(name = "完成金额")
    private BigDecimal finishAmount;

    /** 群组编号 */
    @Excel(name = "群组编号")
    private Long groupNumber;

    /** 老板tg号 */
    @Excel(name = "老板tg号")
    private Long boosTgId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    public Long getGroupId()
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

    public void setIsNotManage(Long isNotManage)
    {
        this.isNotManage = isNotManage;
    }

    public Long getIsNotManage()
    {
        return isNotManage;
    }

    public void setGroupAmount(BigDecimal groupAmount)
    {
        this.groupAmount = groupAmount;
    }

    public BigDecimal getGroupAmount()
    {
        return groupAmount;
    }

    public void setTrandPendingAmount(BigDecimal trandPendingAmount)
    {
        this.trandPendingAmount = trandPendingAmount;
    }

    public BigDecimal getTrandPendingAmount()
    {
        return trandPendingAmount;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setFinishAmount(BigDecimal finishAmount)
    {
        this.finishAmount = finishAmount;
    }

    public BigDecimal getFinishAmount()
    {
        return finishAmount;
    }

    public void setGroupNumber(Long groupNumber)
    {
        this.groupNumber = groupNumber;
    }

    public Long getGroupNumber()
    {
        return groupNumber;
    }

    public void setBoosTgId(Long boosTgId)
    {
        this.boosTgId = boosTgId;
    }

    public Long getBoosTgId()
    {
        return boosTgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("isNotManage", getIsNotManage())
            .append("groupAmount", getGroupAmount())
            .append("trandPendingAmount", getTrandPendingAmount())
            .append("balance", getBalance())
            .append("finishAmount", getFinishAmount())
            .append("groupNumber", getGroupNumber())
            .append("createTime", getCreateTime())
            .append("boosTgId", getBoosTgId())
            .toString();
    }
}
