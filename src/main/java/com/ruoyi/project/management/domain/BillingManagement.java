package com.ruoyi.project.management.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 账单管理记录对象 billing_management
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BillingManagement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 账单接收金额
     */
    @Excel(name = "账单接收金额")
    private BigDecimal amountReceived;

    /**
     * 实时汇率
     */
    @Excel(name = "实时汇率")
    private BigDecimal fixedRate;

    /**
     * 账单结果
     */
    @Excel(name = "账单结果")
    private String orderInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
}
