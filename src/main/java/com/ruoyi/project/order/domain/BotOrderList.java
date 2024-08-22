package com.ruoyi.project.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nonapi.io.github.classgraph.json.Id;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单列对象 bot_order_list
 *
 * @author ruoyi
 * @date 2024-08-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BotOrderList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 交易用户
     */
    @Excel(name = "交易用户")
    private String tradeUser;

    /**
     * 交易信息
     */
    @Excel(name = "交易信息")
    private String tradeInfo;

    /**
     * 发起报备人
     */
    @Excel(name = "发起报备人")
    private String initiatorReportUser;

    /**
     * 交易金额
     */
    @Excel(name = "交易金额")
    private BigDecimal transactionAmount;

    /**
     * 交易时间
     */
    @Excel(name = "交易时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tradeTime;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date createTime;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 订单状态：1 待确认 2 订单取消  3订单完成
     */
    @Excel(name = "订单状态：1 待确认 2 订单取消  3订单完成")
    private Integer orderStatus;
}
