package com.ruoyi.project.group.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import nonapi.io.github.classgraph.json.Id;
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
@EqualsAndHashCode(callSuper = true)
@Data
public class BotGroupList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 群组id
     */
    @Excel(name = "群组id")
    private Long groupId;

    /**
     * 群组名称
     */
    @Excel(name = "群组名称")
    private String groupName;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    private String nickName;

    /**
     * 是否是管理员：true 或者 false
     */
    @Excel(name = "是否是管理员：true 或者 false")
    private Long isNotManage;

    /**
     * 押群金额
     */
    @Excel(name = "押群金额")
    private BigDecimal groupAmount;

    /**
     * 交易中金额
     */
    @Excel(name = "交易中金额")
    private BigDecimal trandPendingAmount;

    /**
     * 剩余交易金额
     */
    @Excel(name = "剩余交易金额")
    private BigDecimal balance;

    /**
     * 完成金额
     */
    @Excel(name = "完成金额")
    private BigDecimal finishAmount;

    /**
     * 群组编号
     */
    @Excel(name = "群组编号")
    private Long groupNumber;

    /**
     * 老板tg号
     */
    @Excel(name = "老板tg号")
    private Long boosTgId;

    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
