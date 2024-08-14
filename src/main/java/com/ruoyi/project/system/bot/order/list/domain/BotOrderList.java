package com.ruoyi.project.system.bot.order.list.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 订单列对象 bot_order_list
 *
 * @author ruoyi
 * @date 2024-08-14
 */
public class BotOrderList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 交易用户 */
    @Excel(name = "交易用户")
    private String tradeUser;

    /** 交易信息 */
    @Excel(name = "交易信息")
    private String tradeInfo;

    /** 发起报备人 */
    @Excel(name = "发起报备人")
    private String initiatorReportUser;

    /** 交易时间 */
    @Excel(name = "交易时间")
    private String tradeTime;

    /** 订单状态：1 待确认 2 订单取消  3订单完成 */
    @Excel(name = "订单状态：1 待确认 2 订单取消  3订单完成")
    private Long orderStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTradeUser(String tradeUser)
    {
        this.tradeUser = tradeUser;
    }

    public String getTradeUser()
    {
        return tradeUser;
    }

    public void setTradeInfo(String tradeInfo)
    {
        this.tradeInfo = tradeInfo;
    }

    public String getTradeInfo()
    {
        return tradeInfo;
    }

    public void setInitiatorReportUser(String initiatorReportUser)
    {
        this.initiatorReportUser = initiatorReportUser;
    }

    public String getInitiatorReportUser()
    {
        return initiatorReportUser;
    }

    public void setTradeTime(String tradeTime)
    {
        this.tradeTime = tradeTime;
    }

    public String getTradeTime()
    {
        return tradeTime;
    }

    public void setOrderStatus(Long orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus()
    {
        return orderStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tradeUser", getTradeUser())
            .append("tradeInfo", getTradeInfo())
            .append("initiatorReportUser", getInitiatorReportUser())
            .append("tradeTime", getTradeTime())
            .append("orderStatus", getOrderStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
