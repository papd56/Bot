package com.ruoyi.project.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import nonapi.io.github.classgraph.json.Id;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

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
    @Id
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tradeTime;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date createTime;

    @Override
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 订单状态：1 待确认 2 订单取消  3订单完成 */
    @Excel(name = "订单状态：1 待确认 2 订单取消  3订单完成")
    private Integer orderStatus;

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

    public void setTradeTime(Date tradeTime)
    {
        this.tradeTime = tradeTime;
    }

    public Date getTradeTime()
    {
        return tradeTime;
    }

    public void setOrderStatus(Integer orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus()
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
