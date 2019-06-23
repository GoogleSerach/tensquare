package com.tensquare.recruit.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 职位
    * </p>
*
* @author GoogleSearch
* @since 2019-06-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("tb_recruit")
    public class Recruit implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * ID
            */
    @TableId(type = IdType.ID_WORKER)
    private String id;

            /**
            * 职位名称
            */
    private String jobname;

            /**
            * 薪资范围
            */
    private String mysalary;

            /**
            * 经验要求
            */
    private String mycondition;

            /**
            * 学历要求
            */
    private String education;

            /**
            * 任职方式
            */
    private String type;

            /**
            * 办公地址
            */
    private String address;

            /**
            * 企业ID
            */
    private String eid;

            /**
            * 创建日期
            */
    private java.util.Date createtime;

            /**
            * 状态
            */
    private String state;

            /**
            * 网址
            */
    private String url;

            /**
            * 标签
            */
    private String label;

            /**
            * 职位描述
            */
    private String content1;

            /**
            * 职位要求
            */
    private String content2;


}
