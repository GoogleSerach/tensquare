package com.tensquare.gathering.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 活动
    * </p>
*
* @author GoogleSearch
* @since 2019-06-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("tb_gathering")
    public class Gathering implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 编号
            */
    private String id;

            /**
            * 活动名称
            */
    private String name;

            /**
            * 大会简介
            */
    private String summary;

            /**
            * 详细说明
            */
    private String detail;

            /**
            * 主办方
            */
    private String sponsor;

            /**
            * 活动图片
            */
    private String image;

            /**
            * 开始时间
            */
    private java.util.Date starttime;

            /**
            * 截止时间
            */
    private java.util.Date endtime;

            /**
            * 举办地点
            */
    private String address;

            /**
            * 报名截止
            */
    private java.util.Date enrolltime;

            /**
            * 是否可见
            */
    private String state;

            /**
            * 城市
            */
    private String city;


}
