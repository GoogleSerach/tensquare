package com.tensquare.gathering.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户关注活动
    * </p>
*
* @author GoogleSearch
* @since 2019-06-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("tb_usergath")
    public class Usergath implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 用户ID
            */
    private String userid;

            /**
            * 活动ID
            */
    private String gathid;

            /**
            * 点击时间
            */
    private java.util.Date exetime;


}
