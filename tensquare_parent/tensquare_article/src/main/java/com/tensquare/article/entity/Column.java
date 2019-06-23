package com.tensquare.article.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 专栏
    * </p>
*
* @author GoogleSearch
* @since 2019-06-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("tb_column")
    public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * ID
            */
    private String id;

            /**
            * 专栏名称
            */
    private String name;

            /**
            * 专栏简介
            */
    private String summary;

            /**
            * 用户ID
            */
    private String userid;

            /**
            * 申请日期
            */
    private java.util.Date createtime;

            /**
            * 审核日期
            */
    private java.util.Date checktime;

            /**
            * 状态
            */
    private String state;


}
